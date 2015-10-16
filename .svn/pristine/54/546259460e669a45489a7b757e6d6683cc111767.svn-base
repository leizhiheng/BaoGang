package com.baosteel.qcsh.ui.activity.my.order;

import android.os.Bundle;
import android.view.View;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * Created by lenovo on 2015/9/21.
 */
public class AddCommentActivity extends BaseActivity {
    private TitleBar mTitle_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        initViews();
        initData();
    }

    private void initViews(){
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
    }

    private void initData(){
        mTitle_bar.setTitle("追加评论");
        mTitle_bar.setRightIconVisibility(0, View.VISIBLE);
        mTitle_bar.setBackgroud(R.color.index_red);
    }
}
