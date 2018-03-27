package com.justin.social.model.loginMode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.view.View;

import com.justin.social.R;
import com.justin.social.activity.ForgetActivity;
import com.justin.social.activity.RegistActivity;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;

/**
 * Created by ASUS on 2018/3/26.
 */

public class LoginModel extends BaseModel {
    public int loginUnClickId = R.drawable.background_login_unclick;
    public int loginSelectId = R.drawable.select_login;
    public ObservableInt loginBackId;

    public LoginModel(Context context) {
        super(context);
        loginBackId = new ObservableInt(R.drawable.background_login_unclick);
    }

    @BindingAdapter("loginBack")
    public static void setLoginBack(View view,int id){
        view.setBackgroundResource(id);
    }

    public void onForgetClick(View view){
        Intent intent = new Intent(mContext, ForgetActivity.class);
        mContext.startActivity(intent);
//        AppUtils.getAppUtilsInstance().addActivity((Activity)mContext);
    }

    public void onRegistClick(View view){
        Intent intent = new Intent(mContext, RegistActivity.class);
        mContext.startActivity(intent);
//        AppUtils.getAppUtilsInstance().addActivity((Activity)mContext);
    }
}
