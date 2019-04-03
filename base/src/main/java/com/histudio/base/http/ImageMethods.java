package com.histudio.base.http;


import android.os.AsyncTask;

import com.histudio.base.HiManager;
import com.histudio.base.compressor.Compressor;
import com.histudio.base.entity.ResIdData;
import com.histudio.base.http.api.CommonApi;
import com.histudio.base.util.FileUtils;
import com.histudio.base.util.ImageUtil;
import com.socks.library.KLog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by ljh on 16/3/9.
 */
public class ImageMethods extends BaseMethods {

    private CommonApi imageApi;


    @Override
    protected void createService() {
        imageApi = retrofit.create(CommonApi.class);
    }


    /**
     * 传单个图片
     */

    public void updateImageInfo(Subscriber<ResIdData> remotePath, String localPath) {
        uploadImageTask task = new uploadImageTask(remotePath, localPath);
        task.execute();
    }




    private class uploadImageTask extends AsyncTask<String, String, File> {
        private String localPath;
        private Subscriber<ResIdData> remotePath;

        public uploadImageTask(Subscriber<ResIdData> remotePath, String localPath) {
            this.remotePath = remotePath;
            this.localPath = localPath;
        }

        @Override
        protected File doInBackground(String... params) {
            try {
                File localFile = new File(localPath);
                KLog.i("压缩前d的 file  大小是  " + FileUtils.formatFileSize(FileUtils.getFileSize(localFile)));

                File file = HiManager.getBean(Compressor.class).compressToFile(new File(localPath));
                KLog.i("压缩后的 file  大小是  " + FileUtils.formatFileSize(FileUtils.getFileSize(file)));
                return file;
            } catch (Exception e) {
                KLog.i("读取文件出错");
            }
            return null;
        }

        @Override
        protected void onPostExecute(File file) {
            super.onPostExecute(file);


            String imageType = ImageUtil.getImageType(file.getPath());
            RequestBody image = RequestBody.create(MediaType.parse(imageType), FileUtils.file2byte(file));
//            RequestBody module = RequestBody.create(MediaType.parse("text/plain"), moduleName);
            RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "image");
            Map<String, RequestBody> params = new HashMap<>();
            params.put("image\"; filename=\"" + file.getName() + "", image);
            params.put("dir", type);
            Observable observable = imageApi.updateImageInfo(params)
                    .map(new HttpResultFunc<ResIdData>());

            toSubscribe(observable, remotePath);
        }
    }


}
