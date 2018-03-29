package com.justin.social.accessor;

import android.content.Context;

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
    protected CommonSettingValue(Context context) {
        super(context);
    }


    public void setCurrentPhone(String phone){
        putString(KEY_CURRENT_PHONE, phone);
    }

    public String getCurrentPhone(){
        return getString(KEY_CURRENT_PHONE, null);
    }
}
