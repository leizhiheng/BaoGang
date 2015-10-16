package com.baosteel.qcsh.ui.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.WebViewUtil;

/**
 * 商品规格参数
 * Created by lian on 2015/5/14.
 */
public class BottomPopWindow {
    private Context context;
    private View parentview;
    private PopupWindowUtils popupWindowUtils;
    private Handler mHandler;
    private WebView web_content;
    private String content;

    public BottomPopWindow(Context context,View relyview,String content){
        this.context=context;
        parentview= LayoutInflater.from(context).inflate(R.layout.layout_pop_bottom, null);
        popupWindowUtils=new PopupWindowUtils(context, parentview, relyview, Gravity.BOTTOM,0,0,R.style.PopupAnimation,0.5f);
        this.content=content;
        init();

    }
    public void init(){
        web_content= (WebView) parentview.findViewById(R.id.web_content);
        LinearLayout btn_close= (LinearLayout) parentview.findViewById(R.id.btn_close);
        if (!TextUtils.isEmpty(content)) {
            loadWebData();
        } else {
            content = "暂无信息";
            loadWebData();
        }
        //设置缩放开关
        web_content.getSettings().setSupportZoom(true);
        //4.4以上的缩放支持
        if(android.os.Build.VERSION.SDK_INT>=19) {
            web_content.getSettings().setBuiltInZoomControls(true);
        }
        web_content.getSettings().setUseWideViewPort(true);
        web_content.getSettings().setLoadWithOverviewMode(true);
        web_content.getSettings().setDisplayZoomControls(true);
        WebSettings webSettings = web_content.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        web_content.setBackgroundResource(R.color.transparent); // 设置背景色
        web_content.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
        web_content.setVisibility(View.INVISIBLE);
        web_content.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // 结束
                super.onPageFinished(view, url);
                web_content.setVisibility(View.VISIBLE);
            }

        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=popupWindowUtils){
                    popupWindowUtils.getPopupWindow().dismiss();
                }
            }
        });
    }

    public void loadWebData() {
        if (web_content != null) {
            web_content.loadDataWithBaseURL(null,
                    WebViewUtil.initWebViewFor19(content), "text/html",
                    "utf-8", null);
        }
    }
}
