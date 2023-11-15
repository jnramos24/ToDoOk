package com.todook.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    VideoView vv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vv1 = findViewById(R.id.videoView);

    }

    public void iniciar (View v) {
        vv1.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=mf_iy3UGYZE&t=298s"));
        vv1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vv1.start();
            }
        });

    }





}