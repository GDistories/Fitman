package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitman.timer.BasicTimer;
import com.fitman.utils.SharedPreferencesUtils;

import java.text.DecimalFormat;


public class RunActivity extends BaseActivity {
    private static final double STEP_LENGTH = 0.00070;//KM

    private Double distance = 0.0;
    private Double calories = 0.0;
    private Double pace = 0.0;
    private Double speed_km_h = 0.0;
    private Double duration_minute = 0.0;
    private Double duration_hour = 0.0;
    private TextView timerView;
    private Double step = 0.0;
    private Double step_init = 0.0;
    private Double step_now = 0.0;
    private Double K = 0.0;
    private long baseTimer;
    Handler handler = new Handler();
    String hh = "00";
    String mm = "00";
    String ss = "00";
    Double duration_second = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        hideStatusAndActionBar();

        //获取初始计步
        step_init = Double.parseDouble(SharedPreferencesUtils.getParam("saveCurrentSteps", "0.0"));
        step_now = step_init;

        ImageView btn_start = findViewById(R.id.btn_start);
        ImageView btn_pause = findViewById(R.id.btn_pause);
        ImageView btn_stop = findViewById(R.id.btn_stop);

        TextView tv_duration_num = findViewById(R.id.tv_duration_num);
        TextView tv_distance_num = findViewById(R.id.tv_distance_num);
        TextView tv_calories_num = findViewById(R.id.tv_calories_num);
        TextView tv_pace_num = findViewById(R.id.tv_pace_num);

        BasicTimer.BasicTimerCallback bc = new BasicTimer.BasicTimerCallback() {
            @Override
            public void onTimer() {
                step_now = Double.parseDouble(SharedPreferencesUtils.getParam("saveCurrentSteps", "0.0"));
                step += step_now - step_init;
                distance = step * STEP_LENGTH;
                duration_second++;
                duration_minute = duration_second / 60;
                duration_hour = duration_minute / 60;
                Log.e("duration_minute", duration_minute.toString());
                hh = new DecimalFormat("00").format(duration_second / 3600);
                mm = new DecimalFormat("00").format((duration_second % 3600) / 60);
                ss = new DecimalFormat("00").format(duration_second % 60);
                String duration = hh + ":" + mm + ":" + ss;
                if (distance > 0){
                    pace = duration_minute / distance;
                }
                if(duration_hour > 0){
                    K = (30 * distance) / (24 * duration_hour);
                }
                calories = K * duration_hour * Double.parseDouble(SharedPreferencesUtils.getParam("weight", "0.0"));
                tv_duration_num.setText(duration);
                tv_pace_num.setText(String.format("%.1f", pace));
                tv_distance_num.setText(String.format("%.2f", distance));
                tv_calories_num.setText(String.format("%.1f", calories));
                Log.e("TAG", duration_second.toString());
                step_init = step_now;
            }
        };
        BasicTimer basicTimer = new BasicTimer(bc);
        basicTimer.start(1000);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_pause.setVisibility(View.VISIBLE);
                btn_start.setVisibility(View.GONE);
                btn_stop.setVisibility(View.GONE);
                basicTimer.start(1000);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_pause.setVisibility(View.GONE);
                btn_start.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.VISIBLE);
                basicTimer.stop();

            }
        });





//        RunActivity.this.baseTimer = SystemClock.elapsedRealtime();
//        timerView = (TextView) this.findViewById(R.id.tv_duration_num);
//        final Handler startTimeHandler = new Handler(){
//            public void handleMessage(android.os.Message msg) {
//                if (null != timerView) {
//                    timerView.setText((String) msg.obj);
//                }
//            }
//        };
//
//        new Timer("duration").scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                int time = (int)((SystemClock.elapsedRealtime() - RunActivity.this.baseTimer) / 1000);
//                String hh = new DecimalFormat("00").format(time / 3600);
//                String mm = new DecimalFormat("00").format(time % 3600 / 60);
//                String ss = new DecimalFormat("00").format(time % 60);
//                String timeFormat = new String(hh + ":" + mm + ":" + ss);
//                Message msg = new Message();
//                msg.obj = timeFormat;
//                startTimeHandler.sendMessage(msg);
//            }
//        }, 0, 1000L);
    }




}