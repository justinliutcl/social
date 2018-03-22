package com.justin.social.RetrofitUtils;

import com.google.gson.TypeAdapter;
import com.justin.social.LogUtils.CommonLog;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String origin = new String(value.bytes(), "utf-8");
        CommonLog.d(origin);
        return adapter.fromJson(origin);
    }
}