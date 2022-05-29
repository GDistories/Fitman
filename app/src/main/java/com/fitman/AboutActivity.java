package com.fitman;

import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {

    private Integer num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        num = 0;
        setContentView(R.layout.activity_about);

        showActionBar();
        showBackButton();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("About");
        }

        TextView developer = findViewById(R.id.developer);
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                if (num % 5 == 0){
                    startActivity(new Intent(AboutActivity.this, DeveloperActivity.class));
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}