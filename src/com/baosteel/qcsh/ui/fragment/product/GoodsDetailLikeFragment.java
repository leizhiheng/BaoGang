package com.baosteel.qcsh.ui.fragment.product;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductMaybeLike;
import com.baosteel.qcsh.ui.adapter.GoodsDetailLikeGridViewAdapter;
import com.common.base.BaseFragment;

/**
 * 猜你喜欢Fragment
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-2
 */
public class GoodsDetailLikeFragment extends BaseFragment implements OnItemClickListener {

	/**GridView控件**/
	private GridView mGridView;

	/**猜你喜欢数据**/
	private List<ProductMaybeLike> listDatas;

	/**猜你喜欢适配器**/
	private GoodsDetailLikeGridViewAdapter adapter;

	public GoodsDetailLikeFragment() {
	}

	public void setData(List<ProductMaybeLike> listDatas) {
		this.listDatas = listDatas;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_goods_detail_like, container, false);
		initData();

		initViews(view);
		return view;
	}

	private void initData() {
		if (listDatas == null) {
			listDatas = new ArrayList<ProductMaybeLike>();
		}

	}

	public void initViews(View view) {
		mGridView = (GridView) view.findViewById(R.id.likeGridView);

		adapter = new GoodsDetailLikeGridViewAdapter(getActivity(), listDatas);
		mGridView.setAdapter(adapter);
		mGridView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		
		//跳转到商品详情
		ProductMaybeLike bean = listDatas.get(position);
		startToProductDetailActivity(bean.getId(), bean.getGoods_genre());
	}

}