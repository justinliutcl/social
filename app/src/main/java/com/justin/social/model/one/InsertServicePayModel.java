package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.justin.social.model.base.BaseModel;

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
    public InsertServicePayModel(Context context) {
        super(context);
        isSelect = new ObservableBoolean();
    }

    public void initDes(String sumMoney, String orderNumber, String name, String serviceType, String payMoney){
        this.sumMoney=sumMoney;
        this.orderNumber=orderNumber;
        this.name=name;
        this.serviceType=serviceType;
        this.payMoney=payMoney;
    }

    public void onNextClick(View view){
//        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
//        ((Activity)mContext).finish();
    }
}
