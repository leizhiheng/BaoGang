package com.common.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.common.okhttp.OkHttpStack;
import com.common.volley.Request;
import com.common.volley.RequestQueue;
import com.squareup.okhttp.OkHttpClient;

/**
 * 单例模式，构造全局唯一的RequestQueue实例和ImageLoader实例
 */
public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public RequestQueue getRequestQueueOld() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), new OkHttpStack(new OkHttpClient()));
        }
        return mRequestQueue;
    }
    
    
    /**将request添加到请求队列
     *
     * @param req  具体请求实现类
     * @param <T>  请求实现类的数据返回类型
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 停止整个请求队列
     */
    public  void stopRequestQueue() {
        getRequestQueue().stop();
    }

    /**
     * 取消对应的tag值的请求
     *
     * @param tag 标签
     */
    public  void cancelAll(final Object tag) {
        getRequestQueue().cancelAll(tag);
    }

    /**
     * 去掉对应的request对应的缓存
     * @param cachekey request对应的cacheKey
     */
    public  void dropCache(String cachekey){
        getRequestQueue().getCache().remove(cachekey);
    }
}
