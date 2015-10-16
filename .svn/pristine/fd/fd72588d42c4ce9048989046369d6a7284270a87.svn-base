package com.baosteel.qcsh.ui.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.DiscountHorizontalListAdapter;
import com.common.view.listview.HorizontialListView;

/**
 * 店铺筛选 Created by lian on 2015/5/22.
 */
public class StoreScreeningPopWindow  {
	private Context context;
	private View parentview;
	private PopupWindowUtils popupWindowUtils;


	public StoreScreeningPopWindow(Context context, View relyview) {
		this.context = context;
		parentview = LayoutInflater.from(context).inflate(
				R.layout.pop_store_screening, null);
		popupWindowUtils = new PopupWindowUtils(context, parentview, relyview,
				Gravity.BOTTOM, 0, 0, R.style.PopupAnimation,0.7f);
		init();

	}

	public void init() {
        TextView btnconfirm = (TextView) parentview.findViewById(R.id.btn_confirm);
        TextView btnreset = (TextView) parentview.findViewById(R.id.btn_reset);
        EditText etpriceend = (EditText) parentview.findViewById(R.id.et_price_end);
        EditText etpricestart = (EditText) parentview.findViewById(R.id.et_price_start);
        CheckBox cbqicaibusiness = (CheckBox) parentview.findViewById(R.id.cb_qicai_business);
        CheckBox cbinventory = (CheckBox) parentview.findViewById(R.id.cb_inventory);
        HorizontialListView lvdiscount = (HorizontialListView) parentview.findViewById(R.id.lv_discount);
        LinearLayout btnrelocation = (LinearLayout) parentview.findViewById(R.id.btn_relocation);
        TextView tvlocation = (TextView) parentview.findViewById(R.id.tv_location);
        DiscountHorizontalListAdapter adapter=new DiscountHorizontalListAdapter(context);
        lvdiscount.setAdapter(adapter);
        btnrelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"定位成功！",Toast.LENGTH_SHORT).show();
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"已重置",Toast.LENGTH_SHORT).show();
            }
        });
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowUtils.getPopupWindow().dismiss();
            }
        });
	}
}
