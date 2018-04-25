package com.justin.social.model.tab;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.OrderListActivity;
import com.justin.social.model.base.BaseModel;

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
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nopay_ll:
                OrderListActivity.JumpToOrderActivity(mContext,noPayConfig,havePayConfig,durPayConfig,allPayConfig,0);
                break;
            case R.id.havepay_ll:
                OrderListActivity.JumpToOrderActivity(mContext,noPayConfig,havePayConfig,durPayConfig,allPayConfig,1);
                break;
            case R.id.durpay_ll:
                OrderListActivity.JumpToOrderActivity(mContext,noPayConfig,havePayConfig,durPayConfig,allPayConfig,2);
                break;
            case R.id.allpay_ll:
                OrderListActivity.JumpToOrderActivity(mContext,noPayConfig,havePayConfig,durPayConfig,allPayConfig,3);
                break;
        }
    }

    public void initAllOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.ALL_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    allOrderConfig = bean;
                    isShowAllPay.set(true);
                    allPayConfig = bean;
                    allPay.set(allOrderConfig.getData().size() + "");
                }

            }
        });
    }

    public void initDurOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.DURPAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    isShowPayDur.set(true);
                    durPayConfig = bean;
                    PayDur.set(bean.getData().size() + "");
                }
            }
        });
    }

    public void initHaveOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.HAVE_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    isShowHavePay.set(true);
                    havePayConfig = bean;
                    havePay.set(bean.getData().size() + "");
                }
            }
        });
    }

    public void initNoPayOrder() {
        new HttpConfigManager().getOrderConfig(OrderConfig.NO_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    isShowNoPay.set(true);
                    noPayConfig = bean;
                    noPay.set(bean.getData().size() + "");
                }
            }
        });
    }


}
