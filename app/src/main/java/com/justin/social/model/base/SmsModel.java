package com.justin.social.model.base;

import android.content.Context;
import android.database.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.justin.social.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by ASUS on 2018/3/25.
 */

public class SmsModel extends BaseModel {
    public static final int CODE_SEND_SUCCESS     = 0;
    public static final int CODE_SEND_FAIL        = 1;
    public static final int CODE_SUBMIT_SUCCESS   = 2;
    public static final int CODE_SUBMIT_FAIL      = 3;
    public static final int CODE_SMS_SHOW_TIME    = 4;
    public EditText phoneEditText;
    public EditText smsEditText;
    public ObservableField<String> timeObservable;
    public ObservableInt sendBackId;
    int time;
    boolean isSendEnable;
    public SmsModel(Context context, EditText phoneEditText,EditText smsEditText) {
        super(context);
        this.phoneEditText = phoneEditText;
        this.smsEditText = smsEditText;
        time = 60;
        isSendEnable = true;
        sendBackId = new ObservableInt(R.drawable.background_login_normal);
        timeObservable = new ObservableField<>(mContext.getString(R.string.regist_hint_send));
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                switch (msg.what){
                    case CODE_SEND_SUCCESS:
                        toastShow("发送成功");
                        break;
                    case CODE_SEND_FAIL:
                        toastShow("发送失败");
                        break;
                    case CODE_SUBMIT_SUCCESS:
                        onSubmitSuccess();
                        break;
                    case CODE_SUBMIT_FAIL:
                        toastShow("验证码错误");
                        break;
                    case CODE_SMS_SHOW_TIME:
                        if(time<1){
                            time = 60;
                            timeObservable.set(mContext.getString(R.string.regist_hint_send));
                            sendBackId.set(R.drawable.background_login_normal);
                            isSendEnable = true;
                        }else{
                            time--;
                            isSendEnable = false;
                            timeObservable.set("重新获取("+time+"s)");
                            sendBackId.set(R.drawable.background_login_unclick);
                            handler.sendEmptyMessageDelayed(CODE_SMS_SHOW_TIME,1000);
                        }
                        break;
                }
        }
    };

    public void onSubmitSuccess() {

    }

    public void sendCode( String phone) {
        if(!isSendEnable)
            return;
        handler.sendEmptyMessage(CODE_SMS_SHOW_TIME);
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    handler.sendEmptyMessage(CODE_SEND_SUCCESS);
                } else{
                    handler.sendEmptyMessage(CODE_SEND_FAIL);
                    time = 0;
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode("86", phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode( String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    handler.sendEmptyMessage(CODE_SUBMIT_SUCCESS);
                } else{
                    handler.sendEmptyMessage(CODE_SUBMIT_FAIL);
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode("86", phone, code);
    }

    public String getPhoneNum(){
        return phoneEditText.getText().toString();
    }

    public String getSmsCode(){
        return smsEditText.getText().toString();
    }
}
