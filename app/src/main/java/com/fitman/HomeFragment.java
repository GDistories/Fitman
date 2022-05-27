package com.fitman;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.fitman.database.Attendance.AttendanceDao;
import com.fitman.utils.SharedPreferencesUtils;
import com.fitman.views.CustomDate;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Objects;

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    SensorManager sensorManager;
    TextView steps_text;
    private Integer initSteps = -1;
    private Integer currentSteps = 0;
    private Integer steps = 0;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        SensorEventListener stepCounterSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                CustomDate customDate = new CustomDate();

                if (!customDate.toString().equals(SharedPreferencesUtils.getParam("saveInitStepDate", "")) || SharedPreferencesUtils.getParam("saveInitStep", "-1").equals("-1"))
                {
                    initSteps = (int) sensorEvent.values[0];
                    SharedPreferencesUtils.setParam("saveInitStepDate", customDate.toString());
                    SharedPreferencesUtils.setParam("saveInitStep", initSteps.toString());
                }
                initSteps = Integer.parseInt(SharedPreferencesUtils.getParam("saveInitStep", "-1"));
                currentSteps = (int) sensorEvent.values[0];
                Log.e(TAG, "initStep: " + initSteps);
                Log.e(TAG, "currentStep: " + currentSteps);
                steps = currentSteps - initSteps;

//                Log.e(TAG, "initStep: " + initSteps);
//                Log.e(TAG, "currentStep: " + currentSteps);
//                if (!customDate.toString().equals(SharedPreferencesUtils.getParam("saveInitStepDate", "").toString())) {
//                    //过一天，清空InitStep值
//                    initSteps = currentSteps;
//                    SharedPreferencesUtils.setParam("saveInitStepDate", customDate.toString());
//                    SharedPreferencesUtils.setParam("saveInitStep", initSteps.toString());
//                    Log.e(TAG, "第二天");
//                }
//                initSteps = Integer.parseInt(SharedPreferencesUtils.getParam("saveInitStep", "-1"));
//                currentSteps = (int) sensorEvent.values[0];
//                if(initSteps > currentSteps || initSteps == -1) {
//                    Log.e(TAG, "initStep: " + initSteps);
//                    Log.e(TAG, "currentStep: " + currentSteps);
//                    Log.e(TAG, "change");
//                    initSteps = currentSteps;
//                    SharedPreferencesUtils.setParam("saveInitStepDate", customDate.toString());
//                    SharedPreferencesUtils.setParam("saveInitStep", initSteps.toString());
//
//                }


                steps = currentSteps - initSteps;
                SharedPreferencesUtils.setParam("step", steps.toString());
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.registerListener(stepCounterSensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        AttendanceDao attendanceDao = new AttendanceDao(getActivity());
        ConstraintLayout sign_up = getActivity().findViewById(R.id.constraintLayout1);
        ConstraintLayout steps = getActivity().findViewById(R.id.constraintLayout2);
        ConstraintLayout weights = getActivity().findViewById(R.id.constraintLayout3);

        TextView sign_in_text = getActivity().findViewById(R.id.tv_attendance_center);
        steps_text = getActivity().findViewById(R.id.tv_step_count);
        TextView weight_text = getActivity().findViewById(R.id.tv_weight_count);

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), StepActivity.class));
            }
        });

        runnable=new Runnable(){
            @Override
            public void run() {
                if(isLogin()){
                    steps_text.setText(SharedPreferencesUtils.getParam("step", "0"));
                }
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(runnable, 1000);

        weights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WeightActivity.class));
            }
        });

        if(isLogin()){
            CustomDate mCustomDate = new CustomDate();
            if(attendanceDao.isAttendanceExist(getUsername(), mCustomDate.toString())){
                sign_in_text.setText(getString(R.string.already_signin));
                sign_in_text.setTextColor(getResources().getColor(R.color.black));
            }else
            {
                sign_in_text.setText(getString(R.string.not_sign_in));
                sign_in_text.setTextColor(getResources().getColor(R.color.red));
            }
            weight_text.setText(SharedPreferencesUtils.getParam("weight", "0"));
            steps_text.setText(SharedPreferencesUtils.getParam("step", "0"));
        } else {
            sign_in_text.setText(getString(R.string.not_login));
            sign_in_text.setTextColor(getResources().getColor(R.color.red));
            steps_text.setText("0");
            weight_text.setText("0");
        }

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




}