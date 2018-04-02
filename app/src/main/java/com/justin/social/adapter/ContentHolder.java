package com.justin.social.adapter;

import android.databinding.ViewDataBinding;

/**
 * Created by Justinliu on 2017/12/7.
 */

public class ContentHolder<T extends ViewDataBinding>  extends BaseHolder<T>  {

    public ContentHolder(T binding, OnBindDataListener listener) {
        super(binding.getRoot());
        mBinding = binding;
        this.listener = listener;
    }


    @Override
    public void bindTo(Object model,int position) {
        if(listener!=null)
            listener.onbindTo(this,model,position);
        mBinding.executePendingBindings();
    }

}
