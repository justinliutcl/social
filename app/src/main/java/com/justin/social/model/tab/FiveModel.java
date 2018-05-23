package com.justin.social.model.tab;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderNumConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.FindFriendActivity;
import com.justin.social.activity.OnlineServiceActivity;
import com.justin.social.activity.OrderListActivity;
import com.justin.social.activity.SettingActivity;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/4/8.
 */

public class FiveModel extends BaseModel {
    public ObservableField<String> headImage;
    public ObservableField<String> name;
    public ObservableField<String> noPay;
    public ObservableField<String> havePay;
    public ObservableField<String> PayDur;
    public ObservableField<String> allPay;
    public ObservableBoolean isShowNoPay;
    public ObservableBoolean isShowHavePay;
    public ObservableBoolean isShowPayDur;
    public ObservableBoolean isShowAllPay;

    private OrderConfig noPayConfig;
    private OrderConfig havePayConfig;
    private OrderConfig durPayConfig;
    private OrderConfig allPayConfig;

    public OrderConfig allOrderConfig;

    public FiveModel(Context context) {
        super(context);
        headImage = new ObservableField<>("");
        name = new ObservableField<>("");

        noPay = new ObservableField<>("");
        havePay = new ObservableField<>("");
        PayDur = new ObservableField<>("");
        allPay = new ObservableField<>("");

        isShowNoPay = new ObservableBoolean(false);
        isShowHavePay = new ObservableBoolean(false);
        isShowPayDur = new ObservableBoolean(false);
        isShowAllPay = new ObservableBoolean(false);

        initAllOrder();
        initDurOrder();
        initHaveOrder();
        initNoPayOrder();
        initOrderNum();
    }

    private void initOrderNum() {
        new HttpConfigManager().getOrderNumConfig(CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderNumConfig>() {
            @Override
            public void onDataResponse(OrderNumConfig bean) {
                if(bean.getData().getOrderNoapply().equals("0")){
                    isShowNoPay.set(false);
                }else{
                    isShowNoPay.set(true);
                    noPay.set(bean.getData().getOrderNoapply());
                }
                if(bean.getData().getOrderApply().equals("0")){
                    isShowHavePay.set(false);
                }else{
                    isShowHavePay.set(true);
                    havePay.set(bean.getData().getOrderApply());
                }
                if(bean.getData().getOrderApply().equals("0")){
                    isShowPayDur.set(false);
                }else{
                    isShowPayDur.set(true);
                    PayDur.set(bean.getData().getOrderApply());
                }
                if(bean.getData().getOrderAll().equals("0")){
                    isShowAllPay.set(false);
                }else{
                    isShowAllPay.set(true);
                    allPay.set(bean.getData().getOrderAll());
                }
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nopay_ll:
                OrderListActivity.JumpToOrderActivity(mContext, noPayConfig, havePayConfig, durPayConfig, allPayConfig, 0);
                break;
            case R.id.havepay_ll:
                OrderListActivity.JumpToOrderActivity(mContext, noPayConfig, havePayConfig, durPayConfig, allPayConfig, 1);
                break;
            case R.id.durpay_ll:
                OrderListActivity.JumpToOrderActivity(mContext, noPayConfig, havePayConfig, durPayConfig, allPayConfig, 2);
                break;
            case R.id.allpay_ll:
                OrderListActivity.JumpToOrderActivity(mContext, noPayConfig, havePayConfig, durPayConfig, allPayConfig, 3);
                break;
            case R.id.setting:
                Intent intent = new Intent(mContext, SettingActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.call_us_ll:
                DialogUtils.getDialogUtilInstance().showCallUsDialog(mContext);
                break;
            case R.id.online_service_ll:
                mContext.startActivity(new Intent(mContext, OnlineServiceActivity.class));
                break;
            case R.id.idfriend_ll:
                mContext.startActivity(new Intent(mContext, FindFriendActivity.class));
                break;
        }
    }

    public void initAllOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.ALL_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    allOrderConfig = bean;
                    allPayConfig = bean;

                }

            }
        });
    }

    public void initDurOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.DURPAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    durPayConfig = bean;

                }
            }
        });
    }

    public void initHaveOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.HAVE_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    havePayConfig = bean;

                }
            }
        });
    }

    public void initNoPayOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.NO_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    noPayConfig = bean;

                }
            }
        });
    }


}
