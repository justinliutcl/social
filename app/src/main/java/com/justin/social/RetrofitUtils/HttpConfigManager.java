package com.justin.social.RetrofitUtils;

import android.widget.Toast;

import com.justin.social.LogUtils.CommonLog;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.four.SocialTool;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NormalProblemListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.OnlineServiceConfig;
import com.justin.social.RetrofitUtils.DataBean.one.PoliceConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddByNameConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialMoneyConfig;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;
import com.justin.social.RetrofitUtils.configRequest.SocialConfigRequest;
import com.justin.social.SocialApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Justinliu on 2018/3/21.
 */

public class HttpConfigManager {
    private static final int LIMIT = 20;
    private int offset = 0;


    public void registerConfig(String q, String code, final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getRegistConfig(q, code);
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void forgetConfig(String q, String code, final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getForgetConfig(q, code);
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginConfig(String q, String code, final BeanConfigCallBack<LoginConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<LoginConfig> config = configRequest.getLoginConfig(q, code);
        config.enqueue(new Callback<LoginConfig>() {
            @Override
            public void onResponse(Call<LoginConfig> call, Response<LoginConfig> response) {
                LoginConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<LoginConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void shortNewsConfig(final BeanConfigCallBack<ShortNewsConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<ShortNewsConfig> config = configRequest.getShortNewsConfig();
        config.enqueue(new Callback<ShortNewsConfig>() {
            @Override
            public void onResponse(Call<ShortNewsConfig> call, Response<ShortNewsConfig> response) {
                ShortNewsConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<ShortNewsConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getNewListConfig(String type, final BeanConfigCallBack<NewsListConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<NewsListConfig> config = configRequest.getNewListConfig(type, "1", "100");
        config.enqueue(new Callback<NewsListConfig>() {
            @Override
            public void onResponse(Call<NewsListConfig> call, Response<NewsListConfig> response) {
                NewsListConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<NewsListConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCityListConfig(final BeanConfigCallBack<CityConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<CityConfig> config = configRequest.getCityListConfig();
        config.enqueue(new Callback<CityConfig>() {
            @Override
            public void onResponse(Call<CityConfig> call, Response<CityConfig> response) {
                CityConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<CityConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSocialMoneyConfig(String applyDuration, String insuredCardinal, String cityName, String householdType, final BeanConfigCallBack<SocialMoneyConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<SocialMoneyConfig> config = configRequest.getSocialMoneyConfig(applyDuration, insuredCardinal, cityName, householdType);
        config.enqueue(new Callback<SocialMoneyConfig>() {
            @Override
            public void onResponse(Call<SocialMoneyConfig> call, Response<SocialMoneyConfig> response) {
                SocialMoneyConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<SocialMoneyConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAccuMoneyConfig(String applyDuration, String insuredCardinal, String cityName, final BeanConfigCallBack<SocialMoneyConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<SocialMoneyConfig> config = configRequest.getAccuMoneyConfig(applyDuration, insuredCardinal, cityName);
        config.enqueue(new Callback<SocialMoneyConfig>() {
            @Override
            public void onResponse(Call<SocialMoneyConfig> call, Response<SocialMoneyConfig> response) {
                SocialMoneyConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<SocialMoneyConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSendOrderConfig(String userId, String userName,
                                   String householdType, String insuredType,
                                   String bankName, String branchNum,
                                   String idCard, String insuredCity,
                                   String applyDuration, String insuredCardinal,
                                   String insuredCharge, String disabilityCharge,
                                   String singleCharge, String serviceCharge,
                                   String overdueFine, String allCharge, String insuredTime,
                                   final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getSendOrderConfig(
                userId, userName,
                householdType, insuredType,
                bankName, branchNum,
                idCard, insuredCity,
                applyDuration, insuredCardinal,
                insuredCharge, disabilityCharge,
                singleCharge, serviceCharge,
                overdueFine, allCharge, insuredTime
        );
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSendAccuOrderConfig(String userId, String userName,
                                       String householdType, String insuredType,
                                       String bankName, String branchNum,
                                       String idCard, String insuredCity,
                                       String applyDuration, String insuredCardinal,
                                       String insuredCharge, String disabilityCharge,
                                       String singleCharge, String serviceCharge,
                                       String overdueFine, String allCharge, String insuredTime,
                                       final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getSendAccuOrderConfig(
                userId, userName,
                householdType, insuredType,
                bankName, branchNum,
                idCard, insuredCity,
                applyDuration, insuredCardinal,
                insuredCharge, disabilityCharge,
                singleCharge, serviceCharge,
                overdueFine, allCharge, insuredTime
        );
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllOrderConfig(String userId, String userName,
                                  String householdType, String insuredType,
                                  String bankName, String branchNum,
                                  String idCard, String insuredCity,
                                  String applyDuration,
                                  String socialSecurityBase, String accumulationBase,
                                  String socialSecurityCharge, String accumulationCharge,
                                  String disabilityCharge,
                                  String singleCharge, String serviceCharge,
                                  String overdueFine, String allCharge, String insuredTime,
                                  final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getAllOrderConfig(
                userId, userName,
                householdType, insuredType,
                bankName, branchNum,
                idCard, insuredCity,
                applyDuration,
                socialSecurityBase, accumulationBase,
                socialSecurityCharge, accumulationCharge,
                disabilityCharge,
                singleCharge, serviceCharge,
                overdueFine, allCharge, insuredTime
        );
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllMoneyConfig(String applyDuration, String insuredCardinal, String accumulationBase, String hourseType, String cityName, final BeanConfigCallBack<SocialMoneyConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<SocialMoneyConfig> config = configRequest.getAllMoneyConfig(applyDuration, insuredCardinal, accumulationBase, hourseType, cityName);
        config.enqueue(new Callback<SocialMoneyConfig>() {
            @Override
            public void onResponse(Call<SocialMoneyConfig> call, Response<SocialMoneyConfig> response) {
                SocialMoneyConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<SocialMoneyConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getServiceConfig(final BeanConfigCallBack<ServiceConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<ServiceConfig> config = configRequest.getServiceConfig();
        config.enqueue(new Callback<ServiceConfig>() {
            @Override
            public void onResponse(Call<ServiceConfig> call, Response<ServiceConfig> response) {
                ServiceConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<ServiceConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getHeadImageConfig(String userIdfinal, String image, final BeanConfigCallBack<HeaderImageConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<HeaderImageConfig> config = configRequest.getHeadImageConfig(userIdfinal, image);
        config.enqueue(new Callback<HeaderImageConfig>() {
            @Override
            public void onResponse(Call<HeaderImageConfig> call, Response<HeaderImageConfig> response) {
                HeaderImageConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<HeaderImageConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getMessageImageConfig(String userIdfinal, String image,String type, final BeanConfigCallBack<HeaderImageConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<HeaderImageConfig> config = configRequest.getMessageImageConfig(userIdfinal, image,type);
        config.enqueue(new Callback<HeaderImageConfig>() {
            @Override
            public void onResponse(Call<HeaderImageConfig> call, Response<HeaderImageConfig> response) {
                HeaderImageConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<HeaderImageConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getNewsContentConfig(String contentId, final BeanConfigCallBack<NewListBean> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<NewListBean> config = configRequest.getNewsContentConfig(contentId);
        config.enqueue(new Callback<NewListBean>() {
            @Override
            public void onResponse(Call<NewListBean> call, Response<NewListBean> response) {
                NewListBean arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<NewListBean> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //tool
    public void getToolMoneyConfig(String socialBase, String accumulationBase, String hourseType, String cityName, final BeanConfigCallBack<SocialTool> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<SocialTool> config = configRequest.getToolMoneyConfig(socialBase, accumulationBase, hourseType, cityName);
        config.enqueue(new Callback<SocialTool>() {
            @Override
            public void onResponse(Call<SocialTool> call, Response<SocialTool> response) {
                SocialTool arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<SocialTool> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //增值服务
    public void getServiceAddConfig(final BeanConfigCallBack<ServiceAddConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<ServiceAddConfig> config = configRequest.getServiceAddConfig();
        config.enqueue(new Callback<ServiceAddConfig>() {
            @Override
            public void onResponse(Call<ServiceAddConfig> call, Response<ServiceAddConfig> response) {
                ServiceAddConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<ServiceAddConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //增值服务
    public void getServiceAddByNameConfig(String name,final BeanConfigCallBack<ServiceAddByNameConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<ServiceAddByNameConfig> config = configRequest.getServiceAddByNameConfig(name);
        config.enqueue(new Callback<ServiceAddByNameConfig>() {
            @Override
            public void onResponse(Call<ServiceAddByNameConfig> call, Response<ServiceAddByNameConfig> response) {
                ServiceAddByNameConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<ServiceAddByNameConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendServiceAddConfig(String userId,String orderType,String userName,String idCard,String charge,String sum,final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getServiceAddOrderConfig(userId,orderType,userName,idCard,charge,sum);
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getOrderConfig(String orderType,String userId,final BeanConfigCallBack<OrderConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<OrderConfig> config = configRequest.getOrderConfig(userId,orderType);
        config.enqueue(new Callback<OrderConfig>() {
            @Override
            public void onResponse(Call<OrderConfig> call, Response<OrderConfig> response) {
                OrderConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<OrderConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSendAdviceConfig(String content,String phone,String userId,final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getSendAdviceConfig(content,phone,userId);
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getNormalProblemConfig(final BeanConfigCallBack<NormalProblemListConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<NormalProblemListConfig> config = configRequest.getNormalProblemConfig("100","1");
        config.enqueue(new Callback<NormalProblemListConfig>() {
            @Override
            public void onResponse(Call<NormalProblemListConfig> call, Response<NormalProblemListConfig> response) {
                NormalProblemListConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<NormalProblemListConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getOnlineServiceConfig(final BeanConfigCallBack<OnlineServiceConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<OnlineServiceConfig> config = configRequest.getOnlineServiceConfig();
        config.enqueue(new Callback<OnlineServiceConfig>() {
            @Override
            public void onResponse(Call<OnlineServiceConfig> call, Response<OnlineServiceConfig> response) {
                OnlineServiceConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<OnlineServiceConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSendUserMesConfig(
            String userName,
            String idCard,
            String userId,
            String insuredCity,
            String householdType,
            String phone,
            String email,
            String bankName,
            String branchNum,
            final BeanConfigCallBack<BaseConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<BaseConfig> config = configRequest.getSendMesConfig(userName,
                idCard,
                userId,
                insuredCity,
                householdType,
                phone,
                email,
                bankName,
                branchNum);
        config.enqueue(new Callback<BaseConfig>() {
            @Override
            public void onResponse(Call<BaseConfig> call, Response<BaseConfig> response) {
                BaseConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<BaseConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPoliceDetialConfig(int type,final BeanConfigCallBack<PoliceConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<PoliceConfig> config = configRequest.getPoliceDetialConfig(String.valueOf(type));
        config.enqueue(new Callback<PoliceConfig>() {
            @Override
            public void onResponse(Call<PoliceConfig> call, Response<PoliceConfig> response) {
                PoliceConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<PoliceConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                Toast.makeText(SocialApplication.context, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
