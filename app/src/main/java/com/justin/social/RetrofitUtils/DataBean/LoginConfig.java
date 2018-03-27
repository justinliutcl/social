package com.justin.social.RetrofitUtils.DataBean;

/**
 * Created by ASUS on 2018/3/27.
 */

public class LoginConfig extends BaseConfig {
    UserConfig data;

    public UserConfig getData() {
        return data;
    }

    public void setData(UserConfig data) {
        this.data = data;
    }
}
