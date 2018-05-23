package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.Toast;

import com.justin.social.alipay2.AliPayUse;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.ContentKey;

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
        isSelect = new ObservableBoolean();
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
        AliPayUse pay = new AliPayUse( view.getContext(), serviceType, 0.01, type, orderNumber, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
            @Override
            public void SuccessCallBack(String mes) {
                Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failCallBack(String mes) {
                Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
            }
        });
        pay.pay();
    }
}
