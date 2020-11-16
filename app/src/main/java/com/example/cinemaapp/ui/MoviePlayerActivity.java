package com.example.cinemaapp.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;

import com.example.cinemaapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MoviePlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    public static  final String VIDEO_TEST_URL="assets:///mulanTrailer.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);

        iniExoPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }

    private void iniExoPlayer() {
        playerView=findViewById(R.id.movie_exo_player);
        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory dataSoureFactory= new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"appname"));
        MediaSource videoSoure=new ExtractorMediaSource.Factory(dataSoureFactory).createMediaSource(Uri.parse(VIDEO_TEST_URL));
        simpleExoPlayer.prepare(videoSoure);
        simpleExoPlayer.setPlayWhenReady(true);

    }


}