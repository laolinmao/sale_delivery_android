package com.histudio.app.login;

import android.app.Activity;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.histudio.app.R;
import com.histudio.app.main.MainFrame;
import com.histudio.app.manager.UpdateManager;
import com.histudio.app.util.ActivityUtil;
import com.histudio.base.HiManager;
import com.histudio.base.cache.InfoCache;
import com.histudio.base.constant.BConstants;
import com.histudio.base.constant.Configuration;
import com.histudio.base.entity.User;
import com.histudio.base.http.CommonMethods;
import com.histudio.base.http.subscribers.LoadingSubscriber;
import com.histudio.base.http.subscribers.SubscriberOnNextListener;
import com.histudio.base.manager.SharedPrefManager;
import com.histudio.base.util.MD5;
import com.histudio.ui.base.HiLoadablePage;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 作者：apple on 2018/10/25 20:36
 */
public class LoginPage extends HiLoadablePage implements TextView.OnEditorActionListener {

    @Bind(R.id.et_login_phone)
    MaterialEditText etLoginPhone;
    @Bind(R.id.et_login_pwd)
    MaterialEditText etLoginPwd;

    @Bind(R.id.et_auto_login)
    CheckBox etAutoLogin;

    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.et_login_company)
    MaterialEditText etLoginCompany;

    public LoginPage(Activity context) {
        super(context);
    }

    @Override
    protected boolean isNeedLoadTask() {
        return false;
    }

    @Override
    protected boolean isShowEmptyView() {
        return false;
    }

    @Override
    protected View createContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frame_login, null);
        ButterKnife.bind(this, view);
        initData();

        initView();
        return view;
    }

    private void initView() {
        if (Configuration.IS_DEV_MODE) {
            version.setText(getResources().getString(R.string.app_version_name_dev_text, HiManager.getContextConfig().getVersion()));

        } else {
            version.setText(getResources().getString(R.string.app_version_name_pro_text, HiManager.getContextConfig().getVersion()));
        }
        etAutoLogin.setChecked(HiManager.getBean(SharedPrefManager.class).getBooleanByKey("isAutoLogin", false));
        String id = HiManager.getBean(SharedPrefManager.class).getStringByKey("id", "");
        etLoginPhone.setText(id);
        String pwd = HiManager.getBean(SharedPrefManager.class).getStringByKey("pwd", "");
        etLoginPwd.setText(pwd);
        etLoginPwd.setOnEditorActionListener(this);


    }

    private void initData() {
        // 检查软件更新
        HiManager.getBean(UpdateManager.class).update(mActivity, false);
    }

    @Override
    protected void loadingTask() {

    }

    @Override
    protected void loadingMoreItemTask() {

    }

    @Override
    public void onHandlePageMessage(Message message) {
        super.onHandlePageMessage(message);
        switch (message.what) {
            case BConstants.UPDATE_SUC:
                String id = HiManager.getBean(SharedPrefManager.class).getStringByKey("id", "");
                String pwd = HiManager.getBean(SharedPrefManager.class).getStringByKey("pwd", "");
                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pwd) && etAutoLogin.isChecked()) {
                    login();
                }
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
                login();
                break;
        }
        return false;
    }

    @OnClick({R.id.et_auto_login, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_auto_login:
                break;

            case R.id.btn_login:
//                login();
                ActivityUtil.launchActivity(getContext(), MainFrame.class);
                break;
        }
    }

    private void login() {
        String ids = etLoginPhone.getText().toString();
        String pwd = MD5.GetMD5Code(etLoginPwd.getText().toString());
        HiManager.getBean(CommonMethods.class).login(new LoadingSubscriber(new SubscriberOnNextListener<User>() {
            @Override
            public void onNext(User data) {
                JPushInterface.setAlias(getContext(), data.getAPP_LOGIN_ID(), data.getNAME());
                if (etAutoLogin.isChecked()) {
                    HiManager.getBean(SharedPrefManager.class).saveStringValue("id", etLoginPhone.getText().toString());
                    HiManager.getBean(SharedPrefManager.class).saveStringValue("pwd", etLoginPwd.getText().toString());

                } else {
                    HiManager.getBean(SharedPrefManager.class).saveStringValue("id", etLoginPhone.getText().toString());
                    HiManager.getBean(SharedPrefManager.class).saveStringValue("pwd", "");
                }
                HiManager.getBean(SharedPrefManager.class).saveBooleanValue("isAutoLogin", etAutoLogin.isChecked());
                HiManager.getBean(SharedPrefManager.class).saveStringValue(BConstants.USER_NAME, data.getNAME());
                HiManager.getBean(InfoCache.class).setUserInfo(data);

                ActivityUtil.launchActivity(getContext(), MainFrame.class);
                finish();
            }
        }), ids, pwd, "3");
    }

    @Override
    public void onPause() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
