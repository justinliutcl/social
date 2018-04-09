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
    public  static final String TYPE            = "type";
    public  static final String MIN             = "min";
    public  static final String MAX             = "max";
    public  static final String MINACCU         = "min_accu";
    public  static final String MAXACCU         = "max_accu";
    public  static final String CITY            = "city";
    public  static final String HOURSE_TYPE     = "hourse_type";
    public static final int SOCIAL_TYPE         = 0;
    public static final int ACCU_TYPE           = 1;
    public static final int FIVECOUNT_TYPE      = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_order_table);
        model = new OrderTableModel(this);
        int t = getIntent().getIntExtra(TYPE,0);
        if(t == FIVECOUNT_TYPE){
            model.initType(
                    getIntent().getStringExtra(MIN),
                    getIntent().getStringExtra(MAX),
                    getIntent().getStringExtra(MINACCU),
                    getIntent().getStringExtra(MAXACCU),
                    getIntent().getStringExtra(CITY),
                    getIntent().getStringExtra(HOURSE_TYPE)
                    );
        } else {
            model.initType(t != SOCIAL_TYPE,
                    getIntent().getStringExtra(MIN),
                    getIntent().getStringExtra(MAX),
                    getIntent().getStringExtra(CITY),
                    getIntent().getStringExtra(HOURSE_TYPE)
                    );
        }

        model.initBind(bind);
        bind.setModel(model);
        bind.baseText.setText(model.defaultBase);
        if(t == FIVECOUNT_TYPE)
            bind.fiveAccuText.setText(model.defaultFiveBase);
    }

    public static void JumpToOrder(Context context,int orderType,String min,String max,String city,String hourseType){
        Intent intent = new Intent(context,OrderTableActivity.class);
        intent.putExtra(TYPE,orderType);
        intent.putExtra(MIN,min);
        intent.putExtra(MAX,max);
        intent.putExtra(CITY,city);
        intent.putExtra(HOURSE_TYPE,hourseType);
        context.startActivity(intent);
    }
    public static void JumpToOrder(Context context,int orderType,String min,String max,String minAccu,String maxAccu,String city,String hourseType){
        Intent intent = new Intent(context,OrderTableActivity.class);
        intent.putExtra(TYPE,orderType);
        intent.putExtra(MIN,min);
        intent.putExtra(MAX,max);
        intent.putExtra(MINACCU,minAccu);
        intent.putExtra(MAXACCU,maxAccu);
        intent.putExtra(CITY,city);
        intent.putExtra(HOURSE_TYPE,hourseType);
        context.startActivity(intent);
    }
}
