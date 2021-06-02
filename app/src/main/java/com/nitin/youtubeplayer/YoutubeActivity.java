package com.nitin.youtubeplayer;


import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
                implements YouTubePlayer.OnInitializedListener {

    private final String GOOGLE_API_KEY = "AIzaSyCwbNdunJe1wz06Tjxxoels9nxSESjoCnY";
    private final String YOUTUBE_VIDEO_ID = "xLlvOo611xQ";
    private final String YOUTUBE_PLAYLIST = "PLsEtM1pbiHlh-hbDBZwruDu9y4l4jVT7P";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we get a instance of our activity_youtube and now we can update it in code. activity_youtube is indeed the Root  so pass null as its root
        ConstraintLayout constraintLayout = (ConstraintLayout)getLayoutInflater().inflate(R.layout.activity_youtube,null,false);
        setContentView(constraintLayout);

//        // adding a button to our layout in codes
//        Button button1 = new Button(this);  // its constructor needs a context and as our class YoutubeActivity extends activity(indirectly) and a activity is a context, so we provided 'this'(object)
//        button1.setText("This button is created in codes");
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(700,120));    // this will set the width and height of the button1
//        constraintLayout.addView(button1);

        YouTubePlayerView player = new YouTubePlayerView(this);
        player.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        constraintLayout.addView(player);



    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
