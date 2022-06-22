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
                return "https://rr5---sn-4g5lznes.googlevideo.com/videoplayback?expire=1655778344&ei=yNewYt3kJ9ia1gLXsYPgBg&ip=154.194.9.13&id=o-AHAEMfNNVr20fhqHgrEahp5HotaM22DZREKVGnN4fjek&itag=22&source=youtube&requiressl=yes&mh=FK&mm=31%2C29&mn=sn-4g5lznes%2Csn-4g5edndz&ms=au%2Crdu&mv=m&mvi=5&pl=24&initcwndbps=607500&vprv=1&mime=video%2Fmp4&ratebypass=yes&dur=1153.056&lmt=1650796440930380&mt=1655756449&fvip=2&fexp=24001373%2C24007246%2C24208265&c=ANDROID&txp=4532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgQjkBVp4nGC3WJM5YnCH1afAR5wRffjPYp5TDokKTIm4CIQCTukTw20_jCEzmdq0-dl17flHKys3pIhUrN_HvVqxJ2w%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIhAMpEvZVK1zQom1RH0QMERYK3Lmq9NbX7W4QrfMxiuiWbAiBJUpPeNG9wKWd3tNZU0lBAc9vHw-1pHSo1DWCR8V0TVg%3D%3D&title=Y2Mate.is%20-%20But%20what%20is%20a%20neural%20network%20%20Chapter%201%2C%20Deep%20learning-aircAruvnKk-720p-1655756755719";
            case 2:
                return "https://rr3---sn-4g5lznle.googlevideo.com/videoplayback?expire=1655784011&ei=6-2wYpmpH8aY1gKr3a_oBA&ip=154.22.133.129&id=o-ACAsPfJf2UHMUNJo_YTiDRqY6HVZ9rYSJOaE8-1UUWTf&itag=22&source=youtube&requiressl=yes&mh=QS&mm=31%2C29&mn=sn-4g5lznle%2Csn-4g5ednkl&ms=au%2Crdu&mv=m&mvi=3&pl=23&initcwndbps=308750&vprv=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=629.191&lmt=1633706105539776&mt=1655761970&fvip=4&fexp=24001373%2C24007246%2C24208264&c=ANDROID&txp=5516222&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAIs1Xhum5kO8N3fzyRybXOxZc4yp-b8-GTzmDY14wJYTAiEAorv-dZY-35NBKWyzmME_1NzIEGGbZrJoCFRrxEjkYKU%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIhAIbna-sViKm46PK4OsMzjHS1LQrrskt0jaKW_D5zcm9hAiB_88GfR71TkRsv1mMnyvnzZZ49j-ZKsLXGZgyZ8Ju2lQ%3D%3D&title=Y2Mate.is%20-%2010%20MIN%20BEGINNER%20AB%20WORKOUT%20%20No%20Equipment%20%20Pamela%20Reif-1f8yoFFdkcY-720p-1655762411827";
            case 3:
                return "https://rr1---sn-4g5lznl6.googlevideo.com/videoplayback?expire=1655784044&ei=DO6wYtC9E4eg8gONnpCgDg&ip=154.22.133.192&id=o-AEPg9WXZzkG1A2a8I31cne2iWNsdFAfXFUfLzqCbnTb-&itag=22&source=youtube&requiressl=yes&mh=-a&mm=31%2C29&mn=sn-4g5lznl6%2Csn-4g5edn6k&ms=au%2Crdu&mv=m&mvi=1&pl=23&initcwndbps=288750&vprv=1&mime=video%2Fmp4&ratebypass=yes&dur=622.062&lmt=1649424288678132&mt=1655761970&fvip=5&fexp=24001373%2C24007246&c=ANDROID&txp=4532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgcUqN08CYowK-MjrxPEM20hV1IIZeJUAmqPQoKotfdrQCIFkbjeXtzRgpUUt-XVch-Dfze3ufEN7LYwSjnvn5z4Tc&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRAIgVXhrHPUhnwMkVvVAg0HKgtUlJNzryAH8xNkLD53-D0wCIGI6aGPDXKhympo4quAA9OKkGSZteOwJHRUAZFia-1fT&title=Y2Mate.is%20-%2010%20MIN%20SIXPACK%20WORKOUT%20%20No%20Equipment%20%20Pamela%20Reif-Q-vuR4PJh2c-720p-1655762444652";
            case 4:
                return "https://rr3---sn-4g5edndz.googlevideo.com/videoplayback?expire=1655784056&ei=GO6wYurxMsmI6dsP3tqKsAM&ip=154.22.133.197&id=o-AGSnOmE0r7ZW6HCs7-iJkbJHKJb7NHvH4lqr5VnEZOFY&itag=22&source=youtube&requiressl=yes&mh=Dn&mm=31%2C29&mn=sn-4g5edndz%2Csn-4g5lznle&ms=au%2Crdu&mv=m&mvi=3&pl=23&initcwndbps=308750&vprv=1&mime=video%2Fmp4&ratebypass=yes&dur=1220.069&lmt=1651306639903235&mt=1655761970&fvip=1&fexp=24001373%2C24007246&c=ANDROID&txp=4532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhALEdsDi_tT9djeoDngOjrt3ye91VpGzXiRvwcrFx9x51AiEA0Qb_YoDH4-EEz0Svx1npqO196oNo-yU2uXMmt2KGosA%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgDeoi_Hl00OYwnCYaNfHUzwQmoGUBkaK8atBKmvRMILUCIQC5BnYVyQ1trKaFt3mzLzJydAL7e52Qn5CTA_17weo4bw%3D%3D&title=Y2Mate.is%20-%2020%20MIN%20FULL%20BODY%20WORKOUT%20%20No%20Equipment%20%20Pamela%20Reif-UBMk30rjy0o-720p-1655762457080";
            case 5:
                return "https://rr2---sn-4g5e6nsy.googlevideo.com/videoplayback?expire=1655878268&ei=HF6yYujpE56o1gLyu4PgBg&ip=154.194.9.57&id=o-AD81JY9sNeOkFPq888KFylLeC3nVTeB5byLrhlrtwdvC&itag=22&source=youtube&requiressl=yes&mh=RL&mm=31%2C29&mn=sn-4g5e6nsy%2Csn-4g5ednds&ms=au%2Crdu&mv=m&mvi=2&pl=24&initcwndbps=541250&vprv=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=673.982&lmt=1654838376834270&mt=1655856287&fvip=3&fexp=24001373%2C24007246%2C24208265&c=ANDROID&txp=4532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAOKne2tJraU77xoFPBJwWFukeG-lMyUnEXouNVOg_g6lAiEAgnM_ECTa_7uHfh1ziYA9mRzZtELqz6JvaTeoXu4PHz0%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgAYmFjM3nSVHMUwuxmQyvOuMyPsDBdE6hBCx3jxCCto0CIQD5AmDe9AakrfhZkv0q673hUPOdobsVNiEZ__HGFZqotg%3D%3D&title=Y2Mate.is%20-%2010%20MIN%20BOOTY%20BURN%20%20No%20Equipment%20%20Pamela%20Reif-RqfkrZA_ie0-720p-1655856672394";
            default:
                return "";
        }
    }
}