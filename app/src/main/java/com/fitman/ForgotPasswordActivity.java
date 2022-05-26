package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button send;
    Animation logoAnimation, disappearAnimation, appearAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText username = findViewById(R.id.et_username);
        EditText password = findViewById(R.id.et_password);
        EditText confirmPassword = findViewById(R.id.et_password_confirm);
        send = findViewById(R.id.btn_send_verification_code);
        Button forgotPassword = findViewById(R.id.btn_forgot_password);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountDownTimer != null) {
                    send.setEnabled(false);
                    Toast.makeText(ForgotPasswordActivity.this, getString(R.string.verification_code_send_toast) + generateVerificationCode() , Toast.LENGTH_SHORT).show();
                    mCountDownTimer.start();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //LOGO进场动画
        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_login);
        disappearAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_disappear);
        appearAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_appear);
        ImageView logo_login = findViewById(R.id.logo_login);
        TextView tv_reset = findViewById(R.id.tv_reset_password);
        logo_login.setVisibility(View.VISIBLE);
        tv_reset.setVisibility(View.INVISIBLE);
        logo_login.setAnimation(logoAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo_login.setAnimation(disappearAnimation);
                logo_login.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_reset.setVisibility(View.VISIBLE);
                        tv_reset.setAnimation(appearAnimation);
                    }
                }, 300);
            }
        }, 1500);
    }

    public void resetPassword(View view) {
        EditText username = findViewById(R.id.et_username);
        EditText password = findViewById(R.id.et_password);
        EditText confirmPassword = findViewById(R.id.et_password_confirm);
        EditText verificationCode = findViewById(R.id.et_verification_code);
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();
        String confirmPasswordStr = confirmPassword.getText().toString();
        String verificationCodeStr = verificationCode.getText().toString();

        if (usernameStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.username_empty), Toast.LENGTH_SHORT).show();
            return;
        } else if (passwordStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
            return;
        } else if (confirmPasswordStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.password_confirm_empty), Toast.LENGTH_SHORT).show();
            return;
        } else if (!passwordStr.equals(confirmPasswordStr)) {
            Toast.makeText(this, getString(R.string.password_not_equal), Toast.LENGTH_SHORT).show();
            return;
        } else if (!verificationCodeStr.equals(SharedPreferencesUtils.getParam("verificationCode", ""))) {
            Toast.makeText(this, getString(R.string.verification_code_error), Toast.LENGTH_SHORT).show();
            return;
        }

        UserDao userDao = new UserDao(this);
        if (userDao.isUserExist(usernameStr)) {
            userDao.updateUserPassword(usernameStr, passwordStr);
            Toast.makeText(this, getString(R.string.reset_password_success), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.username_not_exist), Toast.LENGTH_SHORT).show();
        }
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(5 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            if (send != null) {
                send.setText(getString(R.string.verification_code_sent) + "(" + l / 1000 + ")");
            }
        }

        @Override
        public void onFinish() {
            if (send != null) {
                send.setEnabled(true);
                send.setText(getString(R.string.send));
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        send = null;
    }

    public String generateVerificationCode() {
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            verificationCode.append((int) (Math.random() * 10));
        }
        String code = verificationCode.toString();
        SharedPreferencesUtils.setParam("verificationCode", code);
        return code;
    }

}