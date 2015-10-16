package com.baosteel.qcsh.task;

import android.content.Context;
import android.os.AsyncTask;

/**
 * 线程
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-23
 */
public class MyTask extends AsyncTask<Integer, Integer, Object> {

	public Context mContext;
	public ILoadCallback loader;

	public MyTask(Context context, ILoadCallback callback) {
		this.mContext = context;
		this.loader = callback;
	}

	@Override
	protected Object doInBackground(Integer... params) {
		if (null != loader) {
			return loader.run();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object object) {
		if (null != loader) {
			loader.loadedCallback(object);
		}
	}
}
