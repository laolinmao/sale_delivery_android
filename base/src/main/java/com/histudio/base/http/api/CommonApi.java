package com.histudio.base.http.api;

import com.histudio.base.entity.ResIdData;
import com.histudio.base.entity.Update;
import com.histudio.base.entity.User;
import com.histudio.base.http.HttpResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * 通用的api
 * Created by ljd on 3/29/16.
 */
public interface CommonApi {


    /**
     * 检查更新
     */
    @FormUrlEncoded
    @POST("apps/common/checkUpgrade")
    Observable<HttpResult<Update>> checkUpdate(@Field("client_type") int client_type, @Field("pkg") String pkg, @Field("vcode") int vcode);

    /**
     * 班级详情
     */
    @FormUrlEncoded
    @POST("apps/user/loginByPassword")
    Observable<HttpResult<User>> login(@Field("ID") String id, @Field("PASSWORD") String pwd, @Field("LOCATION") String location);



    @Multipart
    @POST("apps/res/uploadImage")
    Observable<HttpResult<ResIdData>> updateImageInfo(@PartMap Map<String, RequestBody> params);



}