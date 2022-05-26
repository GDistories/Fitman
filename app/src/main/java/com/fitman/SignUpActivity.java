package com.fitman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;

public class SignUpActivity extends BaseActivity {
    Animation logoAnimation, disappearAnimation, appearAnimation;
    ImageView logo_login;
    TextView tv_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showActionBar();
        setActionBarTitle(getString(R.string.login_title));


        //LOGO进场动画
        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_login);
        disappearAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_disappear);
        appearAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_appear);
        logo_login = findViewById(R.id.logo_login);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        logo_login.setVisibility(View.VISIBLE);
        tv_sign_up.setVisibility(View.INVISIBLE);
        logo_login.setAnimation(logoAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo_login.setAnimation(disappearAnimation);
                logo_login.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_sign_up.setVisibility(View.VISIBLE);
                        tv_sign_up.setAnimation(appearAnimation);
                    }
                }, 300);
            }
        }, 1500);


    }

    public void signUp(View view) {
        EditText et_username = findViewById(R.id.et_username);
        EditText et_password = findViewById(R.id.et_password);
        EditText et_password_confirm = findViewById(R.id.et_password_confirm);

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String password_confirm = et_password_confirm.getText().toString();

        if (username.isEmpty()) {
            Toast.makeText(this, getString(R.string.username_empty), Toast.LENGTH_SHORT).show();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
            return;
        } else if (password_confirm.isEmpty()) {
            Toast.makeText(this, getString(R.string.password_confirm_empty), Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(password_confirm)) {
            Toast.makeText(this, getString(R.string.password_not_equal), Toast.LENGTH_SHORT).show();
            return;
        }

        UserDao userDao = new UserDao(this);
        if (userDao.isUserExist(username)) {
            Toast.makeText(this, getString(R.string.username_exist), Toast.LENGTH_SHORT).show();
            return;
        }

        if(userDao.insertUser(username, password) == 1){
            Toast.makeText(this, getString(R.string.sign_up_success), Toast.LENGTH_SHORT).show();
            SharedPreferencesUtils.setParam("isRegistered", "true");
            startActivity(new Intent(this, UserProfileActivity.class));
            finish();
        }



    }
}