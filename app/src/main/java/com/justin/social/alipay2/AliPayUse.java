package com.justin.social.alipay2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.alipay.sdk.app.PayTask;
import com.justin.social.alipay2.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 * Created by 不爱白菜 on 2016/4/10.
 */
public class AliPayUse {
    /**
     *  重要说明:
     *
     *  这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     */

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018042302593262";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088721276012644";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA_PRIVATE = "";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyvGg0DSKCFs05v7kQ//9fqvHZKXtxqBLa1HTLqxvIhjLrunysmpuuxjoCF0zRYrkvI5v7nU1gz1YyZQv0vc2dkzB58p/9l5PWd5b0aWt2I/D7nPdbNngt+gohmlCWMp4YgOOyATwCDC24+LrpbqdJBgHsFuQLIUjidaWkm3slC0PWwes8/CT2KlK2J7OD0n8wdPnoak41epQ6TY+wZx/igBjKGj95XVFyVU1E33Oxo3ultZ9PvQQJeiq6nzc+HQUBsbqespW2YUDaDclVDk/yNjytgXYKGOppOS+wBcY5dM5aqvZEoYm5h3DM/PgFz3nZ2TvjYw30uLsW49ZH8W1pAgMBAAECggEAOZ/GlKl7a6eASNMIDnQKWESbrmDSXKVMVuLSOOuUjIS/nAJGubsq4CEmiJAMgyEqBQmc+3UOgly2NI8hWKlsxhWzNk3DyRV6Y1WxBGGMdM1Hn0dO9EhOXLxVUdoXiyUMlWfQgQ4m054ri4wdbq7vHfri2cPTh8cWYXR+j+BUxm8dLr2tVHXC4RW9AS3aChQOlbMXQSgLev/5J/1NSUzstbK0FnQrW3yOkJT1yOGXz1K9z1yLhfsRzEmSFXrtyxqOxYbKsprTlHDmU/xNyieME1FkIoHd45gV+MCLo1gka7y1cK77RD6x8m7VdKQmjWa7kPjUO9/D8UuIwihajYyI9QKBgQD0N3npxU0BjIgVYD4PAMAmep1PDZ+MdCXwdJ0zYy4M9uWhjOcqKrVTmuFBoY42nyYVyzDCr2nybqCTEB/qCwDpsijNgpLpuWXMli64UWAOBP5GoKEtShfMbJOl2cly8yMwOBAykOT4GAoJTQrPQLl7vw688n9DBvt6bC26usXxxwKBgQC7XB+rqPWBFWtzIcE7ouY8bzW2dXY9XNKPWFLrEeNaTPBPgjWeJmt7v/pocuWjwbrM5jsc4ifqAVwBTiLwBt6uCPbUA0FXEEb9CFA1d0zRRfNtYesr9OpbOS8sCSZizxVNR6/JUjojJJzGKzkh+mrUXlIxcC5YT2OLc2mP2sWnTwKBgQCdKC4eD8e0XXkdOGf3Q6ZjnLgqrTulKQFZMdIPxZ1W3nN7eK8x5XX2rU6T66EW6Wea0r04K6XkSuiuesvu0tHtkabMSWC9Y39tHqBGIKfNncz8KlZOTMXv2VdSfUoz3N8iIpHZ2IBgXyfmQFvab25tgc+8K9lViuK+L2tmJNdI9QKBgDsvw/V3X27StGnfbOqI8FCBFSe5fYuu6flMfo0xzm91RKjpSkTWgDfMkq8yNKcBiSA3Ts82BkPfABCNfW3zzZ6OyGJ7UMXPJ43BdY0eTc8CWP7h4ue069+7ddJkZZLrNiB+l+JyLllxtHDJsLeyItNJpI9nj8xn79tarys5ghzzAoGAItvZbZA/AlZ2b0JVmRFoTT4aZifP+rOoaJ5aafYSOe2rGlXOy9D/p1xzdhlAnPbIKfVSnVWLncNXUWooaE3rAYlCexcwLg3XYdJKTEOe2uBflHzPbuARO74X1HFk2rvdRuycNF8uevJtVkXbczRPWCNYSTugFKRWqF7RAFDOmr0=";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private Context context;
    private double money;
    private String title;
    String type;
    String outUri;
    String orderNum;
    OnPayCall paycall;

    public interface OnPayCall {
        public void SuccessCallBack(String mes);

        public void failCallBack(String mes);
    }


    public AliPayUse( Context context, String title, double money, String type,String orderNum, String path, OnPayCall paycall) {
        this.context = context;
        this.money = money;
        this.title = title;
        this.type = type;
        this.orderNum = orderNum;
        outUri = path;
        this.paycall = paycall;
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        paycall.SuccessCallBack("支付成功");
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            paycall.failCallBack("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    public void pay() {
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,title,type,orderNum,money, rsa2,context);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        Log.e("asd","orderInfo=="+orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                PayTask alipay = new PayTask((Activity) context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}