package com.justin.social.accessor;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

public class ConfigProvider extends ContentProvider {
	public static final int what_boolean = 1 ;
	public static final int what_int = 2 ;
	public static final int what_long = 3 ;
	public static final int what_string = 4 ;


	public static final String CONTENT_KEY = "config_key";
	public static final String CONTENT_VALUE = "config_value";
	public static final String CONTENT_WHAT = "cong_what" ;

	private ConfigFactory mFactory;


	@Override
	public boolean onCreate() {
		mFactory = ConfigFactory.getInstance(getContext());
		return true;
	}


	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		List<String> data = uri.getPathSegments() ;
		String str2 = null ;
		if (data != null && data.size() > 1  && this.mFactory != null)
			str2 = this.mFactory.getData(data.get(0), data.get(1));
		return str2;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String key = (String) values.get(CONTENT_KEY);
		String value = (String) values.get(CONTENT_VALUE);
		String what = (String) values.get(CONTENT_WHAT) ;
		int w = Integer.valueOf(what) ;

		if (mFactory != null){
			mFactory.setData(key, value , w);
		}

		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
		return 0;
	}

}

