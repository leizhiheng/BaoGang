package com.baosteel.qcsh.ui.activity.my.setting;

import android.os.Bundle;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.MessageAdapter;
import com.common.base.BaseActivity;

/**
 * 消息中心
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-7-22
 */
public class MyMessageActivity extends BaseActivity {

	public final static String TAG = "MyMessageActivity";

	private ListView mListView;
	
	private MessageAdapter messageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mymessage);
		setTitle("我的消息");
		
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView(){
		mListView = (ListView)findViewById(R.id.xlistview);
		messageAdapter = new MessageAdapter(mContext);
		mListView.setAdapter(messageAdapter);
		
	}
}
