package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityInsertServiceBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.one.InsertServiceModel;
import com.justin.social.model.one.WritePeopleModel;

public class InsertServiceActivity extends BackActivity {
    public static final int DEFAULT = 0;
    public static final int REPAIR = 1;
    public static final int FILE = 2;
    ActivityInsertServiceBinding bind;
    InsertServiceModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_insert_service);
        model = new InsertServiceModel(this);


        model.type = getIntent().getIntExtra(OrderTableActivity.TYPE,0);
        bind.setModel(model);
        model.setData(bind);
    }

    public static void JumpInsertServiceActivity(Context context ,int isA){
        Intent intent = new Intent(context,InsertServiceActivity.class);
        intent.putExtra(OrderTableActivity.TYPE,isA);
        context.startActivity(intent);
    }
}
