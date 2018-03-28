package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.util.List;

/**
 * Created by ASUS on 2018/3/28.
 */

public class ShortNewsConfig extends BaseConfig {
    private List<SocialPeopleConfig>data;

    public List<SocialPeopleConfig> getData() {
        return data;
    }

    public void setData(List<SocialPeopleConfig> data) {
        this.data = data;
    }
}
