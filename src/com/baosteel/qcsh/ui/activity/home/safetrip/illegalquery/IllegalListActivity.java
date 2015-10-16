package com.baosteel.qcsh.ui.activity.home.safetrip.illegalquery;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.IllegalListAdapter;
import com.common.base.BaseActivity;
import com.common.utils.ResourceUtils;
import com.common.view.topbar.HeadView;

/**
 * 违章列表 Created by kuangyong on 15/9/16.
 */
public class IllegalListActivity extends BaseActivity {

	private com.common.view.topbar.HeadView headview;
	private android.widget.TextView tvcarnum;
	private android.widget.TextView tvillegalnum;
	private android.widget.ListView lvillegal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_illegal_list);
		findView();
		setListener();
		initView();
	}

	private void findView() {
        this.lvillegal = (ListView) findViewById(R.id.lv_illegal);
        this.tvillegalnum = (TextView) findViewById(R.id.tv_illegal_num);
        this.tvcarnum = (TextView) findViewById(R.id.tv_carnum);
        this.headview = (HeadView) findViewById(R.id.headview);
	}

	private void setListener() {
        headview.setTitle("违章查询");
        headview.setHeadViewBackGroundColor(getResources().getColor(
                R.color.green_safetrip));
    }

    private void initView() {
		IllegalListAdapter adapter=new IllegalListAdapter(mContext);
		lvillegal.setAdapter(adapter);
		lvillegal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(mContext,IllegalDetialsActivity.class));
			}
		});
        Spanned content=Html.fromHtml( "共有"+ResourceUtils.changeStringColor("#F03A1A","2")+"起违章，其中未处理"+ResourceUtils.changeStringColor("#F03A1A","")+"起");
        tvillegalnum.setText(content);
    }
}
