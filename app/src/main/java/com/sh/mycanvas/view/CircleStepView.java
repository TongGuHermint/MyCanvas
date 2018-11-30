package com.sh.mycanvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author： TongGuHermit
 * created on： 2018/11/30 14:01
 */

public class CircleStepView extends View {
	private Paint mPaint;
	private float radius = 60;
	private Paint mTextPaint;
	private String mText;
	public CircleStepView(Context context) {
		super(context);
	}

	public CircleStepView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public CircleStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	private void initPaint() {
		mPaint = new Paint();
		mTextPaint = new Paint();
		mPaint.setColor(Color.parseColor("#D8D8D8"));
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.parseColor("#ffffff"));
		mTextPaint.setStyle(Paint.Style.FILL);
		mTextPaint.setTextSize(50);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}

	public void setFinish(boolean finish){
		if (finish == false) {
			mPaint.setColor(Color.parseColor("#D8D8D8"));
			invalidate();

		} else if (finish == true) {
			mPaint.setColor(Color.parseColor("#3F79FE"));
			invalidate();
		}
	}
	public void setText(String text){
		this.mText = text;
	}

	public void setRadius(float radius){
		this.radius = radius;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureWidth = measureWidth(widthMeasureSpec);
		int measureHeight = measureHeight(heightMeasureSpec);
		setMeasuredDimension(measureWidth, measureHeight);
	}

	private int measureHeight(int heightMeasureSpec) {
		int height = 0;
		int size = MeasureSpec.getSize(heightMeasureSpec);
		int mode = MeasureSpec.getMode(heightMeasureSpec);
		if (mode == MeasureSpec.EXACTLY){
			height = size;
		}else if (mode == MeasureSpec.AT_MOST){
			height = (int) radius*2;
		}
		return height;
	}

	private int measureWidth(int widthMeasureSpec) {
		int width = 0;
		int size = MeasureSpec.getSize(widthMeasureSpec);
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		if (mode == MeasureSpec.EXACTLY){
			width = size;
		}else if (mode == MeasureSpec.AT_MOST){
			width = (int) radius*2;
		}
		return width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(radius,radius,radius,mPaint);
		canvas.drawText(mText,radius,radius+10,mTextPaint);
	}
}
