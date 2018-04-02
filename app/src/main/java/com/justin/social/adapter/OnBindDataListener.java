package com.justin.social.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Justinliu on 2017/12/7.
 */

public interface OnBindDataListener<T extends RecyclerView.ViewHolder,D extends Object> {
    void onbindTo(T t, D model,int position);
}
