package com.justin.social.model.dialog;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.YouhuijuanConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.adapter.DialogNormalAdapter;
import com.justin.social.adapter.DialogYouhuiJuanAdapter;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.databinding.DialogYouhuijuanBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogYouhuiJuanModel extends BaseModel{

    public ObservableField<String>title;
    public DialogYouhuiJuanAdapter adapter;
    private DialogYouhuijuanBinding bind;

    public DialogYouhuiJuanModel(Context context) {
        super(context);
        title = new ObservableField<>("");
    }

    public void initTitle(String title){
        this.title.set(title);
    }

    public void initBind(DialogYouhuijuanBinding binding){
        this.bind = binding;
        binding.list.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }
    public void onCancleClick(View view){
        DialogUtils.getDialogUtilInstance().dismiss();
    }

    public void initAdapter(final DialogUtils.ItemClickBack back) {
        UserDataObtain.getInstance(mContext).getCurrentUser(new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                new HttpConfigManager().getYouhuiJuanConfig(dbUser.getUserId(), new BeanConfigCallBack<YouhuijuanConfig>() {
                    @Override
                    public void onDataResponse(YouhuijuanConfig bean) {
                        adapter = new DialogYouhuiJuanAdapter(bean.getData(),mContext,back);
                        bind.list.setAdapter(adapter);
                    }
                });

            }
        });

    }
}
