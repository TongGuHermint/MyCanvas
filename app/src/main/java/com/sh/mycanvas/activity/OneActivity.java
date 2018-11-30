package com.sh.mycanvas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sh.mycanvas.R;
import com.sh.mycanvas.view.BitmapView;
import com.sh.mycanvas.view.CircleView;

public class OneActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		CircleView circleView = (CircleView)findViewById(R.id.circle);
		BitmapView bitmapView = (BitmapView)findViewById(R.id.bitmapView);
	}
}
