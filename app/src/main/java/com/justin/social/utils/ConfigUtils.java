package com.justin.social.utils;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

/**
 * Created by ASUS on 2018/3/27.
 */

public class ConfigUtils {
    private static final String SUCCESS_CODE    = "000000";
    private static final String FAILE_CODE      = "000001";

    public static boolean isSuccess(BaseConfig config){
        if(config == null)
            return false;
        if(config.getCode() == null)
            return false;
        return config.getCode().equals(SUCCESS_CODE);
    }
}
