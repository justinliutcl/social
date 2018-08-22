package com.justin.social.utils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.RetrofitManager;
import com.justin.social.RetrofitUtils.configRequest.FileUploadService;
import com.justin.social.model.five.PhoneModel;
import com.justin.social.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public static String getTime(String formoat,long time){
        SimpleDateFormat sdf = new SimpleDateFormat(formoat);
        Date dt = new Date(time);
        return sdf.format(dt);
    }

    public static String get2Double(double num) {
        NumberFormat nbf = NumberFormat.getInstance();
        nbf.setMinimumFractionDigits(2);
        return nbf.format(num);
    }

    public static String getTwoDecimal(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.00");
        String yearString=dFormat.format(num);
        return yearString;
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
            String url = "mqqwpa://im/chat?chat_type=wpa&uin="+qq;
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
    public static void diallPhone(String phoneNum,Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 复制到剪贴板
     * @param context
     * @return
     */
    public static void copyToClip(String mes,Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(mes);
        Toast.makeText(context,"已复制",Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转
     * @param context
     * @return
     */
    public static void jumpToUrl(String mes,Context context) {
        Uri uri = Uri.parse(mes);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public static List<PhoneModel> getContacts(Context context) {
        //联系人的Uri，也就是content://com.android.contacts/contacts
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //指定获取_id和display_name两列数据，display_name即为姓名
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        //根据Uri查询相应的ContentProvider，cursor为获取到的数据集
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        List<PhoneModel> arr = new ArrayList<>();
        int i = 0;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Long id = cursor.getLong(0);
                //获取姓名
                String name = cursor.getString(1);
                //指定获取NUMBER这一列数据
                String[] phoneProjection = new String[] {
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
//                arr[i] = id + " , 姓名：" + name;
//                arr[i] =  "姓名：" + name;

                //根据联系人的ID获取此人的电话号码
                Cursor phonesCusor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        phoneProjection,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                        null,
                        null);

//                因为每个联系人可能有多个电话号码，所以需要遍历
                if (phonesCusor != null && phonesCusor.moveToFirst()) {
//                    do {
                        String num = phonesCusor.getString(0);
//                        arr[i] += " , 电话号码：" + num;
//                        arr[i] += " " + num;
                    arr.add(new PhoneModel(name,num));
//                    }while (phonesCusor.moveToNext());
                }
                i++;
            } while (cursor.moveToNext());
        }
        return arr;
    }
    public static String getURLencode(String text){
        try {
            return java.net.URLEncoder.encode(text,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     * @param message
     */
    public static void doSendSMSTo(Context context,String phoneNumber,String message){
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", message);
            context.startActivity(intent);
        }
    }

    public static void shareToWechat(Context context,String url,String title,String des,Bitmap bitmap){
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = url;
        WXMediaMessage message = new WXMediaMessage(wxWebpageObject);
        message.title = title;
        message.description = des;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();
        message.thumbData = datas;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "newSocial";
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        IWXAPI api = WXAPIFactory.createWXAPI(context, Constants.APP_ID,true);
        api.registerApp(Constants.APP_ID);
        api.sendReq(req);

    }

    public static void shareToSocail(Context context,String url,String title,String des,Bitmap bitmap){
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = url;
        WXMediaMessage message = new WXMediaMessage(wxWebpageObject);
        message.title = title;
        message.description = des;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();
        message.thumbData = datas;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "newSocial";
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        IWXAPI api = WXAPIFactory.createWXAPI(context, Constants.APP_ID,true);
        api.registerApp(Constants.APP_ID);
        api.sendReq(req);

    }

    public static void uploadFile(Uri fileUri, final Context context,final BeanConfigCallBack<HeaderImageConfig> callBack) {
        // create upload service client
        final FileUploadService service =
                RetrofitManager.getSoundCloudRetrofit().create(FileUploadService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(context, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        final MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // add another part within the multipart request
         UserDataObtain.getInstance(context).getCurrentUser(new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                String descriptionString =dbUser.getUserId();
                RequestBody description =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), descriptionString);

                // finally, execute the request
                Call<HeaderImageConfig> call = service.upload(description, body);
                call.enqueue(new Callback<HeaderImageConfig>() {
                    @Override
                    public void onResponse(Call<HeaderImageConfig> call,
                                           Response<HeaderImageConfig> response) {
                        HeaderImageConfig arr = response.body();
                        callBack.onDataResponse(arr);
                    }

                    @Override
                    public void onFailure(Call<HeaderImageConfig> call, Throwable t) {
                        Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
