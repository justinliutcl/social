package com.justin.social.RetrofitUtils.DataBean;

import java.util.List;

/**
 * Created by Justinliu on 2018/1/29.
 */

public interface SoundConfigCallBack<T> {
    void onDataResponse(List<T> list);
}
