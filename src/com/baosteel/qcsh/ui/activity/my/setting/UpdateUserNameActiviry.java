package com.baosteel.qcsh.ui.activity.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.AppContext;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;

import org.json.JSONObject;

/**
 * 修改用户名
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-8
 */
public class UpdateUserNameActiviry extends BaseActivity implements View.OnClickListener{

    public static final String USER_NAME="username";
    private String userName;

    private EditText etname;
    private TextView btndel;
    private TextView btncommit;
    private TextView left_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_username);
        userName=getIntent().getStringExtra(USER_NAME);
        findView();
        setListener();
    }

    private void findView(){
        this.btncommit = (TextView) findViewById(R.id.btn_commit);
        this.btndel = (TextView) findViewById(R.id.btn_del);
        this.etname = (EditText) findViewById(R.id.et_name);
        this.left_ib = (TextView) findViewById(R.id.left_ib);
        this.etname.setText(userName);
    }

    private void setListener(){
        btndel.setOnClickListener(this);
        btncommit.setOnClickListener(this);
        left_ib.setOnClickListener(this);
        etname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                if (etname.getText().toString().length() > 20) {
                    Toast.makeText(mContext, "您输入的用户名已经超过最大的长度！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_commit://提交
                commit();
                break;
            case R.id.btn_del://删除
                etname.setText("");
                break;
            case R.id.left_ib://删除
                UpdateUserNameActiviry.this.finish();
                break;
        }
    }

    private void commit(){
        String change_name = etname.getText().toString().trim();
        if(etname.equals("")){
            Toast.makeText(mContext, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
            //^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{2,15}$
        }
        
        if(!change_name.matches("[-\\w\u4e00-\u9fa5]{1,20}")){
            Toast.makeText(mContext, "用户名不符合命名规则，请重新输入！",Toast.LENGTH_SHORT).show();
            etname.setText("");
            return;
        }
       
        int length = change_name.length(); 
        if(length < 4 || length > 20){
        	showToast("字符长度为4-20");
        	return;
        }
        setNickName(change_name);
        finish();
    }

    private void setNickName(String nickName){
        if(!userIsLogin()){
            return;
        }
        String userId = BaoGangData.getInstance().getUser().userId;
        RequestClient.queryAppUpdateUserInfo(mContext, "", nickName, "", userId, "", "", new RequestCallback<JSONObject>(false) {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    //获取状态信息
                    String msg = JSONParseUtils.getString(response, "msg");
                    showToast(msg);
                }
            }
        });
    }
}
