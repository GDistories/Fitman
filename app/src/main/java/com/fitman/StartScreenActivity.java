package com.fitman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitman.database.Step.StepDao;
import com.fitman.database.User.UserDao;

import java.util.Date;

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
    private static final String TAG = "StartScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        hideStatusAndActionBar();
        setContentView(R.layout.activity_start_screen);
        hideStatusAndActionBar();
        Button btn_skip = findViewById(R.id.btn_skip);

//        UserDao userDao = new UserDao(this);
//        Integer t = userDao.insertUser("gao", "test");
//        Log.e(TAG, t.toString() );
//        Date date = new Date();
//        userDao.updateBirthday("gao", date);
//        userDao.updateEmail("gao", "111@111.com");
//        userDao.updateGender("gao", "male");
//        userDao.updateFirstName("gao", "first");
//        userDao.updateLastName("gao", "last");
//        userDao.updateHeight("gao", "175cm");
//        userDao.updateWeight("gao", "60kg");
//        userDao.updatePhone("gao", "186639");
//        Log.e(TAG, userDao.getUserIdByUsername("gao").toString());
//        Log.e(TAG, userDao.queryUserByUsernameAndPassword("gao", "test").toString());
//
//        StepDao stepDao = new StepDao(this);
//        Integer step = stepDao.insertStep("2022");
//        Log.e(TAG, step.toString());
        
        

        logoAnimation1 = AnimationUtils.loadAnimation(this, R.anim.logo_animation_1);
        logoAnimation2 = AnimationUtils.loadAnimation(this, R.anim.logo_animation_2);

        logo_image = findViewById(R.id.logo_image);
        logo_text = findViewById(R.id.logo_text);

        logo_image.setAnimation(logoAnimation1);
        logo_text.setAnimation(logoAnimation2);

        startScreenRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartScreenActivity.this, NavigationBottomActivity.class);
                startActivity(intent);
                finish();
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
        startActivity(new Intent(this, NavigationBottomActivity.class));
        startScreenHandler.removeCallbacks(startScreenRunnable);
        skipHandler.removeCallbacks(skipRunnable);
        finish();
    }

}