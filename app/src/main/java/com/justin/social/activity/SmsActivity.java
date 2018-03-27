package com.justin.social.activity;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by ASUS on 2018/3/25.
 */

public class SmsActivity extends BackActivity {

    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    }
}
