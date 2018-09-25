package com.sh.mycanvas.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.sh.mycanvas.MeasureUtil;

/**
 * author： TongGuHermit
 * created on： 2018/9/17 17:12
 */

public class DoCanvas extends View {
	private Paint mPaint = new Paint();
	private Paint mPaint1 = new Paint();
	private Context mContext;
	private float height;
	private float width ;
	float w ;//偏移量
	private ValueAnimator animator;



	public DoCanvas(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initPaint();
	}


	private void initPaint() {
		mPaint.setColor(Color.BLUE);//设置画笔颜色
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(10f);//设置画笔宽度为10px
		mPaint1.setColor(Color.BLUE);
		mPaint1.setStyle(Paint.Style.STROKE);
		mPaint1.setStrokeWidth(2f);
	}

	private void drawSin(Canvas canvas) {
		float y;
		int A  = 200;

		Path path = new Path();
		path.reset();
		canvas.translate(0,height/2);
		path.moveTo(0, 0);

		for (float d = 0; d <= width; d++) {
			y = (float) (A * Math.sin((Math.PI /180) * d - w* Math.PI * 2));
			path.lineTo(d, y);
		}
		canvas.drawPath(path, mPaint);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		height = MeasureUtil.getScreenHeight((Activity)mContext);
		width = MeasureUtil.getScreenWidth((Activity)mContext);
		drawSin(canvas);
	}

	public void startAnimation() {
		animator = ValueAnimator.ofInt(0, 1);
		animator.setDuration(5000);
		animator.setRepeatCount(ValueAnimator.INFINITE);//设置无限循环
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				w = animation.getAnimatedFraction();
				postInvalidate();
			}
		});
		animator.start();
	}
}
