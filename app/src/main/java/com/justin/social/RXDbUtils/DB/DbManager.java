package com.justin.social.RXDbUtils.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.justin.social.RXDbUtils.DBbean.DbUser;

import java.io.File;
import java.util.Locale;


/**
 * Created by Justinliu on 2018/3/21.
 */

public class DbManager extends SQLiteOpenHelper {

    private SQLiteDatabase mDb;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "custom.db";
    private static DbManager mDbHelper;

    /**
     * 获取数据库管理实例，保持默认访问权限，不允许包以外的类访问
     *
     * @param c 注意使用Application
     * @return
     */
    static DbManager getInstance(Context c) {
        if (null == mDbHelper) {
            mDbHelper = new DbManager(c.getApplicationContext());
        }

        return mDbHelper;
    }

    private Context mContext;
    /**
     * 构造函数，保持私有属性，禁止之外访问,除了单元测试也不要使用反射创建对象
     *
     * @param c
     */
    private DbManager(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = c;
    }

    private static final String TBL_USER_TABLE_NAME = "_user";
    private static final String TBL_USER_COLUMN_ID = "_id";
    private static final String TBL_USER_COLUMN_NAME = "_name";
    private static final String TBL_USER_COLUMN_DESC = "_description";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblPlaylistCreateSql = formatInUkFormat("CREATE TABLE %1$s (%2$s INTEGER PRIMARY KEY AUTOINCREMENT, %3$s TEXT, %4$s TEXT);",
                TBL_USER_TABLE_NAME,
                TBL_USER_COLUMN_ID,
                TBL_USER_COLUMN_NAME,
                TBL_USER_COLUMN_DESC
        );
        db.execSQL(tblPlaylistCreateSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private @NonNull
    DbUser getMusicByCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(TBL_USER_COLUMN_ID));
        String name = c.getString(c.getColumnIndex(TBL_USER_COLUMN_NAME));
        String des = c.getString(c.getColumnIndex(TBL_USER_COLUMN_DESC));
        return new DbUser(id, name, des);
    }

    public DbUser getUser(int userId) {
        getDb();
        DbUser ret = null;
        Cursor c = null;
        try {
            c = mDb.query(TBL_USER_TABLE_NAME, null, TBL_USER_COLUMN_ID + " = " + userId + "", null, null, null, null, null);
            if (null == c || !c.moveToFirst()) {
                return ret;
            }
            ret = getMusicByCursor(c);
            return ret;
        } finally {
            if (null != c) {
                c.close();
            }
        }
    }

    private boolean addUser(@NonNull SQLiteDatabase db, @NonNull DbUser user) {
        ContentValues cv = new ContentValues();
        cv.put(TBL_USER_COLUMN_ID, user.id);
        cv.put(TBL_USER_COLUMN_NAME, user.name);
        cv.put(TBL_USER_COLUMN_DESC, user.des);
        long insertResult = db.insertWithOnConflict(TBL_USER_TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        return (insertResult != -1);
    }


    private static String formatInUkFormat(String format, Object... args) {
        String t = String.format(Locale.UK, format, args);
        return String.format(Locale.UK, format, args);
    }

    private void getDb() {
        if (null == mDb) {
            mDb = getWritableDatabase();
        }
    }
}
