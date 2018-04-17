package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.databinding.DialogNormalItemBinding;
import com.justin.social.databinding.ItemInsertServiceBinding;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class InsertServiceAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, ServiceAddConfig> {

    private List<ServiceAddConfig> mDataList;
    private Context context;
    public InsertServiceAdapter(List<ServiceAddConfig> mDataList, Context context ) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @Override
    public BaseHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_insert_service, parent, false), this);
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
    public void onbindTo(BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, ServiceAddConfig model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof ItemInsertServiceBinding) {
            ((ItemInsertServiceBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }
    }
}
