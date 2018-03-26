package com.justin.social.RetrofitUtils.DataBean.callBack;

import java.util.List;

/**
 * Created by Justinliu on 2018/1/29.
 */

public interface BeanConfigCallBack<T> {
    void onDataResponse(T bean);
}
