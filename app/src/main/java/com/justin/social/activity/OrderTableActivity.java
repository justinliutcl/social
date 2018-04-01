package com.justin.social.activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_order_table);
        model = new OrderTableModel(this);
        bind.setModel(model);
    }
}
