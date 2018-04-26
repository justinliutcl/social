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

public class OrderDetialActivity extends BackActivity {
    ActivityOrderDetialBinding bind;
    private static final String ORDER_NUM = "order_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_order_detial);

    }


    public static void JumpOrderDetialActivity(Context context,String orderId){
        Intent intent = new Intent(context,OrderDetialActivity.class);
        intent.putExtra(ORDER_NUM,orderId);
        context.startActivity(intent);
    }
}
