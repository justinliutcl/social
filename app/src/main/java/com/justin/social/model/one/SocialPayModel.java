package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.activity.OrderTableDetailActivity;
import com.justin.social.activity.SocialPayActivity;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.ContentKey;
import com.justin.social.wxapi.WePayUser;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by ASUS on 2018/3/31.
 */

public class SocialPayModel extends BaseModel {
    public  String name,idCard,base,accu,starTime,city,typeName;
    private String individual, residual, serviceMoney, overdel;
    public ObservableBoolean isSelect;
    public String num,type,money;


    public SocialPayModel(Context context,String num,String type,String money,
                          String name,String idCard,String base,String starTime,String city,
                          String typeName,String individual,String residual,String serviceMoney,
                          String overdel,String accu) {
        super(context);
        isSelect = new ObservableBoolean(true);
        this.num = num;
        this.type = type;
        this.money = money;
        this.name = name;
        this.idCard = idCard;
        this.base = base;
        this.starTime = starTime;
        this.city = city;
        this.typeName = typeName;
        this.individual = individual;
        this.residual = residual;
        this.serviceMoney = serviceMoney;
        this.overdel = overdel;
        this.accu = accu;

    }

    public void onNextClick(View view){
//        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
//        ((Activity)mContext).finish();
    }

    public void onClick(View view){
        if(isSelect.get()){
            aliPay();
        }else {
            weiPay();
        }


    }
    public void onOrderDetialClick(View view){
        OrderTableDetailActivity.JumpToOrder(mContext,num,type,money,name,idCard,base,starTime,city,typeName,
                individual, residual, serviceMoney, overdel,accu);
    }
    public void onAlipyClick(View view){
        isSelect.set(true);
    }

    public void onWechatClick(View view){
        isSelect.set(false);
    }

    public void aliPay(){
        AliPayUse pay = new AliPayUse( mContext, typeName, 0.01, type,num, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
            @Override
            public void SuccessCallBack(String mes) {

                toastShow("支付成功");
                MainActivity.JumpTMain(mContext);
            }

            @Override
            public void failCallBack(String mes) {
                toastShow("支付失败");
            }
        });
        pay.pay();
    }

    public void weiPay(){
        WePayUser.wePay(mContext,num,type,0.01);
    }

    @BindingAdapter("selectType")
    public static void setSelect(ImageView view, boolean isSelect){
        Glide.with(view.getContext().getApplicationContext()).load(isSelect?R.drawable.icon_pay_select:R.drawable.icon_pay_normal)
                .into(view);
    }
}
