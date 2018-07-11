package com.justin.social.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.justin.social.R;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.ActivitySettingBinding;
import com.justin.social.databinding.ActivityZizhiBinding;
import com.justin.social.utils.DimensUtils;

public class ZizhiActivity extends BackActivity {
    ActivityZizhiBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_zizhi);
        bind.img1.getLayoutParams().height = (int) (DimensUtils.getScreenWidthHeight(this)[0]*1.7);
        bind.img2.getLayoutParams().height = (int) (DimensUtils.getScreenWidthHeight(this)[0]*0.75);
        bind.img3.getLayoutParams().height = (int) (DimensUtils.getScreenWidthHeight(this)[0]*0.75);
    }

}
