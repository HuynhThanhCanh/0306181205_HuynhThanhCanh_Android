package com.example.cinemaapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TrailerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String API_KEY="AIzaSyAixkNXXyjb83mXzpG63qF5wH4NF8cOgh0";
    YouTubePlayerView youTubePlayerView;
    int REQUEST_VIDEO=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.trailer);
        youTubePlayerView.initialize(API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("MzYoVbzy_4U");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_VIDEO);
        }else {
            Toast.makeText(TrailerActivity.this,"Error!!!",Toast.LENGTH_SHORT).show();
        }
    }
}