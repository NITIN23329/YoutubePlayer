package com.nitin.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity
        implements View.OnClickListener {
        // using Standalone of Youtube API, when orientation changes the Video don't get stop but it continue playing
    // unlike in case of the YoutubeActivity we used in which video gets paused when orientation changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

        Button playVideoInYouTube = findViewById(R.id.playVideoInYouTube);
        Button playPlaylistInWindow = findViewById(R.id.playPlaylistInWindow);
        // this::onClick will refers to the onClick() we wrote inside this class
        // we created a common OnCLickListener() for both button and uses switch statement to get which button was pressed and take particular action
        playVideoInYouTube.setOnClickListener(this::onClick);
        playPlaylistInWindow.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.playVideoInYouTube:
                // createVideoIntent() takes the activity from which it was called as 1st parameter
                // 2nd parameter in GOOGLE API KEY and 3rd is the ID of the video we want to play
                // 4th parameter is time in ms from where video should start initially.
                // 5th parameter is boolean which is true if we want to start playing video as soon as the Player launches else false if we want the video in cue
                // 6th parameter is whether we want to play video in a dialog above the current activity, false for full screen
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_ID,0,true,false);
                break;
            // only difference her is the 3rd parameter takes the ID of playlist we want to play
            // 4th parameter is the index of video in playlist we want to play
            // 5th parameter is time in ms from where video should start initially.
            // 6th parameter is boolean which is true if we want to start playing video as soon as the Player launches else false if we want the video in cue
            // 7th parameter is whether we want to play video in a dialog above the current activity, false for full screen
            case R.id.playPlaylistInWindow:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_PLAYLIST_ID,0,0,true,true);
        }
        // to start a new activity from another activity, we call call startActivity and pass a intent
        if (intent != null) startActivity(intent);

    }
}
