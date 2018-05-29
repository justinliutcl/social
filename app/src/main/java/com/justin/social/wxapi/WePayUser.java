package com.justin.social.wxapi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.ResultConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;


/**
 * Created by 不爱白菜 on 2016/5/10.
 */
public class WePayUser {
    public static void wePay(final Context context, String ordernumber, double money) {
        IWXAPI api;
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
        final IWXAPI finalApi;
        finalApi = WXAPIFactory.createWXAPI(context, null);
        finalApi.registerApp(Constants.APP_ID);

        new HttpConfigManager().getWeChatConfig(money, ordernumber, new BeanConfigCallBack<String>() {
            @Override
            public void onDataResponse(String bean) {
                try {
                    JSONObject json = new JSONObject(bean);
                    JSONObject Data = json.getJSONObject("data");
                    PayReq req = new PayReq();
                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                    req.appId        = Data.getString("appid");
                    req.partnerId    = Data.getString("partnerid");
                    req.prepayId     = Data.getString("prepayid");
                    req.nonceStr     = Data.getString("noncestr");
                    req.timeStamp    = Data.getString("timestamp");
                    req.packageValue = Data.getString("package");
                    req.sign = Data.getString("sign");
                    Log.e("asd","req.appId       "+req.appId);
                    Log.e("asd","req.partnerId   "+req.partnerId);
                    Log.e("asd","req.prepayId    "+req.prepayId);
                    Log.e("asd","req.nonceStr    "+req.nonceStr);
                    Log.e("asd","req.timeStamp   "+req.timeStamp);
                    Log.e("asd","req.packageValue"+req.packageValue);
                    Log.e("asd",""+req.sign);
//                        req.extData = "app data"; // optional
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    finalApi.sendReq(req);
                    Toast.makeText(context,"吊起微信",Toast.LENGTH_SHORT).show();


                } catch (Exception e) {

                }
            }
        });
    }

}
