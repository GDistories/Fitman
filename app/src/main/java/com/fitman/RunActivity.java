package com.fitman;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private int num = 0;
    String duration;
    Handler handlers = new Handler();
    String hh = "00";
    String mm = "00";
    String ss = "00";
    Double duration_second = 0.0;
    Animation green_animation_enlarge;
    Animation green_animation_narrow;
    Animation num_enlarge;
    Animation num_narrow;

    ImageView green_circle;
    TextView tv_countdown_num_3;
    TextView tv_countdown_num_2;
    TextView tv_countdown_num_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        hideStatusAndActionBar();
        num = 0;

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
                duration = hh + ":" + mm + ":" + ss;
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
        run_circle_show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                basicTimer.start(1000);
            }
        }, 3300);


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
                SharedPreferencesUtils.setParam("duration_record", duration);
                SharedPreferencesUtils.setParam("distance_record", distance.toString());
                SharedPreferencesUtils.setParam("calories_record", calories.toString());
                SharedPreferencesUtils.setParam("pace_record", pace.toString());
                SharedPreferencesUtils.setParam("run_record_username", getUsername());
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

    public void run_circle_show(){
        Handler handler = new Handler();
        green_animation_enlarge = AnimationUtils.loadAnimation(this, R.anim.green_circle_enlarge);
        green_animation_narrow = AnimationUtils.loadAnimation(this, R.anim.green_circle_narrow);
        num_enlarge = AnimationUtils.loadAnimation(this, R.anim.num_enlarge);
        num_narrow = AnimationUtils.loadAnimation(this, R.anim.num_narrow);

        green_circle = findViewById(R.id.green_circle);
        tv_countdown_num_3 = findViewById(R.id.tv_countdown_num_3);
        tv_countdown_num_2 = findViewById(R.id.tv_countdown_num_2);
        tv_countdown_num_1 = findViewById(R.id.tv_countdown_num_1);

        green_circle.setVisibility(View.VISIBLE);
        green_circle.setAnimation(green_animation_enlarge);

        BasicTimer.BasicTimerCallback bc2 = new BasicTimer.BasicTimerCallback() {
            @Override
            public void onTimer() {
                num++;
                switch (num){
                    case 5:
                        rb_num_enlarge_3();
                        break;
                    case 9:
                        rb_num_narrow_3();
                        break;
                    case 15:
                        rb_num_enlarge_2();
                        break;
                    case 19:
                        rb_num_narrow_2();
                        break;
                    case 25:
                        rb_num_enlarge_1();
                        break;
                    case 29:
                        rb_num_narrow_1();
                        rb_circle_narrow();
                        break;
                }
            }
        };
        BasicTimer basicTimer2 = new BasicTimer(bc2);
        basicTimer2.start(100);
//        handler.postDelayed(rb_num_enlarge_3, 500);
//        handler.postDelayed(rb_num_narrow_3, 1000);
//        handler.postDelayed(rb_num_enlarge_2, 1400);
//        handler.postDelayed(rb_num_narrow_2, 1900);
//        handler.postDelayed(rb_num_enlarge_1, 2300);
//        handler.postDelayed(rb_num_narrow_1, 2800);
//        handler.postDelayed(rb_circle_narrow, 2800);



    }

    public void rb_circle_enlarge() {
            green_circle.setVisibility(View.VISIBLE);
            green_circle.setAnimation(green_animation_enlarge);
    }

    public void getRb_circle_narrow() {
            green_circle.setAnimation(green_animation_narrow);
        }

    public void rb_num_enlarge_3() {

            num_enlarge = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_enlarge);
            tv_countdown_num_3.setVisibility(View.VISIBLE);
            tv_countdown_num_3.setAnimation(num_enlarge);
        }

    public void rb_num_enlarge_2() {

            num_enlarge = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_enlarge);
            tv_countdown_num_2.setVisibility(View.VISIBLE);
            tv_countdown_num_2.setAnimation(num_enlarge);
        }

    public void rb_num_enlarge_1() {

            num_enlarge = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_enlarge);
            tv_countdown_num_1.setVisibility(View.VISIBLE);
            tv_countdown_num_1.setAnimation(num_enlarge);
        }

    public void rb_num_narrow_3() {

            num_narrow = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_narrow);
            tv_countdown_num_3.setAnimation(num_narrow);
        }

    public void rb_num_narrow_2() {

            num_narrow = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_narrow);
            tv_countdown_num_2.setAnimation(num_narrow);
        }

    public void rb_num_narrow_1() {

            num_narrow = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_narrow);
            tv_countdown_num_1.setAnimation(num_narrow);
        }

    public void rb_circle_narrow() {

            green_circle.setAnimation(green_animation_narrow);
        }


//    public Runnable rb_circle_enlarge = new Runnable() {
//        @Override
//        public void run() {
//            green_circle.setVisibility(View.VISIBLE);
//            green_circle.setAnimation(green_animation_enlarge);
//        }
//    };
//    public Runnable getRb_circle_narrow = new Runnable() {
//        @Override
//        public void run() {
//            green_circle.setAnimation(green_animation_narrow);
//        }
//    };
//    public Runnable rb_num_enlarge_3 = new Runnable() {
//        @Override
//        public void run() {
//            num_enlarge = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_enlarge);
//            tv_countdown_num_3.setVisibility(View.VISIBLE);
//            tv_countdown_num_3.setAnimation(num_enlarge);
//        }
//    };
//    public Runnable rb_num_enlarge_2 = new Runnable() {
//        @Override
//        public void run() {
//            num_enlarge = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_enlarge);
//            tv_countdown_num_2.setVisibility(View.VISIBLE);
//            tv_countdown_num_2.setAnimation(num_enlarge);
//        }
//    };
//    public Runnable rb_num_enlarge_1 = new Runnable() {
//        @Override
//        public void run() {
//            num_enlarge = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_enlarge);
//            tv_countdown_num_1.setVisibility(View.VISIBLE);
//            tv_countdown_num_1.setAnimation(num_enlarge);
//        }
//    };
//    public Runnable rb_num_narrow_3 = new Runnable() {
//        @Override
//        public void run() {
//            num_narrow = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_narrow);
//            tv_countdown_num_3.setAnimation(num_narrow);
//        }
//    };
//    public Runnable rb_num_narrow_2 = new Runnable() {
//        @Override
//        public void run() {
//            num_narrow = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_narrow);
//            tv_countdown_num_2.setAnimation(num_narrow);
//        }
//    };
//    public Runnable rb_num_narrow_1 = new Runnable() {
//        @Override
//        public void run() {
//            num_narrow = AnimationUtils.loadAnimation(RunActivity.this, R.anim.num_narrow);
//            tv_countdown_num_1.setAnimation(num_narrow);
//        }
//    };
//    public Runnable rb_circle_narrow = new Runnable() {
//        @Override
//        public void run() {
//            green_circle.setAnimation(green_animation_narrow);
//        }
//    };


}