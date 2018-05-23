package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.databinding.ActivitySocialPayBinding;
import com.justin.social.model.one.OrderTableModel;
import com.justin.social.model.one.SocialPayModel;

public class SocialPayActivity extends BackActivity {
    ActivitySocialPayBinding bind;
    private SocialPayModel model;

    public static final String ORDER_NUM        = "order_num";
    public static final String ORDER_TYPE       = "order_type";
    public static final String ORDER_MONEY      = "order_money";
    public static final String ORDER_name       = "order_name";
    public static final String ORDER_idCard     = "order_idcard";
    public static final String ORDER_base       = "order_base";
    public static final String ORDER_starTime   = "order_starTime";
    public static final String ORDER_city       = "order_city";
    public static final String ORDER_typeName   = "order_typeName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_social_pay);
        Intent intent = getIntent();
        model = new SocialPayModel(this,intent.getStringExtra(ORDER_NUM),
                intent.getStringExtra(ORDER_TYPE),
                intent.getStringExtra(ORDER_MONEY),
                intent.getStringExtra(ORDER_name),
                intent.getStringExtra(ORDER_idCard),
                intent.getStringExtra(ORDER_base),
                intent.getStringExtra(ORDER_starTime),
                intent.getStringExtra(ORDER_city),
                intent.getStringExtra(ORDER_typeName));
        bind.setModel(model);
    }

    public static void JumpToSocialPay(Context context,String orderNum,String type,String money,
                                       String name,String idCard,String base,String starTime,String city,
                                       String typeName){
        Intent intent = new Intent(context,SocialPayActivity.class);
        intent.putExtra(ORDER_NUM, orderNum);
        intent.putExtra(ORDER_TYPE, type);
        intent.putExtra(ORDER_MONEY, money);
        intent.putExtra(ORDER_name, name);
        intent.putExtra(ORDER_idCard, idCard);
        intent.putExtra(ORDER_base, base);
        intent.putExtra(ORDER_starTime, starTime);
        intent.putExtra(ORDER_city, city);
        intent.putExtra(ORDER_typeName, typeName);
        context.startActivity(intent);
    }


}
