package com.justin.social;

import android.app.Application;
import android.content.Context;

import com.justin.social.utils.DimensionUtils;
import com.mob.MobSDK;

/**
 * Created by ASUS on 2018/3/25.
 */

public class SocialApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        MobSDK.init(this);
        DimensionUtils.initDimension(this);
    }
}
