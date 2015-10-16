package com.baosteel.qcsh.ui.activity.prodcut;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.ui.view.BottomPopWindow;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.WebViewUtil;
import com.common.base.BaseFragment;
import com.common.view.webview.MyWebView;

import org.json.JSONObject;

/**
 * 舌尖宝商品详情-详情
 * Created by kuangyong on 15/9/1.
 */
public class TongueTipDetailsFragment extends BaseFragment{
    // --------------------产品轮换图---------
    private WebView webview;
    private String goodsId;//商品id
    private String content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.layout_fragment_tonguetip_details, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        queryAppGoodsIntroduce();
    }

    private void findView() {
        webview = (MyWebView)findViewById(R.id.webview);
    }

    /**
     * 商品参数
     */
    private void queryAppGoodsIntroduce(){
        goodsId=85+"";
        RequestClient.queryAppGoodsIntroduce(mContext, goodsId, "2", "2", new RequestCallback<JSONObject>(false) {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    JSONObject returnMap = response.optJSONObject("returnMap");
                    content = returnMap.optString("content");//商品参数
                    initView();
                }
            }
        });
    }


    private void initView() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
//		webSettings.setLoadWithOverviewMode(true);
//		webSettings.setBuiltInZoomControls(true);
//		webSettings.setSupportZoom(true);			//设定支持缩放
//		webSettings.setUseWideViewPort(true);		//设定支持viewport
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);//可以缩放
        webSettings.setBuiltInZoomControls(true);//显示放大缩小
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);//默认缩放模式
        webview.setBackgroundResource(R.color.transparent); // 设置背景色
        webview.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
        webview.setVisibility(View.INVISIBLE);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // 结束
                super.onPageFinished(view, url);
                webview.setVisibility(View.VISIBLE);
            }

        });
        if (!TextUtils.isEmpty(content)) {
            loadWebData();
        } else {
            content = "暂无信息";
            loadWebData();
        }
    }

    public void loadWebData() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null,
                    WebViewUtil.initWebViewFor19(content), "text/html",
                    "utf-8", null);
        }
    }
}
