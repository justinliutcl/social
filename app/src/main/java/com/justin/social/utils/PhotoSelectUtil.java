package com.justin.social.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PhotoSelectUtil {
	String selectitem[]={"打开相机","打开图库","取消"};
	AlertDialog ad;
	private static final int SELECT_BY_PHOTO=1;
	private static final int SELECT_BY_STORE=2;
	private static final int SELECT_BY_caijian=3;
	private static final String INTENT_key="photo_path";
	Uri photoUri;
	String photopath;
	Context context;
	ImageView img;
	Fragment frag;
	Bitmap bmap;
	String afterpath;
	public PhotoSelectUtil(Context context, Fragment frag){
		this.context=context;
		this.frag=frag;
		setDialog();
	}
	public void setimg(ImageView imag){
		img=imag;
	}
	public void showDialog(){
		ad.show();
	}
	
	public void dismisDialog(){
		ad.dismiss();
	}
	
	public void forresult(int requestCode, int resultCode, Intent data){
		if(resultCode== Activity.RESULT_OK){
			if(requestCode==SELECT_BY_caijian){
				go2caijian(data);
			}else{
				doinPhoto(requestCode,data);
			}
		}
	}
	
	private void setDialog() {
		AlertDialog.Builder ab=new AlertDialog.Builder(context, R.style.dialog);
		ab.setItems(selectitem, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					openPhoto();
					break;
				case 1:
					openImageStore();
					break;
				case 2:
					break;
				default:
					break;
				}
			}
		});

		ad=ab.create();
		ad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		ad.getWindow().setWindowAnimations(R.style.dialog_updown_anim);
		ad.getWindow().setGravity(Gravity.BOTTOM);
	}
	public void openPhoto(){
		String sdPath= Environment.getExternalStorageState();
		if(sdPath.equals(Environment.MEDIA_MOUNTED)){
//			Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			photoUri= Uri.fromFile(getUribyPhoto());
			photopath=photoUri.getPath();
//			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//			frag.startActivityForResult(intent, SELECT_BY_PHOTO);

			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 /*获取当前系统的android版本号*/
			int currentapiVersion = android.os.Build.VERSION.SDK_INT;
			if (currentapiVersion<24){
				intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
				frag.startActivityForResult(intent, SELECT_BY_PHOTO);
			}else {
				ContentValues contentValues = new ContentValues(1);
				contentValues.put(MediaStore.Images.Media.DATA, photopath);
				Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				frag.startActivityForResult(intent, SELECT_BY_PHOTO);
			}
		}else{
			Toast.makeText(context, "请检查sd卡", Toast.LENGTH_SHORT).show();
		}



	}
	//得到拍照的地址
	public File getUribyPhoto(){
		File root=new File(Environment.getExternalStorageDirectory(), "csd");
		if(!root.exists())
			root.mkdirs();
		String filename=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date())+".jpg";
		File uri=new File(root, filename);
		return uri;
	}

	public void openImageStore(){
//		Intent intent=new Intent();
//		intent.setType("image/*");
//		intent.setAction(Intent.ACTION_GET_CONTENT);
		Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		frag.startActivityForResult(intent, SELECT_BY_STORE);
	}

	public String getUribycaijian(){
		File root=new File(Environment.getExternalStorageDirectory(), "csd");
		if(!root.exists())
			root.mkdirs();
		String filename=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date())+".jpg";
		File uri=new File(root, filename);
		return uri.getPath();
	}

	private void go2caijian(Intent data) {
        // 拿到剪切数据  
//        bmap = data.getParcelableExtra("data");
//        // 显示剪切的图像
//
//        // 图像保存到文件中
//        FileOutputStream foutput = null;
//        ByteArrayOutputStream byteout=new ByteArrayOutputStream();
//        try {
//			afterpath=getUribycaijian();
//			photopath=afterpath;
//			foutput = new FileOutputStream(new File(afterpath));
//            bmap.compress(Bitmap.CompressFormat.PNG, 100, foutput);
//            bmap.compress(Bitmap.CompressFormat.PNG, 100, byteout);
//            byte[] bytes=byteout.toByteArray();
//
//            byte[] encode= Base64.encode(bytes, Base64.DEFAULT);
//            String encodeString = new String(encode);
		ByteArrayOutputStream byteout = null;
		try {
			FileInputStream fis = new FileInputStream(photopath);
			Bitmap bitmap= BitmapFactory.decodeStream(fis);
			FileOutputStream foutput = null;
			byteout=new ByteArrayOutputStream();

			afterpath=getUribycaijian();
			photopath=afterpath;
			foutput = new FileOutputStream(new File(afterpath));

//			bitmap.compress(Bitmap.CompressFormat.PNG, 40, foutput);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteout);
			byte[] bytes=byteout.toByteArray();
			byte[] encode= Base64.encode(bytes, Base64.DEFAULT);
			String encodeString = new String(encode);
			new HttpConfigManager().getHeadImageConfig(CommonSettingValue.getIns(context).getCurrentUserId(), encodeString,
					new BeanConfigCallBack<HeaderImageConfig>() {
				@Override
				public void onDataResponse(HeaderImageConfig bean) {
					if(bean.isSuccess()){
						CommonSettingValue.getIns(context).setHeaderImage(CommonSettingValue.getIns(context).getCurrentPhone(),bean.getData());
						ImageUtils.setIcon(img,bean.getData());
						Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();
					}
				}
			});
		}catch (Exception e){

		}finally {
			if (null != byteout) {
				try {
					byteout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//            String encodeString = fileToBase64(new File(photopath));

//			uploadUtil.uploadFile(photopath, fileKey, Path.HOST+Path.ip+Path.PHOTOUP_PATH, params,loading);

//            HttpconnectUtil.getPostJson(context, Path.TEACHER_UPIMG_PATH, new String[]{"phone","img","type"}, new String[]{phone,encodeString,type}, new OnJsonCall() {
//
//				@Override
//				public void JsonCallBack(String str) {
//					Userlogin user=HttpconnectUtil.getLoginMes(str);
//					if(user.getStatus().equals("1")){
//						Toast.makeText(context, "上传成功", 0).show();
//						SharedPreferencesUtil.getInstances(context).setImg(photopath);
//					}else{
//						Toast.makeText(context, user.getMsg(), 0).show();
//					}
//				}
//			});


//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }finally{
//            if(null != foutput){
//                try {
//                    foutput.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
	
}
	public  String fileToBase64(File file) {
		String base64 = null;
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] bytes = new byte[in.available()];
			int length = in.read(bytes);
			base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return base64;
	}


	private void doincaijian(String path) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    intent.setAction("com.android.camera.action.CROP");
		String type = "image/*";
		File file = new File(path);
//	    intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");// mUri是已经选择的图片Uri
	    intent.putExtra("crop", "true");  
	    intent.putExtra("aspectX", 1);// 裁剪框比例  
	    intent.putExtra("aspectY", 1);  
	    intent.putExtra("outputX", 150);// 输出图片大小  
	    intent.putExtra("outputY", 150);  
	    intent.putExtra("return-data", true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName()+".provider", file);//android 7.0以上
			intent.setDataAndType(fileUri, type);
			PermessionUtils.grantUriPermission(context, fileUri, intent);
		}else {
			intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
		}
	    frag.startActivityForResult(intent, SELECT_BY_caijian); 
	}
	
	private void doinPhoto(int requestCode,Intent data) {
		if(requestCode==SELECT_BY_STORE){
			if(data==null){
				Toast.makeText(context, "请选择正确的图片", Toast.LENGTH_SHORT).show();
			}
			photoUri=data.getData();
			if(photoUri==null){
				Toast.makeText(context, "路径出错", Toast.LENGTH_SHORT).show();
			}
			ContentResolver cr=context.getContentResolver();
			Cursor cursor=cr.query(photoUri, null, null, null, null);
			cursor.moveToFirst();
			photopath=cursor.getString(1);
		}
		go2caijian(data);
//		doincaijian(photopath);

	}

}
