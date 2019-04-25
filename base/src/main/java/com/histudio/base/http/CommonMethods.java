package com.histudio.base.http;


import com.histudio.base.entity.Update;
import com.histudio.base.http.api.CommonApi;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by ljh on 16/3/9.
 */
public class CommonMethods extends BaseMethods {

    private CommonApi commonApi;


    @Override
    protected void createService() {
        commonApi = retrofit.create(CommonApi.class);
    }

    public void checkUpdate(Observer<Update> data, int vcode) {

        Observable observable = commonApi.checkUpdate(1, "com.devstudio.delivery.sale", vcode)
                .map(new HttpResultFunc<Update>());

        toSubscribe(observable, data);

    }

    public void login(Observer data, String id, String pwd, String location) {
        Observable observable = commonApi.login(id, pwd, location)
                .map(new HttpResultFunc());

        toSubscribe(observable, data);
    }

}
