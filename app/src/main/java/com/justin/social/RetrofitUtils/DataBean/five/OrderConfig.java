package com.justin.social.RetrofitUtils.DataBean.five;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.RetrofitUtils.configRequest.CallBack;
import com.justin.social.activity.OrderDetialActivity;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.ContentKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/4/10.
 */

public class OrderConfig extends BaseConfig implements Serializable{
    public static final String HAVE_PAY         = "1";
    public static final String NO_PAY           = "0";
    public static final String DURPAY           = "-1";
    public static final String ALL_PAY          = "-2";
    public static final String INVOIC_PAY       = "2";

    public static final String CANCLE       = "未支付";
    public static final String PAY          = "已支付";

    public ObservableBoolean isSelect = new ObservableBoolean(false);


    public List<OrderConfig>data;
    public String orderNum;
    public String createTime;
    public String insuredTime;
    public String allCharge;
    public String status;
    public String type;
    public String orderStatus;

    public CallBack<OrderConfig>call;

    public CallBack<OrderConfig> getCall() {
        return call;
    }

    public void setCall(CallBack<OrderConfig> call) {
        this.call = call;
    }

    public List<OrderConfig> getData() {
        return data;
    }

    public void setData(List<OrderConfig> data) {
        this.data = data;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInsuredTime() {
        return insuredTime;
    }

    public void setInsuredTime(String insuredTime) {
        this.insuredTime = insuredTime;
    }

    public String getAllCharge() {
        return allCharge;
    }

    public void setAllCharge(String allCharge) {
        this.allCharge = allCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void onItemClick(View view){}

    @BindingAdapter("createTime")
    public static void changeCreate(TextView text,String time){
        if(time!=null&&!time.isEmpty())
            text.setText("下单日期:  "+AppUtils.getTime("yyyy-MM-dd  hh:mm:ss",Long.parseLong(time)));
    }

    @BindingAdapter("isShowVisiableDetial")
    public static void isShowVisiableDetial(TextView text,String st){
        text.setVisibility(st.equals(PAY)?View.VISIBLE:View.GONE);
    }

    @BindingAdapter("isShowVisiableCancle")
    public static void isShowVisiableCancle(TextView text,String st){
        text.setVisibility(st.equals(CANCLE)?View.VISIBLE:View.GONE);
    }

    @BindingAdapter("duringImage")
    public static void setDuringImage(View view, String st){
        switch (st) {
            case ContentKey.ORDER_STATUS_NOPAY:
                view.setBackgroundResource(R.drawable.icon_order_during_dur);
                break;
            case ContentKey.ORDER_STATUS_SEEING:
                view.setBackgroundResource(R.drawable.icon_order_during_dur);
                break;
            case ContentKey.ORDER_STATUS_SENDING:
                view.setBackgroundResource(R.drawable.icon_order_during_sending);
                break;
            case ContentKey.ORDER_STATUS_SUCCESS:
                view.setBackgroundResource(R.drawable.icon_order_during_success);
                break;
        }
    }

    public void onDetialClick(View view){
        OrderDetialActivity.JumpOrderDetialActivity(view.getContext(),orderNum,type);
    }

    public void onPayClick(final View view){
        String title= "社保订单";
        String t="0";
        switch (type){
            case "1":
                title= "社保订单";
                t = ContentKey.ORDER_TYPE_SOCIAL;
                break;
            case "2":
                title= "公积金订单";
                t = ContentKey.ORDER_TYPE_ACCU;
                break;
            case "3":
                title= "五险一金订单";
                t = ContentKey.ORDER_TYPE_FIVE;
                break;
        }
        AliPayUse pay = new AliPayUse( view.getContext(), title, 0.01, t,orderNum, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
            @Override
            public void SuccessCallBack(String mes) {
                Toast.makeText(view.getContext(),"支付成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failCallBack(String mes) {
                Toast.makeText(view.getContext(),"支付失败",Toast.LENGTH_SHORT).show();
            }
        });
        pay.pay();
    }


    @BindingAdapter("selectImage")
    public static void setSelectImage(ImageView text,boolean isSelect){
        if(isSelect){
            text.setImageResource(R.drawable.icon_invoic_select);
        }else{
            text.setImageResource(R.drawable.icon_invoic_normal);
        }
    }

    public void onSelectClick(View view){
        isSelect.set(!isSelect.get());
    }
}
