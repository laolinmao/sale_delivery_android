package com.histudio.app.module.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.histudio.app.R;
import com.histudio.base.entity.Advert;
import com.histudio.ui.base.HiLoadablePage;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 基础的web页
 * Created by apple on 16/6/1.
 */
public class BaseWebPage extends HiLoadablePage {

    @Bind(R.id.progressbar)
    ProgressBar progressBar;
    @Bind(R.id.webView)
    WebView mWebView;
    private String url = "";
    private Advert advert = new Advert();

    public BaseWebPage(Activity context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frame_web_detail, null);
        ButterKnife.bind(this, view);

//        advert = (Advert) mBundle.getSerializable(Constants.ADVERT_DETAIL);
        url = advert.getLink();
        //        url = "http://www.hudow.com";
        if (!TextUtils.isEmpty(url)) {
            WebSettings settings = mWebView.getSettings();
            settings.setJavaScriptEnabled(true);//设置可以运行JS脚本
            //手动设置UA,让运营商劫持DNS的浏览器广告不生效 http://my.oschina.net/zxcholmes/blog/596192
            settings.setUserAgentString("suijishu" + "#" + settings.getUserAgentString() + "01234560");
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

            settings.setUseWideViewPort(true); //打开页面时， 自适应屏幕
            settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
            settings.setSupportZoom(true);// 用于设置webview放大
            settings.setDomStorageEnabled(true);
            //            // 启用内置缩放
            settings.setBuiltInZoomControls(false);
            //            mWebView.setBackgroundResource(R.color.transparent);
            //            // 添加js交互接口类，并起别名 imagelistner
            mWebView.addJavascriptInterface(new JsObject(), "imagelistner");
            //让缩放显示的最小值为起始
            mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
            mWebView.setVerticalScrollBarEnabled(true); //垂直显示
            mWebView.loadUrl(url);
            mWebView.setWebChromeClient(new MyWebChromeClient());
            mWebView.setWebViewClient(new MyWebViewClient());

        }

        return view;
    }

    @Override
    protected void loadingTask() {

    }

    @Override
    protected void loadingMoreItemTask() {

    }


    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
        mWebView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\");"
                + "var imgurl=''; " + "for(var i=0;i<objs.length;i++)  " + "{"
                + "imgurl+=objs[i].src+',';"
                + "    objs[i].onclick=function()  " + "    {  "
                + "        window.imagelistner.openImage(imgurl);  "
                + "    }  " + "}" + "})()");
    }

    // js通信接口
    class JsObject {
        @JavascriptInterface
        public void openImage(String img) {

            //
            String[] imgs = img.split(",");
            ArrayList<String> imgsUrl = new ArrayList<String>();
            for (String s : imgs) {
                imgsUrl.add(s);
            }
            Intent intent = new Intent();
            intent.putStringArrayListExtra("infos", imgsUrl);
            intent.setClass(getContext(), GalleryAnimationFrame.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }

    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            String scheme = Uri.parse(url).getScheme();
//            if (TextUtils.equals("uschool", scheme)) {
//                String module = Uri.parse(url).getHost();
//                Map<String, String> map = StringUtil.URLRequest(url);
//                String type = map.get("type");
//                String uuid = map.get("id");
//                switch (module) {
//                    case "event": {
//                        Bundle bundle = new Bundle();
//                        bundle.putString(Constants.ACT_ID, uuid);
////                        ActivityUtil.launchActivity(getContext(), ActDetailFrame.class, bundle);
//                    }
//                    break;
//                    case "association": {
//                        Bundle bundle = new Bundle();
//                        bundle.putString(Constants.CLUB_ID, uuid);
////                        ActivityUtil.launchActivity(getContext(), ClubInfoFrame.class, bundle);
//                    }
//                    break;
//                    case "topic": {
//                        Bundle bundle = new Bundle();
//                        bundle.putString(Constants.TOPIC_ID, uuid);
////                        ActivityUtil.launchActivity(getContext(), TopicSquaresFrame.class, bundle);
//                    }
//                    break;
//                }
//                return true;
//            }
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            //            addImageClickListner();
            progressBar.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            progressBar.setVisibility(View.GONE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress != 100) {
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }



    public void goToBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
//        } else if (TextUtils.equals("adview", advert.getType())) {
//            ActivityUtil.launchLoginActivity(getContext(), MainTabFrame.class);
//            finish();
        } else {
            finish();
        }
    }
}
