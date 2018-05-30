package com.justin.social.RetrofitUtils.DataBean.five;

import android.databinding.BindingAdapter;
import android.view.View;
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
import com.justin.social.utils.DialogUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/4/10.
 */

public class OrderConfig2 extends BaseConfig {
    public static final String HAVE_PAY     = "1";
    public static final String NO_PAY       = "0";
    public static final String DURPAY       = "-1";
    public static final String ALL_PAY      = "-2";

    public static final String CANCLE       = "未支付";
    public static final String PAY          = "已支付";

    public List<OrderConfig2>data;
    public String orderNum;
    public String createTime;
    public String insuredTime;
    public String allCharge;
    public String status;
    public String type;
    public String orderStatus;

    public OrderConfig2(String orderNum, String createTime, String insuredTime, String allCharge, String status, String type, String orderStatus, CallBack<OrderConfig2> call) {
        this.orderNum = orderNum;
        this.createTime = createTime;
        this.insuredTime = insuredTime;
        this.allCharge = allCharge;
        this.status = status;
        this.type = type;
        this.orderStatus = orderStatus;
        this.call = call;
    }

    public CallBack<OrderConfig2>call;

    public CallBack<OrderConfig2> getCall() {
        return call;
    }

    public void setCall(CallBack<OrderConfig2> call) {
        this.call = call;
    }

    public List<OrderConfig2> getData() {
        return data;
    }

    public void setData(List<OrderConfig2> data) {
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

    @BindingAdapter("orderTitleType")
    public static void setOrderTitleType(TextView text,String type){
        String title= "社保订单";
        switch (type){
            case "1":
                title= "社保订单";
                break;
            case "2":
                title= "公积金订单";
                break;
            case "3":
                title= "五险一金订单";
                break;
            case "4":
                title= "增值服务订单";
                break;
            case "5":
                title= "申请发票订单";
                break;
        }
        text.setText(title);
    }

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
            case "4":
                title= "增值服务订单";
                t = ContentKey.ORDER_TYPE_INSERT;
                break;
            case "5":
                title= "申请发票订单";
                t = ContentKey.ORDER_TYPE_INVOIC;
                break;
        }
        DialogUtils.getDialogUtilInstance().showPayChoseDialog(view.getContext(),title,t,orderNum,0.01);
//        AliPayUse pay = new AliPayUse( view.getContext(), title, 0.01, t,orderNum, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
//            @Override
//            public void SuccessCallBack(String mes) {
//                Toast.makeText(view.getContext(),"支付成功",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void failCallBack(String mes) {
//                Toast.makeText(view.getContext(),"支付失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//        pay.pay();
    }

    public void onCancelClick(final View view){
        new HttpConfigManager().getCancelOrderConfig(orderNum, type, new BeanConfigCallBack<BaseConfig>() {
            @Override
            public void onDataResponse(BaseConfig bean) {
                if(bean!=null&&bean.isSuccess()){
                    Toast.makeText(view.getContext(),"取消成功",Toast.LENGTH_SHORT).show();
                    if(call!=null)
                        call.onCall(OrderConfig2.this);
                }
            }
        });
    }
}
