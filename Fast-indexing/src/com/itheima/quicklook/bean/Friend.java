package com.itheima.quicklook.bean;

import com.itheima.quicklook.util.PinYinUtil;

import android.content.Context;

public class Friend implements Comparable<Friend>{
	public String name;
	private String pinyin;

	public Friend(String name) {
		super();
		this.name = name;
		pinyin = PinYinUtil.getPinYin(name);
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public int compareTo(Friend another) {
//		String pinYin = PinYinUtil.getPinYin(name);
//		String anotherPinYin = PinYinUtil.getPinYin(another.name);
//		return pinYin.compareTo(anotherPinYin);
		return pinyin.compareTo(another.getPinyin());
	}
	
}
