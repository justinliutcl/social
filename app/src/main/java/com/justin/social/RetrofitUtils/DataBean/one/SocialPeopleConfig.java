package com.justin.social.RetrofitUtils.DataBean.one;

import android.databinding.ObservableField;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class SocialPeopleConfig extends BaseConfig {
    public String title;

    public SocialPeopleConfig(String title) {
        this.title = title;
        socialTitle = new ObservableField<>(title);
    }

    public SocialPeopleConfig() {
    }
    public ObservableField<String>socialTitle;
}
