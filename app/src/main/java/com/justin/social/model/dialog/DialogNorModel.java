package com.justin.social.model.dialog;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.adapter.DialogNormalAdapter;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogNorModel extends BaseModel{

    public ObservableField<String>title;
    public DialogNormalAdapter adapter;
    private DialogDuringBinding bind;

    public DialogNorModel(Context context) {
        super(context);
        title = new ObservableField<>("");
    }

    public void initTitle(String title){
        this.title.set(title);
    }

    public void initBind(DialogDuringBinding binding){
        this.bind = binding;
        binding.list.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }
    public void onCancleClick(View view){
        DialogUtils.getDialogUtilInstance().dismiss();
    }

    public void initAdapter(List<String>list,DialogUtils.ItemClickBack back) {
        adapter = new DialogNormalAdapter(list,mContext,back);
        bind.list.setAdapter(adapter);
    }
}
