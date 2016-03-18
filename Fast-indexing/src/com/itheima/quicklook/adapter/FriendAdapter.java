package com.itheima.quicklook.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xxx.fastindexing.R;
import com.itheima.quicklook.bean.Friend;
import com.itheima.quicklook.util.PinYinUtil;

public class FriendAdapter extends BaseAdapter {
	private ArrayList<Friend> list;
	private Context context;
	
	public FriendAdapter(Context context,ArrayList<Friend> list) {
		super();
		this.context = context;
		this.list = list;
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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = View.inflate(context,R.layout.item_friend ,null);
			holder = new ViewHolder();
			holder.tv_letter = (TextView)convertView.findViewById(R.id.tv_letter);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Friend friend = list.get(position);
		//String letter = PinYinUtil.getPinYin(friend.name).charAt(0)+"";
		String letter = friend.getPinyin().charAt(0)+"";
		if(position>0){
			//获取上一个首字母
			String lastLetter = list.get(position - 1).getPinyin().charAt(0)+"";
			if(letter.equals(lastLetter)){
				holder.tv_letter.setVisibility(View.GONE);
			}else{
				holder.tv_letter.setVisibility(View.VISIBLE);
				holder.tv_letter.setText(letter);
			}
		}else{
			holder.tv_letter.setVisibility(View.VISIBLE);
			holder.tv_letter.setText(letter);
		}
		holder.tv_name.setText(friend.name);
		
		return convertView;
	}
	static class ViewHolder{
		TextView tv_name;
		TextView tv_letter;
	}

}
