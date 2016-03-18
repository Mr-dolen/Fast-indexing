package com.itheima.quicklook.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class PinYinUtil {
	/**
	 * ��ȡ���ֶ�Ӧ��ƴ��,�����ڲ�ʵ���Ƕ�ȡxml���ң�������һ����Ч������÷�����Ӧ�ñ�Ƶ������
	 * @param chinese
	 * @return
	 */
	public static String getPinYin(String chinese){
		if(TextUtils.isEmpty(chinese)){
			return null;
		}
		//ƴ��ת���ĸ�ʽ����
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		//�����Ǵ�д��ĸ
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		//���ò�������
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		//����pinyin4j���ܶԴ������ת����ֻ�ܶԵ�������ת��,
		//1.������Ҫ��chineseת���ַ����飬��һ������ȡ
		StringBuilder sb = new StringBuilder();
		char[] charArray = chinese.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if(Character.isWhitespace(c)){
				continue;
			}
			if(c>127){
				//�����Ǻ���
				try {
					//���ڶ����ֵĴ��ڣ����Է��ص�������, ����dan shan
					String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(c, format);
					if(pinyinArr!=null){
						//�˴�ֻ��ȡ��һ��,��Ϊ���ڿ͹�ԭ����������Ϊ��ȥ�ж���ʵӦ�ö�ʲô���ڣ�ֻ��ȡ��1��
						sb.append(pinyinArr[0]);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
				
			}else{
				//�϶����Ǻ���,ֱ��ƴ��
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
