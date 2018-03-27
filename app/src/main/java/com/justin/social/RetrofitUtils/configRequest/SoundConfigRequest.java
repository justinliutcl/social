package com.justin.social.RetrofitUtils.configRequest;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.UserConfig;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Justinliu on 2018/1/29.
 */

public interface SoundConfigRequest {

    @POST("user/sign")
    Call<BaseConfig> getRegistConfig(@Query("phoneNum") String phoneNum,
                                     @Query("passWord") String passWord
    );

    @POST("user/login")
    Call<LoginConfig> getLoginConfig(@Query("phoneNum") String phoneNum,
                                     @Query("passWord") String passWord
    );
    @POST("user/changePwd")
    Call<BaseConfig> getForgetConfig(@Query("phoneNum") String phoneNum,
                                    @Query("newPassWord") String passWord
    );
}
