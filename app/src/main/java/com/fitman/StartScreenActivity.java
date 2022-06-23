package com.fitman;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.Attendance.AttendanceDao;
import com.fitman.database.Step.StepDao;
import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StartScreenActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 5000;
    Animation logoAnimation1, logoAnimation2;
    ImageView logo_image;
    TextView logo_text;
    Handler startScreenHandler = new Handler();
    Handler skipHandler = new Handler();
    Runnable startScreenRunnable;
    Runnable skipRunnable;
    int times = SPLASH_TIME_OUT/1000;
    String isFirstStart;
    private static final String TAG = "StartScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        hideStatusAndActionBar();
        setContentView(R.layout.activity_start_screen);
        hideStatusAndActionBar();
        Button btn_skip = findViewById(R.id.btn_skip);
        isFirstStart = SharedPreferencesUtils.getParam("isFirstStart", "true");

        {
            //TODO:权限注册
            List<String> permissionList = getPermission();
            String[] permission = permissionList.toArray(new String[permissionList.size()]);
            if(permission.length > 0){
                requestPermissions(permission, 1);
            }
        }

        logoAnimation1 = AnimationUtils.loadAnimation(this, R.anim.logo_animation_1);
        logoAnimation2 = AnimationUtils.loadAnimation(this, R.anim.logo_animation_2);

        logo_image = findViewById(R.id.logo_image);
        logo_text = findViewById(R.id.logo_text);

        logo_image.setAnimation(logoAnimation1);
        logo_text.setAnimation(logoAnimation2);

        startScreenRunnable = new Runnable() {
            @Override
            public void run() {
                if (hasAllPermission()) {
                    Intent intent;
                    if (isFirstStart.equals("true"))
                    {
                        intent = new Intent(StartScreenActivity.this, FirstStartActivity.class);
                    }else{
                        intent = new Intent(StartScreenActivity.this, NavigationBottomActivity.class);
                    }
                    startActivity(intent);
                    finish();

                }else
                {
//                    Toast.makeText(StartScreenActivity.this, getString(R.string.permission_not_all), Toast.LENGTH_SHORT).show();
                    startScreenHandler.postDelayed(this, 1000);
                }

            }
        };
        startScreenHandler.postDelayed(startScreenRunnable,SPLASH_TIME_OUT);

        skipRunnable = new Runnable() {
            @Override
            public void run() {
                btn_skip.setText(StartScreenActivity.this.getString(R.string.skip) + "("+ times +")");
                times = times - 1;
                if (times != 0){
                    skipHandler.postDelayed(skipRunnable,1000);
                }
            }
        };
        skipHandler.postDelayed(skipRunnable,0);

    }

    public void skipStartScreen(View view) {
        if (!hasAllPermission()) {
            Toast.makeText(StartScreenActivity.this, getString(R.string.permission_not_all), Toast.LENGTH_SHORT).show();
            exitApp(2000);
            return;
        }
        if (isFirstStart.equals("true"))
        {
            Intent intent = new Intent(StartScreenActivity.this, FirstStartActivity.class);
            startActivity(intent);
        }else{
            startActivity(new Intent(this, NavigationBottomActivity.class));
        }
        startScreenHandler.removeCallbacks(startScreenRunnable);
        skipHandler.removeCallbacks(skipRunnable);
        finish();
    }
}