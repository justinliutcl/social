package com.justin.social.RetrofitUtils;

import com.justin.social.LogUtils.CommonLog;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.configRequest.SocialConfigRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Justinliu on 2018/3/21.
 */

public class HttpConfigManager {
    private static final int LIMIT  = 20;
    private              int offset = 0;


    public void registerConfig( String q,String code, final BeanConfigCallBack<BaseConfig> callBack) {
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
                callBack.onDataResponse(null);
            }
        });
    }

    public void forgetConfig( String q,String code, final BeanConfigCallBack<BaseConfig> callBack) {
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
                callBack.onDataResponse(null);
            }
        });
    }

    public void loginConfig( String q,String code, final BeanConfigCallBack<LoginConfig> callBack) {
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
                callBack.onDataResponse(null);
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
                callBack.onDataResponse(null);
            }
        });
    }

    public void getNewListConfig(String type,final BeanConfigCallBack<NewsListConfig> callBack) {
        SocialConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SocialConfigRequest.class);
        Call<NewsListConfig> config = configRequest.getNewListConfig(type,"1","100");
        config.enqueue(new Callback<NewsListConfig>() {
            @Override
            public void onResponse(Call<NewsListConfig> call, Response<NewsListConfig> response) {
                NewsListConfig arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<NewsListConfig> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                callBack.onDataResponse(null);
            }
        });
    }
}
