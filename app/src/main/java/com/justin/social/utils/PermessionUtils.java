package com.justin.social.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * Created by ASUS on 2018/4/10.
 */

public class PermessionUtils {
    public static boolean checkPermission(Context context, Fragment fragment, final Activity activity, String permission, int requestCode) {
        //检查权限
        if (ContextCompat.checkSelfPermission(context, permission) !=  PackageManager.PERMISSION_GRANTED) {
                if (fragment == null) {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{permission},
                            requestCode);
                } else {
                    fragment.requestPermissions(
                            new String[]{permission},
                            requestCode);
                }
            return false;
        } else {  //已经拥有权限
            return true;
        }
    }

    public static void grantUriPermission (Context context, Uri fileUri, Intent intent) {
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }
}
