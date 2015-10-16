package com.baosteel.qcsh.dialog.tagviewdialog;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean.ReturnMapEntity.SpecEntity;
import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean.ReturnMapEntity.SpecEntity.SpecValueListEntity;
import com.common.utils.LogUtil;
import com.common.view.tagview.Tag;
import com.common.view.tagview.TagView;

/**
 * 商品详情属性适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-10-9
 */
public class ProductAttributeSelecteAdapter extends BaseAdapter {

	/**
	 * 上下文
	 */
	private Context context;
	
	private List<SpecEntity> dataList;// 规格列表

	public ProductAttributeSelecteAdapter(Context context) {
		this.context = context;
	}

	public void refreshData(List<SpecEntity> dataList){
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return null != dataList ? dataList.size() :  0;
	}

	/**
	 * 获取选中的规格
	 * @return
	 */
	public String getSelectSpec(){
		if(null == dataList || dataList.size() == 0){
			return "";
		}
		
		String spec = "";
		for(SpecEntity data : dataList){
			if(!TextUtils.isEmpty(data.getSelectSpec())){
				if(TextUtils.isEmpty(spec)){
					spec += data.getSelectSpec();
				}else{
					spec += (","+data.getSelectSpec());
				}
			}
		}
		return spec;
	}
	
	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(null  == convertView){
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_product_attribute_my_adapter, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		//显示数据
		holder.showData(dataList.get(position));
		
		return convertView;
	}
	
	class ViewHolder{
		
		/**规格名称**/
		TextView nameTv;
		
		/**规格列表控件**/
		TagView tagView;
		
		ViewHolder(View v){
			nameTv = (TextView)v.findViewById(R.id.nameTv);
			tagView = (TagView)v.findViewById(R.id.tagview);
		}
		
		void showData(final SpecEntity spec){
			this.nameTv.setText(spec.getName());
			this.tagView.clearAllData();
			if(null != spec.getSpecValueList() && spec.getSpecValueList().size() > 0){
				
				//添加标签数据
				for(int i=0; i<spec.getSpecValueList().size(); i++){
					SpecValueListEntity entity = spec.getSpecValueList().get(i);
					addTagView(entity, tagView);
				}
			
				//重新绘制所有tag
				tagView.drawTags();
				
				tagView.setOnTagClickListener(new com.common.view.tagview.OnTagClickListener() {
		            @Override
		            public void onTagClick(Tag tag, int position) {

		            	//选中状态发生改变，需要重新刷新列表
						if(!spec.isRepeat(position)){//是否点击选中的
							spec.selectIndex(position);
							notifyDataSetChanged();
							LogUtil.d("规格", "点击了："+position);
							if(null!=listener){
								StringBuffer sb=new StringBuffer();
								int count=0;
								for (int i=0;i<dataList.size();i++){
									SpecEntity specEntity=dataList.get(i);
									LogUtil.d("规格", "选中的id："+specEntity.getSelectedSpecId());
									if(0==sb.length()){
										count++;
										sb.append(specEntity.getSelectedSpecId());
									}else{
										count++;
										sb.append(","+specEntity.getSelectedSpecId());
									}
								}
								if(count==dataList.size()){
									listener.onItemClick(sb.toString());
								}
							}
						}
		            }
		        });
			}
		}

		/**
	     * 把tag加入到tagview
	     * @param type
	     */
	    private void addTagView(SpecValueListEntity type, TagView tagView){
	        Tag tag=new Tag(type.getValue(), false);
	        tag.tag = type;
	        
	        if(type.isSelected()){//是否选中
	            tag.layoutColor= context.getResources().getColor(R.color.orange_light);
	            tag.layoutColorPress= context.getResources().getColor(R.color.orange_light);
	            tag.tagTextColor= context.getResources().getColor(R.color.white);
	        }else{
	            tag.layoutColor= context.getResources().getColor(R.color.gray);
	            tag.layoutColorPress= context.getResources().getColor(R.color.gray);
	            tag.tagTextColor= context.getResources().getColor(R.color.white);
	        }
	        tag.radius=10;
	        tagView.add(tag);
	    }
	}

	private OnAttributeItemSelectedListener listener;

	public void setOnAttributeItemSelectedListener(OnAttributeItemSelectedListener listener){
		this.listener=listener;
	}

	public interface OnAttributeItemSelectedListener{
		void onItemClick(String spec_value);
	}
}
