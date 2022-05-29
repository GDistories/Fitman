package com.fitman;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class FitnessFragment extends Fragment {

    private VideoView vv_fitness;
    private Uri uri;
    private MediaController mediaController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        vv_fitness = getActivity().findViewById(R.id.vv_fitness);
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.fitness;
        uri = Uri.parse(videoPath);
        vv_fitness.setVideoURI(uri);

        mediaController = new MediaController(getActivity());
        vv_fitness.setMediaController(mediaController);
        mediaController.setAnchorView(vv_fitness);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fitness, container, false);
    }
}