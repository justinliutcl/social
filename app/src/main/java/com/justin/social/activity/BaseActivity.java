package com.justin.social.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.justin.social.accessor.CommonSettingValue;

/**
 * Created by ASUS on 2018/3/25.
 */

public class BaseActivity extends AppCompatActivity {
    public void toastShow(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public boolean isLogin() {
        return CommonSettingValue.getIns(this).getCurrentPhone() != null;
    }

}
