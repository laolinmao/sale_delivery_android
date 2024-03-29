package com.histudio.base.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
//先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        if (httpResult.getError_code() == 0) {
//200的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式
            return gson.fromJson(response, type);
        } else {
            HttpResult errorResponse = gson.fromJson(response, HttpResult.class);
//抛一个自定义ResultException 传入失败时候的状态码，和信息
            throw new ApiException(errorResponse.getError_code(), errorResponse.getMsg());
        }
    }
}
