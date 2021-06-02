package com.nitin.youtubeplayer;


import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class YoutubeActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
    }
}