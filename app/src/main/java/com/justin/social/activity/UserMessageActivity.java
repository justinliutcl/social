package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.one.WritePeopleModel;

public class UserMessageActivity extends BackActivity {
    ActivityWriteSocialNoteBinding bind;
    WritePeopleModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_write_social_note);
        model = new WritePeopleModel(this);
        model.setData(bind);

        model.isAccu = getIntent().getIntExtra(OrderTableActivity.TYPE,0)==1;
        model.type = getIntent().getIntExtra(OrderTableActivity.TYPE,0);
        bind.setModel(model);
        model.getCity();
    }

    public static void JumpWriteSocial(Context context ,int isA){
        Intent intent = new Intent(context,UserMessageActivity.class);
        intent.putExtra(OrderTableActivity.TYPE,isA);
        context.startActivity(intent);
    }
}
