package com.baosteel.qcsh.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.baosteel.qcsh.R;

public class PopupWindowUtils {
    private Context context;
    private PopupWindow popupWindow;
    View view;
    View downview;
    private float popHeightScale;//弹出窗占屏幕比例

    public PopupWindowUtils(Context context,
                            View view, View downview) {
        this.context = context;
        this.view = view;
        this.downview = downview;
        init();
    }

    public PopupWindowUtils(Context context,
                            View view, View relayview,int location,int x,int y,int anim,float popHeightScale){
        this.context = context;
        this.view = view;
        this.downview = relayview;
        this.popHeightScale = popHeightScale;
        init(location,anim,x,y);
    }

    public PopupWindow getPopupWindow(){
        return popupWindow;
    }

    public void init(int locayion,int anim,int x,int y){
        Rect outRect = new Rect();
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        int widActivity = outRect.width();
        int heightActivity = (int)(outRect.height()*(popHeightScale==0?0.5:popHeightScale));
        int height = heightActivity;
        popupWindow = new PopupWindow(view, widActivity, height);
        // view.setBackgroundResource(R.drawable.popupwindow);
        /***  这2句很重要   ***/
//        ColorDrawable cd = new ColorDrawable(-0000);
//        popupWindow.setBackgroundDrawable(cd);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
//        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.7f);
        // popupWindow.showAsDropDown(down);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//设置外部能点击
        popupWindow.setAnimationStyle(anim);
        popupWindow.setIgnoreCheekPress();
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        //setting popupWindow d点击消失
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /****   如果点击了popupwindow的外部，popupwindow也会消失 ****/
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();;
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAtLocation(downview,locayion, x, y);
    }

    public void init(){
        Rect outRect = new Rect();
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        int widActivity = (int)(outRect.width()*0.8);
        int heightActivity = outRect.height();
        int pianyi = context.getResources().getDimensionPixelSize(R.dimen.dp_46);
        int height = heightActivity - pianyi;
        popupWindow = new PopupWindow(view, widActivity, height);
        // view.setBackgroundResource(R.drawable.popupwindow);
        /***  这2句很重要   ***/
//        ColorDrawable cd = new ColorDrawable(-0000);
//        popupWindow.setBackgroundDrawable(cd);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
//        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.7f);
        // popupWindow.showAsDropDown(down);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//设置外部能点击
        popupWindow.setAnimationStyle(R.style.PopAnimation);
        popupWindow.setIgnoreCheekPress();
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        //setting popupWindow d点击消失
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /****   如果点击了popupwindow的外部，popupwindow也会消失 ****/
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAtLocation(downview, Gravity.RIGHT, 0, pianyi);
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity)context).getWindow().setAttributes(lp);
    }

    private OnBackKeyDownListener listener;

    public void setOnBackKeyDownListener(OnBackKeyDownListener listener){
        this.listener=listener;
    }

    public interface OnBackKeyDownListener {
        void callBack();
    }
}
