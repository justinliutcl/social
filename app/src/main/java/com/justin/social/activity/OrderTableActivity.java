package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.one.OrderTableModel;
import com.justin.social.model.one.WritePeopleModel;

public class OrderTableActivity extends BackActivity {
    ActivityOrderTableBinding bind;
    private OrderTableModel model;
    public  static final String TYPE = "type";
    public  static final String MIN = "min";
    public  static final String MAX = "max";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_order_table);
        model = new OrderTableModel(this);
        model.initType(getIntent().getBooleanExtra(TYPE,false),
                getIntent().getStringExtra(MIN),
                getIntent().getStringExtra(MAX));
        model.initBind(bind);
        bind.setModel(model);
    }

    public static void JumpToOrder(Context context,boolean isAcc,String min,String max){
        Intent intent = new Intent(context,OrderTableActivity.class);
        intent.putExtra(TYPE,isAcc);
        intent.putExtra(MIN,min);
        intent.putExtra(MAX,max);
        context.startActivity(intent);
    }
}
