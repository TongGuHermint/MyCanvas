package com.sh.mycanvas.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author： TongGuHermit
 * created on： 2018/9/20 15:14
 */

public class MyRenderer implements GLSurfaceView.Renderer {

	private int textures[] = new int[2];

	float[] rectData = new float[]//三棱锥,4面(4个三角形 )4个顶点 上\左\右\里
			{
					0f, 1f, 0f,
					-1f, -1f, 0f,
					1f, -1f, 0f,
					0f, 0f, -1f

			};
	byte[] indices = new byte[]//4个三角形的3*4顶点
			{

					0, 1, 2,
					0, 1, 3,
					0, 2, 3,
					1, 2, 3
			};

	int[] rectColor = new int[]//rgbn顶点的颜色值(3*4顶点颜色值重复了不必要)
			{
					65535, 0, 0, 0,
					0, 65535, 0, 0,
					0, 0, 65535, 0,
					0, 65535, 65535, 0

			};

	private float angle = 0f;


	public FloatBuffer floatUtils(float[] floatArr) {//绘制方法要求数据为Buffer类型   float[]-->FloatBuffer[]
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(floatArr.length * 4);//要求用allocateDirect()方法,只有ByteBuffer有该方法,so
		byteBuffer.order(ByteOrder.nativeOrder());          //要求nativeOrder  Java 是大端字节序(BigEdian)，
		// 而 OpenGL 所需要的数据是小端字节序(LittleEdian)
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(floatArr);
		floatBuffer.flip();
		return floatBuffer;
	}

	public IntBuffer intUtils(int[] intArr) {

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(intArr.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		IntBuffer intBuffer = byteBuffer.asIntBuffer();
		intBuffer.put(intArr);
		intBuffer.flip();
		return intBuffer;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glDisable(GL10.GL_DITHER);// 关闭服务器端GL功能
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);//这里告诉OpenGL我们希望进行最好的透视修正。这会十分轻微的影响性能。但使得透视图看起来好一点.
		gl.glClearColor(0, 0, 0, 0);//清屏黑色
		gl.glShadeModel(GL10.GL_SMOOTH);//启用smooth shading（阴影平滑）.阴影平滑通过多边形精细的混合色彩，并对外部光进行平滑
		gl.glEnable(GL10.GL_DEPTH_TEST);//开启深度测试
		gl.glDepthFunc(GL10.GL_LEQUAL);//深度测试类型

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);// 设置可见区域
		gl.glMatrixMode(GL10.GL_PROJECTION);//有3种模式: GL_PROJECTION 投影, GL_MODELVIEW 模型视图, GL_TEXTURE 纹理，这里设置为"投影矩阵"
		gl.glLoadIdentity();//重设视图模型变换 ， 用于观测创建的物体.
		float ratio = (float) width / height;
		gl.glFrustumf(-ratio, ratio, -1f, 1f, 1f, 10f);//三维坐标系的值不是直接取屏幕像素,是根据该方法设置空间大小范围内可见最后2个参数为绝对值.
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		GLU.gluLookAt(gl, 0f, 0f, 0f, 0f, 0f, -1f, 0f, 1f, 0f);//观察者视角
		// 颜色数据 绘制2D图形 绘制完成 关闭顶点&&颜色数据
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);//需要绘图时设置为"模型视图"
		gl.glLoadIdentity();
		gl.glTranslatef(0f, 0f, -5f);//改变绘图坐标原点
		gl.glRotatef(angle++, 0f, 0.5f, 0f);//y轴旋转
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		FloatBuffer pointerData = floatUtils(rectData);
		IntBuffer pointColor = intUtils(rectColor);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, pointerData);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, pointColor);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 12, GL10.GL_UNSIGNED_BYTE, ByteBuffer.wrap(indices));//Byte[]类型可直接包装成Buffer
		//  gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
}
