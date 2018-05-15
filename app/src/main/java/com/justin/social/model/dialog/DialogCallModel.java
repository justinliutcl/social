package com.justin.social.model.dialog;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.R;
import com.justin.social.adapter.DialogNormalAdapter;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogCallModel extends BaseModel {

    public ObservableField<String> title;
    private DialogDuringBinding bind;

    public ObservableField<String> phone;
    public ObservableField<String> email;
    public ObservableField<String> local;

    public DialogCallModel(Context context) {
        super(context);
        title = new ObservableField<>("");
        phone = new ObservableField<>("");
        email = new ObservableField<>("");
        local = new ObservableField<>("");
    }

    public void initTitle(String title) {
        this.title.set(title);
    }

    public void initBind(DialogDuringBinding binding) {
        this.bind = binding;
        binding.list.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    public void onCancleClick(View view) {
        DialogUtils.getDialogUtilInstance().dismiss();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                AppUtils.diallPhone(phone.get(),mContext);
                break;
            case R.id.email:
                AppUtils.copyToClip(email.get(),mContext);
                break;
        }
    }
}
