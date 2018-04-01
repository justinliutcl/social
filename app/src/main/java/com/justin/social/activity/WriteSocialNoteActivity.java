package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.one.WritePeopleModel;

public class WriteSocialNoteActivity extends BackActivity {
    ActivityWriteSocialNoteBinding bind;
    WritePeopleModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_write_social_note);
        model = new WritePeopleModel(this);
        bind.setModel(model);
    }
}
