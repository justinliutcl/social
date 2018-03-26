package com.justin.social.RetrofitUtils;

import android.content.Context;

import com.justin.social.LogUtils.CommonLog;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.SoundCloudMusic;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.callBack.ListConfigCallBack;
import com.justin.social.RetrofitUtils.configRequest.SoundConfigRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Justinliu on 2018/3/21.
 */

public class HttpConfigManager {
    private static final int LIMIT  = 20;
    private              int offset = 0;

    public void refreshConfig(final Context context, String q, final ListConfigCallBack<SoundCloudMusic> callBack) {
        SoundConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SoundConfigRequest.class);
        offset = LIMIT;
        Call<List<SoundCloudMusic>> config = configRequest.getConfig("streamable", "hotness", q, LIMIT + "", "0");
        config.enqueue(new Callback<List<SoundCloudMusic>>() {
            @Override
            public void onResponse(Call<List<SoundCloudMusic>> call, Response<List<SoundCloudMusic>> response) {
                List<SoundCloudMusic> arr = response.body();
                callBack.onDataResponse(arr);
            }

            @Override
            public void onFailure(Call<List<SoundCloudMusic>> call, Throwable t) {
                CommonLog.e("request ad config failed : " + t.getMessage());
                callBack.onDataResponse(null);
            }
        });
    }

    public void registerConfig( String q,String code, final BeanConfigCallBack<BaseConfig> callBack) {
        SoundConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SoundConfigRequest.class);
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

    public void loginConfig( String q,String code, final BeanConfigCallBack<BaseConfig> callBack) {
        SoundConfigRequest configRequest = RetrofitManager.getSoundCloudRetrofit().create(SoundConfigRequest.class);
        Call<BaseConfig> config = configRequest.getLoginConfig(q, code);
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
}
