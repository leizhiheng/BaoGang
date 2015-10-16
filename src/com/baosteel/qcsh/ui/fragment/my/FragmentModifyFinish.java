package com.baosteel.qcsh.ui.fragment.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * 修改密码第三步-完成
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentModifyFinish extends Fragment {

	private OnStepToModifyFinishListener mListener;

	private TextView tv_tip;						//提示

	interface OnStepToModifyFinishListener {
		void stepToModifyPw();
	}

	public void setOnStepToModifyPwListener(OnStepToModifyFinishListener listener) {
		mListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_modify_mobile_step_3, null);
		tv_tip= (TextView) view.findViewById(R.id.tv_tip);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if(null != msg){
			tv_tip.setText(msg);
		}
	}

	private String msg = null;
	public void setTipMsg(String tipMsg){
		msg = tipMsg;
	}
}
