package com.justin.social.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.NumberFormat;
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

    public static String getTime(String formoat){
        SimpleDateFormat sdf = new SimpleDateFormat(formoat);
        Date dt = new Date(System.currentTimeMillis());
        return sdf.format(dt);
    }

    public String get2Double(double num) {
        NumberFormat nbf = NumberFormat.getInstance();
        nbf.setMinimumFractionDigits(2);
        return nbf.format(num);
    }

    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断 Uri是否有效
     */
    public static boolean isValidIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }

    public static void JumpToQQ(Context context,String qq){
        // 跳转之前，可以先判断手机是否安装QQ
        if (isQQClientAvailable(context)) {
            // 跳转到客服的QQ
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=qq";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            // 跳转前先判断Uri是否存在，如果打开一个不存在的Uri，App可能会崩溃
            if (isValidIntent(context,intent)) {
                context.startActivity(intent);
            }
        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void diallPhone(String phoneNum,Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

}
