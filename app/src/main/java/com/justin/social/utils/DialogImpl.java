package com.justin.social.utils;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.justin.social.R;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.model.dialog.DialogNorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogImpl {
    public static View getDuringView(Context context, DialogUtils.ItemClickBack back){
        DialogDuringBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_during,null,false);
        DialogNorModel model = new DialogNorModel(context);
        List<String> list = new ArrayList<>();
        model.initTitle("请选择付款套餐");
        list.add(context.getString(R.string.during_month));
        list.add(context.getString(R.string.during_quarter));
        list.add(context.getString(R.string.during_half_year));
        list.add(context.getString(R.string.during_year));
        model.initBind(binding);
        model.initAdapter(list,back);
        binding.setModel(model);
        return binding.getRoot();
    }
}
