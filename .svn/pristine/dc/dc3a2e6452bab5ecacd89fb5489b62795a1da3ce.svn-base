package com.baosteel.qcsh.ui.fragment.my;

import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseFragment;

import org.json.JSONObject;

/**
 * 修改密码第一步
 *
 * @author 刘远祺
 * @todo TODO
 * @date 2015-9-18
 */
public class FragmentModifyEmailOne extends BaseFragment implements View.OnClickListener {

    private Handler mHandler;

    /**
     * 邮箱
     **/
    private TextView emailTv;

    /**
     * 登录密码
     **/
    private EditText loginPasswordEt;

    private String email;                               //邮箱

    private OnStepToModifyPwListener mListener;

    public interface OnStepToModifyPwListener {
        void stepToModifyPw(String userPwd);
    }

    public void setOnStepToModifyPwListener(OnStepToModifyPwListener listener) {
        mListener = listener;
    }

    public FragmentModifyEmailOne(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_email_step_1, null);

        //初始化控件
        emailTv = (TextView) view.findViewById(R.id.emailTv);
        loginPasswordEt = (EditText) view.findViewById(R.id.loginPasswordTv);

        view.findViewById(R.id.next_step).setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        email = BaoGangData.getInstance().getUserInfo().getEmail();
        if(TextUtils.isEmpty(email)){
            emailTv.setText("温馨提示：您正在绑定账号关联邮箱！");
        }else{
            emailTv.setText("您当前的邮箱地址：" + StringUtils.changeEmailForrmat(email));
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step:
                queryAppUpdateEmailFirstValidate();

                break;

        }
    }

    /**
     * 验证身份
     */
    private void queryAppUpdateEmailFirstValidate() {
        final String pwd = loginPasswordEt.getText().toString();//密码
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入登录密码！");
            return;
        }
        RequestClient.queryAppUpdateEmailFirstValidate(mContext, BaoGangData.getInstance().getUserId(), pwd, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(JSONParseUtils.isSuccessRequest(mContext, response)){
                    //下一步
                    if (mListener != null) {
                        mListener.stepToModifyPw(pwd);
                    }
                    String msg=response.optString("msg");
                    showToast(msg);
                    showToast("验证成功！");
                }
            }
        });
    }
}
