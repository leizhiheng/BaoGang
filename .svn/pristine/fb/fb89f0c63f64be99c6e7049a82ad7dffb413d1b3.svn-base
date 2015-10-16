package com.baosteel.qcsh;

import java.io.File;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.Poi;
import com.baosteel.qcsh.utils.BaiduMapUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * 应用Appcontext类
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-26
 */
public class AppContext extends Application {

	public static AppContext appContext;

	public static String ROOTPATH_NAME 	= "/BaoGang";
	public static String ROOTPATH 		= "/BaoGang";
	public static String IMAGEPATH 		= "/image";
	
	public static boolean isHasUpdate = false;
	
	/**百度地图定位**/
    public LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    public TextView mLocationResult,logMsg;
    public TextView trigger,exit;
    public Vibrator mVibrator;
	
	@Override
	public void onCreate() {
		super.onCreate();
		appContext = this;

		// 初始化目录
		initPath();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
		//初始化imageloader
		initImageLoader(getApplicationContext());
		
		//初始化BaiduMapUtil
		BaiduMapUtil.initiateBaiduMapUtil(this);
	}

	/**
	 * 初始化图片加载器
	 *
	 * @param context
	 */
	public void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "baogang/cache/ImageCache");
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.image_default_rectangle)
				.showImageForEmptyUri(R.drawable.image_default_rectangle).showImageOnFail(R.drawable.image_default_rectangle)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheInMemory(true).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY)
				.denyCacheImageMultipleSizesInMemory().defaultDisplayImageOptions(options)
				.memoryCache(new LruMemoryCache(5 * 1024 * 1024)).discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).discCache(new UnlimitedDiscCache(cacheDir)).build();
		ImageLoader.getInstance().init(config);
	
	}
	
	 /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");// 位置语义化信息
            sb.append(location.getLocationDescribe());
            List<Poi> list = location.getPoiList();// POI信息
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
        }


    }
	
	/**
	 * 建立用户数据文件夹
	 */
	public void initPath() {
		String ROOT;
		// 判断SD卡是否插入
		if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			ROOT = Environment.getExternalStorageDirectory().getPath();
		} else {
			Log.v("MKDIR", "No SD card!!!");
			ROOT = "/data/data";
		}

		// 定义目录文件夹
		if (ROOTPATH.equals(ROOTPATH_NAME)) {
			ROOTPATH = ROOT + ROOTPATH;
			IMAGEPATH = ROOTPATH + IMAGEPATH;
		}

		// 生成sunsacred目录
		File rootPath = new File(ROOTPATH);
		if (!rootPath.exists()) {
			rootPath.mkdirs();
			Log.d("INITPATH", "ROOT");
		}

		// 生成sunsacred/image目录
		File imagePath = new File(IMAGEPATH);
		if (!imagePath.exists()) {
			imagePath.mkdirs();
			Log.d("INITPATH", "IMAGE");
		}
	}

	/**
	 * 获取项目目录-图片目录
	 * 
	 * @return
	 */
	public String getImagePath() {
		return IMAGEPATH;
	}
	
}