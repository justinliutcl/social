package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.justin.social.activity.OrderTableActivity;
import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/3/31.
 */

public class WritePeopleModel extends BaseModel {
    public WritePeopleModel(Context context) {
        super(context);
    }

    public void onNextClick(View view){
        mContext.startActivity(new Intent(mContext, OrderTableActivity.class));
        ((Activity)mContext).finish();
    }
}
