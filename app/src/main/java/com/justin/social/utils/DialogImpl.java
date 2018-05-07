package com.justin.social.utils;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.databinding.DialogCallUsBinding;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.databinding.DialogServiceAddBinding;
import com.justin.social.model.dialog.DialogCallModel;
import com.justin.social.model.dialog.DialogNorModel;
import com.justin.social.model.dialog.DialogSelectModel;

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

    public static View getCityView(Context context,List<String>list, DialogUtils.ItemClickBack back){
        DialogDuringBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_during,null,false);
        DialogNorModel model = new DialogNorModel(context);
        model.initTitle("请选择参保城市");
        model.initBind(binding);
        model.initAdapter(list,back);
        binding.setModel(model);
        return binding.getRoot();
    }

    public static View getHourseTypeView(Context context, DialogUtils.ItemClickBack back){
        DialogDuringBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_during,null,false);
        DialogNorModel model = new DialogNorModel(context);
        List<String> list = new ArrayList<>();
        model.initTitle("请选择户口性质");
        list.add(context.getString(R.string.hourse_type_local_city));
        list.add(context.getString(R.string.hourse_type_local_rural));
        list.add(context.getString(R.string.hourse_type_other_city));
        list.add(context.getString(R.string.hourse_type_other_rural));
        model.initBind(binding);
        model.initAdapter(list,back);
        binding.setModel(model);
        return binding.getRoot();
    }

    public static View getSocialTypeView(Context context, DialogUtils.ItemClickBack back){
        DialogDuringBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_during,null,false);
        DialogNorModel model = new DialogNorModel(context);
        List<String> list = new ArrayList<>();
        model.initTitle("请选择户口性质");
        list.add(context.getString(R.string.social_type_have));
        list.add(context.getString(R.string.social_type_dishave));
        model.initBind(binding);
        model.initAdapter(list,back);
        binding.setModel(model);
        return binding.getRoot();
    }
    public static View getDuringTimeView(Context context, DialogUtils.ItemClickBack back){
        DialogDuringBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_during,null,false);
        DialogNorModel model = new DialogNorModel(context);
        List<String> list = new ArrayList<>();
        long oneMonth = 2592000000L;
        model.initTitle("请选择开始时长");
        list.add(AppUtils.getTime("yyyy-MM-") + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - oneMonth) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (2 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (3 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (4 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (5 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (6 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (7 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (8 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (9 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (10 * oneMonth)) + "01");
        list.add(AppUtils.getTime("yyyy-MM-", System.currentTimeMillis() - (11 * oneMonth)) + "01");
        model.initBind(binding);
        model.initAdapter(list, back);
        binding.setModel(model);
        binding.list.getLayoutParams().height = (int) (DimensionUtils.HEIGHT_PIXELS * 0.5);
        return binding.getRoot();
    }

    public static View getServiceAddView(Context context, ServiceAddConfig config, DialogUtils.ItemObjectClickBack<List<ServiceAddConfig>> back){
        DialogServiceAddBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_service_add,null,false);
        DialogSelectModel model = new DialogSelectModel(context);
        binding.list.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        List<ServiceAddConfig> list = config.getData();
        model.initTitle("请选择服务类型");
        model.initBind(binding);
        model.initAdapter(list,back);
        binding.setModel(model);
        binding.list.getLayoutParams().height = (int) (DimensionUtils.HEIGHT_PIXELS * 0.5);
        return binding.getRoot();
    }

    public static View getCallUsView(Context context){
        DialogCallUsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_call_us,null,false);
        DialogCallModel model = new DialogCallModel(context);
        return binding.getRoot();
    }
}
