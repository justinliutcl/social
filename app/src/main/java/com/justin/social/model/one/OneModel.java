package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableField;

import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.model.base.BaseModel;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class OneModel extends BaseModel {
    FragmentOneBinding mBinding;
    public OneModel(Context context) {
        super(context);
        socialPeople = new ObservableField<>("title");
    }
    public ObservableField<String> socialPeople;

    public void init(FragmentOneBinding mBinding) {
        this.mBinding = mBinding;
    }
}
