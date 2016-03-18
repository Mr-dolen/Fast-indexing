package com.itheima.quicklook;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.quicklook.QuickLookBar.OnLetterChangeListener;
import com.itheima.quicklook.adapter.FriendAdapter;
import com.itheima.quicklook.bean.Friend;
import com.xxx.fastindexing.R;

public class MainActivity extends Activity {
	private ArrayList<Friend> friends = new ArrayList<Friend>();
	private QuickLookBar ql_quicklookbar;
	private ListView lv_listview;
	private TextView tv_currentword;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		ql_quicklookbar = (QuickLookBar) findViewById(R.id.ql_quicklookbar);
		tv_currentword = (TextView) findViewById(R.id.tv_currentword);

		ql_quicklookbar.setOnLetterChangeListener(new OnLetterChangeListener() {

			@Override
			public void onLetterChange(String letter) {
				// Log.e("tag", letter);
				// 根据触摸的字母去集合中找首字母和letter相同的条目，然后将条目置顶
				for (int i = 0; i < friends.size(); i++) {
					String word = friends.get(i).getPinyin().charAt(0) + "";
					if (word.equals(letter)) {
						// 说明当前的i就是需要的，则直接置顶
						lv_listview.setSelection(i);
						break;
					}
				}
				// 在中间显示所触摸的字母
				showCurrentWord(letter);
			}

		});

		// 加载数据
		fillData();
		// 对数据进行排序
		Collections.sort(friends);
		lv_listview.setAdapter(new FriendAdapter(this, friends));

	}

	/**
	 * 显示当前字母
	 * 
	 * @param letter
	 */
	private void showCurrentWord(String letter) {
		tv_currentword.setVisibility(View.VISIBLE);
		tv_currentword.setText(letter);

		// 让之前的任务取消掉
		handler.removeCallbacksAndMessages(null);
		// 延时隐藏
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				tv_currentword.setVisibility(View.GONE);
			}
		}, 1000);

	}

	private void fillData() {
		// 虚拟数据
		friends.add(new Friend("李伟"));
		friends.add(new Friend("张三"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王二a"));
		friends.add(new Friend("林俊杰a"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二b"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤1"));
		friends.add(new Friend("李伟1"));
		friends.add(new Friend("高进"));
		friends.add(new Friend("高球"));
		friends.add(new Friend("黄渤"));
		friends.add(new Friend("姜昆"));
		friends.add(new Friend("魅族"));
		friends.add(new Friend("黄圣依"));
		friends.add(new Friend("喵喵"));
		friends.add(new Friend("Ealla"));
		friends.add(new Friend("苏轼"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江1"));
		friends.add(new Friend("李伟3"));
	}

}
