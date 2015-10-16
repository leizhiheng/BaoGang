package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.AppContext;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SysMessage;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.ui.activity.my.LoginActivity;
import com.baosteel.qcsh.ui.activity.my.setting.MyMessageActivity;
import com.baosteel.qcsh.utils.StringUtils;

public class MessageAdapter extends BaseAdapter {

	private List<SysMessage> messages = new ArrayList<SysMessage>();
	Context context;

	public MessageAdapter(Context context) {
		this.context = context;
	}

	public List<SysMessage> returnDatas() {
		return messages;
	}

	public void add(List<SysMessage> data) {
		this.messages.addAll(data);
		notifyDataSetChanged();
	}

	public void refresh(List<SysMessage> arrayList) {
		this.messages.clear();
		this.messages = null;
		this.messages = arrayList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		//return null != messages ? messages.size() : 0;
		return 20;
	}

	@Override
	public Object getItem(int position) {
		return messages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.adapter_sysmessage_item, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}


		return convertView;
	}

	class ViewHolder {
		TextView title;
		TextView time;
		ImageView img_arraw;
	}


}