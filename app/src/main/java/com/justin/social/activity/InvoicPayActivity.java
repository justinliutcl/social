package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.ActivityInsertServicePayBinding;
import com.justin.social.databinding.ActivityInvoicPayBinding;
import com.justin.social.model.one.InsertServicePayModel;

public class InvoicPayActivity extends BackActivity {
    ActivityInvoicPayBinding bind;
    private InsertServicePayModel model;
    public static final String ORDER_MONEY = "orderMoney";
    public static final String ORDER_NUM = "orderNum";
    public static final String NAME = "name";
    public static final String SERVICE_TYPE = "serviceType";
    public static final String ORDER_TYPE = "type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_invoic_pay);
        model = new InsertServicePayModel(this);
        Intent intent = getIntent();
        model.initDes(intent.getStringExtra(ORDER_MONEY),intent.getStringExtra(ORDER_NUM),
                intent.getStringExtra(NAME),intent.getStringExtra(SERVICE_TYPE),intent.getStringExtra(ORDER_MONEY)
                ,intent.getStringExtra(ORDER_TYPE));
        bind.setModel(model);
        bind.sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind.sendTv.setClickable(false);
                String address = bind.addressEt.getText().toString();
                if(address.isEmpty())
                    address = "自取";
                new HttpConfigManager().getSendAddressConfig(model.orderNumber, address, new BeanConfigCallBack<BaseConfig>() {
                    @Override
                    public void onDataResponse(BaseConfig bean) {
                        bind.sendTv.setClickable(true);
                        model.pay();
                    }
                });
            }
        });
    }

    public static void JumpToInsertServicePay(Context context,String orderMoney,
                                              String orderNum,String name,String serviceType,String type){
        Intent intent = new Intent(context,InvoicPayActivity.class);
        intent.putExtra(ORDER_MONEY,orderMoney);
        intent.putExtra(ORDER_NUM,orderNum);
        intent.putExtra(NAME,name);
        intent.putExtra(SERVICE_TYPE,serviceType);
        intent.putExtra(ORDER_TYPE,type);
        context.startActivity(intent);

    }
}
