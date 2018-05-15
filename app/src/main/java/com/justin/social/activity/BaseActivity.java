package com.justin.social.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.service.DemoIntentService;
import com.justin.social.service.MyPushService;

/**
 * Created by ASUS on 2018/3/25.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushManager.getInstance().initialize(this.getApplicationContext(), MyPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        // 竖屏显示
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void toastShow(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public boolean isLogin() {
        return CommonSettingValue.getIns(this).getCurrentPhone() != null;
    }

}
