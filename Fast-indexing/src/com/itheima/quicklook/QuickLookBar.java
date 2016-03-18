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
		// 设置抗锯点
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#1FFCE8"));
		paint.setTextSize(15);
		// 画笔绘制文本默认的起点是文本的左下角,将文本的起点设置为文本底边的中心
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
			// 判断触摸的和正在绘制的是否是同一个字母,如果是改变颜色
			paint.setColor(i == lastIndex ? Color.DKGRAY : Color.WHITE);
			canvas.drawText(indexArr[i], x, y, paint);
		}
	}

	/**
	 * 获取文本的高度
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
			// 获取当前触摸字母索引
			int index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < indexArr.length) {
				// 如果当前触摸的和上一次触摸的不是同一个则打印
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
			// 抬起的时候重新赋值
			lastIndex = -1;
			break;
		}
		// 重绘
		invalidate();
		return true;
	}

	public void setOnLetterChangeListener(OnLetterChangeListener listener) {
		this.listener = listener;

	}

	// 定义一个接口
	public interface OnLetterChangeListener {
		void onLetterChange(String letter);
	}
}
