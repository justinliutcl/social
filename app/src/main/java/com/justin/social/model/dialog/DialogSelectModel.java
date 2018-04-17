package com.justin.social.model.dialog;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.adapter.DialogNormalAdapter;
import com.justin.social.adapter.DialogSelectAdapter;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.databinding.DialogServiceAddBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogSelectModel extends BaseModel{

    public ObservableField<String>title;
    public DialogSelectAdapter adapter;
    private DialogServiceAddBinding bind;
    DialogUtils.ItemObjectClickBack<List<ServiceAddConfig>> back;
    public DialogSelectModel(Context context) {
        super(context);
        title = new ObservableField<>("");
    }

    public void initTitle(String title){
        this.title.set(title);
    }

    public void initBind(DialogServiceAddBinding binding){
        this.bind = binding;
        binding.list.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }
    public void onCancleClick(View view){
        DialogUtils.getDialogUtilInstance().dismiss();
    }

    public void onSureClick(View view) {
        if (adapter != null && adapter.getmDataList() != null && !adapter.getmDataList().isEmpty()) {
            List<ServiceAddConfig> list = adapter.getmDataList();
            List<ServiceAddConfig> list2 = new ArrayList<>();
            for(ServiceAddConfig config:list){
                if(config.isSelect.get())
                    list2.add(config);
            }
            if(back!=null)
                back.onBack(list2);
        }
        DialogUtils.getDialogUtilInstance().dismiss();
    }

    public void initAdapter(List<ServiceAddConfig>list, DialogUtils.ItemObjectClickBack<List<ServiceAddConfig>> back) {
        for(int i=0;i<list.size();i++){
            list.get(i).isSelect = new ObservableBoolean(false);
        }
        this.back = back;
        adapter = new DialogSelectAdapter(list,mContext,back);
        bind.list.setAdapter(adapter);
    }
}
