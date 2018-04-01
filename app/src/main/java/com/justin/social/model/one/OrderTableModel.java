package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.justin.social.activity.OrderTableActivity;
import com.justin.social.activity.SocialPayActivity;
import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/3/31.
 */

public class OrderTableModel extends BaseModel {
    public OrderTableModel(Context context) {
        super(context);
    }

    public void onNextClick(View view){
        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
        ((Activity)mContext).finish();
    }
}
