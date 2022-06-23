package com.fitman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class FitnessActivity extends BaseActivity {
    private JzvdStd jzvdStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        showActionBar();
        showBackButton();
        ImageView imageStart = findViewById(R.id.imageStart);

        ImageView imageCover = findViewById(R.id.imageCover);
        TextView fitness_title = findViewById(R.id.fitness_title);
        TextView time_num = findViewById(R.id.time_num);
        TextView like_num = findViewById(R.id.like_num);
        TextView level = findViewById(R.id.level);
        TextView description = findViewById(R.id.description);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout_video);
        jzvdStd = findViewById(R.id.jzvd_Video);
        Integer index = getIntent().getIntExtra("index", 1);

        switch (index){
            case 1:
                setActionBarTitle(getString(R.string.fitness_title_1));

                imageCover.setImageResource(R.drawable.fitness1);
                fitness_title.setText(getString(R.string.fitness_title_1));
                time_num.setText("10");
                like_num.setText("7530");
                level.setText(getString(R.string.easy));
                description.setText(getString(R.string.fitness_description_1));

                imageStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        jzvdStd.setUp(getVideoUrl(1), getString(R.string.fitness_title_1));
                        jzvdStd.posterImageView.setImageResource(R.drawable.fitness1);
                    }
                });
                break;
            case 2:
                setActionBarTitle(getString(R.string.fitness_title_2));

                imageCover.setImageResource(R.drawable.fitness2);
                fitness_title.setText(getString(R.string.fitness_title_2));
                time_num.setText("10");
                like_num.setText("6315");
                level.setText(getString(R.string.easy));
                description.setText(getString(R.string.fitness_description_2));

                imageStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        jzvdStd.setUp(getVideoUrl(2), getString(R.string.fitness_title_2));
                        jzvdStd.posterImageView.setImageResource(R.drawable.fitness2);
                    }
                });
                break;
            case 3:
                setActionBarTitle(getString(R.string.fitness_title_3));

                imageCover.setImageResource(R.drawable.fitness3);
                fitness_title.setText(getString(R.string.fitness_title_3));
                time_num.setText("10");
                like_num.setText("5391");
                level.setText(getString(R.string.easy));
                description.setText(getString(R.string.fitness_description_3));

                imageStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        jzvdStd.setUp(getVideoUrl(3), getString(R.string.fitness_title_3));
                        jzvdStd.posterImageView.setImageResource(R.drawable.fitness3);
                    }
                });
                break;
            case 4:
                setActionBarTitle(getString(R.string.fitness_title_4));

                imageCover.setImageResource(R.drawable.fitness4);
                fitness_title.setText(getString(R.string.fitness_title_4));
                time_num.setText("20");
                like_num.setText("5150");
                level.setText(getString(R.string.hard));
                description.setText(getString(R.string.fitness_description_4));

                imageStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        jzvdStd.setUp(getVideoUrl(4), getString(R.string.fitness_title_4));
                        jzvdStd.posterImageView.setImageResource(R.drawable.fitness4);
                    }
                });
                break;
            case 5:
                setActionBarTitle(getString(R.string.fitness_title_5));

                imageCover.setImageResource(R.drawable.fitness5);
                fitness_title.setText(getString(R.string.fitness_title_5));
                time_num.setText("10");
                like_num.setText("4939");
                level.setText(getString(R.string.easy));
                description.setText(getString(R.string.fitness_description_5));

                imageStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        constraintLayout.setVisibility(View.VISIBLE);
                        jzvdStd.setUp(getVideoUrl(5), getString(R.string.fitness_title_5));
                        jzvdStd.posterImageView.setImageResource(R.drawable.fitness5);
                    }
                });
                break;
        }


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
    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }





    public String getVideoUrl(Integer index){
        switch (index){
            case 1:
                return "http://111.229.100.125:8888/down/qCQ6waZkzeQH";
            case 2:
                return "http://111.229.100.125:8888/down/bNGwmh72ulRB";
            case 3:
                return "http://111.229.100.125:8888/down/QtWSaxWHV55y";
            case 4:
                return "http://111.229.100.125:8888/down/mA70mGCQ2nqq";
            case 5:
                return "http://111.229.100.125:8888/down/HV4R714oSF44";
            default:
                return "";
        }
    }
}