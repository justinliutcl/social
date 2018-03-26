package com.justin.social.model.loginMode;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.model.base.SmsModel;

/**
 * Created by ASUS on 2018/3/25.
 */

public class ForgetModel extends SmsModel {
    public EditText codeEditText;

    public ForgetModel(Context context, EditText phoneEditText, EditText smsEditText, EditText codeEditText) {
        super(context, phoneEditText, smsEditText);
        this.codeEditText = codeEditText;
    }

    public void onSendClick(View view) {
        sendCode(phoneEditText.getText().toString());
    }

    public void onRegistClick(View view) {
        submitCode(phoneEditText.getText().toString(), smsEditText.getText().toString());
    }

    @Override
    public void onSubmitSuccess() {
        super.onSubmitSuccess();
        new HttpConfigManager().registerConfig( getPhoneNum(), codeEditText.getText().toString(), new BeanConfigCallBack<BaseConfig>() {
            @Override
            public void onDataResponse(BaseConfig list) {
                toastShow(list.getMsg());
            }
        });
    }
}
