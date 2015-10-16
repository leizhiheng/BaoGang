package com.baosteel.qcsh.dialog;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl.Area;
import com.baosteel.qcsh.ui.view.PopupWindowUtils;

/**
 * Created by lian on 2015/5/14.
 */
public class PositionSelect {
	
    private Context context;
    private View parentview;
    
    private ArrayList<SQLOpearteImpl.Area> provinces;
    private ArrayList<SQLOpearteImpl.Area> cities;
    private ArrayList<SQLOpearteImpl.Area> areas;
    
    //当前选中的省市县id(用于默认选中之前选中过的城市)
    private int pId,cId,aId;
    
    //当前选中的省市县对象(用于回传对象，不再通过id去查找数据库获取name)
    private Area province,city,area;
    
    private ListView lv_position;
    private ArrayList<SQLOpearteImpl.Area> currentArea;
    private AreaListAdapter adapterArea;
    private int currentLevel;//当前级别
    private ImageView btn_back;
    private boolean selected=true;//是否都选择
    private PopupWindowUtils popupWindowUtils;
    private Handler mHandler;

    public PositionSelect(Context context,View relyview,int pId,int cId,int aId){
        this.context=context;
        Log.d("City", "provinceId:"+pId+"  cityId:"+cId+" areaId:"+cId);
        this.pId = pId;
        this.cId = cId;
        this.aId = aId;
        parentview= LayoutInflater.from(context).inflate(R.layout.layout_pop_selectposition, null);
        popupWindowUtils=new PopupWindowUtils(context, parentview, relyview);
        init();

    }
    public void init(){
        lv_position= (ListView) parentview.findViewById(R.id.lv_position);
        btn_back= (ImageView) parentview.findViewById(R.id.btn_back);
        SQLOpearteImpl temp = new SQLOpearteImpl(context);
        provinces =temp.checkAllProvince();
        currentLevel=1;
        selectIndex(provinces,pId);
        temp.CloseDB();
        currentArea= provinces;
        adapterArea=new AreaListAdapter(currentArea);
        lv_position.setAdapter(adapterArea);
        lv_position.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int level=currentArea.get(position).rank;
                SQLOpearteImpl temp = new SQLOpearteImpl(context);
                if(1==level){
                    currentLevel=level+1;
                    
                    pId = currentArea.get(position).RecNo;
                    province = currentArea.get(position);
                    selectIndex(provinces,pId);
                    
                    cities=temp.checkAllCityById(currentArea.get(position).RecNo);
                    boolean isTrue=selectIndex(cities,cId);
                    if(!isTrue){//下一级是否选中
                        selected=false;
                    }
                    currentArea=cities;
                }else if(2==level){
                	currentLevel=level+1;
                    
                    cId = currentArea.get(position).RecNo;
                    city = currentArea.get(position);
                    selectIndex(cities,cId);
                    
                    areas=temp.checkAllDistriceById(currentArea.get(position).RecNo);
                    if(0==areas.size()){//没有3级
                        aId = -1;
                        selected=true;
                        adapterArea.setList(currentArea);
                        adapterArea.notifyDataSetChanged();
                        temp.CloseDB();
                        dismiss();
                        return ;
                    }
                    boolean isTrue=selectIndex(areas,aId);
                    if(!isTrue){//下一级是否选中
                        selected=false;
                    }
                    currentArea=areas;
                }else if(3==level){
                    currentLevel=level;
                    selected=true;
                    
                    aId = currentArea.get(position).RecNo;
                    area = currentArea.get(position);
                    selectIndex(areas, aId);
                    
                    
                    adapterArea.setList(currentArea);
                    adapterArea.notifyDataSetChanged();
                    temp.CloseDB();
                    dismiss();
                    return;
                }
                adapterArea.setList(currentArea);
                adapterArea.notifyDataSetChanged();
                temp.CloseDB();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(3==currentLevel){
                    currentLevel--;
                    currentArea=cities;
                }else if(2==currentLevel){
                    currentLevel--;
                    currentArea= provinces;
                }else if(1==currentLevel){
                    popupWindowUtils.getPopupWindow().dismiss();
                }
                adapterArea.setList(currentArea);
                adapterArea.notifyDataSetChanged();
            }
        });
        popupWindowUtils.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
                lp.alpha = 1; //0.0-1.0
                ((Activity)context).getWindow().setAttributes(lp);
                if(selected){//是否都选择了
                    if(null!=listener){
                    	if(null != province && null != city && null != area){
                    		listener.selectResult(province, city, area);//回掉接口
                    	}
                        popupWindowUtils.getPopupWindow().dismiss();
                    }
                }
            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (popupWindowUtils != null) {
                    popupWindowUtils.getPopupWindow().dismiss();
                }
            }
        };
    }

    /**
     * 延迟dismiss
     */
    private void dismiss() {
        if (mHandler != null) {
            mHandler.sendEmptyMessageDelayed(0, 200);
        }
    }
    public boolean selectIndex(ArrayList<SQLOpearteImpl.Area> areas,int id){
        boolean isSelected=false;
        for (SQLOpearteImpl.Area area:areas){
            area.isSelected=false;
            if(id==area.RecNo){
                area.isSelected=true;
                isSelected=true;
                Log.d("City", "rank:"+area.rank+"  rawid:"+area.RecNo);
            }
        }
        return isSelected;
    }

    private SelectResultListener listener;

    public void setOnSelectResultListener(SelectResultListener listener){
        this.listener=listener;
    }

    public interface SelectResultListener{
    	
        void selectResult(Area province, Area city, Area area);
    }

    public class AreaListAdapter extends BaseAdapter{
        ArrayList<SQLOpearteImpl.Area> list;
        public AreaListAdapter(ArrayList<SQLOpearteImpl.Area> areas){
            this.list=areas;
        }
        public void setList(ArrayList<SQLOpearteImpl.Area> areas){
            this.list=areas;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder holder=null;
            if(null==convertView){
                convertView=LayoutInflater.from(context).inflate(R.layout.layout_position_lv_item,null);
                holder=new MyHolder();
                holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
                holder.iv_gou= (ImageView) convertView.findViewById(R.id.iv_gou);
                convertView.setTag(holder);
            }else
                holder= (MyHolder) convertView.getTag();
            holder.tv_name.setText(list.get(position).name);
            if(list.get(position).isSelected){
                holder.iv_gou.setImageResource(R.drawable.icon_cheked_red);
                holder.iv_gou.setVisibility(View.VISIBLE);
            }else{
                holder.iv_gou.setVisibility(View.GONE);
            }
            return convertView;
        }
    }
    static class MyHolder{
        TextView tv_name;
        ImageView iv_gou;
    }
}
