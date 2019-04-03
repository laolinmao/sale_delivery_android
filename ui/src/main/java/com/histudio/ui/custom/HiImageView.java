package com.histudio.ui.custom;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.util.StringUtil;
import com.histudio.ui.R;
import com.histudio.ui.manager.FrescoManager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * 自定义的imageview，实现图片自动加载，等宽高比拉伸图片
 *
 * @author qsj
 */
public class HiImageView extends SimpleDraweeView {

    protected int defaultErrorDrawableRes = R.drawable.empty_icon;
    private HiApplication mContext = HiApplication.instance;
    private int placeholderId = 0;

    /**
     * 这里采用外部传入的hierarchy
     */
    public HiImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        setHierarchy(hierarchy);
        if (getAspectRatio() < 0.01f) {
            setAspectRatio(1f);
        }
        if (isInEditMode()) {
            return;
        }
    }

    public HiImageView(Context context) {
        super(context);
    }

    public HiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public HiImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView(attrs);

    }


    private void initView(AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        if (attrs != null) {
            TypedArray builder = getContext().obtainStyledAttributes(attrs, R.styleable
                    .GenericDraweeView);
            try {
                placeholderId = builder.getResourceId(R.styleable
                        .GenericDraweeView_placeholderImage, placeholderId);
            } finally {
                builder.recycle();
            }
        }
        GenericDraweeHierarchy gdh = HiManager.getBean(FrescoManager.class).getGenericDraweeHierarchy(getHierarchy(), placeholderId == 0 ? R
                .drawable.empty_icon : placeholderId);

        setHierarchy(gdh);

        if (getAspectRatio() < 0.01f) {
            setAspectRatio(1f);
        }

    }

    /**
     * 异步加载图片
     */
    public void asyncLoadImage(String url) {
        doLoadImageUri(url, defaultErrorDrawableRes);
    }

    /**
     * 异步加载图片,附监听过程
     */
    public void asyncLoadImage(String url, ControllerListener controllerListener) {
        doLoadImageUri(url, controllerListener, defaultErrorDrawableRes);
    }

    /**
     * 直接加载本地资源文件的URI，确保Uri通过ImageUtil里面的res生成方法生成
     */
    public void asyncLocalResUri(String imageUri) {
        loadLocalResUri(imageUri, defaultErrorDrawableRes);
    }

    /**
     * 直接加载本地资源文件的URI，确保Uri通过ImageUtil里面的res生成方法生成
     *
     * @param customErrorDrawableRes 自定义加载错误时使用的视图资源
     */
    public void loadLocalResUri(String imageUri, int customErrorDrawableRes) {
        if (imageUri.startsWith(FrescoManager.LOCAL_RES_URI_SCHEME) || imageUri.startsWith(FrescoManager
                .LOCAL_FILE_URI_SCHEME)) {
            doLoadImageUri(imageUri, customErrorDrawableRes);

        } else {
            //Uri不合法，加载错误视图
            doLoadImageUri(null, customErrorDrawableRes);
        }
    }

    /**
     * 通过加载缩略图的形式直接加载本地资源文件的URI，批量加载大图时可以节省内存占用。
     * 确保Uri通过ImageUtil里面的res生成方法生成
     */
    public void asyncLocalThumbResUri(String imageUri) {
        loadLocalThumbResUri(imageUri, defaultErrorDrawableRes);
    }


    /**
     * 通过加载缩略图的形式直接加载本地资源文件的URI，批量加载大图时可以节省内存占用。
     * 确保Uri通过ImageUtil里面的res生成方法生成
     *
     * @param customErrorDrawableRes 自定义加载错误时使用的视图资源
     */
    public void loadLocalThumbResUri(String imageUri, int customErrorDrawableRes) {
        if (imageUri.startsWith(FrescoManager.LOCAL_RES_URI_SCHEME) || imageUri.startsWith(FrescoManager
                .LOCAL_FILE_URI_SCHEME)) {
            doLoadLocalThumbImage(imageUri, customErrorDrawableRes);

        } else {
            //Uri不合法，加载错误视图
            doLoadImageUri(null, customErrorDrawableRes);
        }
    }

    protected void doLoadImageUri(String imageUri, int customErrorDrawableRes) {
        if (StringUtil.isEmptyString(imageUri)) {
            imageUri = FrescoManager.generateLocalResUri(mContext, customErrorDrawableRes);
        }
        DraweeController draweeController = HiManager.getBean(FrescoManager.class).getDraweeController(imageUri, getController());
        setController(draweeController);
    }

    // 加载图片附带监听过程
    protected void doLoadImageUri(String imageUri, ControllerListener controllerListener, int customErrorDrawableRes) {
        if (StringUtil.isEmptyString(imageUri)) {
            imageUri = FrescoManager.generateLocalResUri(mContext, customErrorDrawableRes);
        }
        DraweeController draweeController = HiManager.getBean(FrescoManager.class).getDraweeController(imageUri, controllerListener, getController());
        setController(draweeController);
    }

    protected void doLoadLocalThumbImage(String imageUri, int customErrorDrawableRes) {
        if (StringUtil.isEmptyString(imageUri)) {
            imageUri = FrescoManager.generateLocalResUri(mContext, customErrorDrawableRes);
        }
        DraweeController draweeController = HiManager.getBean(FrescoManager.class).getDraweeLocalThumbnailController(imageUri, getController());
        setController(draweeController);
    }

}
