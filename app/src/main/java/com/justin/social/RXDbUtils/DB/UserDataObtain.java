package com.justin.social.RXDbUtils.DB;

import android.content.Context;
import android.util.ArrayMap;

import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.model.five.PhoneModel;
import com.justin.social.utils.AppUtils;

import java.util.List;

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
                DbUser dbUser = dbManager.getUser(id);
                e.onNext(dbUser);
                e.onComplete();
            }
        };

        Observer<DbUser> obs = createObserver(callback);
        execute(ob, obs);
    }

    public void getUserFromPhoneRxJava(final String phone, IDataObtain.IDBResCallback<DbUser> callback) {
        ObservableOnSubscribe<DbUser> ob = new ObservableOnSubscribe<DbUser>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<DbUser> e) throws Exception {
                if (dbManager == null)
                    init();
                DbUser dbUser = dbManager.getUserFromPhone(phone);
                e.onNext(dbUser);
                e.onComplete();
            }
        };

        Observer<DbUser> obs = createObserver(callback);
        execute(ob, obs);
    }

    public void getCurrentUser(IDataObtain.IDBResCallback<DbUser> callback) {
        ObservableOnSubscribe<DbUser> ob = new ObservableOnSubscribe<DbUser>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<DbUser> e) throws Exception {
                if (dbManager == null)
                    init();
                DbUser dbUser = dbManager.getUserFromPhone(CommonSettingValue.getIns(mContext).getCurrentPhone());
                e.onNext(dbUser);
                e.onComplete();
            }
        };

        Observer<DbUser> obs = createObserver(callback);
        execute(ob, obs);
    }

    public void updataUser(final DbUser user, IDataObtain.IDBResCallback<DbUser> callback){
        ObservableOnSubscribe<DbUser> ob = new ObservableOnSubscribe<DbUser>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<DbUser> e) throws Exception {
                if (dbManager == null)
                    init();
                DbUser dbUser = dbManager.getUserFromPhone(user.phone);
                if(dbUser == null)
                    dbManager.addUser(user);
                else
                    dbManager.updateUser(user);
                e.onNext(user);
                e.onComplete();
            }
        };

        Observer<DbUser> obs = createObserver(callback);
        execute(ob, obs);
    }

    public void updataCurrentHeadImage(final String image, IDataObtain.IDBResCallback<DbUser> callback){
        ObservableOnSubscribe<DbUser> ob = new ObservableOnSubscribe<DbUser>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<DbUser> e) throws Exception {
                if (dbManager == null)
                    init();
                DbUser dbUser = dbManager.getUserFromPhone(CommonSettingValue.getIns(mContext).getCurrentPhone());
                if(dbUser != null) {
                    dbUser.headImg = image;
                    dbManager.updateUser(dbUser);
                }
                e.onNext(dbUser);
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


    public void getFriend(IDataObtain.IDBResCallback<List<PhoneModel>> callback){
        ObservableOnSubscribe<List<PhoneModel>> ob = new ObservableOnSubscribe<List<PhoneModel>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<PhoneModel>> e) throws Exception {

                e.onNext(AppUtils.getContacts(mContext));
                e.onComplete();
            }
        };

        Observer<List<PhoneModel>> obs = createObserver(callback);
        execute(ob, obs);
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
