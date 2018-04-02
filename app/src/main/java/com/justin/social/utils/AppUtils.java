package com.justin.social.utils;

import android.app.Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ASUS on 2018/3/26.
 */

public class AppUtils {
    private Set<Activity> activityList = new HashSet<>();

    private static class Ins{
        private static AppUtils ins = new AppUtils();
    }
    public static AppUtils getAppUtilsInstance(){
        return Ins.ins;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void finish(){
        for(Activity activity : activityList){
            if(!activity.isDestroyed())
                activity.finish();
        }
        activityList.clear();
    }

    public static String getNewstime(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date dt = new Date(time);
        return sdf.format(dt);
    }
}
