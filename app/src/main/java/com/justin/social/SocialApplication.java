package com.justin.social;

import android.app.Application;

import com.justin.social.utils.DimensionUtils;
import com.mob.MobSDK;

/**
 * Created by ASUS on 2018/3/25.
 */

public class SocialApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MobSDK.init(this);
        DimensionUtils.initDimension(this);
    }
}
