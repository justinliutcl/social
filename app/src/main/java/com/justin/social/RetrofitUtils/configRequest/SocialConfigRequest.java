package com.justin.social.RetrofitUtils.configRequest;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.UserConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Justinliu on 2018/1/29.
 */

public interface SocialConfigRequest {

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

    @POST("news/shortNewsList")
    Call<ShortNewsConfig> getShortNewsConfig(
    );
}
