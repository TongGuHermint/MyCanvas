package com.sh.mycanvas.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sh.mycanvas.R;

import java.util.logging.LogRecord;

/**
 * author： TongGuHermit
 * created on： 2018/9/26 13:23
 */

public class BitmapView extends View {
	private Paint mPaint ;
	private Bitmap mBitmap;
	private Context mContext;
	private int mHeight;
	private int mWidth;
	private Matrix matrix;


	public BitmapView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(1f);
		mPaint.setColor(Color.BLACK);
		mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dog);
		int mPicHeight = mBitmap.getHeight();
		int mPicWidth = mBitmap.getWidth();
		matrix = new Matrix();
		float[] a = {0,0, mPicWidth,0,mPicWidth,mPicHeight,0,mPicHeight};
		float[] b = {0,0, mPicWidth,100,mPicWidth,mPicHeight - 100,0,mPicHeight};
//		matrix.setPolyToPoly(a,0,b,0,4);

//		matrix.postTranslate(400,0);
		matrix.postSkew(0.6f,0.6f);
//		matrix.postRotate(30);
//		matrix.postScale(1.5f,1.5f);
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
		/*int mPicHeight = mBitmap.getHeight();
		int mPicWidth = mBitmap.getWidth();
		Log.e("mPicHeight",String.valueOf(mPicHeight));
		Log.e("mPicWidth",String.valueOf(mPicWidth));
		Rect rectS = new Rect(0,0, mPicWidth ,mPicHeight);// 指定图片绘制区域
		Rect rectF = new Rect(-200,-200,200,200);// 指定图片在屏幕上显示的区域*/
//		canvas.drawBitmap(mBitmap,rectS,rectF,mPaint);
		canvas.drawBitmap(mBitmap,matrix,null);
	}

}
