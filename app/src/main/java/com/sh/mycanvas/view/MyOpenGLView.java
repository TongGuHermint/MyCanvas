package com.sh.mycanvas.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * author： TongGuHermit
 * created on： 2018/9/20 15:05
 */

public class MyOpenGLView extends GLSurfaceView {
	private Context mContext;
	MyRenderer mRenderer;

	public MyOpenGLView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	private void init() {
		// 创建一个OpenGL ES 2.0 context
		setEGLContextClientVersion(2);
		//渲染器
		mRenderer = new MyRenderer();
		setRenderer(mRenderer);
		//设置渲染模式
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	public MyOpenGLView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

}
