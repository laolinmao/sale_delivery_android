package com.histudio.ui.manager;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ByteConstants;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.entity.ISingleable;
import com.histudio.base.util.FileUtils;
import com.histudio.base.util.StringUtil;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;

/**
 * Fresco管理类
 */
public class FrescoManager extends HiManager implements ISingleable {

    public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
    //图片缓存主目录
    private static final String IMAGE_PIPELINE_CACHE_ROOT = "image";
    //fresco自身缓存路径
    private static final String IMAGE_PIPELINE_CACHE_DIR = "/fresco_cache";
    //本地图片文件保存的路径
    private static final String IMAGE_LOCAL_CACHE_DIR = "/local_cache";
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    private static ImagePipelineConfig sImagePipelineConfig;
    // 完整的本地图片缓存目录,设置后直接使用，加快读取速度
    private String TEMP_LOCAL_IMAGE_ROOT_DIR = "";
    private HiApplication mContext = HiApplication.instance;

    public static final String LOCAL_RES_URI_SCHEME = "res://";
    public static final String LOCAL_FILE_URI_SCHEME = "file://";


    /**
     * 生成Fresco可识别的本地ResUri
     *
     * @param context
     * @param drawableRes
     * @return
     */
    public static String generateLocalResUri(Context context, int drawableRes) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOCAL_RES_URI_SCHEME).append(context.getPackageName());
        sb.append(File.separator).append(drawableRes);
        return sb.toString();
    }

    /**
     * 生成Fresco可识别的本地图片Uri
     *
     * @param file
     * @return
     */
    public static String generateLocalFileUri(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOCAL_FILE_URI_SCHEME).append(file.getAbsolutePath());
        return sb.toString();
    }

    /**
     * 生成Fresco可识别的本地图片Uri
     *
     * @param filePath
     * @return
     */
    public static String generateLocalFileUri(String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOCAL_FILE_URI_SCHEME).append(filePath);
        return sb.toString();
    }

    /**
     * 建立Fresco缓存配置
     *
     * @return
     */
    public ImagePipelineConfig initConfig() {
        if (sImagePipelineConfig == null) {
            ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(mContext);
            configureCaches(configBuilder);
            sImagePipelineConfig = configBuilder.build();
        }
        return sImagePipelineConfig;
    }

    public void init(){
        // fresco图片库的初始化
        ImagePipelineConfig imagePipelineConfig = initConfig();
        Fresco.initialize(mContext, imagePipelineConfig);
    }


    /**
     * Configures disk and memory cache not to exceed common limits
     */
    private void configureCaches(ImagePipelineConfig.Builder configBuilder) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size
        configBuilder
                .setBitmapMemoryCacheParamsSupplier(
                        new Supplier<MemoryCacheParams>() {
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        })
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder()
                                .setBaseDirectoryPath(getFrescoImageCacheRootDir())
                                .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)
                                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                                .build());
    }

    /**
     * 获得图片保存目录<br>
     *
     * @return
     */
    public File getFrescoImageCacheRootDir() {
        File cacheRootDir = null;
        String savePath = FileUtils.getImageCacheDirectory();
        // 检查目录是否存在
        if (savePath != null) {
            cacheRootDir = new File(savePath);
            if (!cacheRootDir.exists()) {
                cacheRootDir.mkdirs();
            }
        }

        return cacheRootDir;
    }
    /**
     * 对默认显示视图等做设置，应该放入onCreateView中
     *
     * @param placeholderImageRes
     */
    public GenericDraweeHierarchy getGenericDraweeHierarchy(GenericDraweeHierarchy genericDraweeHierarchy, int
            placeholderImageRes) {
        Resources resources = mContext.getResources();
        genericDraweeHierarchy.setPlaceholderImage(resources.getDrawable(placeholderImageRes));
        return genericDraweeHierarchy;
    }

    /**
     * 加载单一图片请求
     *
     * @param realUri
     * @param oldController
     * @return
     */
    public DraweeController getDraweeController(String realUri, @Nullable DraweeController oldController) {
        DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(realUri)).setOldController
                (oldController).setAutoPlayAnimations(true).build();
        return controller;
    }

    /**
     * 加载单一图片请求，附带过程监听
     *
     * @param realUri
     * @param oldController
     * @return
     */
    public DraweeController getDraweeController(String realUri, ControllerListener controllerListener,
                                                @Nullable DraweeController oldController) {
        DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(realUri)).setOldController
                (oldController).setAutoPlayAnimations(true).setControllerListener(controllerListener).build();
        return controller;
    }

    /**
     * 加载本地图片的缩略图
     *
     * @param realUri
     * @param oldController
     * @return
     */
    public DraweeController getDraweeLocalThumbnailController(String realUri, @Nullable DraweeController
            oldController) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(realUri))
                .setLocalThumbnailPreviewsEnabled(true).build();

        DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request).setOldController
                (oldController).build();
        return controller;
    }

    /**
     * 从fresco缓存获取已缓存的图片的bitmap
     *
     * @return
     */
    public Bitmap getCachedBitmapFromFrescoLocalCache( List<String> urls) {
        for (String url : urls) {
            if (StringUtil.isEmptyString(url)) {
                continue;
            }
            Bitmap bitmap = getCachedBitmapFromFrescoLocalCache(url);
            if (bitmap != null) {
                return bitmap;
            }
        }

        return null;
    }

    /**
     * 从fresco缓存获取已缓存的图片的bitmap
     *
     * @return
     */
    public Bitmap getCachedBitmapFromFrescoLocalCache(String url) {
        if (StringUtil.isEmptyString(url)) {
            return null;
        }
        Uri imageUri = Uri.parse(url);
        DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline().fetchDecodedImage
                (ImageRequest.fromUri(imageUri), mContext);
        try {
            CloseableReference<CloseableImage> closeableImageRef = dataSource.getResult();
            Bitmap bitmap = null;
            if (closeableImageRef != null) {
                try {
                    if (closeableImageRef.get() instanceof CloseableBitmap) {
                        bitmap = ((CloseableBitmap) closeableImageRef.get()).getUnderlyingBitmap();
                    }

                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    CloseableReference.closeSafely(closeableImageRef);
                }
            }

        } finally {
            if (dataSource != null) {
                try {
                    dataSource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // 删除一条缓存url
    public void deleteUrl(String url){
        Uri imageUri = Uri.parse(url);
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.evictFromMemoryCache(imageUri);
    }
}
