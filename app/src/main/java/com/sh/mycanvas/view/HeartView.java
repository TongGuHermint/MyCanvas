package com.sh.mycanvas.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sh.mycanvas.MeasureUtil;

/**
 * author： TongGuHermit
 * created on： 2018/9/19 10:59
 */

public class HeartView extends View {
	private Context mContext;
	private float height;
	private float width;
	private int mHeartColor;
	private Paint mPaint = new Paint();
	private Paint mPaint1 = new Paint();

	public HeartView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initPaints();
	}

	private void initPaints() {
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setStrokeWidth(5f);
		mPaint1.setColor(Color.YELLOW);
		mPaint1.setStyle(Paint.Style.FILL);
		mPaint.setAntiAlias(true);
//		mPaint1.setStrokeWidth(5f);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = measureWidth(widthMeasureSpec);
		int measuredHeight = measureHeight(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}
	//单位为像素px wrap_content 默认200dp
	private int measureHeight(int heightMeasureSpec) {
		int height = 0;
		int mode = MeasureSpec.getMode(heightMeasureSpec);
		int size = MeasureSpec.getSize(heightMeasureSpec);
		if (mode == MeasureSpec.EXACTLY) {
			height = size;
		} else if (mode == MeasureSpec.AT_MOST) {
			height = 400;
		}
		return height;
	}

	private int measureWidth(int widthMeasureSpec) {
		int width = 0;
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		if (mode == MeasureSpec.EXACTLY) {
			width = size;
		} else if (mode == MeasureSpec.AT_MOST) {
			width = 400;
		}
		return width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		height = MeasureUtil.getScreenHeight((Activity) mContext);
		width = MeasureUtil.getScreenWidth((Activity) mContext);
		RectF rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
		canvas.drawRect(rectF,mPaint1);
		Path mPath = new Path();
		mPath.reset();
		mPath.moveTo(getMeasuredWidth()/2,getMeasuredHeight()/3-getMeasuredHeight()/6);
		mPath.cubicTo(getMeasuredWidth(), getMeasuredHeight()/30-getMeasuredHeight()/6,getMeasuredWidth(),getMeasuredHeight()*5/6-getMeasuredHeight()/6,getMeasuredWidth()/2,getMeasuredHeight()-getMeasuredHeight()/6);
		mPath.moveTo(getMeasuredWidth()/2,getMeasuredHeight()/3-getMeasuredHeight()/6);
		mPath.cubicTo(0, getMeasuredHeight()/30-getMeasuredHeight()/6,0,getMeasuredHeight()*5/6-getMeasuredHeight()/6,getMeasuredWidth()/2,getMeasuredHeight()-getMeasuredHeight()/6);
//		mPath.lineTo(getMeasuredWidth(),getMeasuredHeight());
		canvas.drawPath(mPath,mPaint);
	}

	/**
	 * 设置心的颜色
	 * @param heartColor
	 */
	public void setHeartColor(int heartColor){
		this.mHeartColor = heartColor;
		mPaint.setColor(mHeartColor);
	}

}
