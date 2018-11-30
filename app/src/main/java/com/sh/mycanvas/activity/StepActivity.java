package com.sh.mycanvas.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sh.mycanvas.R;
import com.sh.mycanvas.view.CircleStepView;
import com.sh.mycanvas.view.StepView;
import com.sh.mycanvas.view.VerticalStepView;

public class StepActivity extends AppCompatActivity {
	private StepView stepView ;
	private VerticalStepView mVStepView;
	private Button btnNext;
	private CircleStepView mCircleStepView;
	private CircleStepView mCircleStepViewT;
	private View stepLineOne;
	private int step = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step);
		stepView = (StepView)findViewById(R.id.stepView);
		mVStepView = (VerticalStepView)findViewById(R.id.stepView2);
		btnNext = (Button)findViewById(R.id.btn_next) ;
		mCircleStepView = (CircleStepView)findViewById(R.id.circleStepOne);
		mCircleStepViewT = (CircleStepView)findViewById(R.id.circleStepTwo);
		stepLineOne = (View)findViewById(R.id.stepLineOne);

		mCircleStepView.setRadius(70);
		mCircleStepView.setText("1");
		mCircleStepViewT.setRadius(70);
		mCircleStepViewT.setText("2");

		mVStepView.setLineWith(6);
		mVStepView.setRadius(40);
		mVStepView.setPadding(100);
		mVStepView.setStep(0);
		stepView.setStep(0);
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (step <= 3){
					step = step + 1;
					mVStepView.setStep(step);
					stepView.setStep(step);
					mCircleStepView.setFinish(true);
					mCircleStepViewT.setFinish(true);
					stepLineOne.setBackgroundColor(getResources().getColor(R.color.color_3F79FE));
				}else {
					step = 0;
					stepView.setStep(0);
					mVStepView.setStep(0);
					mCircleStepView.setFinish(false);
					mCircleStepViewT.setFinish(false);
					stepLineOne.setBackgroundColor(Color.parseColor("#D8D8D8"));
				}
			}
		});
//		stepView.startAnimation();

	}


	private void getSIMContacts() {
	/*	ContentResolver resolver = this.getContentResolver();
		// 获取Sims卡联系人
		Uri uri = Uri.parse("content://icc/adn");
		Cursor phoneCursor = resolver.query(uri,
				new String[] { ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
						ContactsContract.CommonDataKinds.Phone.NUMBER },
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=?" + " AND " + ContactsContract.CommonDataKinds.Phone.TYPE + "='"
						+ ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE + "'", new String[] { name }, null);
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				String number = phoneCursor.getString(2);
				// 当手机号码为空的或者为空字段 跳过当前循环
				if (TextUtils.isEmpty(phoneNumber))
					continue;
				// 得到联系人名称
				String username = phoneCursor.getString(1);
				mContactsName.add(contactName);
				mContactsNumber.add(phoneNumber);

			}
			phoneCursor.close();
		}*/
		ContentResolver resolver = this.getContentResolver();
		Uri uri = Uri.parse("content://icc/adn");
		String[] PHONES_PROJECTION = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
		Cursor phoneCursor = resolver.query(uri, PHONES_PROJECTION, null, null,
				null);
		String phoneNumber = phoneCursor.getString(1);
		String contactName = phoneCursor.getString(2);
	}
}
