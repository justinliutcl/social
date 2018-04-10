package com.justin.social.model.tab;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/4/8.
 */

public class FiveModel extends BaseModel {
    public ObservableField<String>headImage;
    public FiveModel(Context context) {
        super(context);
        headImage = new ObservableField<>("");
    }

    public void onClick(View view){

    }
}
