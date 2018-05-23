package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityInvoicBinding;
import com.justin.social.model.one.InvoicModel;

public class InvoicActivity extends BackActivity {
    ActivityInvoicBinding bind;
    private InvoicModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_invoic);
        model = new InvoicModel(this);
        model.initBind(bind);
        bind.setModel(model);
    }

}
