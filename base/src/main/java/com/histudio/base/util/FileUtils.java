package com.histudio.base.util;

import android.graphics.Bitmap;
import android.os.Environment;

import com.histudio.base.HiApplication;
import com.histudio.base.constant.BConstants;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

public class FileUtils {
    /**
     * sd卡的根目录
     */
    private static String mSdRootPath = Environment.getExternalStorageDirectory().getPath();

    /**
     * 手机的缓存根目录
     */
    private static String mDataRootPath = HiApplication.instance.getCacheDir().getPath();

    /**
     * 项目SD卡中文件根目录
     */
    private static final String BASE_FILE_PATH = File.separator + HiApplication.instance.getPackageName();

    /**
     * 存放数据库的路径
     */
    public static final String BASE_INFO_PATH = BASE_FILE_PATH + File.separator + "info" + File.separator;

    /**
     * 图片存放根目录
     */
    public static final String BASE_IMAGE_DIR_PATH = BASE_FILE_PATH + File.separator + "images" + File.separator;
    /**
     * 声音存放根目录
     */
    public static final String BASE_AUDIO_DIR_PATH = BASE_FILE_PATH + File.separator + "audio" + File.separator;
    /**
     * 文件存放根目录
     */
    public static final String BASE_FILE_DIR_PATH = BASE_FILE_PATH + File.separator + "datas" + File.separator;

    /**
     * 异常存放根目录
     */
    public static final String BASE_EXCEPTION_DIR_PATH = BASE_FILE_PATH + File.separator + "exceptions"
            + File.separator;

    /**
     * 图片缓存路径
     */
    public static final String BASE_IMAGE_CACHE_PATH = BASE_IMAGE_DIR_PATH + "cache" + File.separator;

    /**
     * 文件缓存路径
     */
    public static final String BASE_FILE_CACHE_PATH = BASE_FILE_DIR_PATH + "cache" + File.separator;
    /**
     * 声音缓存路径
     */
    public static final String BASE_AUDIO_CACHE_PATH = BASE_AUDIO_DIR_PATH + "cache" + File.separator;
    /**
     * 拍照目录
     */
    public static final String BASE_IMAGE_CAMERA_PATH = BASE_IMAGE_DIR_PATH + "cam" + File.separator;

    /**
     * 拍照目录
     */
    public static final String BASE_IMAGE_DOWNLOAD_PATH = BASE_IMAGE_DIR_PATH + "download" + File.separator;

    // 最大缓存图片 2000 张
    private static int MAXCACHESIZE = 2000;

    /**
     * 判断手机是否有SD卡。
     *
     * @return 有SD卡返回true，没有返回false。
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String getFilePath() {
        return hasSDCard() ? mSdRootPath : mDataRootPath;
    }

    /**
     * 获取储存Image的目录
     *
     * @return
     */
    public static String getImageCacheDirectory() {
        return getFilePath() + BASE_IMAGE_CACHE_PATH;
    }

    /**
     * 获取储存audio的目录
     *
     * @return
     */
    public static String getAudioCacheDirectory() {
        return getFilePath() + BASE_AUDIO_CACHE_PATH;
    }

    /**
     * 获取储存file的目录
     *
     * @return
     */
    public static String getFileCacheDirectory() {
        return getFilePath() + BASE_FILE_CACHE_PATH;
    }

    /**
     * 获取储存相机拍照的缓存目录
     *
     * @return
     */
    public static String getCamCacheDirectory() {
        return getFilePath() + BASE_IMAGE_CAMERA_PATH;
    }

    /**
     * 获取异常日志目录
     *
     * @return
     */
    public static String getExceptionsDirectory() {
        return getFilePath() + BASE_EXCEPTION_DIR_PATH;
    }

    /**
     * 获取储存数据信息的缓存目录
     *
     * @return
     */
    public static String getBaseInfoCacheDirectory() {
        return getFilePath() + BASE_INFO_PATH;
    }

    /**
     * 获取图片下载目录
     *
     * @return
     */
    public static String getImageDownloadDirectory() {
        return getFilePath() + BASE_IMAGE_DOWNLOAD_PATH;
    }

    /**
     * 计算存储目录下的文件大小，当文件总大小大于规定的CACHESIZE或者SD卡小于FREE_SD_SPACE_NEEDED_TO_CACHE
     * 删除40%最近未使用的文件
     *
     * @param dirPath
     */
    public static void removeCache(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        // 如果超过文件夹大小，则清理Cache文件夹
        if (files.length > MAXCACHESIZE) {
            int removeFactor = (int) ((0.4 * files.length) + 1);
            Arrays.sort(files, new FileLastModifSort());
            for (int i = 0; i < removeFactor; i++) {
                files[i].delete();
            }
        }
    }

    /**
     * 删除该路径下所有文件
     *
     * @param file 文件夹or文件
     */
    public static void deleteDirFile(File file) throws Exception {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteDirFile(f);// 递归删除每一个文件
                f.delete();// 删除该文件夹
            }
        }
    }

    /**
     * 删除该路径下所有文件
     *
     * @param path 路径
     */
    public static void deleteDirFile(String path) {
        try {
            deleteDirFile(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查文件是否存在，不存在则创建一个
     *
     * @param paramString
     */
    public static void checkFile(String paramString) {
        File localFile = new File(paramString);
        if (!localFile.exists())
            localFile.mkdirs();
    }

    /**
     * 文件排序器
     *
     * @author Administrator
     */
    public static class FileLastModifSort implements Comparator<File> {
        @Override
        public int compare(File arg1, File arg2) {
            if (arg1.lastModified() > arg2.lastModified()) {
                return 1;
            } else if (arg1.lastModified() == arg2.lastModified()) {
                return 0;
            } else
                return -1;
        }
    }

    /**
     * 设置文件最后修改时间
     */
    private static void updateFileLastTime(File file) {
        long time = System.currentTimeMillis();
        file.setLastModified(time);
    }

    /**
     * 创建新的图片
     *
     * @param imageUrl
     * @param bitmap
     */

    public static void createFile(String mLocalPath, String imageUrl, Bitmap bitmap) {
        File dir = new File(mLocalPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File bitmapFile = new File(mLocalPath + imageUrl.substring(imageUrl.lastIndexOf("/") + 1));
        if (!bitmapFile.exists()) {
            try {
                bitmapFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            // 创建后设置最后修改时间
            updateFileLastTime(bitmapFile);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFileExists(String path, String bitmapName) {
        return new File(path + bitmapName).exists();
    }

    public static boolean isFileExists(String path) {
        return new File(path).exists();
    }

    /**
     * 将字节数组写入文件
     */
    public static void savePictureFromByte(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取指定文件大小
     *
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
            fis.close();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * file中读取String
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readString(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();
            byte[] data = baos.toByteArray();
            String fileStr = new String(data, BConstants.DEFALUT_CODESET);
            return fileStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string 写入file
     *
     * @param file
     * @throws Exception
     */
    public static void writeString(String data, String file) {
        FileOutputStream fos = null;
        try {
            byte[] b = data.getBytes(BConstants.DEFALUT_CODESET);
            fos = new FileOutputStream(new File(file));
            fos.write(b);
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入文件
     *
     * @param in
     * @param file
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file != null && file.exists())
            file.delete();

        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 4];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();

    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        byte[] buffer = Base64.decode(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 将base64字符保存文本文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }


    public static byte[] file2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
