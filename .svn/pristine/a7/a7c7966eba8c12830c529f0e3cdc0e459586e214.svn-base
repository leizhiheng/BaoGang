package com.common.net.uploadimg;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Tip;
import com.common.utils.Base64;
import com.common.utils.BitmapUtils;
import com.common.volley.VolleyError;

/**
 * 图片上传
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-10-13
 */
public class ImageUpload {
	
	/**上下文**/
	private Context mContext;
	
	/**本地待上传的文件集合**/
	private ArrayList<String> localImgFileList = null;
	
	//这两个图片集合数量最后一定是一样的，不一样则上传有问题，缺胳膊少腿
	
	/**上传成功返回的图片地址集合**/
	private ArrayList<String> remoteImgUrlList = null;
	
	/**当前正在上传的本地图片索引值**/
	private int curUploadImgIndex = 0;
	
	/**上传回调**/
	private UpLoadImageListener mUpLoadImageListener;
	
	private int flag = 2;

	public ImageUpload(Context mContext, ArrayList<String> hostimageurls, UpLoadImageListener mUpLoadImageListener) {
		this.mContext = mContext;
		this.localImgFileList = hostimageurls;
		this.mUpLoadImageListener = mUpLoadImageListener;
		remoteImgUrlList = new ArrayList<String>();
	}

	/**
	 * 开始上传
	 */
	public void reLoad() {
		if (localImgFileList != null && localImgFileList.size() > 0) {
			Tip.showLoadDialog(mContext, "正在上传");
			
			//默认从第一张开始上传
			imageup(localImgFileList.get(curUploadImgIndex));
			
		} else if (mUpLoadImageListener != null) {
			mUpLoadImageListener.UpLoadFail();
		}
	}

	public void startLoad() {
		this.reLoad();
	}

	/**
	 * 图片上传
	 * @param path
	 */
	private void imageup(String path) {
		String data;
		Bitmap temp = BitmapUtils.showimageFull(path, 1024, 1024);
		data = Base64.imgToBase64(temp);
		temp.recycle();
		
		//上传图片
		String userId = BaoGangData.getInstance().getUserId();
		RequestClient.mobileImageUpload(mContext, userId, data, ConstantsAPI.File_Type_jpg, new RequestCallback<JSONObject>(false) {
			
			@Override
			public void onFinish() {
				super.onFinish();
				if(!isSuccessResult){
					
					//图片上传失败
					Tip.colesLoadDialog();
					Toast.makeText(mContext, "图片上传失败", Toast.LENGTH_LONG).show();
				}
			}
			
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					
					//取得当前上传成功的图片
					String imgUrl = response.optString("imgurl");
					Log.i("ImageUpload", "图片地址：" + imgUrl);
					remoteImgUrlList.add(imgUrl);
					
					if((localImgFileList.size()-1) > curUploadImgIndex){
						//图片尚未全部上传完毕
						curUploadImgIndex++;
						imageup(localImgFileList.get(curUploadImgIndex));
						
					}else{
						
						//图片上传完毕
						Tip.colesLoadDialog();
						Toast.makeText(mContext, "图片上传完毕", Toast.LENGTH_LONG).show();
						if (mUpLoadImageListener != null) {
							mUpLoadImageListener.UpLoadSuccess(remoteImgUrlList);
						}
					}
				}else{
					
					//图片上传失败
					Tip.colesLoadDialog();
					Toast.makeText(mContext, "图片上传失败", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	public interface UpLoadImageListener {

		void UpLoadSuccess(ArrayList<String> netimageurls);

		void UpLoadFail();

	}

	public void upLoadHeadImage() {
		flag = 1;
		this.reLoad();
	}
}
