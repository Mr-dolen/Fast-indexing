package com.itheima.quicklook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class QuickLookBar extends View {
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	private Paint paint;
	private float cellHeight;
	private int width;

	public QuickLookBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public QuickLookBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public QuickLookBar(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		// ���ÿ����
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#1FFCE8"));
		paint.setTextSize(15);
		// ���ʻ����ı�Ĭ�ϵ�������ı������½�,���ı����������Ϊ�ı��ױߵ�����
		paint.setTextAlign(Align.CENTER);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = getMeasuredWidth();
		cellHeight = getMeasuredHeight() * 1f / indexArr.length;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < indexArr.length; i++) {
			float x = width / 2;
			float y = cellHeight / 2 + getTextHeight(indexArr[i]) / 2 + i
					* cellHeight;
			// �жϴ����ĺ����ڻ��Ƶ��Ƿ���ͬһ����ĸ,����Ǹı���ɫ
			paint.setColor(i == lastIndex ? Color.DKGRAY : Color.WHITE);
			canvas.drawText(indexArr[i], x, y, paint);
		}
	}

	/**
	 * ��ȡ�ı��ĸ߶�
	 * 
	 * @param text
	 */
	private float getTextHeight(String text) {
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}

	private int lastIndex = -1;
	private OnLetterChangeListener listener;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// ��ȡ��ǰ������ĸ����
			int index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < indexArr.length) {
				// �����ǰ�����ĺ���һ�δ����Ĳ���ͬһ�����ӡ
				if (index != lastIndex) {
					// Log.e("tag", indexArr[index]);
					if (listener != null) {
						listener.onLetterChange(indexArr[index]);
					}
				}
			}
			lastIndex = index;
			break;
		case MotionEvent.ACTION_UP:
			// ̧���ʱ�����¸�ֵ
			lastIndex = -1;
			break;
		}
		// �ػ�
		invalidate();
		return true;
	}

	public void setOnLetterChangeListener(OnLetterChangeListener listener) {
		this.listener = listener;

	}

	// ����һ���ӿ�
	public interface OnLetterChangeListener {
		void onLetterChange(String letter);
	}
}
