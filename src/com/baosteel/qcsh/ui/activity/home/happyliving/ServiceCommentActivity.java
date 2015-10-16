package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.AmendComment;
import com.baosteel.qcsh.model.ServiceCommentData;
import com.baosteel.qcsh.ui.adapter.ServiceCommentAdapter;
import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment;
import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment.OnRefreshLisenter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：乐居宝-》预约服务-》服务评价
 * @author blue
 * @Time 2015-9-9
 *
 */
public class ServiceCommentActivity extends BaseActivity{

	private TitleBar mTitleBar;
	private RefreshListFragment mRefreshListFragment;
	
	/**
	 * 评价数据
	 */
	private List<ServiceCommentData> mServiceCommentDatas;
	
	private ServiceCommentAdapter mAdapter;
	
	private int mCurPage;
	private int mPageSize = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_comment);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mServiceCommentDatas = new ArrayList<ServiceCommentData>();
		mAdapter = new ServiceCommentAdapter(this, mServiceCommentDatas);
	}
	
	private void initView() {
		setTitle("服务评价");
		
		mRefreshListFragment = (RefreshListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_refresh_listview);
		mRefreshListFragment.setAdapter(mAdapter);
		mRefreshListFragment.setOnRefreshLisenter(new OnRefreshLisenter() {
			
			@Override
			public void onRefresh() {
				loadData();
			}
		});
		
	}
	
	private void loadData() {
		String picUrl = "http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg";
		for (int i = mCurPage * mPageSize; i < mCurPage * mPageSize + 2; i++) {
			ServiceCommentData data = new ServiceCommentData();
			data.setmUserName("评论者 "+i);
			data.setmComment("修理师傅很细心，上门服务也很准准时。修理技术也非常好很快就修好了，给满分！");
			//设置图片
			List<String> pics = new ArrayList<String>();
			pics.add(picUrl);
			pics.add(picUrl);
			data.setmImgUrls(pics);
			//设置追加评论
			data.setmAmendComments(setAmendComments());
			mServiceCommentDatas.add(data);
		}
		
		for (int i = mCurPage * mPageSize + 2; i < mCurPage * mPageSize + 5; i++) {
			ServiceCommentData data = new ServiceCommentData();
			data.setmUserName("评论者 "+i);
			data.setmComment("修理师傅很细心，上门服务也很准准时。修理技术也非常好很快就修好了，给满分！");
			//设置图片
			List<String> pics = new ArrayList<String>();
			pics.add(picUrl);
			pics.add(picUrl);
			data.setmImgUrls(pics);
			mServiceCommentDatas.add(data);
		}
		
		mCurPage++;
		mAdapter.notifyDataSetChanged();
	}
	
	private List<AmendComment> setAmendComments() {
		String picUrl = "http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg";
		
		List<AmendComment> datas = new ArrayList<AmendComment>();
		
		for (int i = 0; i < 2; i++) {
			AmendComment comment = new AmendComment();
			comment.setmComment("这是追加评价！修理师傅很细心，上门服务也很准准时！");
			//设置图片
			List<String> pics = new ArrayList<String>();
			pics.add(picUrl);
			pics.add(picUrl);
			comment.setmImgUrls(pics);
			datas.add(comment);
		}
		return datas;
	}
}
