package com.sh.mycanvas.activity;

import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sh.mycanvas.view.MyRenderer;
import com.sh.mycanvas.view.WaveProgressView;

public class MainActivity extends AppCompatActivity {
	private WaveProgressView heartWaveView;
	GLSurfaceView surfaceView ;
	private  int progress = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		surfaceView = new GLSurfaceView(this);
		MyRenderer myRenderer = new MyRenderer();
		surfaceView.setRenderer(myRenderer);
//		surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);//绘制静图,无须刷新需要重绘时requestRenderer()节省cpu
		setContentView(surfaceView);
//		MyOpenGLView openGLView = new MyOpenGLView(this);
//		setContentView(openGLView);
//		openGLView.setRenderer(new MyRenderer(this));

//		DoCanvas doCanvas = (DoCanvas)findViewById(R.id.doCanvas);
//		doCanvas.startAnimation();
//		HeartView heartView = (HeartView)findViewById(R.id.heart);
//		heartView.setHeartColor(Color.RED);
		/*heartWaveView = (WaveProgressView)findViewById(R.id.heartWave);
		heartWaveView.setProgress(0);
		new Thread(new MyThread()).start();*/
	}
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1){
				if (progress<100){
					progress = progress + 1;
					heartWaveView.setProgress((float)progress);
				}
			}
			super.handleMessage(msg);
		}
	};
	class MyThread extends Thread {
		@Override
		public void run() {
			while (true){
				try {
					Thread.sleep(100);
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
