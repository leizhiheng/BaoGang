package com.baosteel.qcsh.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.model.QueryAppGoodsCommentBean;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.common.net.uploadimg.ImageAdapter;
import com.common.net.uploadimg.ImageData;
import com.common.utils.DeviceUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 评价列表适配器
 * Created by kuangyong on 15/9/8.
 */
public class AppraiseListAdapter extends BaseAdapter {
    private Activity context;
    private List<QueryAppGoodsCommentBean.ReturnMapEntity.ListEntity> list;                  //评价列表数据

    public AppraiseListAdapter(Activity context) {
        this.context = context;
    }

    public void refreshData(List<QueryAppGoodsCommentBean.ReturnMapEntity.ListEntity> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void appendData(List<QueryAppGoodsCommentBean.ReturnMapEntity.ListEntity> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null==list?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if (null == convertView) {
            myHolder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_appraise_list_item, null);
            myHolder.layoutadd = (LinearLayout) convertView.findViewById(R.id.layout_add);
            myHolder.gvimgsadd = (MyGridView) convertView.findViewById(R.id.gv_imgs_add);
            myHolder.tvdescribadd = (TextView) convertView.findViewById(R.id.tv_describ_add);
            myHolder.tvtimeadd = (TextView) convertView.findViewById(R.id.tv_time_add);
            myHolder.gvimgs = (MyGridView) convertView.findViewById(R.id.gv_imgs);
            myHolder.tvdescrib = (TextView) convertView.findViewById(R.id.tv_describ);
            myHolder.tvtime = (TextView) convertView.findViewById(R.id.tv_time);
            myHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
            myHolder.tvappraiseusername = (TextView) convertView.findViewById(R.id.tv_appraise_username);
            convertView.setTag(myHolder);
        } else {
            myHolder= (MyHolder) convertView.getTag();
        }
        /**
         * 评价
         */
        myHolder.tvappraiseusername.setText(list.get(position).getUsername());
        myHolder.ratingBar.setRating(list.get(position).getAvgScore());
        myHolder.tvtime.setText(list.get(position).getComment_time());
        myHolder.tvdescrib.setText(list.get(position).getContent());
        String images = list.get(position).getUrl();
        String[] imgs = images.split(",");
        List<ImageData> imageDatas = new ArrayList<ImageData>();
        for (int i = 0; i < imgs.length; i++) {
            ImageData imageData = new ImageData("", imgs[i]);
            imageDatas.add(imageData);
        }
        int margin = context.getResources().getDimensionPixelSize(R.dimen.dp_10)*2;
        ImageAdapter uploadImageAdapter=new ImageAdapter(context, DeviceUtils.getWidthMaxPx(context)-margin,5);
        myHolder.gvimgs.setAdapter(uploadImageAdapter);
        myHolder.gvimgs.setNumColumns(5);
        uploadImageAdapter.refreshData(imageDatas);
        /**
         * 追加评价
         */
        if(0!=list.get(position).getAppendComment().size()){
            myHolder.tvdescribadd.setText(list.get(position).getAppendComment().get(0).getContent());
            myHolder.tvtimeadd.setText(list.get(position).getAppendComment().get(0).getComment_time());
            String imagesAdd = list.get(position).getAppendComment().get(0).getUrl();
            String[] imgsAdd = imagesAdd.split(",");
            List<ImageData> imageDatasAdd = new ArrayList<ImageData>();
            for (int i = 0; i < imgsAdd.length; i++) {
                ImageData imageDataAdd = new ImageData("", imgsAdd[i]);
                imageDatasAdd.add(imageDataAdd);
            }
            ImageAdapter uploadImageAdapter1=new ImageAdapter(context, DeviceUtils.getWidthMaxPx(context)-margin,5);
            myHolder.gvimgsadd.setAdapter(uploadImageAdapter1);
            myHolder.gvimgsadd.setNumColumns(5);
            uploadImageAdapter1.refreshData(imageDatasAdd);
            myHolder.layoutadd.setVisibility(View.VISIBLE);
        }else{
            myHolder.layoutadd.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class MyHolder {
        LinearLayout layoutadd;//追加评价布局
        MyGridView gvimgsadd;//追加评价图片
        TextView tvdescribadd;//追加评价描述
        TextView tvtimeadd;//追加评价时间
        MyGridView gvimgs;//评价图片
        TextView tvdescrib;//评价描述
        TextView tvtime;//评价时间
        RatingBar ratingBar;//评价评分
        TextView tvappraiseusername;//评价人
    }
}
