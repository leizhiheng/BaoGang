package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.MemberRank;
import com.baosteel.qcsh.model.UserInfo;
import com.baosteel.qcsh.model.VIPLevel;
import com.baosteel.qcsh.ui.activity.CommonWebPageActivity;
import com.baosteel.qcsh.ui.adapter.MyRankAdapter;
import com.baosteel.qcsh.ui.adapter.MyRankChangeAdapter;
import com.baosteel.qcsh.ui.view.SpringProgressView;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.imageview.CircleImageView;
import com.common.view.listview.MyListView;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 会员等级页面
 * 
 * @author 刘远祺
 * 
 */
public class AccountLevelActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = "AccountLevelActivity";

	/**成长值**/
	private String growthValue;
	
	/**等级**/
	private String gradeExplain;
	
	/****/
	private String nextGrowthValue;
	
	/****/
	private String nextGrowthNeed;
	
	/****/
	private String nextGrowthName;

	/****/
	private TextView my_rank_level;
	
	/****/
	private TextView rank_level_next;
	
	/****/
	private TextView member_discount_des;
	
	/****/
	private ImageView my_rank_icon;
	
	/****/
	private CircleImageView user_icon;
	
	/****/
	private MyListView rankListView;

	/****/
	private MyListView rankChangeListView;
	
	/****/
	private SpringProgressView member_rank_level_bar;

	/****/
	private MyRankAdapter mAdapter;
	
	/****/
	private MyRankChangeAdapter mChnageAdapter;

	private TextView my_rank_levelTv;
	private TextView tv_current_progree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_rank);
		init();
		setTitle("会员等级");
		loadVipLevel();
		// loadMemberRankInfo();
	}

	public void init() {
		
		user_icon = (CircleImageView) findViewById(R.id.my_rank_level_head);
		my_rank_level = (TextView) findViewById(R.id.my_rank_level);
		tv_current_progree = (TextView) findViewById(R.id.tv_current_progree);
		my_rank_icon = (ImageView) findViewById(R.id.my_rank_icon);
		rank_level_next = (TextView) findViewById(R.id.my_rank_level_next);
		my_rank_levelTv = (TextView) findViewById(R.id.my_rank_levelTv);
		member_discount_des = (TextView) findViewById(R.id.my_rank_level_my_rank_level_discount_content);
		rankListView = (MyListView) findViewById(R.id.listview_rank);
		member_rank_level_bar = (SpringProgressView) findViewById(R.id.my_rank_level_bar);
		
		//成长值说明
		findViewById(R.id.growTipTv).setOnClickListener(this);
		
		
		mAdapter = new MyRankAdapter(mContext, getData());
		rankListView.setAdapter(mAdapter);
		
		
		mChnageAdapter = new MyRankChangeAdapter(mContext, getData());
		rankChangeListView = (MyListView)findViewById(R.id.listview_rank_change);
		rankChangeListView.setAdapter(mChnageAdapter);
		
		
		my_rank_level.setFocusable(true);
		my_rank_level.setFocusableInTouchMode(true);
		my_rank_level.requestFocus();

		member_rank_level_bar.setMaxCount(100);
		member_rank_level_bar.setCurrentCount(50);
	}

	/**
	 * 构造会员等级数据
	 * @return
	 */
	private List<MemberRank> getData(){
		List<MemberRank> list = new ArrayList<MemberRank>();
		list.add(new MemberRank(R.drawable.dengji_hong, ""));
		list.add(new MemberRank(R.drawable.dengji_chen, ""));
		list.add(new MemberRank(R.drawable.dengji_huang, ""));
		list.add(new MemberRank(R.drawable.dengji_lv, ""));
		list.add(new MemberRank(R.drawable.dengji_qing, ""));
		list.add(new MemberRank(R.drawable.dengji_lan, ""));
		list.add(new MemberRank(R.drawable.dengji_zi, ""));
		return list;
	}

	private void loadVipLevel(){
		if(!userIsLogin()){
			return;
		}
		String userId = BaoGangData.getInstance().getUser().userId;
		RequestClient.queryAppVIPLevel(mContext, userId, new RequestCallback<JSONObject>(false) {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					JSONObject returnMap = response.optJSONObject("returnMap");

					//解析userId
					Gson gson = new Gson();
					VIPLevel vipLevel = gson.fromJson(returnMap.toString(), VIPLevel.class);
					if (null != vipLevel) {
					
						ImageManager.Load(vipLevel.getHeadUrl(), user_icon);
						member_rank_level_bar.setMaxCount(100);
						member_rank_level_bar.setCurrentCount(Integer.parseInt(vipLevel.getGrowValue()));
						tv_current_progree.setText(Integer.parseInt(vipLevel.getGrowValue())+"");
						my_rank_levelTv.setText(vipLevel.getGradeName());
						rank_level_next.setText("再累计"+vipLevel.getAwayNextGradeScore()+"成长值到"+vipLevel.getNextGradeName());
						
						
					} else {
						//用户信息解析错误
						showToast("获取用户信息失败");
					}
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.growTipTv:
			
			//成长值说明
			Intent i = new Intent();
			i.setClass(mContext, CommonWebPageActivity.class);
			i.putExtra(CommonWebPageActivity.Title, "成长值说明");
			i.putExtra(CommonWebPageActivity.Content_Type, ConstantsAPI.Content_Type_2);
			startActivity(i);
			
			
			break;
		}
	}
}
