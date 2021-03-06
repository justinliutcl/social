package com.justin.social.model.loginMode;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.EditText;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.ActivityForgetBinding;
import com.justin.social.model.base.SmsModel;
import com.justin.social.utils.AccountUtils;
import com.justin.social.utils.ConfigUtils;

/**
 * Created by ASUS on 2018/3/25.
 */

public class ForgetModel extends SmsModel {
    public int loginUnClickId = R.drawable.background_login_unclick;
    public int loginSelectId = R.drawable.select_login;
    public EditText codeEditText;
    public ObservableInt loginBackId;
    ActivityForgetBinding binding;
    public ForgetModel(Context context, EditText phoneEditText, EditText smsEditText, EditText codeEditText) {
        super(context, phoneEditText, smsEditText);
        this.codeEditText = codeEditText;
        loginBackId = new ObservableInt(R.drawable.background_login_unclick);
    }

    public void initBind(ActivityForgetBinding binding){
        this.binding = binding;
    }
    public void onSendClick(View view) {
        sendCode(phoneEditText.getText().toString());
    }

    public void onRegistClick(View view) {
        if(!AccountUtils.isMobile(binding.phoneEd.getText().toString())){
            toastShow("请输入正确手机号");
            return;
        }
        submitCode(phoneEditText.getText().toString(), smsEditText.getText().toString());
    }

    @BindingAdapter("loginBack")
    public static void setLoginBack(View view,int id){
        view.setBackgroundResource(id);
    }

    @Override
    public void onSubmitSuccess() {
        super.onSubmitSuccess();
        if(!AccountUtils.isMobile(binding.phoneEd.getText().toString())){
            toastShow("请输入正确手机号");
            return;
        }
        new HttpConfigManager().forgetConfig( getPhoneNum(), codeEditText.getText().toString(), new BeanConfigCallBack<BaseConfig>() {
            @Override
            public void onDataResponse(BaseConfig bean) {
                if(ConfigUtils.isSuccess(bean)){
                    ((Activity)mContext).finish();
                }else {
                    toastShow(bean.getMsg());
                }
            }
        });
    }
}
