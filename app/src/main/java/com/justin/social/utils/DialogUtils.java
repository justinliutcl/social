package com.justin.social.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.views.AutoHideKeyBoardDialog;

import java.util.List;


/**
 * Created by Justinliu on 2017/12/13.
 */

public class DialogUtils {
    private AutoHideKeyBoardDialog builder;
    public Activity activity;

    private static class DialogInstance {
        private static DialogUtils utilInstance = new DialogUtils();
    }

    private DialogUtils() {

    }

    public interface ItemClickBack {
        void onBack(String s);
    }

    public interface ItemObjectClickBack<T> {
        void onBack(T s);
    }

    public static DialogUtils getDialogUtilInstance() {
        return DialogUtils.DialogInstance.utilInstance;
    }

    public void setFragment(Activity activity) {
        this.activity = activity;
    }

    private AutoHideKeyBoardDialog showSimpleDialog(final Context context, final View view) {
        builder = new AutoHideKeyBoardDialog(context, R.style.dialog);
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        builder.getWindow().setGravity(Gravity.CENTER);
        builder.setContentView(view);
        return builder;
    }


    private AutoHideKeyBoardDialog showBottomDialog(Context context, View view) {
        builder = new AutoHideKeyBoardDialog(context, R.style.dialog);
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        WindowManager.LayoutParams lp = builder.getWindow().getAttributes();
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        builder.getWindow().setAttributes(lp);
        builder.getWindow().setWindowAnimations(R.style.dialogAnim);
        builder.getWindow().setGravity(Gravity.BOTTOM);
        builder.setContentView(view);
        return builder;
    }

    public void dismiss() {
        if (builder != null) {
            builder.dismiss();
        }
    }

    public void release() {
        builder = null;
        activity = null;
    }

    public void showDuringDialog(Context context, ItemClickBack back) {
        showSimpleDialog(context, DialogImpl.getDuringView(context, back));
    }

    public void showCityDialog(Context context, List<String> city, ItemClickBack back) {
        showSimpleDialog(context, DialogImpl.getCityView(context, city, back));
    }

    public void showHourseTypeDialog(Context context, ItemClickBack back) {
        showSimpleDialog(context, DialogImpl.getHourseTypeView(context, back));
    }

    public void showSocialTypeDialog(Context context, ItemClickBack back) {
        showSimpleDialog(context, DialogImpl.getSocialTypeView(context, back));
    }

    public void showDuringTimeDialog(Context context, ItemClickBack back) {
        showSimpleDialog(context, DialogImpl.getDuringTimeView(context, back));
    }

    public void showServiceAddDialog(Context context, ServiceAddConfig config, ItemObjectClickBack<List<ServiceAddConfig>> back) {
        showSimpleDialog(context, DialogImpl.getServiceAddView(context,config, back));
    }

    public void showCallUsDialog(Context context) {
        showSimpleDialog(context, DialogImpl.getCallUsView(context));
    }

    public void showPayChoseDialog(Context context,String typeName,String type,String orderNum,double money) {
        showSimpleDialog(context, DialogImpl.getPayChoseView(context,typeName,type,orderNum,money));
    }


    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
