package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.five.YouhuijuanConfig;
import com.justin.social.databinding.DialogNormalItemBinding;
import com.justin.social.databinding.DialogYouhuijuanBinding;
import com.justin.social.databinding.DialogYouhuijuanItemBinding;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogYouhuiJuanAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, YouhuijuanConfig> {

    public List<YouhuijuanConfig> mDataList;
    private Context context;
    DialogUtils.ItemYouhuijuanClickBack back;
    public DialogYouhuiJuanAdapter(List<YouhuijuanConfig> mDataList, Context context, DialogUtils.ItemYouhuijuanClickBack back) {
        this.mDataList = mDataList;
        this.context = context;
        this.back = back;
    }

    @Override
    public BaseHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_youhuijuan_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(BaseHolder<ViewDataBinding> holder, int position) {
        holder.bindTo(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onbindTo(BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, YouhuijuanConfig model, final int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof DialogYouhuijuanItemBinding) {
            ((DialogYouhuijuanItemBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
            ((DialogYouhuijuanItemBinding) viewDataBindingBaseHolder.mBinding).content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<mDataList.size();i++){
                        if(i == position){
                            mDataList.get(i).isSelect.set(true);
                        }else{
                            mDataList.get(i).isSelect.set(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
