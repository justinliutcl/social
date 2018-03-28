package com.justin.social.accessor;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigFactory {

    private static ConfigFactory mDubaConfigCusorFactory;

    private OnSettingChangeListener mListener ;

    public interface OnSettingChangeListener{
        public void onSettingChange(String key, String value, int what) ;
    }

    public void registeListener(OnSettingChangeListener listener){
        mListener = listener ;
    }

    public synchronized static ConfigFactory getInstance(Context context) {
        if (mDubaConfigCusorFactory == null) {
            mDubaConfigCusorFactory = new ConfigFactory(context);
        }
        return mDubaConfigCusorFactory;
    }

    private SharedPreferences mPref;

    public ConfigFactory(Context context) {
        mPref = context.getSharedPreferences("music" , context.MODE_PRIVATE) ;
    }


    public void setData(String key, String value , int what) {
        SharedPreferences.Editor edit = mPref.edit();
        switch(what){
            case ConfigProvider.what_boolean:
                edit.putBoolean(key, Boolean.valueOf(value)) ;
                break ;
            case ConfigProvider.what_int:
                edit.putInt(key, Integer.valueOf(value)) ;
                break ;
            case ConfigProvider.what_long:
                edit.putLong(key, Long.valueOf(value)) ;
                break ;
            case ConfigProvider.what_string:
                edit.putString(key, value);
                break ;
        }
        edit.commit();

        if(mListener != null){
            mListener.onSettingChange(key, value, what) ;
        }
    }

    public String getData(String key , String what) {
        if(!mPref.contains(key)){
            return null ;
        }
        int what_flags = Integer.valueOf(what) ;
        String value = null;
        switch(what_flags){
            case ConfigProvider.what_boolean:
                value = mPref.getBoolean(key, true) + "";
                break ;
            case ConfigProvider.what_int:
                value = mPref.getInt(key, 0) + "";
                break ;
            case ConfigProvider.what_long:
                value = mPref.getLong(key, 0) + "";
                break ;
            case ConfigProvider.what_string:
                value = mPref.getString(key, null) + "";
                break ;
        }
        return value;
    }
}

