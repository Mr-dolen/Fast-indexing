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
				// ���ݴ�������ĸȥ������������ĸ��letter��ͬ����Ŀ��Ȼ����Ŀ�ö�
				for (int i = 0; i < friends.size(); i++) {
					String word = friends.get(i).getPinyin().charAt(0) + "";
					if (word.equals(letter)) {
						// ˵����ǰ��i������Ҫ�ģ���ֱ���ö�
						lv_listview.setSelection(i);
						break;
					}
				}
				// ���м���ʾ����������ĸ
				showCurrentWord(letter);
			}

		});

		// ��������
		fillData();
		// �����ݽ�������
		Collections.sort(friends);
		lv_listview.setAdapter(new FriendAdapter(this, friends));

	}

	/**
	 * ��ʾ��ǰ��ĸ
	 * 
	 * @param letter
	 */
	private void showCurrentWord(String letter) {
		tv_currentword.setVisibility(View.VISIBLE);
		tv_currentword.setText(letter);

		// ��֮ǰ������ȡ����
		handler.removeCallbacksAndMessages(null);
		// ��ʱ����
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				tv_currentword.setVisibility(View.GONE);
			}
		}, 1000);

	}

	private void fillData() {
		// ��������
		friends.add(new Friend("��ΰ"));
		friends.add(new Friend("����"));
		friends.add(new Friend("����"));
		friends.add(new Friend("����"));
		friends.add(new Friend("����"));
		friends.add(new Friend("������"));
		friends.add(new Friend("������"));
		friends.add(new Friend("����"));
		friends.add(new Friend("�ֿ���1"));
		friends.add(new Friend("����2"));
		friends.add(new Friend("����a"));
		friends.add(new Friend("�ֿ���a"));
		friends.add(new Friend("����"));
		friends.add(new Friend("�ֿ���"));
		friends.add(new Friend("����"));
		friends.add(new Friend("����b"));
		friends.add(new Friend("����"));
		friends.add(new Friend("����"));
		friends.add(new Friend("������"));
		friends.add(new Friend("����1"));
		friends.add(new Friend("��ΰ1"));
		friends.add(new Friend("�߽�"));
		friends.add(new Friend("����"));
		friends.add(new Friend("�Ʋ�"));
		friends.add(new Friend("����"));
		friends.add(new Friend("����"));
		friends.add(new Friend("��ʥ��"));
		friends.add(new Friend("����"));
		friends.add(new Friend("Ealla"));
		friends.add(new Friend("����"));
		friends.add(new Friend("�ν�"));
		friends.add(new Friend("�ν�1"));
		friends.add(new Friend("��ΰ3"));
	}

}
