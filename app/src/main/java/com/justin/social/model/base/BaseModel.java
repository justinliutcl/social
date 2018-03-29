package com.justin.social.model.base;

import android.content.Context;
import android.widget.Toast;

import com.justin.social.accessor.CommonSettingValue;

/**
 * Created by ASUS on 2018/3/25.
 */

public class BaseModel {
    public Context mContext;

    public BaseModel(Context context) {
        this.mContext = context;
    }

    public void toastShow(String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }

    public boolean isLogin() {
        return CommonSettingValue.getIns(mContext).getCurrentPhone() != null;
    }
}
