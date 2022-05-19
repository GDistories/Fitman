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

public class StartScreenActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 5000;
    Animation logoAnimation;
    ImageView logo;
    Handler startScreenHandler = new Handler();
    Handler skipHandler = new Handler();
    Runnable startScreenRunnable;
    Runnable skipRunnable;
    int times = SPLASH_TIME_OUT/1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        hideStatusAndActionBar();
        Button btn_skip = findViewById(R.id.btn_skip);

        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_1);
        logo = findViewById(R.id.logo_image);
        logo.setAnimation(logoAnimation);
        startScreenRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartScreenActivity.this, LanguageChangeActivity.class);
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
        startActivity(new Intent(this, LanguageChangeActivity.class));
        startScreenHandler.removeCallbacks(startScreenRunnable);
        finish();

    }
}