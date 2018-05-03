package com.justin.social.model.five;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.text.InputType;
import android.view.View;

import com.justin.social.R;
import com.justin.social.activity.ForgetActivity;
import com.justin.social.activity.RegistActivity;
import com.justin.social.databinding.ActivityLoginBinding;
import com.justin.social.databinding.ActivityResetcodeBinding;
import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/3/26.
 */

public class ResetCodeModel extends BaseModel {
    public int loginUnClickId = R.drawable.background_login_unclick;
    public int loginSelectId = R.drawable.select_login;
    public ObservableBoolean passWordShow;
    public ObservableInt loginBackId;
    private ActivityResetcodeBinding bind;

    public ResetCodeModel(Context context) {
        super(context);
        loginBackId = new ObservableInt(R.drawable.background_login_unclick);
        passWordShow = new ObservableBoolean(false);
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

    public void onPasswordImageClick(View view){
        if(passWordShow.get()){
            bind.passwordEd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            bind.passwordIv.setImageResource(R.drawable.icon_password_show);
        }else{
            bind.passwordEd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            bind.passwordIv.setImageResource(R.drawable.icon_password_hide);
        }
        passWordShow.set(!passWordShow.get());
    }

    public void initBind(ActivityResetcodeBinding binding) {
        this.bind = binding;
    }
}
