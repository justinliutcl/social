package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ResultConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.InsertServicePayActivity;
import com.justin.social.adapter.InvoicAdapter;
import com.justin.social.databinding.ActivityInvoicBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class InvoicModel extends BaseModel {
    ActivityInvoicBinding bind;
    InvoicAdapter adapter;
    public ObservableBoolean isSelectAll;

    public InvoicModel(Context context) {
        super(context);
        isSelectAll = new ObservableBoolean(false);
    }


    public void onNextClick(View view) {
        if (adapter == null) {
            toastShow("未选择订单");
            return;
        }
        int n = 0;
        for (int i = 0; i < adapter.mDataList.size(); i++) {
            if (adapter.mDataList.get(i).isSelect.get()) {
                n++;
            }
        }
        if (n < 1) {
            toastShow("未选择订单");
            return;
        }
        UserDataObtain.getInstance(mContext).getCurrentUser(new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(final DbUser dbUser) {
                new HttpConfigManager().getInvoicConfig(dbUser.userName, dbUser.userId, getAllOrderNum(), getAllMoney(), new BeanConfigCallBack<ResultConfig>() {
                    @Override
                    public void onDataResponse(ResultConfig bean) {
                        InsertServicePayActivity.JumpToInsertServicePay(mContext, bean.getData().getAllCharge(), bean.getData().orderNum, dbUser.userName, "申请发票", bean.getData().type);

                    }
                });

            }
        });

    }

    public String getAllMoney() {
        double num = 0;
        if (adapter != null) {
            for (int i = 0; i < adapter.mDataList.size(); i++) {
                if (adapter.mDataList.get(i).isSelect.get()) {
                    num += Double.parseDouble(adapter.mDataList.get(i).allCharge);
                }
            }
        }
        return AppUtils.getTwoDecimal(num * 0.05);
    }

    public String getAllOrderNum() {
        String text = "";
        if (adapter != null) {
            for (int i = 0; i < adapter.mDataList.size(); i++) {
                if (adapter.mDataList.get(i).isSelect.get()) {
                    text=text+adapter.mDataList.get(i).orderNum+",";
                }
            }
        }
        if(text.length() == 0)
            return "";
        return text.substring(0,text.length()-1);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_all_layout:
                isSelectAll.set(!isSelectAll.get());
                if (adapter != null) {
                    for (int i = 0; i < adapter.mDataList.size(); i++) {
                        adapter.mDataList.get(i).isSelect.set(isSelectAll.get());
                    }
                }
                break;
        }
    }


    public void initBind(final ActivityInvoicBinding bind) {
        this.bind = bind;
        new HttpConfigManager().getOrderConfig(OrderConfig.INVOIC_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    adapter = new InvoicAdapter(bean.getData(), mContext);
                    bind.list.setAdapter(adapter);
                }

            }
        });
    }
}
