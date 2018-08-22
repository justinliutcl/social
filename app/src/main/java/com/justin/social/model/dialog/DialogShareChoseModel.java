package com.justin.social.model.dialog;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.ContentKey;
import com.justin.social.utils.DialogUtils;
import com.justin.social.wxapi.WePayUser;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogShareChoseModel extends BaseModel {

    String title;
    String content;
    String mes;

    public DialogShareChoseModel(Context context, String title,String content,String mes) {
        super(context);
        this.title = title;
        this.content = content;
        this.mes = mes;
    }

    public void onCancleClick(View view) {
        DialogUtils.getDialogUtilInstance().dismiss();
    }



    public void onClick(View view) {
    }

    public void onWeFriendClick(View view){
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.welogo);
        AppUtils.shareToWechat(mContext,content,title,"快叫上小伙伴们一起来缴社保及存档案哦！",bmp);
    }
    public void onWeSocialClick(View view){
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.welogo);
        AppUtils.shareToSocail(mContext,content,title,"快叫上小伙伴们一起来缴社保及存档案哦！",bmp);
    }
    public void onShortNoteClick(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+""));
        intent.putExtra("sms_body", "快叫上小伙伴们一起来缴社保及存档案哦！http://www.youxuanzhijia.top");
        mContext.startActivity(intent);
    }
}
