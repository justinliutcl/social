package com.justin.social.LogUtils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;

public class BuildUtils {

    public static boolean DEBUG = false;

    static {
        try {
            Class<?> clazz = Class.forName("com.ehawk.music.BuildConfig");
            Field field = clazz.getField("DEBUG");
            field.setAccessible(true);
            DEBUG = (Boolean) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static boolean initDebugInfo(Context context) {
        DEBUG = isApkInDebug(context);
        return DEBUG;
    }

    private static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

}