package com.justin.social.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Justinliu on 2017/12/6.
 */

public abstract class BaseHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public T mBinding;

    protected OnBindDataListener listener;

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindTo(Object bean,int position);

    public BaseHolder(T binding, OnBindDataListener listener) {
        super(binding.getRoot());
        mBinding = binding;
        this.listener = listener;
    }

}