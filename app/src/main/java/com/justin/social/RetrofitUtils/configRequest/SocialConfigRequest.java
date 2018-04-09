package com.justin.social.RetrofitUtils.configRequest;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.UserConfig;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialMoneyConfig;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @POST("news/newsList")
    Call<NewsListConfig> getNewListConfig(@Query("contenTypeId") String contenTypeId,
                                          @Query("pageIndex") String pageIndex,
                                          @Query("pageSize") String pageSize
    );

    @POST("order/getCityNameList")
    Call<CityConfig> getCityListConfig(
    );

    @FormUrlEncoded
    @POST("order/getSocialsecurityOrderCharge")
    Call<SocialMoneyConfig> getSocialMoneyConfig(@Field("applyDuration") String applyDuration,
                                                 @Field("insuredCardinal") String insuredCardinal,
                                                 @Field("cityName") String cityName,
                                                 @Field("householdType") String householdType
    );

    @FormUrlEncoded
    @POST("order/getAccumulationCharge")
    Call<SocialMoneyConfig> getAccuMoneyConfig(@Field("applyDuration") String applyDuration,
                                                 @Field("insuredCardinal") String insuredCardinal,
                                                 @Field("cityName") String cityName
    );

    @FormUrlEncoded
    @POST("order/getAllCharge")
    Call<SocialMoneyConfig> getAllMoneyConfig(@Field("applyDuration") String applyDuration,
                                                 @Field("socialsecurityBase") String socialsecurityBase,
                                                 @Field("accumulationBase") String accumulationBase,
                                                 @Field("householdType") String householdType,
                                                 @Field("cityName") String cityName
    );

    @FormUrlEncoded
    @POST("order/addSocialsecurityOrder")
    Call<BaseConfig> getSendOrderConfig(@Field("userId") String userId,
                                                 @Field("userName") String userName,
                                                 @Field("householdType") String householdType,
                                                 @Field("insuredType") String insuredType,
                                                 @Field("bankName") String bankName,
                                                 @Field("branchNum") String branchNum,
                                                 @Field("idCard") String idCard,
                                                 @Field("insuredCity") String insuredCity,
                                                 @Field("applyDuration") String applyDuration,
                                                 @Field("insuredCardinal") String insuredCardinal,
                                                 @Field("insuredCharge") String insuredCharge,
                                                 @Field("disabilityCharge") String disabilityCharge,
                                                 @Field("singleCharge") String singleCharge,
                                                 @Field("serviceCharge") String serviceCharge,
                                                 @Field("overdueFine") String overdueFine,
                                                 @Field("allCharge") String allCharge,
                                                 @Field("insuredTime") String insuredTime
    );
    @FormUrlEncoded
    @POST("order/addAccumulationOrder")
    Call<BaseConfig> getSendAccuOrderConfig(@Field("userId") String userId,
                                                 @Field("userName") String userName,
                                                 @Field("householdType") String householdType,
                                                 @Field("insuredType") String insuredType,
                                                 @Field("bankName") String bankName,
                                                 @Field("branchNum") String branchNum,
                                                 @Field("idCard") String idCard,
                                                 @Field("insuredCity") String insuredCity,
                                                 @Field("applyDuration") String applyDuration,
                                                 @Field("insuredCardinal") String insuredCardinal,
                                                 @Field("accumulationCharge") String insuredCharge,
                                                 @Field("disabilityCharge") String disabilityCharge,
                                                 @Field("singleCharge") String singleCharge,
                                                 @Field("serviceCharge") String serviceCharge,
                                                 @Field("overdueFine") String overdueFine,
                                                 @Field("allCharge") String allCharge,
                                                 @Field("insuredTime") String insuredTime
    );

    @POST("others/getServiceList")
    Call<ServiceConfig> getServiceConfig(
    );
}
