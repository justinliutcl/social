package com.justin.social.accessor;

import android.content.Context;

import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;

/**
 * Created by Justinliu on 2018/2/7.
 */

public class CommonSettingValue extends GlobPre {

    private static CommonSettingValue commonValue;

    public static synchronized CommonSettingValue getIns(Context context) {
        if (commonValue == null) {
            commonValue = new CommonSettingValue(context.getApplicationContext());
        }
        return commonValue;
    }
    private static final String KEY_CURRENT_PHONE           = "key_current_phone";
    private static final String KEY_CITY                    = "key_city";
    protected CommonSettingValue(Context context) {
        super(context);
    }


    public void setCurrentPhone(String phone){
        putString(KEY_CURRENT_PHONE, phone);
    }

    public String getCurrentPhone(){
        return getString(KEY_CURRENT_PHONE, null);
    }

    public void setCity(CityConfig phone){
        putObject(KEY_CITY, phone);
    }

    public CityConfig getCity(){
        return getObject(KEY_CITY, null);
    }

    public void setSocialBase(String phone,String base){
        putString(phone+"social", base);
    }

    public String getSocialBase(String phone){
        return getString(phone+"social", null);
    }

    public void setAccuBase(String phone,String base){
        putString(phone+"accu", base);
    }

    public String getAccuBase(String phone){
        return getString(phone+"accu", null);
    }
}
