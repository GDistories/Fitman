package com.fitman;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.fitman.utils.SharedPreferencesUtils;

import java.util.List;

public class StepActivity extends BaseActivity {
    protected SensorManager sensorManager;
    private static final String TAG = "StepActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            Log.e(TAG, "onCreate: step counter sensor is available");
            SharedPreferencesUtils.setParam("step_counter_sensor", "true");
        } else {
            Log.e(TAG, "onCreate: step counter sensor is not available");
            SharedPreferencesUtils.setParam("step_counter_sensor", "false");
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            Log.e(TAG, "onCreate: step detector sensor is available");
            SharedPreferencesUtils.setParam("step_detector_sensor", "true");
        } else {
            Log.e(TAG, "onCreate: step detector sensor is not available");
            SharedPreferencesUtils.setParam("step_detector_sensor", "false");
        }
    }

}