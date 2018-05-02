package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.ActivitySettingBinding;
import com.justin.social.databinding.ActivitySettingSuggestBinding;

public class SetSuggestActivity extends BackActivity {
    ActivitySettingSuggestBinding bing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bing = DataBindingUtil.setContentView(this, R.layout.activity_setting_suggest);

    }

    public void onClick(View view) {
        String editText = bing.edit.getText().toString();
        if(!editText.isEmpty()){
            new HttpConfigManager().getSendAdviceConfig(editText, CommonSettingValue.getIns(this).getCurrentPhone(),
                    CommonSettingValue.getIns(this).getCurrentUserId(), new BeanConfigCallBack<BaseConfig>() {
                        @Override
                        public void onDataResponse(BaseConfig bean) {
                            if(bean.isSuccess()){
                                toastShow("上传成功");
                            }
                        }
                    });
        }else {
            toastShow("请输入反馈内容");
        }

    }
}
