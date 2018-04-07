package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;
import com.justin.social.databinding.DialogNormalItemBinding;
import com.justin.social.databinding.ItemTwoServiceBinding;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class ServiceAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, ServiceConfig> {

    private List<ServiceConfig> mDataList;
    private Context context;
    public ServiceAdapter(List<ServiceConfig> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @Override
    public BaseHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_two_service, parent, false), this);
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
    public void onbindTo(BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, ServiceConfig model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof ItemTwoServiceBinding) {
            ((ItemTwoServiceBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }
    }
}
