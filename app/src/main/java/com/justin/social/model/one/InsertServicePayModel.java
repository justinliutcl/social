package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.Toast;

import com.justin.social.MainActivity;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.ContentKey;
import com.justin.social.wxapi.WePayUser;

/**
 * Created by ASUS on 2018/3/31.
 */

public class InsertServicePayModel extends BaseModel {
    public ObservableBoolean isSelect;
    public String sumMoney;
    public String orderNumber;
    public String name;
    public String serviceType;
    public String payMoney;
    public String type;
    public InsertServicePayModel(Context context) {
        super(context);
        isSelect = new ObservableBoolean(true);
    }

    public void initDes(String sumMoney, String orderNumber, String name, String serviceType, String payMoney,String type){
        this.sumMoney=sumMoney;
        this.orderNumber=orderNumber;
        this.name=name;
        this.serviceType=serviceType;
        this.payMoney=payMoney;
        this.type=type;
    }

    public void onNextClick(View view){
        if(isSelect.get()){
            aliPay();
        }else {
            weiPay();
        }
    }

    public void pay(){
        onNextClick(null);
    }

    public void aliPay(){
        AliPayUse pay = new AliPayUse( mContext, serviceType, Double.parseDouble(payMoney), type,orderNumber, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
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
        WePayUser.wePay(mContext,orderNumber,type,Double.parseDouble(payMoney),"0");
    }

    public void onAlipyClick(View view){
        isSelect.set(true);
    }

    public void onWechatClick(View view){
        isSelect.set(false);
    }
}
