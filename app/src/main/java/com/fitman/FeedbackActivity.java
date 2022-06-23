package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        showActionBar();
        showBackButton();
        setActionBarTitle(getString(R.string.feedback));
    }

    public void submit(View view) {
        Spinner spinner = findViewById(R.id.sp_category);
        EditText et_description = findViewById(R.id.et_description);
        EditText et_contact = findViewById(R.id.et_contact);
        Button btn_submit = findViewById(R.id.btn_submit);

        String category = spinner.getSelectedItem().toString();
        String description = et_description.getText().toString();
        String contact = et_contact.getText().toString();

        if (category.equals("") || description.equals("") || contact.equals("")) {
            Toast.makeText(this, getString(R.string.not_fill_all), Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, getString(R.string.feedback_success), Toast.LENGTH_SHORT).show();
        finish();
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