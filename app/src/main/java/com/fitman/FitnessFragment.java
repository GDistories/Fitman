package com.fitman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class FitnessFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        ConstraintLayout fitness_1 = getActivity().findViewById(R.id.constraintLayoutFitness1);
        ConstraintLayout fitness_2 = getActivity().findViewById(R.id.constraintLayoutFitness2);
        ConstraintLayout fitness_3 = getActivity().findViewById(R.id.constraintLayoutFitness3);
        ConstraintLayout fitness_4 = getActivity().findViewById(R.id.constraintLayoutFitness4);
        ConstraintLayout fitness_5 = getActivity().findViewById(R.id.constraintLayoutFitness5);

        fitness_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessActivity.class);
                intent.putExtra("index", 1);
                startActivity(intent);
            }
        });

        fitness_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessActivity.class);
                intent.putExtra("index", 2);
                startActivity(intent);
            }
        });

        fitness_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessActivity.class);
                intent.putExtra("index", 3);
                startActivity(intent);
            }
        });

        fitness_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessActivity.class);
                intent.putExtra("index", 4);
                startActivity(intent);
            }
        });

        fitness_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessActivity.class);
                intent.putExtra("index", 5);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fitness, container, false);
    }


//    void initView() {
//        jzvdStd = (JzvdStd) getActivity().findViewById(R.id.jzvdStd6);
//        jzvdStd.setUp("https://rr5---sn-4g5lznes.googlevideo.com/videoplayback?expire=1655778344&ei=yNewYt3kJ9ia1gLXsYPgBg&ip=154.194.9.13&id=o-AHAEMfNNVr20fhqHgrEahp5HotaM22DZREKVGnN4fjek&itag=22&source=youtube&requiressl=yes&mh=FK&mm=31%2C29&mn=sn-4g5lznes%2Csn-4g5edndz&ms=au%2Crdu&mv=m&mvi=5&pl=24&initcwndbps=607500&vprv=1&mime=video%2Fmp4&ratebypass=yes&dur=1153.056&lmt=1650796440930380&mt=1655756449&fvip=2&fexp=24001373%2C24007246%2C24208265&c=ANDROID&txp=4532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgQjkBVp4nGC3WJM5YnCH1afAR5wRffjPYp5TDokKTIm4CIQCTukTw20_jCEzmdq0-dl17flHKys3pIhUrN_HvVqxJ2w%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIhAMpEvZVK1zQom1RH0QMERYK3Lmq9NbX7W4QrfMxiuiWbAiBJUpPeNG9wKWd3tNZU0lBAc9vHw-1pHSo1DWCR8V0TVg%3D%3D&title=Y2Mate.is%20-%20But%20what%20is%20a%20neural%20network%20%20Chapter%201%2C%20Deep%20learning-aircAruvnKk-720p-1655756755719"
//                , "Test01");
//        Glide.with(this)
//                .load("https://i.ytimg.com/vi_webp/ZVnfjTVZrWY/maxresdefault.webp")
//                .into(jzvdStd.posterImageView);
//    }
}
