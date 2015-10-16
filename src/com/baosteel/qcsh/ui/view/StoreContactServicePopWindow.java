package com.baosteel.qcsh.ui.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductCategory;

/**
 * 店铺分类 Created by lian on 2015/5/14.
 */
public class StoreContactServicePopWindow {
	private Context context;
	private View parentview;
	private PopupWindowUtils popupWindowUtils;
	private List<ProductCategory> categories;

	public StoreContactServicePopWindow(Context context, View relyview) {
		this.context = context;
		parentview = LayoutInflater.from(context).inflate(
				R.layout.pop_store_contact_service, null);
		popupWindowUtils = new PopupWindowUtils(context, parentview, relyview,
				Gravity.BOTTOM, 0, 0, R.style.PopupAnimation, 0.25f);
		init();

	}

	public void init() {
        TextView btncancel = (TextView) parentview.findViewById(R.id.btn_cancel);
        LinearLayout btnphone = (LinearLayout)  parentview.findViewById(R.id.btn_phone);
        LinearLayout btncontactservice = (LinearLayout)  parentview.findViewById(R.id.btn_contact_service);
        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "电话联系", Toast.LENGTH_SHORT).show();
                popupWindowUtils.getPopupWindow().dismiss();
            }
        });
        btncontactservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "联系客服", Toast.LENGTH_SHORT).show();
                popupWindowUtils.getPopupWindow().dismiss();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowUtils.getPopupWindow().dismiss();
            }
        });
    }
}
