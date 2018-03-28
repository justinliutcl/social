package com.justin.social.accessor;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GlobPre {

    private ContentResolver mCR = null;
    private static String URI_CONFIG ;


    protected GlobPre(Context context){
        URI_CONFIG = "content://" + context.getPackageName() + ".configprovicer";
        if(mCR == null)
            mCR = context.getContentResolver();
    }

    private synchronized String queryProvidor(String key , String what) {
        try {
            Uri uri = Uri.parse(URI_CONFIG + "/" + key + "/" + what);
            return mCR.getType(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    private synchronized void updateValue(String key, String value , String what) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(ConfigProvider.CONTENT_KEY, key);
            cv.put(ConfigProvider.CONTENT_VALUE, value);
            cv.put(ConfigProvider.CONTENT_WHAT, what);
            mCR.insert(Uri.parse(URI_CONFIG), cv);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized boolean deleteKey(String key){
        int row = mCR.delete(Uri.parse(URI_CONFIG), ConfigProvider.CONTENT_KEY + " = ?", new String[]{key});
        if(row > 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean getBoolean(String key, boolean defValue) {
        String res = queryProvidor(key , "" + ConfigProvider.what_boolean);
        if (TextUtils.isEmpty(res))
            return defValue;
        return Boolean.parseBoolean(res);
    }
    public String getString(String key, String defValue) {
        String res = queryProvidor(key , "" + ConfigProvider.what_string);
        if (TextUtils.isEmpty(res))
            return defValue;
        return res;
    }

    public int getInt(String key, int defValue) {
        String res = queryProvidor(key , "" + ConfigProvider.what_int);
        if (TextUtils.isEmpty(res))
            return defValue;
        return Integer.parseInt(res);
    }

    public long getLong(String key, long defValue) {
        String res = queryProvidor(key , "" + ConfigProvider.what_long);
        if (TextUtils.isEmpty(res))
            return defValue;
        return Long.parseLong(res);
    }

    public void putString(String key, String value) {
        updateValue(key, value  , ConfigProvider.what_string + "");
    }

    public void putInt(String key, int value) {
        updateValue(key, value + "" , ConfigProvider.what_int + "");
    }

    public void putLong(String key, long value) {
        updateValue(key, value + "" , ConfigProvider.what_long + "");
    }

    public void putBoolean(String key, boolean value) {
        updateValue(key, value + "" , ConfigProvider.what_boolean + "");
    }

    public synchronized void putObject(String key, Object obj) {
        try {
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream os=new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(obj);
            //将序列化的数据转为16进制保存
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            putString(key, bytesToHexString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }

    public synchronized <T> T getObject(String key, Class<T> clazz) {
        try {
            String str = getString(key, "null");
            if(TextUtils.isEmpty(str)){
                return null;
            }else{
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = StringToBytes(str);
                if(stringToBytes == null){
                    return null;
                }
                ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is=new ObjectInputStream(bis);
                //返回反序列化得到的对象
                Object readObject = is.readObject();
                return (T) readObject;
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    /**
     * desc:将数组转为16进制
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray) {
        if(bArray == null){
            return null;
        }
        if(bArray.length == 0){
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * desc:将16进制的数据转为数组
     * @param data
     * @return
     */
    public static byte[] StringToBytes(String data){
        String hexString=data.toUpperCase().trim();
        if (hexString.length()%2!=0) {
            return null;
        }
        byte[] retData=new byte[hexString.length()/2];
        for(int i=0;i<hexString.length();i++)
        {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
            else if(hex_char2 >= 'A' && hex_char2 <='F')
                int_ch2 = hex_char2-55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1+int_ch2;
            retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }

}



