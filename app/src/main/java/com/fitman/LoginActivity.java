package com.fitman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;


public class LoginActivity extends BaseActivity {
    Animation logoAnimation;
    ImageView logo_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO
        showActionBar();
        setActionBarTitle(getString(R.string.login_title));

        //LOGO进场动画
        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_login);
        logo_login = findViewById(R.id.logo_login);
        logo_login.setAnimation(logoAnimation);

        TextView forgot_password = findViewById(R.id.tv_forgot_password);
        TextView register = findViewById(R.id.tv_register);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login(View view) {
        EditText username = findViewById(R.id.et_username);
        EditText password = findViewById(R.id.et_password);

        String username_str = username.getText().toString();
        String password_str = password.getText().toString();

        UserDao userDao = new UserDao(this);

        if(username_str.isEmpty())
        {
            Toast.makeText(this, getString(R.string.username_empty), Toast.LENGTH_SHORT).show();
            return;
        }else if (password_str.isEmpty())
        {
            Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if(userDao.login(username_str, password_str)){
            //登陆成功
            SharedPreferencesUtils.setParam("isLogin","true");
            SharedPreferencesUtils.setParam("username",username_str);
            startActivity(new Intent(this, NavigationBottomActivity.class));
            finish();
        }else {
            //登陆失败
            SharedPreferencesUtils.setParam("isLogin","false");
            Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
            username.getText().clear();
            password.getText().clear();
        }
    }


}