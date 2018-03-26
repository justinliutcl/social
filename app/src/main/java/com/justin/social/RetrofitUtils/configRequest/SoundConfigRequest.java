package com.justin.social.RetrofitUtils.configRequest;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.SoundCloudMusic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Justinliu on 2018/1/29.
 */

public interface SoundConfigRequest {

    @GET("tracks.json")
    Call<List<SoundCloudMusic>> getConfig(@Query("filter") String filter,
                                          @Query("order") String order,
                                          @Query("q") String q,
                                          @Query("limit") String limit,
                                          @Query("offset") String offset
    );

    @POST("user/sign")
    Call<BaseConfig> getRegistConfig(@Query("phoneNum") String phoneNum,
                                     @Query("passWord") String passWord
    );

    @POST("user/login")
    Call<BaseConfig> getLoginConfig(@Query("phoneNum") String phoneNum,
                                    @Query("passWord") String passWord
    );
}
