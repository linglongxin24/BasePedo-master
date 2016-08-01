package com.example.stepdemp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity  {
	private MainActivity instance;
	private TextView tv;
	private SensorListener listener;

	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		instance = this;
		listener = new SensorListener();
		tv =(TextView)findViewById(R.id.tv);
		// Get the default sensor for the sensor type from the SenorManager
		registerSensor(Sensor.TYPE_STEP_DETECTOR);


	}

	class SensorListener implements SensorEventListener{ 
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			
			System.out.println(event.values.length);
			count+= event.values[0];
			System.out.println(count);

			Log.v("xf", "event.values[0]="+event.values[0]);
			if (tv!=null) {
				tv.setText(count+"");
			}  
		}

	}


	private void registerSensor(int sensorType) {
		mSensorManager =
				(SensorManager) getActivity().getSystemService(Activity.SENSOR_SERVICE);
		// sensorType is either Sensor.TYPE_STEP_COUNTER or Sensor.TYPE_STEP_DETECTOR
		mSensor = mSensorManager.getDefaultSensor(sensorType);
		if (mSensorManager.getDefaultSensor(sensorType) != null){
			// Success! There's a magnetometer.
			if (tv!=null) {
				tv.setText("sensorType支持");
			}
		}
		else {
			// Failure! No magnetometer.
			if (tv!=null) {
				tv.setText("sensorType 不支持");
			}
		}


		mSensorManager.registerListener(listener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

	}

	private Activity getActivity() {
		// TODO Auto-generated method stub
		return instance;
	}


}
