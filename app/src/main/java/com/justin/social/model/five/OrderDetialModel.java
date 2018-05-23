package com.justin.social.model.five;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderDetialConfig;
import com.justin.social.RetrofitUtils.DataBean.five.UserInfoConfig;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.ActivityOrderDetialBinding;
import com.justin.social.databinding.ActivityUserMessageBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.ContentKey;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/4/19.
 */

public class OrderDetialModel extends BaseModel {
    HttpConfigManager httpConfigManager;
    ActivityOrderDetialBinding mBinding;
    public ObservableField<String> name;
    public ObservableField<String> idCard;
    public ObservableField<String> city;
    public ObservableField<String> orderState;
    public ObservableField<String> orderTypeMes;
    public ObservableField<String> orderMoney;
    public ObservableField<String> createTime;
    public ObservableField<String> insuredTime;
    public ObservableField<String> allCharge;
    public ObservableField<String> serviceCharge;
    public ObservableField<String> insuredCharge;
    public ObservableField<String> overdueFine;
    public String orderNum, orderType;

    public OrderDetialModel(Context context, String orderNum, String orderType) {
        super(context);
        httpConfigManager = new HttpConfigManager();
        name = new ObservableField<>("");
        idCard = new ObservableField<>("");
        city = new ObservableField<>("");
        orderState = new ObservableField<>("");
        orderTypeMes = new ObservableField<>("");
        orderMoney = new ObservableField<>("");
        createTime = new ObservableField<>("");
        insuredTime = new ObservableField<>("");
        allCharge = new ObservableField<>("");
        serviceCharge = new ObservableField<>("");
        insuredCharge = new ObservableField<>("");
        overdueFine = new ObservableField<>("");
        this.orderNum = orderNum;
        this.orderType = orderType;
    }


    public void setData(final ActivityOrderDetialBinding bind) {
        this.mBinding = bind;
        new HttpConfigManager().getOrderDetialConfig(orderNum, orderType, new BeanConfigCallBack<OrderDetialConfig>() {
            @Override
            public void onDataResponse(OrderDetialConfig bean) {
                name.set(bean.getOrderData().getUserName());
                idCard.set(bean.getOrderData().getIdCard());
                city.set(bean.getOrderData().getInsuredCity());
                orderState.set(bean.getOrderData().getOrderStatus());

                orderMoney.set(bean.getOrderData().getAllCharge());
                createTime.set(bean.getOrderData().getCreateTime());
                insuredTime.set(bean.getOrderData().getInsuredTime());
                allCharge.set(bean.getOrderData().getAllCharge());
                serviceCharge.set(bean.getOrderData().getServiceCharge());
                insuredCharge.set(bean.getOrderData().getInsuredCharge());
                overdueFine.set(bean.getOrderData().getOverdueFine());
                switch (orderType){
                    case "1":
                        orderTypeMes.set("社保订单");
                        break;
                    case "2":
                        orderTypeMes.set("公积金订单");
                        break;
                    case "3":
                        orderTypeMes.set("五险一金订单");
                        break;
                    case "4":
                        orderTypeMes.set("增值服务订单");
                        break;
                    case "5":
                        orderTypeMes.set("申请发票");
                        break;
                }
            }
        });
    }


    @BindingAdapter("image")
    public static void setOrderImage(ImageView view, String order) {
        if(order==null)
            order = ContentKey.ORDER_STATUS_NOPAY;
        switch (order) {
            case ContentKey.ORDER_STATUS_NOPAY:
                view.setImageResource(R.drawable.icon_order_detial_look);
                break;
            case ContentKey.ORDER_STATUS_SEEING:
                view.setImageResource(R.drawable.icon_order_detial_look);
                break;
            case ContentKey.ORDER_STATUS_SENDING:
                view.setImageResource(R.drawable.icon_order_detial_during);
                break;
            case ContentKey.ORDER_STATUS_SUCCESS:
                view.setImageResource(R.drawable.icon_order_detial_send_success);
                break;
        }
    }

    @BindingAdapter("createTime")
    public static void changeCreate(TextView text, String time) {
        if(time!=null&&!time.isEmpty())
            text.setText("下单日期:  " + AppUtils.getTime("yyyy-MM-dd  hh:mm:ss", Long.parseLong(time)));
    }
}
