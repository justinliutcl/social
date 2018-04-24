package com.justin.social.model.tab;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/4/8.
 */

public class FiveModel extends BaseModel {
    public ObservableField<String>headImage;
    public ObservableField<String>name;
    public ObservableField<String>noPay;
    public ObservableField<String>havePay;
    public ObservableField<String>PayDur;
    public ObservableField<String>allPay;
    public ObservableBoolean isShowNoPay;
    public ObservableBoolean isShowHavePay;
    public ObservableBoolean isShowPayDur;
    public ObservableBoolean isShowAllPay;
    public FiveModel(Context context) {
        super(context);
        headImage = new ObservableField<>("");
        headImage = new ObservableField<>("");

        noPay = new ObservableField<>("");
        havePay = new ObservableField<>("");
        PayDur = new ObservableField<>("");
        allPay = new ObservableField<>("");

        isShowNoPay = new ObservableBoolean(false);
        isShowHavePay = new ObservableBoolean(false);
        isShowPayDur = new ObservableBoolean(false);
        isShowAllPay = new ObservableBoolean(false);
    }

    public void onClick(View view){

    }

    public void initOrder(){
        new HttpConfigManager().getOrderConfig(OrderConfig.ALL_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {

            }
        });
    }
}
