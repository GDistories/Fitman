package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fitman.database.User.UserDao;

public class SignUpActivity extends BaseActivity {
    Animation logoAnimation;
    ImageView logo_login;

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
        logo_login = findViewById(R.id.logo_login);
        logo_login.setAnimation(logoAnimation);


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

        userDao.insertUser(username, password);
        Toast.makeText(this, getString(R.string.sign_up_success), Toast.LENGTH_SHORT).show();
        finish();

        //TODO 跳转到用户信息界面填信息，再跳到登录界面

    }
}