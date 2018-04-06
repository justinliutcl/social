package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.justin.social.activity.SocialPayActivity;
import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/3/31.
 */

public class SocialPayModel extends BaseModel {
    public ObservableBoolean isSelect;

    public SocialPayModel(Context context) {
        super(context);
        isSelect = new ObservableBoolean();
    }

    public void onNextClick(View view){
//        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
//        ((Activity)mContext).finish();
    }
}
