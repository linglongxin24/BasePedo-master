package com.example.stepdemp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

public class StepActivity extends Activity {
	private Pedometer pedometer;
	private TextView tv;
	StringBuilder mBuilder = new StringBuilder();
	private TextView tvD;
	private ViewPropertyAnimator mStepEventAnim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pedometer = new Pedometer(this); 
		tv =(TextView)findViewById(R.id.tv);
		tvD =(TextView)findViewById(R.id.TextViewDetected);


		TimerTask task = new TimerTask() {


			@Override
			public void run() {
				tv.post(new Runnable() { 

					@Override
					public void run() {
						tv.setText(""+pedometer.getStepCount());
						tv.postInvalidate(); 
					}
				}); 
				
				tvD.post(new Runnable() {  

					@Override
					public void run() {
						
						if (mStepEventAnim != null) {
							mStepEventAnim.cancel();
						}
						tvD.setText(""+pedometer.getmDetector());
						tvD.postInvalidate(); 
						tvD.setAlpha(1f); 
						mStepEventAnim =tvD.animate().setDuration(500).alpha(0f);
					}
				}); 

			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 100,1000);
	}

	@Override
	protected void onStart() {
		super.onStart(); 
		pedometer.register();
	}

	@Override
	protected void onStop() {
		super.onStop();
		pedometer.unRegister();
	}
}
