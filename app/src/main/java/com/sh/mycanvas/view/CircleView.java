package com.sh.mycanvas.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * author： TongGuHermit
 * created on： 2018/9/25 14:22
 */

public class CircleView extends View{
	private int mWidth;
	private int mHeight;
	private PathMeasure mMeasure;
	private Paint mPaint;
	private Path mPath;
	private ValueAnimator mAnimator;
	private ValueAnimator.AnimatorUpdateListener mUpdateListener;
	private Animator.AnimatorListener mAnimatorListener;
	private Handler mAnimatorHandler;
	private int count = 0;
	// 动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
	private float mAnimatorValue = 0;
	public CircleView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initPaint();
		initPath();
//		initAnimator();
//		initListen();
//		initHandle();
	}

	private void initPath() {
		mPath = new Path();
		RectF oval2 = new RectF(-100, -100, 100, 100);      // 外部圆环
		mPath.addArc(oval2, 45, -359.9f);
		mMeasure = new PathMeasure(mPath,false);
		Log.e("TAG", "forceClosed=false---->"+mMeasure.getLength());
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(15f);
//		mPaint.setColor(Color.parseColor("#0082D7"));
		mPaint.setColor(Color.WHITE);
		mPaint.setAntiAlias(true);
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
			height = 400;
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
			width = 400;
		}
		return width;
	}


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mHeight = h;
		mWidth = w;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.translate(mWidth/2,mHeight/2);
		canvas.drawColor(Color.parseColor("#0082D7"));
		mMeasure.setPath(mPath, false);
		Path newPath = new Path();
		float stop = mMeasure.getLength() * mAnimatorValue;
		float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 300f));
		mMeasure.getSegment(start, stop, newPath, true);
		canvas.drawPath(newPath,mPaint);
	}
	public void startAnimation() {
		mAnimator = ValueAnimator.ofInt(0, 1);
		mAnimator.setDuration(3000);
		mAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置无限循环
		mAnimator.setInterpolator(new LinearInterpolator());
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				mAnimatorValue = animation.getAnimatedFraction();
				invalidate();
			}
		});
		mAnimator.start();
	}
}
