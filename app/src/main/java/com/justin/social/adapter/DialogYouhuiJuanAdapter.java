package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
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

public class DialogYouhuiJuanAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, String> {

    private List<YouhuijuanConfig> mDataList;
    private Context context;
    DialogUtils.ItemClickBack back;
    public DialogYouhuiJuanAdapter(List<YouhuijuanConfig> mDataList, Context context, DialogUtils.ItemClickBack back) {
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
        holder.bindTo(mDataList.get(position).getCouponId(), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onbindTo(BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, String model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof DialogYouhuijuanItemBinding) {
            ((DialogYouhuijuanItemBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
            ((DialogYouhuijuanItemBinding) viewDataBindingBaseHolder.mBinding).setCall(back);
        }
    }
}
