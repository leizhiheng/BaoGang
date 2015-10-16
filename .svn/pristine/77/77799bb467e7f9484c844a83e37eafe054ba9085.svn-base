package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HealthyContactPerson;

public class ChooseLiveInPersonAdapter extends BaseAdapter {

	public interface OnClickEditListener {
		void onEdit(int position);
	}
	private OnClickEditListener mListener;
	/**
	 * 
	 * @Description 通过该方法设置毁掉，当Item的编辑按钮被点击时，通知外部Activity
	 * @Author blue
	 * @Time 2015-9-17
	 */
	public void setOnClickEditListener(OnClickEditListener listener) {
		mListener = listener;
	}
	
	private Context mContext;
	private List<HealthyContactPerson> mDatas;
	private LayoutInflater mInflater;
	
	private int mSelectedIndex;
	
	public ChooseLiveInPersonAdapter(Context context, List<HealthyContactPerson> datas) {
		mContext = context;
		mDatas =datas;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		HealthyContactPerson person = mDatas.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_choose_live_in_person, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		setCheckBox(position, holder.checkBox);
		holder.tvName.setText(person.getName());
		holder.tvPhone.setText(person.getPhone());
		holder.tvSex.setText(person.getSex());
		holder.tvCode.setText(person.getIdentiryCode());
		holder.ivEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/**
				 * 将点击事件通知到外部
				 */
				if (mListener != null) {
					mListener.onEdit(position);
				}
			}
		});
		
		return convertView;
	}
	
	class ViewHolder{
		TextView tvName;
		TextView tvPhone;
		TextView tvSex;
		TextView tvCode;
		CheckBox checkBox;
		ImageView ivEdit;
		
		public ViewHolder(View v) {
			tvName = (TextView) v.findViewById(R.id.tv_live_in_name);
			tvPhone = (TextView) v.findViewById(R.id.tv_live_in_phone);
			tvSex = (TextView) v.findViewById(R.id.tv_live_in_sex);
			tvCode = (TextView) v.findViewById(R.id.tv_live_in_identity_code);
			checkBox = (CheckBox) v.findViewById(R.id.checkbox_selected);
			ivEdit = (ImageView) v.findViewById(R.id.iv_live_in_edit);
		}
		
	}
	
	
	
	private void setCheckBox(final int position, CheckBox checkBox) {
		if (mDatas.get(position).isSelected()) {
			checkBox.setChecked(true);
		} else {
			checkBox.setChecked(false);
		}
		
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked && mSelectedIndex != position) {
					mDatas.get(mSelectedIndex).setSelected(false);
					mDatas.get(position).setSelected(true);
					mSelectedIndex = position;
					ChooseLiveInPersonAdapter.this.notifyDataSetChanged();
				}
			}
		});
	}
	
	public HealthyContactPerson getSelectedPerson() {
		return mDatas.get(mSelectedIndex);
	}

}

