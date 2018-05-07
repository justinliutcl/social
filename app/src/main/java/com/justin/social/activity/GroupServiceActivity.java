package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityGroupServiceBinding;
import com.justin.social.model.one.GroupServiceModel;

public class GroupServiceActivity extends BackActivity {
    ActivityGroupServiceBinding bind;
    GroupServiceModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_group_service);
        model = new GroupServiceModel(this);
        bind.setModel(model);
    }

}
