package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityUserMessageBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.five.UserModel;
import com.justin.social.model.one.WritePeopleModel;

public class UserMessageActivity extends BackActivity {
    ActivityUserMessageBinding bind;
    UserModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_user_message);
        model = new UserModel(this);
        model.setData(bind);

        bind.setModel(model);
    }

}
