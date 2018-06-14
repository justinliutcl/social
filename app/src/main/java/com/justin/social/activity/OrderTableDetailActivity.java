package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOrderDetialTableBinding;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.model.five.OrderDetialModel;
import com.justin.social.model.one.OrderTableDetialModel;
import com.justin.social.model.one.OrderTableModel;

public class OrderTableDetailActivity extends BackActivity {
    ActivityOrderDetialTableBinding bind;
    private OrderTableDetialModel model;
    public static final String ORDER_NUM        = "order_num";
    public static final String ORDER_TYPE       = "order_type";
    public static final String ORDER_MONEY      = "order_money";
    public static final String ORDER_name       = "order_name";
    public static final String ORDER_idCard     = "order_idcard";
    public static final String ORDER_base       = "order_base";
    public static final String ORDER_starTime   = "order_starTime";
    public static final String ORDER_city       = "order_city";
    public static final String ORDER_typeName   = "order_typeName";
    public static final String ORDER_individual     = "order_individual";
    public static final String ORDER_residual       = "order_residual";
    public static final String ORDER_serviceMoney   = "order_serviceMoney";
    public static final String ORDER_overdel        = "order_overdel";
    public static final String ORDER_ACCU           = "order_accu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_order_detial_table);
        Intent intent = getIntent();
        model = new OrderTableDetialModel(this,intent.getStringExtra(ORDER_NUM),
                intent.getStringExtra(ORDER_TYPE),
                intent.getStringExtra(ORDER_MONEY),
                intent.getStringExtra(ORDER_name),
                intent.getStringExtra(ORDER_idCard),
                intent.getStringExtra(ORDER_base),
                intent.getStringExtra(ORDER_starTime),
                intent.getStringExtra(ORDER_city),
                intent.getStringExtra(ORDER_typeName),
                intent.getStringExtra(ORDER_individual),
                intent.getStringExtra(ORDER_residual),
                intent.getStringExtra(ORDER_serviceMoney),
                intent.getStringExtra(ORDER_overdel),
                intent.getStringExtra(ORDER_ACCU));

        model.initBind(bind);
        bind.setModel(model);
    }

    public static void JumpToOrder(Context context,String orderNum,String type,String money,
                                   String name,String idCard,String base,String starTime,String city,
                                   String typeName,String individual,String residual,String serviceMoney,
                                   String overdel,String accu){
        Intent intent = new Intent(context,OrderTableDetailActivity.class);
        intent.putExtra(ORDER_NUM, orderNum);
        intent.putExtra(ORDER_TYPE, type);
        intent.putExtra(ORDER_MONEY, money);
        intent.putExtra(ORDER_name, name);
        intent.putExtra(ORDER_idCard, idCard);
        intent.putExtra(ORDER_base, base);
        intent.putExtra(ORDER_starTime, starTime);
        intent.putExtra(ORDER_city, city);
        intent.putExtra(ORDER_typeName, typeName);
        intent.putExtra(ORDER_individual, individual);
        intent.putExtra(ORDER_residual, residual);
        intent.putExtra(ORDER_serviceMoney, serviceMoney);
        intent.putExtra(ORDER_overdel, overdel);
        intent.putExtra(ORDER_ACCU, accu);
        context.startActivity(intent);
    }
}
