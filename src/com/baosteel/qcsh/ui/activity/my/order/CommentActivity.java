package com.baosteel.qcsh.ui.activity.my.order;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.base.BaseCameraActivity;
import com.common.net.uploadimg.UploadImageAdapter;
import com.common.utils.DeviceUtils;

/**
 * Created by lenovo on 2015/9/21.
 */
public class CommentActivity extends BaseCameraActivity {
    private UploadImageAdapter adapter;//上传图片适配器
    private GridView mGv_comment_pic;
    private TitleBar mTitle_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initViews();
        initData();
    }

    private void initViews() {
        mGv_comment_pic = (GridView) findViewById(R.id.gv_comment_pic);
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
    }

    private void initData() {
        mTitle_bar.setTitle("评价晒单");
        mTitle_bar.setBackgroud(R.color.index_red);
        mTitle_bar.setRightIconVisibility(0, View.VISIBLE);
        adapter = new UploadImageAdapter(mContext, DeviceUtils.getWidthMaxPx(mContext) / 2, 3);
        adapter.setMax(2);
        mGv_comment_pic.setAdapter(adapter);
        mGv_comment_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // 上传图片
                if (adapter.isClickAddPic(i)) {

                    // 打开相册，相机
                    showCameraPopwindow(view, false, false);
                }
            }
        });
    }
}
