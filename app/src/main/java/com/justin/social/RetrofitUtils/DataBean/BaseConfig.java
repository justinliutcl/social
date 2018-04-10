package com.justin.social.RetrofitUtils.DataBean;

/**
 * Created by ASUS on 2018/3/25.
 */

public class BaseConfig {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return code != null && code.equals("000000");
    }
}
