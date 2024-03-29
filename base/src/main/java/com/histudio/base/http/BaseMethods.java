package com.histudio.base.http;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.constant.Configuration;
import com.histudio.base.entity.ISingleable;
import com.histudio.base.manager.SharedPrefManager;
import com.histudio.base.util.MD5;
import com.histudio.base.util.NetWorkUtil;
import com.histudio.base.util.StringUtil;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by ljh on 16/3/9.
 */
public abstract class BaseMethods implements ISingleable {

    public static final String BASE_URL = Configuration.IS_DEV_MODE ? Configuration.BaseDevHost : Configuration.BaseProHost;

    protected Retrofit retrofit;

    // 必须使用get方法,才有缓存,post方法无缓存
    private static File httpCacheDirectory = new File(HiApplication.instance.getCacheDir(), "histudioCache");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    public static Map<String, Long> requestIdsMap = new HashMap<>();//Value 里面保存的是时间
    public static final String CUSTOM_REPEAT_REQ_PROTOCOL = "MY_CUSTOM_REPEAT_REQ_PROTOCOL";

    //构造方法私有
    protected BaseMethods() {

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                MediaType mediaType = originalResponse.body().contentType();
                String content = originalResponse.body().string();

                if (NetWorkUtil.isNetWorkAvailable(HiApplication.instance)) {
                    int maxAge = 60; // 有网络时 设置缓存超时时间0个小时
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Cache-Control")
                            .addHeader("Cache-Control", "public, max-age=" + maxAge)
                            .body(ResponseBody.create(mediaType, content))
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .body(ResponseBody.create(mediaType, content))
                            .build();
                }
            }
        };
        HttpLoggingInterceptor.Logger logger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                KLog.e(message);
            }
        };
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger) {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String id = HiManager.getBean(SharedPrefManager.class).getStringByKey("id", "");
                String userName = HiManager.getBean(SharedPrefManager.class).getStringByKey(BConstants.USER_NAME, "");
                Request originalRequest = chain.request();
                Request authorisedRequest = originalRequest.newBuilder().addHeader("Accept", "application/json")
                        .addHeader("oper", id.trim())
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader("opername", StringUtil.encodeString(userName))
                        .build();
                //拦截处理重复的HTTP 请求,假如你们的业务有需求部分请求不去重可以自己处理
                String requestKey = MD5.GetMD5Code(authorisedRequest.toString());
                if (null == requestIdsMap.get(requestKey) || System.currentTimeMillis() - requestIdsMap.get(requestKey) > 100) {
                    requestIdsMap.put(requestKey, System.currentTimeMillis());
                    KLog.e("REPEAT REQUEST", "Add Request:" + requestKey);
                } else {

                    return new Response.Builder()
                            .protocol(Protocol.get(CUSTOM_REPEAT_REQ_PROTOCOL))
                            .request(authorisedRequest) //multi thread
                            .build();
                }
                Response originalResponse = chain.proceed(authorisedRequest);

                return originalResponse.newBuilder().build();

            }
        };
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                // 加入头部拦截
                .addInterceptor(cacheInterceptor)
                .addInterceptor(interceptor)
                //添加请求拦截
                .addInterceptor(new LoggingInterceptor())
                .cache(cache)
                // 忽略https验证请求
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier(DO_NOT_VERIFY)
                .retryOnConnectionFailure(false) // 不重复请求
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                //配置服务器路径
                .baseUrl(BASE_URL)
                //配置转化库，默认是Gson
                //                .addConverterFactory(GsonConverterFactory.create())
                //传入buildGson生成的自定义Gson
                .addConverterFactory(ResponseConverterFactory.create(buildGson()))
                //配置回调库，采用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置OKHttpClient为网络客户端
                .client(client)
                .build();

        createService();

    }

    public Gson buildGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())// null 转为 ""
                .create();
        return gson;
    }

    // 创建服务
    protected abstract void createService();

    /**
     * @param o 由调用者传过来的观察者对象
     */

    protected <T> void toSubscribe(Observable<T> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
//                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    protected class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

        @Override
        public T apply(HttpResult<T> httpResult) {

            if (httpResult.getError_code() != 0) {
                // 1 开头直接系统异常
                throw new ApiException(httpResult.getError_code(), httpResult.getMsg());

            } else if (httpResult.getError_code() == 0) {
                // 为0 则成功
                if (httpResult.getData() != null && !TextUtils.isEmpty(httpResult.getData().toString())) {
                    return httpResult.getData();
                }
            }
            // 如果数据无data，则默认为空
            return (T) "";
        }
    }


}
