package com.justin.social.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.justin.social.R;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.ActivitySettingBinding;

public class SettingActivity extends BackActivity {
    ActivitySettingBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_setting);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.suggest_ll:
                Intent intent = new Intent(this, SetSuggestActivity.class);
                startActivity(intent);
                break;
            case R.id.clear_ll:
                toastShow("清除成功");
                break;
            case R.id.service_ll:
                startActivity(new Intent(this,ServiceRullActivity.class));
                break;
            case R.id.back_button:
                CommonSettingValue.getIns(this).setCurrentPhone(null);
                CommonSettingValue.getIns(this).setCurrentUserID(null);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
