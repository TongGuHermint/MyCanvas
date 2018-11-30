package com.sh.mycanvas.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * author： TongGuHermit
 * created on： 2018/10/23 10:47
 */

public class StepView extends View {
	private Paint mPaint1;
	private Paint mPaint2;
	private Paint mPaint3;
	private Paint mPaint4;
	private Paint mTextPaint;
	private ValueAnimator mAnimator;
	private  float radius1 = 80;
	private  float radius2 = 60;
	private  float radius3 = 60;
	private  float radius4 = 60;
	// 动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
	private float mAnimatorValue = 0;



	public StepView(Context context) {
		super(context);
	}

	public StepView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}


	private void initPaint() {
		mPaint1 = new Paint();
		mPaint2 = new Paint();
		mPaint3 = new Paint();
		mPaint4 = new Paint();
		mTextPaint = new Paint();
		mPaint1.setColor(Color.parseColor("#2B79FF"));
		mPaint1.setStyle(Paint.Style.FILL);
		mPaint1.setAntiAlias(true);
		mPaint2.setColor(Color.parseColor("#555E63"));
		mPaint2.setStyle(Paint.Style.FILL);
		mPaint2.setAntiAlias(true);
		mPaint3.setColor(Color.parseColor("#555E63"));
		mPaint3.setStyle(Paint.Style.FILL);
		mPaint3.setAntiAlias(true);
		mPaint4.setColor(Color.parseColor("#555E63"));
		mPaint4.setStyle(Paint.Style.FILL);
		mPaint4.setAntiAlias(true);
		mTextPaint.setColor(Color.parseColor("#ffffff"));
		mTextPaint.setStyle(Paint.Style.FILL);
		mTextPaint.setTextSize(50);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}

	public void setStep(int step){
		switch (step){
			case 0:
				radius1 = 80;
				mPaint1.setColor(Color.parseColor("#2B79FF"));
				radius2 = 60;
				mPaint2.setColor(Color.parseColor("#555E63"));
				radius3 = 60;
				mPaint3.setColor(Color.parseColor("#555E63"));
				radius4 = 60;
				mPaint4.setColor(Color.parseColor("#555E63"));
				invalidate();
				break;
			case 1:
				radius1 = 60;
				mPaint1.setColor(Color.parseColor("#2B79FF"));
				radius2 = 80;
				mPaint2.setColor(Color.parseColor("#2B79FF"));
				radius3 = 60;
				mPaint3.setColor(Color.parseColor("#555E63"));
				radius4 = 60;
				mPaint4.setColor(Color.parseColor("#555E63"));
				invalidate();
				break;
			case 2:
				radius1 = 60;
				mPaint1.setColor(Color.parseColor("#2B79FF"));
				radius2 = 60;
				mPaint2.setColor(Color.parseColor("#2B79FF"));
				radius3 = 80;
				mPaint3.setColor(Color.parseColor("#2B79FF"));
				radius4 = 60;
				mPaint4.setColor(Color.parseColor("#555E63"));
				invalidate();
				break;
			case 3:
				radius1 = 60;
				mPaint1.setColor(Color.parseColor("#2B79FF"));
				radius2 = 60;
				mPaint2.setColor(Color.parseColor("#2B79FF"));
				radius3 = 60;
				mPaint3.setColor(Color.parseColor("#2B79FF"));
				radius4 = 80;
				mPaint4.setColor(Color.parseColor("#2B79FF"));
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
			height = 160;
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
			width = 760;
		}
		return width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		/*Shader shader = new LinearGradient(80,70,180,90,Color.parseColor("#2B79FF"),Color.parseColor("#4A6698"),Shader.TileMode.CLAMP);
		mPaint1.setShader(shader);*/
		canvas.drawRect(80,70,180,90,mPaint1);
//		mPaint1.setShader(null);
		canvas.drawCircle(80,80,radius1,mPaint1);

		canvas.drawText("1",80,90,mTextPaint);

		canvas.drawCircle(280,80,radius2,mPaint2);
		canvas.drawRect(180,70,280,90,mPaint2);
		canvas.drawRect(280,70,380,90,mPaint2);
		canvas.drawText("2",280,90,mTextPaint);

		canvas.drawCircle(480,80,radius3,mPaint3);
		canvas.drawRect(380,70,480,90,mPaint3);
		canvas.drawRect(480,70,580,90,mPaint3);
		canvas.drawText("3",480,90,mTextPaint);

		canvas.drawRect(580,70,680,90,mPaint4);
		canvas.drawCircle(680,80,radius4,mPaint4);
		canvas.drawText("4",680,90,mTextPaint);
	}
}
