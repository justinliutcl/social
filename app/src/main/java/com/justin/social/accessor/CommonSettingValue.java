package com.justin.social.accessor;

import android.content.Context;

import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;

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
    private static final String KEY_CURRENT_PHONE               = "key_current_phone";
    private static final String KEY_CURRENT_USER_ID             = "key_current_userid";
    private static final String KEY_CITY                        = "key_city";
    private static final String SERVICE_ADD                     = "service_add";
    private static final String KEY_SERVICE                     = "key_service";
    protected CommonSettingValue(Context context) {
        super(context);
    }


    public void setCurrentPhone(String phone){
        putString(KEY_CURRENT_PHONE, phone);
    }

    public String getCurrentPhone(){
        return getString(KEY_CURRENT_PHONE, null);
    }
    public void setCurrentUserID(String id){
        putString(KEY_CURRENT_USER_ID, id);
    }

    public String getCurrentUserId(){
        return getString(KEY_CURRENT_USER_ID, null);
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

    public void setCity(String phone,String city){
        putString(phone+"city", city);
    }

    public String getCity(String phone){
        return getString(phone+"city", "北京");
    }

    public void setHourseType(String phone,String hourse){
        putString(phone+"hourse", hourse);
    }

    public String getHourseType(String phone){
        return getString(phone+"hourse", "本市城镇职工");
    }

//    public void setHeaderImage(String phone,String hourse){
//        putString(phone+"headImage", hourse);
//    }
//
//    public String getHeaderImage(){
//        return getString(getCurrentPhone()+"headImage", "");
//    }

    public void setService(ServiceConfig phone){
        putObject(KEY_SERVICE, phone);
    }

    public ServiceConfig getService(){
        return getObject(KEY_SERVICE, null);
    }

    public void setServiceAdd(ServiceAddConfig phone){
        putObject(SERVICE_ADD, phone);
    }

    public ServiceAddConfig getServiceAdd(){
        return getObject(SERVICE_ADD, null);
    }
}
