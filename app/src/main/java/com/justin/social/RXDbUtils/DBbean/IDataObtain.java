package com.justin.social.RXDbUtils.DBbean;

import java.util.Collection;

import io.reactivex.annotations.NonNull;

/**
 * Created by Justinliu on 2018/3/21.
 */

public interface IDataObtain {

    interface IDBResCallback<T> {
        void complete(T t);
    }

    interface IDbChangeCallback {
        /**
         * 添加对象
         *
         * @param tab  添加表名
         * @param size 添加数量
         */
        void onDataAdded(String tab, int size);

        /**
         * 数据删除
         *
         * @param id 被删除的播放列表id，注意根据此id找到播放列表然后删除
         */
        void onDataDeleted(int id);

        /**
         * @param tab 更新的表名
         * @param num 变更的数量
         */
        void onDataUpdated(String tab, int num);

    }


    abstract class DataChanged<T> implements IDBResCallback<T> {
        Collection<IDbChangeCallback> mListeners = null;
        IDBResCallback<T> mCallBack;

        public DataChanged(@NonNull Collection<IDbChangeCallback> listeners, IDBResCallback<T> callBack) {
            mListeners = listeners;
            mCallBack = callBack;
        }
    }
}
