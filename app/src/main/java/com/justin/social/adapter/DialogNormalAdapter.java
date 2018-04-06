package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.databinding.DialogNormalItemBinding;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogNormalAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, String> {

    private List<String> mDataList;
    private Context context;
    DialogUtils.ItemClickBack back;
    public DialogNormalAdapter(List<String> mDataList, Context context,DialogUtils.ItemClickBack back) {
        this.mDataList = mDataList;
        this.context = context;
        this.back = back;
    }

    @Override
    public BaseHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_normal_item, parent, false), this);
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
    public void onbindTo(BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, String model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof DialogNormalItemBinding) {
            ((DialogNormalItemBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
            ((DialogNormalItemBinding) viewDataBindingBaseHolder.mBinding).setCall(back);
        }
    }
}
