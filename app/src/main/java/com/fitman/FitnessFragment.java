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
}
