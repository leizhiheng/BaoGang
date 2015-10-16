package com.baosteel.qcsh.utils;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baosteel.qcsh.database.adress.DBOpenHelper;

/**
 * 
 * @description 百度地图，定位工具类
 * @author blue
 * @Time 2015-10-15
 *
 */
public class BaiduMapUtil {
	
	private static final String TAG = "BaiduMapUtil";
	
	private Context mContext;
	
    public LocationClient mLocationClient;
    /**定位成功后的回调，与LocationClient对象绑定*/
    public MyLocationListener mMyLocationListener;
    public Vibrator mVibrator;
    
    private LocationMode tempMode = LocationMode.Hight_Accuracy;
    private String tempcoor="gcj02";//"gcj02":国家测绘局标准;"bd09ll":百度经纬度标准;"bd09":百度墨卡托标准
    
    private static BaiduMapUtil mInstance;
    
    /**
     * 由于定位成功后会多次调用回调方法，使用该Boolean值控制，只执行一次回调方法中的代码
     */
    private boolean isFirstLoc = true;
    
    public interface OnLocateLResultistener {
    	void onLocateResult(BDLocation location);
    }
    
    private OnLocateLResultistener mOnLocateLResultistener;
    
	private BaiduMapUtil(Context context) {
		
		mContext = context;
		
		// 百度地图——地图图层功能SDK初始化。在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(context);
		
		// 百度地图——定位功能初始化
		mLocationClient = new LocationClient(context.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        //该回调又外部定义
        mLocationClient.registerLocationListener(mMyLocationListener);
        mVibrator =(Vibrator)context.getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
	
        initLocation();
	}
	
	public static void initiateBaiduMapUtil(Context context) {
		mInstance = new BaiduMapUtil(context);
	}
	
	public static BaiduMapUtil getInstance() {
		return mInstance;
	}
	
	
	 private void initLocation(){
         LocationClientOption option = new LocationClientOption();
         option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
         option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
         int span=1000;
         option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
         option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
         option.setOpenGps(true);//可选，默认false,设置是否使用gps
         option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
         option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
         option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
         option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
         option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
         mLocationClient.setLocOption(option);
	}
	 
	/**
	 * @Description 设置定位回调方法，定位结果在该回调方法中获取。在调用start方法之前，需要调用该方法，否则收不到定位结果。
	 * @Author blue
	 * @Time 2015-10-15
	 */
    public void setOnLocateLResultistener(OnLocateLResultistener listener) {
    	this.mOnLocateLResultistener = listener;
    }
	
	/**
	 * 
	 * @Description 开始定位
	 * @Author blue
	 * @Time 2015-10-15
	 */
	public void start() {
		Log.d(TAG, "==> start");
		isFirstLoc = true;//每次开始定位时，将设置为true
		mLocationClient.start();
	}
	
	/**
	 * 
	 * @Description 停止定位。每次结束定位需调用该方法停止定位
	 * @Author blue
	 * @Time 2015-10-15
	 */
	public void stop() {
		Log.d(TAG, "==> stop");
		mLocationClient.stop();
	}
	
	/**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
        	
        	if (location == null) {
				showToast("定位失败");
				return;
			}
        	/*
        	 * 如果不是第一次调用该回调方法，则直接返回
        	 */
        	if (isFirstLoc) {
				isFirstLoc = false;
        	} else {
        		return;
			}
        	
            //Receive Location
        	StringBuffer sb = new StringBuffer(256);
        	if (location.getLocType() == BDLocation.TypeServerError) {
//                showToast("服务端网络定位失败");
                return;
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                showToast("网络不通导致定位失败，请检查网络是否通畅");
                return;              
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                showToast("无法获取有效定位依据导致定位失败");
                return;                 
            }
        	
        	/*
        	 * 将定位结果通回调方法通知到外部
        	 */
        	if (mOnLocateLResultistener != null) {
				mOnLocateLResultistener.onLocateResult(location);
			}

//            
//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());
//            if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());// 单位：公里每小时
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());// 单位：米
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                //运营商信息
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//            }
//            sb.append("\nlocationdescribe : ");// 位置语义化信息
//            sb.append(location.getLocationDescribe());
//            List<Poi> list = location.getPoiList();// POI信息
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
//            Log.i(TAG, sb.toString());
//            showToast(sb.toString());
        }

    }
    
    private void showToast(String msg) {
    	Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
