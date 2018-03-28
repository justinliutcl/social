package com.justin.social.RetrofitUtils.DataBean.one;

import android.databinding.ObservableField;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class SocialPeopleConfig extends BaseConfig {
    public String content;

    public SocialPeopleConfig(String content) {
        this.content = content;
        socialTitle = new ObservableField<>(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        socialTitle.set(content);
    }

    public SocialPeopleConfig() {
    }
    public ObservableField<String>socialTitle;
}
