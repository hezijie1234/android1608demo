package com.androidxx.yangjw.day36_vitamio_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.vov.vitamio.widget.VideoView;

/**
 * Vitamio的基本使用
 */
public class MainActivity extends AppCompatActivity {

    private VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_main);
        videoview = (VideoView) findViewById(R.id.main_vitamio_video);
        videoview.setVideoPath("http://mvvideo1.meitudata.com/57bfff5c69171354.mp4");

    }

    @Override
    protected void onStart() {
        super.onStart();
        videoview.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoview.pause();
    }
}
