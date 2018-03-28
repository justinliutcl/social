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

    private static final String TBL_USER_TABLE_NAME             = "_user";
    private static final String TBL_USER_COLUMN_ID              = "_id";
    private static final String TBL_USER_COLUMN_USER_ID         = "userId";
    private static final String TBL_USER_COLUMN_USER_NAME       = "userName";
    private static final String TBL_USER_COLUMN_PHONE           = "phone";
    private static final String TBL_USER_COLUMN_NIKE_NAME       = "nikeName";
    private static final String TBL_USER_COLUMN_HEADIMAGE       = "headImg";
    private static final String TBL_USER_COLUMN_EMAIL           = "email;";
    private static final String TBL_USER_COLUMN_IDCARD          = "idCard;";
    private static final String TBL_USER_COLUMN_INSUREDCITY     = "insuredCity;";
    private static final String TBL_USER_COLUMN_HOUSEHOLD_TYPE  = "householdType;";
    private static final String TBL_USER_COLUMN_BANKNAME        = "bankName;";
    private static final String TBL_USER_COLUMN_BRANCH_NAME     = "branchName;";
    private static final String TBL_USER_COLUMN_BRANCH_NUM      = "branchNum;";
    private static final String TBL_USER_COLUMN_TOKEN           = "token;";
    private static final String TBL_USER_COLUMN_PASSWORD        = "passWord;";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblPlaylistCreateSql = formatInUkFormat("CREATE TABLE %1$s (%2$s INTEGER PRIMARY KEY AUTOINCREMENT, %3$s TEXT, %4$s TEXT, %5$s TEXT" +
                        ", %6$s TEXT, %7$s TEXT, %8$s TEXT, %9$s TEXT, %10$s TEXT, %11$s TEXT, %12$s TEXT, %13$s TEXT, %14$s TEXT, %15$s TEXT, %16$s TEXT);",
                TBL_USER_TABLE_NAME,                //1
                TBL_USER_COLUMN_ID,                 //2
                TBL_USER_COLUMN_USER_ID,            //3
                TBL_USER_COLUMN_USER_NAME,          //4
                TBL_USER_COLUMN_PHONE,              //5
                TBL_USER_COLUMN_NIKE_NAME,          //6
                TBL_USER_COLUMN_HEADIMAGE,          //7
                TBL_USER_COLUMN_EMAIL,              //8
                TBL_USER_COLUMN_IDCARD,             //9
                TBL_USER_COLUMN_INSUREDCITY,        //10
                TBL_USER_COLUMN_HOUSEHOLD_TYPE,     //11
                TBL_USER_COLUMN_BANKNAME,           //12
                TBL_USER_COLUMN_BRANCH_NAME,        //13
                TBL_USER_COLUMN_BRANCH_NUM,         //14
                TBL_USER_COLUMN_TOKEN,              //15
                TBL_USER_COLUMN_PASSWORD            //16
        );
        db.execSQL(tblPlaylistCreateSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private @NonNull
    DbUser getMusicByCursor(Cursor c) {
        int    id               = c.getInt   (c.getColumnIndex(TBL_USER_COLUMN_ID));
        String userId           = c.getString(c.getColumnIndex(TBL_USER_COLUMN_USER_ID));
        String userName         = c.getString(c.getColumnIndex(TBL_USER_COLUMN_USER_NAME));
        String phone            = c.getString(c.getColumnIndex(TBL_USER_COLUMN_PHONE));
        String nikeName         = c.getString(c.getColumnIndex(TBL_USER_COLUMN_NIKE_NAME));
        String headImg          = c.getString(c.getColumnIndex(TBL_USER_COLUMN_HEADIMAGE));
        String email            = c.getString(c.getColumnIndex(TBL_USER_COLUMN_EMAIL));
        String idCard           = c.getString(c.getColumnIndex(TBL_USER_COLUMN_IDCARD));
        String insuredCity      = c.getString(c.getColumnIndex(TBL_USER_COLUMN_INSUREDCITY));
        String householdType    = c.getString(c.getColumnIndex(TBL_USER_COLUMN_HOUSEHOLD_TYPE));
        String bankName         = c.getString(c.getColumnIndex(TBL_USER_COLUMN_BANKNAME));
        String branchName       = c.getString(c.getColumnIndex(TBL_USER_COLUMN_BRANCH_NAME));
        String branchNum        = c.getString(c.getColumnIndex(TBL_USER_COLUMN_BRANCH_NUM));
        String token            = c.getString(c.getColumnIndex(TBL_USER_COLUMN_TOKEN));
        String passWord         = c.getString(c.getColumnIndex(TBL_USER_COLUMN_PASSWORD));
        return new DbUser(id,userId,userName,phone,nikeName,headImg,email,idCard,insuredCity,householdType,bankName,branchName,branchNum,token,passWord);
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
        cv.put(TBL_USER_COLUMN_ID ,             user.id           );
        cv.put(TBL_USER_COLUMN_USER_ID,         user.userId       );
        cv.put(TBL_USER_COLUMN_USER_NAME,       user.userName     );
        cv.put(TBL_USER_COLUMN_PHONE,           user.phone        );
        cv.put(TBL_USER_COLUMN_NIKE_NAME,       user.nikeName     );
        cv.put(TBL_USER_COLUMN_HEADIMAGE,       user.headImg      );
        cv.put(TBL_USER_COLUMN_EMAIL,           user.email        );
        cv.put(TBL_USER_COLUMN_IDCARD,          user.idCard       );
        cv.put(TBL_USER_COLUMN_INSUREDCITY,     user.insuredCity  );
        cv.put(TBL_USER_COLUMN_HOUSEHOLD_TYPE,  user.householdType);
        cv.put(TBL_USER_COLUMN_BANKNAME,        user.bankName     );
        cv.put(TBL_USER_COLUMN_BRANCH_NAME,     user.branchName   );
        cv.put(TBL_USER_COLUMN_BRANCH_NUM,      user.branchNum    );
        cv.put(TBL_USER_COLUMN_TOKEN,           user.token        );
        cv.put(TBL_USER_COLUMN_PASSWORD,        user.passWord     );
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
