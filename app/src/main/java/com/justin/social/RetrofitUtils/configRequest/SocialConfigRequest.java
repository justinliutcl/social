package com.justin.social.RetrofitUtils.configRequest;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderDetialConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderNumConfig;
import com.justin.social.RetrofitUtils.DataBean.five.UserInfoConfig;
import com.justin.social.RetrofitUtils.DataBean.five.YouhuijuanConfig;
import com.justin.social.RetrofitUtils.DataBean.four.SocialTool;
import com.justin.social.RetrofitUtils.DataBean.one.AboutMeConfig;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.DataBean.one.HosptialConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NormalProblemListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.OnlineServiceConfig;
import com.justin.social.RetrofitUtils.DataBean.one.PoliceConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ResultConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SendImageConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddByNameConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialMoneyConfig;
import com.justin.social.RetrofitUtils.DataBean.three.ThreeConfig;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;

import okhttp3.ResponseBody;
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
    Call<NewsListConfig> getNewListConfig(@Query("contentTypeId") String contenTypeId,
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
    @POST("tool/socialSecurity")
    Call<SocialTool> getToolMoneyConfig(
                                                 @Field("socialSecurityBase") String socialsecurityBase,
                                                 @Field("accumulationFundBase") String accumulationBase,
                                                 @Field("householdType") String householdType,
                                                 @Field("cityName") String cityName
    );

    @FormUrlEncoded
    @POST("order/addSocialsecurityOrder")
    Call<ResultConfig> getSendOrderConfig(@Field("userId") String userId,
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
    Call<ResultConfig> getSendAccuOrderConfig(@Field("userId") String userId,
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

    @FormUrlEncoded
    @POST("order/addAllOrder")
    Call<ResultConfig> getAllOrderConfig(@Field("userId") String userId,
                                            @Field("userName") String userName,
                                            @Field("householdType") String householdType,
                                            @Field("insuredType") String insuredType,
                                            @Field("bankName") String bankName,
                                            @Field("branchNum") String branchNum,
                                            @Field("idCard") String idCard,
                                            @Field("insuredCity") String insuredCity,
                                            @Field("applyDuration") String applyDuration,
                                            @Field("socialSecurityBase") String socialSecurityBase,
                                            @Field("accumulationBase") String accumulationBase,
                                            @Field("socialSecurityCharge") String socialSecurityCharge,
                                            @Field("accumulationCharge") String accumulationCharge,
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

    @POST("user/headImg")
    Call<HeaderImageConfig> getHeadImageConfig(
            @Query("userId") String userId,
            @Query("imgData") String imgData
    );

    @POST("others/imgInfo")
    Call<HeaderImageConfig> getMessageImageConfig(
            @Query("userId") String userId,
            @Query("imgData") String imgData,
            @Query("typeId") String typeId
    );

    @POST("news/getNewsInfo")
    Call<NewListBean> getNewsContentConfig(@Query("contentId") String contentId
    );

    @FormUrlEncoded
    @POST("others/getAddService")
    Call<ServiceAddConfig> getServiceAddConfig(@Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("others/getAddServiceByName")
    Call<ServiceAddByNameConfig> getServiceAddByNameConfig(@Field("serviceName") String serviceName
    );

    @FormUrlEncoded
    @POST("order/addServiceOrder")
    Call<ResultConfig> getServiceAddOrderConfig(
            @Field("userId") String userId,
            @Field("orderType") String orderType,
            @Field("userName") String userName,
            @Field("idCard") String idCard,
            @Field("serviceType") String serviceType,
            @Field("allCharge") String allCharge
    );

    @FormUrlEncoded
    @POST("order/getOrder")
    Call<OrderConfig> getOrderConfig(@Field("userId") String orderType,
                                     @Field("status") String userName
    );

    @FormUrlEncoded
    @POST("user/addAdvice")
    Call<BaseConfig> getSendAdviceConfig(@Field("content") String content,
                                     @Field("phone") String phone,
                                     @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("question/questionList")
    Call<NormalProblemListConfig> getNormalProblemConfig(@Field("pageSize") String pageSize,
                                                         @Field("pageIndex") String pageIndex
    );

    @POST("custom/customList")
    Call<OnlineServiceConfig> getOnlineServiceConfig(
    );

    @FormUrlEncoded
    @POST("user/changeByUserId")
    Call<BaseConfig> getSendMesConfig(@Field("userName")        String userName,
                                      @Field("idCard")          String idCard,
                                      @Field("userId")          String userId,
                                      @Field("insuredCity")     String insuredCity,
                                      @Field("householdType")   String householdType,
                                      @Field("phone")           String phone,
                                      @Field("email")           String email,
                                      @Field("bankName")        String bankName,
                                      @Field("branchNum")       String branchNum
    );
    @FormUrlEncoded
    @POST("others/getOtherNew")
    Call<PoliceConfig> getPoliceDetialConfig(@Field("type")  String type );

    @POST("others/getAboutMe")
    Call<AboutMeConfig> getAboutMeConfig();

    @FormUrlEncoded
    @POST("user/getUserInfo")
    Call<UserInfoConfig> getUserInfoConfig(@Field("userId")  String userId );

    @FormUrlEncoded
    @POST("order/getOrderInfo")
    Call<OrderDetialConfig> getOrderDetialConfig(@Field("orderNum") String userId
            , @Field("type") String type);

    @FormUrlEncoded
    @POST("others/getAddOrderNum")
    Call<OrderNumConfig> getOrderNumConfig(@Field("userId")  String userId );

    @FormUrlEncoded
    @POST("others/delAddOrderNum")
    Call<BaseConfig> getDelOrderConfig(@Field("userId")  String userId,
                                           @Field("type")  String type);

    @FormUrlEncoded
    @POST("user/saveUserHospitalInfo")
    Call<BaseConfig> getDelOrderConfig(@Field("userId")  String userId,
                                       @Field("hospitalOne")  String hospitalOne,
                                       @Field("hospitalTwo")  String hospitalTwo,
                                       @Field("hospitalThree")  String hospitalThree,
                                       @Field("hospitalFour")  String hospitalFour,
                                       @Field("hospitalFive")  String hospitalFive);
    @FormUrlEncoded
    @POST("user/getUserHospitalInfo")
    Call<HosptialConfig> getHospatalConfig(@Field("userId")  String userId);

    @FormUrlEncoded
    @POST("others/getImgInfo")
    Call<SendImageConfig> getSendImageConfig(@Field("userId")  String userId);

    @FormUrlEncoded
    @POST("order/getOrderNow")
    Call<ThreeConfig> getThreeConfig(@Field("userId")  String userId);

    @FormUrlEncoded
    @POST("order/delOrder")
    Call<BaseConfig> cancelOrderConfig(@Field("orderNum")  String orderNum,
                                       @Field("type")  String type);

    @FormUrlEncoded
    @POST("receipts/addOrder")
    Call<ResultConfig> sendInvoiceConfig(@Field("userName")  String userName,
                                       @Field("userId")  String userId,
                                       @Field("allCharge")  String allCharge,
                                       @Field("orderNums")  String orderNums);

    @POST("weixin/pay")
    Call<ResponseBody> sendWeChatConfig(@Query("cashnum")  double cashnum,
                                        @Query("mercid")  String mercid,
                                        @Query("type") int type,
                                        @Query("couponId") String couponId
    );

    @FormUrlEncoded
    @POST("receipts/addSendAddress")
    Call<BaseConfig> addSendAddressConfig(@Field("orderNum") String orderNum,
                                            @Field("sendAddress") String sendAddress
    );

    @FormUrlEncoded
    @POST("coupon/getCouponByUserId")
    Call<YouhuijuanConfig> getYouhuiJuanConfig(@Field("userId") String orderNum
    );

    @FormUrlEncoded
    @POST("tool/overPayTool")
    Call<HeaderImageConfig> getAddCalculaterConfig(@Field("cityName") String cityName,
                                                @Field("days") String days,
                                                @Field("chargeBase") String chargeBase
    );
}
