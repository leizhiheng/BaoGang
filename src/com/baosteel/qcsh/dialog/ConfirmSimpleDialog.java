package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 *确认对话框 Created by kuangyong on 15/9/14.
 */
public class ConfirmSimpleDialog extends Dialog {

	private Context context;
	private LinearLayout btnclose;              //关闭按钮
	private ImageView ivicon;                   //标题图标
	private TextView tvtitle;                   //标题
	private TextView tvtip;                     //提示
	private TextView btnconfirm;                //确认按钮

	public ConfirmSimpleDialog(Context context) {
		super(context, R.style.dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}

	public void init() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_confirm_simple, null);
		this.btnconfirm = (TextView) view.findViewById(R.id.btn_confirm);
		this.tvtip = (TextView) view.findViewById(R.id.tv_tip);
		this.tvtitle = (TextView) view.findViewById(R.id.tv_title);
		this.ivicon = (ImageView) view.findViewById(R.id.iv_icon);
		this.btnclose = (LinearLayout) view.findViewById(R.id.btn_close);
        btnclose.setOnClickListener(new clickListener());
        btnconfirm.setOnClickListener(new clickListener());
		setContentView(view);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
		lp.height = (int) (d.heightPixels * 0.35); // 高度设置为屏幕的0.35
		dialogWindow.setAttributes(lp);
	}

    /**
     * 设置dialog的图标
     * @param src
     */
    public void setDialogTitleIcon(int src){
		ivicon.setImageResource(src);
    }

    /**
     * 设置dialog标题
     * @param title
     */
    public void setDialogTitle(String title){
        tvtitle.setText(title);
    }

    /**
     * 设置dialog提示
     * @param tip
     */
    public void setDialogTip(String tip){
        tvtip.setText(tip);
    }

	/**
	 * 设置提示显示/隐藏
	 * @param visible
	 */
	public void setDialogTipVisible(int visible){
		tvtip.setVisibility(visible);
	}

	private class clickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.btn_close://
			case R.id.btn_confirm://
                if(isShowing()){
                    dismiss();
                }
				break;
			}
		}
	}
}
