package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityInsertServicePayBinding;
import com.justin.social.databinding.ActivitySocialPayBinding;
import com.justin.social.model.one.InsertServicePayModel;
import com.justin.social.model.one.SocialPayModel;

public class InsertServicePayActivity extends BackActivity {
    ActivityInsertServicePayBinding bind;
    private InsertServicePayModel model;
    public static final String ORDER_MONEY = "orderMoney";
    public static final String ORDER_NUM = "orderNum";
    public static final String NAME = "name";
    public static final String SERVICE_TYPE = "serviceType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_insert_service_pay);
        model = new InsertServicePayModel(this);
        Intent intent = getIntent();
        model.initDes(intent.getStringExtra(ORDER_MONEY),intent.getStringExtra(ORDER_NUM),
                intent.getStringExtra(NAME),intent.getStringExtra(SERVICE_TYPE),intent.getStringExtra(ORDER_MONEY));
        bind.setModel(model);
    }

    public static void JumpToInsertServicePay(Context context,String orderMoney,
                                              String orderNum,String name,String serviceType){
        Intent intent = new Intent(context,InsertServicePayActivity.class);
        intent.putExtra(ORDER_MONEY,orderMoney);
        intent.putExtra(ORDER_NUM,orderNum);
        intent.putExtra(NAME,name);
        intent.putExtra(SERVICE_TYPE,serviceType);
        context.startActivity(intent);

    }
}
