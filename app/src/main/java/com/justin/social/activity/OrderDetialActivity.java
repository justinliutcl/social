package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.databinding.ActivityFindFriendBinding;
import com.justin.social.databinding.ActivityOrderDetialBinding;
import com.justin.social.model.five.OrderDetialModel;

public class OrderDetialActivity extends BackActivity {
    ActivityOrderDetialBinding bind;
    private static final String ORDER_NUM = "order_num";
    private static final String ORDER_type = "order_type";
    OrderDetialModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_order_detial);
        model = new OrderDetialModel(this,getIntent().getStringExtra(ORDER_NUM),getIntent().getStringExtra(ORDER_type));
        model.setData(bind);
        bind.setModel(model);
    }


    public static void JumpOrderDetialActivity(Context context,String orderId,String orderType){
        Intent intent = new Intent(context,OrderDetialActivity.class);
        intent.putExtra(ORDER_NUM,orderId);
        intent.putExtra(ORDER_type,orderType);
        context.startActivity(intent);
    }
}
