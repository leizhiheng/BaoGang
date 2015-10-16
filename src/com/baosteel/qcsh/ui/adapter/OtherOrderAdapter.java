package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;

public class OtherOrderAdapter extends BaseExpandableListAdapter
{
	 
    private Context context;
    
    private List<String> mGroupDatas;
    private List<List<String>> mChildItemDatas;

    public OtherOrderAdapter(Context context, List<String> groupDatas, List<List<String>> childItemDatas) {
        this.context = context;
        this.mGroupDatas = groupDatas;
        this.mChildItemDatas = childItemDatas;
    }

    /**
     *
     * 获取组的个数
     *
     * @return
     * @see android.widget.ExpandableListAdapter#getGroupCount()
     */
    @Override
    public int getGroupCount() {
        return mGroupDatas == null ? 0 : mGroupDatas.size();
    }

    /**
     *
     * 获取指定组中的子元素个数
     *
     * @param groupPosition
     * @return
     * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildItemDatas == null ? 0 : mChildItemDatas.get(groupPosition).size();
    }

    /**
     *
     * 获取指定组中的数据
     *
     * @param groupPosition
     * @return
     * @see android.widget.ExpandableListAdapter#getGroup(int)
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mGroupDatas.get(groupPosition);
    }

    /**
     *
     * 获取指定组中的指定子元素数据。
     *
     * @param groupPosition
     * @param childPosition
     * @return
     * @see android.widget.ExpandableListAdapter#getChild(int, int)
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildItemDatas.get(groupPosition).get(childPosition);
    }

    /**
     *
     * 获取指定组的ID，这个组ID必须是唯一的
     *
     * @param groupPosition
     * @return
     * @see android.widget.ExpandableListAdapter#getGroupId(int)
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     *
     * 获取指定组中的指定子元素ID
     *
     * @param groupPosition
     * @param childPosition
     * @return
     * @see android.widget.ExpandableListAdapter#getChildId(int, int)
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     *
     * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
     *
     * @return
     * @see android.widget.ExpandableListAdapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     *
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d("zhiheng", "getGroupView");
    	GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_other_order_group, null);
            groupHolder = new GroupHolder();
            groupHolder.txt = (TextView)convertView.findViewById(R.id.tv_group_item_title);
            groupHolder.img = (ImageView)convertView.findViewById(R.id.iv_arrow);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder)convertView.getTag();
        }

        if (!isExpanded) {
             groupHolder.img.setImageResource(R.drawable.icon_arrow_down_gray);
        } else {
             groupHolder.img.setImageResource(R.drawable.icon_arrow_up_gray);
        }

        groupHolder.txt.setText(mGroupDatas.get(groupPosition));
        return convertView;
    }

    /**
     *
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        
    	ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_other_order_child, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView)convertView.findViewById(R.id.tv_child_item_title);
            itemHolder.line0 = convertView.findViewById(R.id.line0);
            itemHolder.line1 = convertView.findViewById(R.id.line1);
            itemHolder.line2 = convertView.findViewById(R.id.line2);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder)convertView.getTag();
        }
        itemHolder.txt.setText(mChildItemDatas.get(groupPosition).get(childPosition));
        /*
         * 显示底部自定义的divider
         */
        if (childPosition == mChildItemDatas.get(groupPosition).size() - 1) {
			itemHolder.line1.setVisibility(View.GONE);
			itemHolder.line2.setVisibility(View.VISIBLE);
		} else {
			itemHolder.line1.setVisibility(View.VISIBLE);
			itemHolder.line2.setVisibility(View.GONE);
		}
        
        /*
         * 显示顶部自定义的divider
         */
        if (childPosition == 0) {
			itemHolder.line0.setVisibility(View.VISIBLE);
		} else {
			itemHolder.line0.setVisibility(View.GONE);
		}
        
        return convertView;
    }

    /**
     *
     * 是否选中指定位置上的子元素。
     *
     * @param groupPosition
     * @param childPosition
     * @return
     * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

	class GroupHolder {
		public TextView txt;

		public ImageView img;
	}

	class ItemHolder {

		public TextView txt;
		
		public View line0;
		
		public View line1;
		
		public View line2;
	}

}