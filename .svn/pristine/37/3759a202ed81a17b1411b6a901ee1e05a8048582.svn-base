package com.baosteel.qcsh.ui.activity.home.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.utils.DialogUtil;

/**
 * 旅行宝-国内酒店
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class HotelActivity extends BaseActivity implements OnClickListener {

	/**目的地**/
	private static final int REQUEST_CODE_DESTINATION 	= 1;
	
	/**关键字**/
	private static final int REQUEST_CODE_KEYWORDS 		= 2;
	
	
	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**目的地**/
	private RelativeLayout destinationRl;
	
	/**目的地**/
	private TextView destinationTv;
	
	/**入住日期**/
	private RelativeLayout beginTimeRl;
	
	/**入住日期**/
	private TextView beginTimeTv;
	
	/**入住日期-右边提示**/
	private TextView beginTimeTipTv;
	
	/**离店日期**/
	private RelativeLayout leaveDateRl;
	
	/**离店日期**/
	private TextView leaveDateTv;
	
	/**共多少天**/
	private TextView totalDayTv;
	
	/**关键字**/
	private RelativeLayout keywordsRl;
	
	/**关键字**/
	private TextView keywordsTv;
	
	/**星级价格**/
	private RelativeLayout priceStartRl;
	
	/**星级价格**/
	private TextView priceStartTv;
	
	/**开始搜索**/
	private Button startSearchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_hotel);

		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initView() {

		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setBackgroudValue(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		mTitleBar.setTitle("国内酒店");

		destinationRl = (RelativeLayout) findViewById(R.id.destinationRl);
        destinationTv = (TextView) findViewById(R.id.destinationTv);
        beginTimeRl = (RelativeLayout) findViewById(R.id.beginTimeRl);
        beginTimeTv = (TextView) findViewById(R.id.beginTimeTv);
        beginTimeTipTv = (TextView) findViewById(R.id.beginTimeTipTv);
        leaveDateRl = (RelativeLayout) findViewById(R.id.leaveDateRl);
        leaveDateTv = (TextView) findViewById(R.id.leaveDateTv);
        totalDayTv = (TextView) findViewById(R.id.totalDayTv);
        keywordsRl = (RelativeLayout) findViewById(R.id.keywordsRl);
        keywordsTv = (TextView) findViewById(R.id.keywordsTv);
        priceStartRl = (RelativeLayout) findViewById(R.id.priceStartRl);
        priceStartTv = (TextView) findViewById(R.id.priceStartTv);
        startSearchBtn = (Button) findViewById(R.id.startSearchBtn);
        
        destinationRl.setOnClickListener(this);
        beginTimeRl.setOnClickListener(this);
        leaveDateRl.setOnClickListener(this);
        keywordsRl.setOnClickListener(this);
        startSearchBtn.setOnClickListener(this);
		
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.destinationRl:
			
			//目的地
			Intent intent = new Intent(mContext, DestinationActivity.class);
			startActivityForResult(intent, REQUEST_CODE_DESTINATION);
			
			break;

		case R.id.beginTimeRl:

			//住店日期
			DialogUtil.showBirthdayDialog(beginTimeTv, mContext);
			
			break;
			
		case R.id.leaveDateRl:
			
			//离店日期
			DialogUtil.showBirthdayDialog(leaveDateTv, mContext);
			
			break;
		case R.id.keywordsRl:
			
			//关键字
			Intent keywords = new Intent(mContext, KeywordsActivity.class);
			startActivityForResult(keywords, REQUEST_CODE_KEYWORDS);
			
			
			break;
		case R.id.startSearchBtn:
			
			//开始搜索-进入酒店列表
			Intent hotel = new Intent(mContext, HotelSearchActivity.class);
			startActivity(hotel);
			
			
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != RESULT_OK){
			return;
		}
		
		switch (requestCode) {
		case REQUEST_CODE_DESTINATION:
			
			//目的地
			String name = data.getStringExtra("name");
			String id = data.getStringExtra("id");
			destinationTv.setText(name);
			destinationTv.setTag(id);
			
			break;
		}
	}
}
