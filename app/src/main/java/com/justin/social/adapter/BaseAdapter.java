package com.justin.social.adapter;

import android.support.v7.widget.RecyclerView;


/**
 * Created by Justinliu on 2017/12/7.
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder,D extends Object> extends RecyclerView.Adapter<VH> implements OnBindDataListener<VH,D> {
}
