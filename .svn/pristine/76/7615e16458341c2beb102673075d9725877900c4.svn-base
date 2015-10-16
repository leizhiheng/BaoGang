package com.common.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow;
import com.common.net.uploadimg.ImageData;
import com.common.net.uploadimg.ImageUpload;
import com.common.net.uploadimg.ImageUpload.UpLoadImageListener;
import com.common.utils.DeviceUtils;
import com.common.utils.ImageFolder;
import com.common.utils.LogUtil;
import com.common.view.popwindow.SelectPicPopupWindow;

/**
 * Fragment基类
 * @author 刘远祺
 *
 */
public class BaseCameraFragment extends BaseFragment {
    public Object tag;
	
    private final static String TAG = BaseCameraFragment.class.getSimpleName();
    
    /**相机**/
	public static final int CAMER_CODE = 007;
	
	/**相册**/
	public static final int LOCAL_CODE = 006;
	
	/**当前弹出的popwindow**/
	private SelectPicPopupWindow mPopupWindow = null;
	
	/**当前照片的文件**/
	private File imageFile;
  
	/**图片上传类**/
	private ImageUpload mIUpload;
	
	/**是否裁剪，相机相册之后**/
	private boolean crop = true;
	
	/**是否需要上传图片**/
	private boolean needUpload;
	
	/**是否打开相机取图片**/
	private boolean isOpenCamera = false;
	
	/**是否是上传头像**/
	private boolean isUploadUserHead;
	
	
	/**
	 * 显示弹起菜单
	 * @param main
	 */
	protected void showCameraPopwindow(View main){
		showCameraPopwindow(main, true, true);
	}
	
	public void showCameraPopwindow(View main, final boolean crop, boolean needUpload){
		showCameraPopwindow(main, crop, needUpload, false);
	}
	
	/**
	 * <b>显示相机，相册PopWindow</br></b>
	 * 1.自动处理了图片上传功能</br>
	 * 2.需要重写 UpLoadSuccess方法来接受上传成功的图片</br>
	 * @param main
	 */
	public void showCameraPopwindow(View main, final boolean crop, boolean needUpload, boolean isUploadUserHead) {
		
		this.crop = crop;
		this.needUpload = needUpload;
		this.isUploadUserHead = isUploadUserHead;
		
		if (mPopupWindow == null) {
			mPopupWindow = new SelectPicPopupWindow(mContext, new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mPopupWindow.isShowing()) {
						mPopupWindow.dismiss();
					}
					switch (v.getId()) {
					case R.id.btn_take_photo: // 拍照
						callCamerImage(crop);
						break;
					case R.id.btn_pick_photo:
						callLocalImage(crop);
						break;
					}
				}
			});
		}
		if (!mPopupWindow.isShowing()) {
			mPopupWindow.showAtLocation(main, Gravity.BOTTOM, 0, 0);
		}
	}
	
	/**
	 * 打开相机
	 */
	private void callCamerImage(boolean crop) {

		LogUtil.i(TAG, "启动照相机");
		imageFile = ImageFolder.getTempImageName();
		Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uri = Uri.fromFile(imageFile);
		captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		captureIntent.putExtra("return-data", true);
		
		if(crop){
			startActivityForResult(captureIntent, CAMER_CODE);
		}else{
			startActivityForResult(captureIntent, LOCAL_CODE);
		}
		
		//标记是打开相机
		isOpenCamera = true;
	}

	/**
	 * 打开本地图库
	 */
	private void callLocalImage(boolean crop) {

		LogUtil.i(TAG, "打开图库");
		// Intent localIntent = new Intent(Intent.ACTION_GET_CONTENT);
		// localIntent.addCategory(Intent.CATEGORY_OPENABLE);
		// localIntent.setType("image/*");
		// // 是否调用系统裁剪
		// localIntent.putExtra("crop", "true");
		//
		// /* 图片宽高的像素值 */
		// localIntent.putExtra("outputX", 100);
		// localIntent.putExtra("outputY", 100);
		// localIntent.putExtra("return-data", true);
		// startActivityForResult(localIntent, LOCAL_CODE);
		imageFile = ImageFolder.getTempImageName();
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		
		//如果裁剪，则设置裁剪参数
		if(crop){
			intent.putExtra("crop", "true"); // 开启剪切
			intent.putExtra("aspectX", 1); // 剪切的宽高比为1：1
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 100); // 保存图片的宽和高
			intent.putExtra("outputY", 100);
		}
		
		intent.putExtra("output", Uri.fromFile(imageFile)); // 保存路径
		intent.putExtra("outputFormat", "JPEG");// 返回格式
		startActivityForResult(intent, LOCAL_CODE);
		
		//标记是打开图库
		isOpenCamera = false;
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/*if (requestCode == CommonConfrimCancelDialog.CHOICE_UPLOAD_TYPE_CODE && resultCode == Activity.RESULT_OK) {
			//对话框Activity, setResult过来的
			
			 选择图片来源 
			String uploadType = data.getStringExtra(CommonConfrimCancelDialog.TAG);
			if (uploadType.equals(CommonConfrimCancelDialog.UPLOAD_TYPE_CAMER)) {
				
				 相机 
				callCamerImage(this.crop);
			} else if (uploadType.equals(CommonConfrimCancelDialog.UPLOAD_TYPE_LOCAL)) {
				
				 图库 
				callLocalImage(this.crop);
			}

		} else*/ if (resultCode == Activity.RESULT_OK && requestCode == CAMER_CODE) {
			
			//相机拍照，返回，再跳转到裁剪界面
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(Uri.fromFile(imageFile), "image/*");
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 100);
			intent.putExtra("outputY", 100);
			intent.putExtra("noFaceDetection", false);
			intent.putExtra("scale", true);
			imageFile = ImageFolder.getTempImageName();
			intent.putExtra("output", Uri.fromFile(imageFile));
			intent.putExtra("outputFormat", "JPEG");// 返回格式
			startActivityForResult(intent, LOCAL_CODE);
		} else if (resultCode == Activity.RESULT_OK && requestCode == LOCAL_CODE) {
			LogUtil.i(TAG, "获取图片返回成功");
			
//			ArrayList<String> temp = new ArrayList<String>();
//			temp.add(imageFile.getPath());
			ArrayList<String> temp = getPhotoPath(data, crop, isOpenCamera);
			
			if(needUpload){
				//上传图片
				mIUpload = new ImageUpload(mContext, temp, uploadListener);
				if(isUploadUserHead){
					mIUpload.upLoadHeadImage();
				}else{
					mIUpload.startLoad();
				}
			}else{
				
				//将本地图片路径返回
				onUpLoadSuccess("", temp.get(0));
			}
		}
	}

	/**
	 * 取照片
	 * @param isCrop
	 * @param isOpenCamera
	 * @return
	 */
	private ArrayList<String> getPhotoPath(Intent data, boolean isCrop, boolean isOpenCamera) {
		ArrayList<String> temp = new ArrayList<String>();
		if (isOpenCamera) {
			
			//拍照是一定有路径的
			temp.add(imageFile.getAbsolutePath());
		} else {

			if(isCrop){
				
				//相册-裁剪也是一定有路径的
				temp.add(imageFile.getAbsolutePath());
				
			}else{
				
				//单独选择相册图片是没有图片的,要去图库查询
				if (data != null && data.getData() != null) {
					Uri selectedImage = data.getData();
					String[] filePathColumns = { MediaStore.Images.Media.DATA };
					Cursor c = mContext.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
					c.moveToFirst();
					int columnIndex = c.getColumnIndex(filePathColumns[0]);
					String picturePath = c.getString(columnIndex);
					c.close();
					temp.add(picturePath);
				}
			}
		}

		return temp;
	}
	
	
	
	private UpLoadImageListener uploadListener = new UpLoadImageListener() {
		
		@Override
		public void UpLoadSuccess(ArrayList<String> netimageurls) {
			if(null != netimageurls && netimageurls.size() > 0){
				
				LogUtil.i(TAG, "----图片上传成功-----");
				for(int i=0; i<netimageurls.size(); i++){
					
					//打印所有上传成功的图片
					LogUtil.i(TAG, "图片"+i+" url: "+ netimageurls.get(i));
					LogUtil.i(TAG, "图片"+i+" path: "+ imageFile.getPath());
					
					//通知到界面，刷新数据
					onUpLoadSuccess(netimageurls.get(i), imageFile.getPath());
				}
				
			}else{
				
				//图片上传失败
				UpLoadFail();
			}
			
		}
		
		@Override
		public void UpLoadFail() {
			//提示图片上传失败
			showToast("上传失败");
		}
	};
	
	/**
	 * 图片上传成功，将图片url，和本地路径返回
	 * @param imageUrl
	 * @param imageFile
	 */
	public void onUpLoadSuccess(String imageUrl, String imageFile) {
	}


    /**
     * 批量上传图片
     * @param list
     */
    public void uploadImage(List<ImageData> list, UpLoadImageListener listener){

        //判断图片非空
        if(null == list || list.size() == 0){

            if(null != listener){
                listener.UpLoadSuccess(null);
            }
            return;
        }

        //上传图片
        ArrayList<String> imgs = new ArrayList<String>();
        ImageData imgData = null;
        for(int i=0; i<list.size(); i++){
            imgData = list.get(i);
            if(imgData.localFileExist()){
                imgs.add(list.get(i).fileName);
            }
        }
        ImageUpload mIUpload = new ImageUpload(mContext, imgs, listener);
        mIUpload.startLoad();

    }

	/**
	 * 显示申请理由
	 * @param view
	 * @param dataMap
	 */
	public void showReasonPopwindow(View view, Map<String, String> dataMap, String checkedKey, boolean showBottomTip, SelectApplyReasonWindow.IOnItemClick onItenClick){
		SelectApplyReasonWindow pop = new SelectApplyReasonWindow(mContext, dataMap, checkedKey, showBottomTip, onItenClick);

		if (!pop.isShowing()) {
			pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);

			int height = pop.getHeight();
			int screenHeight = DeviceUtils.getHeightMaxPx(mContext);
			int maxHeight = ((screenHeight/3)*2);
			if(height > maxHeight){
				height = maxHeight;
				pop.setHeight(height);
			}
		}
	}
}
