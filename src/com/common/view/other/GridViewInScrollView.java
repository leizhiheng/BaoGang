package com.common.view.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class GridViewInScrollView extends GridView {

//	   public boolean haveScrollbar = true;
     	private boolean haveScrollbars=false;
	    public GridViewInScrollView(Context context) {   
	        super(context);   
	    }   
	    public GridViewInScrollView(Context context, AttributeSet attrs) {   
	        super(context, attrs);   
	    }   
	    public GridViewInScrollView(Context context, AttributeSet attrs, int defStyle) {   
	        super(context, attrs, defStyle);   
	    }   
	    /**  
	     * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true  
	     *   
	     * @param haveScrollbar
	     */   
	    public void setHaveScrollbar(boolean haveScrollbar) {   
	        this.haveScrollbars = haveScrollbar;   
	    }   
	    @Override   
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
	        if (haveScrollbars == false) {   
	            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);   
	            super.onMeasure(widthMeasureSpec, expandSpec);   
	        } else {   
	            super.onMeasure(widthMeasureSpec, heightMeasureSpec);   
	        }   
	    }   

}
