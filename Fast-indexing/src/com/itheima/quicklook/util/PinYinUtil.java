package com.itheima.quicklook.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class PinYinUtil {
	/**
	 * 获取汉字对应的拼音,由于内部实现是读取xml查找，所以有一定的效率问题该方法不应该被频繁调用
	 * @param chinese
	 * @return
	 */
	public static String getPinYin(String chinese){
		if(TextUtils.isEmpty(chinese)){
			return null;
		}
		//拼音转化的格式化类
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		//设置是大写字母
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		//设置不带声调
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		//由于pinyin4j不能对词语进行转化，只能对单个汉字转化,
		//1.所以需要将chinese转成字符数组，逐一遍历获取
		StringBuilder sb = new StringBuilder();
		char[] charArray = chinese.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if(Character.isWhitespace(c)){
				continue;
			}
			if(c>127){
				//可能是汉字
				try {
					//由于多音字的存在，所以返回的是数组, 单：dan shan
					String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(c, format);
					if(pinyinArr!=null){
						//此处只能取第一个,因为由于客观原因，我们无能为力去判断真实应该读什么音节，只能取第1个
						sb.append(pinyinArr[0]);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
				
			}else{
				//肯定不是汉字,直接拼接
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
