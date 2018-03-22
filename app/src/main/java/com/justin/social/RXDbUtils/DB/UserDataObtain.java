package com.justin.social.RXDbUtils.DB;

import android.content.Context;
import android.util.ArrayMap;

import java.util.List;

import RXDbUtils.DBbean.DbUser;
import RXDbUtils.DBbean.IDataObtain;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Justinliu on 2018/3/21.
 */

public class UserDataObtain {
    private final Context mContext;
    private static UserDataObtain mInstance;
    private DbManager dbManager;
    protected ArrayMap<Integer, IDataObtain.IDBResCallback> mCaches = new ArrayMap<>();
    private UserDataObtain(Context context) {
        this.mContext = context;
        init();
    }

    public static UserDataObtain getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UserDataObtain.class) {
                if (mInstance == null)
                    mInstance = new UserDataObtain(context.getApplicationContext());
            }
        }
        return mInstance;
    }

    void init() {
        dbManager = DbManager.getInstance(mContext);
    }


    public void getUserRxJava(final int id, IDataObtain.IDBResCallback<DbUser> callback) {
        ObservableOnSubscribe<DbUser> ob = new ObservableOnSubscribe<DbUser>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<DbUser> e) throws Exception {
                if (dbManager == null)
                    init();
                DbUser musics = dbManager.getUser(id);
                e.onNext(musics);
                e.onComplete();
            }
        };

        Observer<DbUser> obs = createObserver(callback);
        execute(ob, obs);
    }

    protected <T> Observer<T> createObserver(IDataObtain.IDBResCallback<T> cb) {
        final int key;
        if (null != cb) {
            key = cb.hashCode();
            mCaches.put(key, cb);
        }else{
            key = -1;
        }

        return new Observer<T>() {
            T t = null;
            @Override
            public void onSubscribe(@android.support.annotation.NonNull Disposable d) {
                t = null;
            }

            @Override
            public void onNext(@android.support.annotation.NonNull T t) {
                this.t = t;
            }

            @Override
            public void onError(@android.support.annotation.NonNull Throwable e) {
                IDataObtain.IDBResCallback callback = mCaches.get(key);
                if (callback != null)
                    callback.complete(t);
            }

            @Override
            public void onComplete() {
                IDataObtain.IDBResCallback callback = mCaches.get(key);
                if (callback != null) {
                    callback.complete(t);
                    mCaches.remove(key);
                }
            }
        };
    }


    /**
     * 用于执行所有的数据库查询,
     *
     * @param ob  在非UI线程执行的操作
     * @param obs 在UI线程的回调
     */
    protected void execute(ObservableOnSubscribe ob, Observer obs) {
        Observable.create(ob)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obs);
    }

}
