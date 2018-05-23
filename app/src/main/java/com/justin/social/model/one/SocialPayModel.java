package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.justin.social.activity.SocialPayActivity;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.ContentKey;

/**
 * Created by ASUS on 2018/3/31.
 */

public class SocialPayModel extends BaseModel {
    public  String name,idCard,base,starTime,city,typeName;
    public ObservableBoolean isSelect;
    public String num,type,money;

    public SocialPayModel(Context context,String num,String type,String money,
                          String name,String idCard,String base,String starTime,String city,
                          String typeName) {
        super(context);
        isSelect = new ObservableBoolean();
        this.num = num;
        this.type = type;
        this.money = money;
        this.name = name;
        this.idCard = idCard;
        this.base = base;
        this.starTime = starTime;
        this.city = city;
        this.typeName = typeName;
    }

    public void onNextClick(View view){
//        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
//        ((Activity)mContext).finish();
    }

    public void onClick(View view){
        AliPayUse pay = new AliPayUse( mContext, "社保付款", 0.01, type,num, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
            @Override
            public void SuccessCallBack(String mes) {

                toastShow("支付成功");

            }

            @Override
            public void failCallBack(String mes) {
                toastShow("支付失败");
            }
        });
        pay.pay();
    }
}
