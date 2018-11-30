package com.sh.mycanvas.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author： TongGuHermit
 * created on： 2018/10/23 10:47
 */

public class VerticalStepView extends View {
	private Paint mPaint1;
	private Paint mPaint2;
	private Paint mPaint3;
	private Paint mPaint4;
	private Paint mTextPaint;
	private ValueAnimator mAnimator;
	private  float radius1 = 60;
	private  float radius2 = 60;
	private  float radius3 = 60;
	private  float radius4 = 60;
	private  float radius = 50;
	private int padding = 195;
	private int lineWith = 4;
	// 动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
	private float mAnimatorValue = 0;



	public VerticalStepView(Context context) {
		super(context);
	}

	public VerticalStepView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public VerticalStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}


	private void initPaint() {
		mPaint1 = new Paint();
		mPaint2 = new Paint();
		mPaint3 = new Paint();
		mPaint4 = new Paint();
		mTextPaint = new Paint();
		mPaint1.setColor(Color.parseColor("#3F79FE"));
		mPaint1.setStyle(Paint.Style.FILL);
		mPaint1.setAntiAlias(true);
		mPaint2.setColor(Color.parseColor("#D8D8D8"));
		mPaint2.setStyle(Paint.Style.FILL);
		mPaint2.setAntiAlias(true);
		mPaint3.setColor(Color.parseColor("#D8D8D8"));
		mPaint3.setStyle(Paint.Style.FILL);
		mPaint3.setAntiAlias(true);
		mPaint4.setColor(Color.parseColor("#D8D8D8"));
		mPaint4.setStyle(Paint.Style.FILL);
		mPaint4.setAntiAlias(true);
		mTextPaint.setColor(Color.parseColor("#ffffff"));
		mTextPaint.setStyle(Paint.Style.FILL);
		mTextPaint.setTextSize(50);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}

	public void setPadding(int padding){
		this.padding = padding;
	}
	public void setRadius(int radius){
		this.radius = radius;
	}
	public void setLineWith(int lineWith){
		this.lineWith = lineWith;
	}

	public void setStep(int step){
		switch (step){
			case 0:
				radius1 = radius;
				mPaint1.setColor(Color.parseColor("#D8D8D8"));
				radius2 = radius;
				mPaint2.setColor(Color.parseColor("#D8D8D8"));
				radius3 = radius;
				mPaint3.setColor(Color.parseColor("#D8D8D8"));
				radius4 = radius;
				mPaint4.setColor(Color.parseColor("#D8D8D8"));
				invalidate();
				break;
			case 1:
				radius1 = radius;
				mPaint1.setColor(Color.parseColor("#3F79FE"));
				radius2 = radius;
				mPaint2.setColor(Color.parseColor("#D8D8D8"));
				radius3 = radius;
				mPaint3.setColor(Color.parseColor("#D8D8D8"));
				radius4 = radius;
				mPaint4.setColor(Color.parseColor("#D8D8D8"));
				invalidate();
				break;
			case 2:
				radius1 = radius;
				mPaint1.setColor(Color.parseColor("#3F79FE"));
				radius2 = radius;
				mPaint2.setColor(Color.parseColor("#3F79FE"));
				radius3 = radius;
				mPaint3.setColor(Color.parseColor("#D8D8D8"));
				radius4 = radius;
				mPaint4.setColor(Color.parseColor("#D8D8D8"));
				invalidate();
				break;
			case 3:
				radius1 = radius;
				mPaint1.setColor(Color.parseColor("#3F79FE"));
				radius2 = radius;
				mPaint2.setColor(Color.parseColor("#3F79FE"));
				radius3 = radius;
				mPaint3.setColor(Color.parseColor("#3F79FE"));
				radius4 = radius;
				mPaint4.setColor(Color.parseColor("#D8D8D8"));
				invalidate();
				break;
			case 4:
				radius1 = radius;
				mPaint1.setColor(Color.parseColor("#3F79FE"));
				radius2 = radius;
				mPaint2.setColor(Color.parseColor("#3F79FE"));
				radius3 = radius;
				mPaint3.setColor(Color.parseColor("#3F79FE"));
				radius4 = radius;
				mPaint4.setColor(Color.parseColor("#3F79FE"));
				invalidate();
				break;
		}


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
			height = (int) (radius*2+padding*3);
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

		canvas.drawRect(radius-lineWith/2,radius,radius+lineWith/2,radius+padding,mPaint1);
		canvas.drawCircle(radius,radius,radius,mPaint1);
		canvas.drawText("1",radius,radius+12,mTextPaint);

		canvas.drawCircle(radius,radius+padding,radius,mPaint2);
		canvas.drawRect(radius-lineWith/2,radius+padding,radius+lineWith/2,radius+padding*2,mPaint2);
//		canvas.drawRect(radius-2,radius+padding,radius+2,radius+padding*3/2,mPaint2);
		canvas.drawText("2",radius,radius+padding+12,mTextPaint);

		canvas.drawCircle(radius,radius+2*padding,radius,mPaint3);
//		canvas.drawRect(radius-2,radius+padding*3/2,radius+2,radius+2*padding,mPaint3);
		canvas.drawRect(radius-lineWith/2,radius+2*padding,radius+lineWith/2,radius+3*padding,mPaint3);
		canvas.drawText("3",radius,radius+2*padding+12,mTextPaint);


		canvas.drawCircle(radius,radius+3*padding,radius,mPaint4);
//		canvas.drawRect(radius-2,radius+5*padding/2,radius+2,radius+3*padding,mPaint4);
		canvas.drawText("4",radius,radius+3*padding+12,mTextPaint);
	}
}
