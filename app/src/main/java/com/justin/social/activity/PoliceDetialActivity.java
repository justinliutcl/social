package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.databinding.ActivityPoliceDetialBinding;
import com.justin.social.model.one.OrderTableModel;

public class PoliceDetialActivity extends BackActivity {
    public static final int HOURSE_TYPE     = 0;
    public static final int CAR_TYPE        = 1;
    public static final int SCHOOL_TYPE     = 2;
    public static final int LOCIAL_TYPE     = 3;
    public static final String      TYPE    = "detial_type";
    ActivityPoliceDetialBinding bind;
    private OrderTableModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_police_detial);

    }

    private void initContent(int type){
        String titleBar="";
        String title="";
        String top="";
        String content="";
        switch (type){
            case HOURSE_TYPE:
                titleBar = getString(R.string.police_hourse_title_bar);
                title = getString(R.string.police_hourse_title);
                top = getString(R.string.police_hourse_top);
                content = getString(R.string.police_hourse_content);
                break;
            case CAR_TYPE:
                break;
            case SCHOOL_TYPE:
                break;
            case LOCIAL_TYPE:
                break;
        }
        bind.title.setText(titleBar);
        bind.title.setText(titleBar);
        bind.title.setText(titleBar);
        bind.title.setText(titleBar);
    }

    public static void jumpToPoliceDetial(Context context,int type){
        Intent intent = new Intent(context,PoliceDetialActivity.class);
        intent.putExtra(TYPE,type);
        context.startActivity(intent);
    }
}
