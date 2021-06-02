package com.nitin.youtubeplayer;


import android.os.Bundle;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class YoutubeActivity extends YouTubeBaseActivity
                implements YouTubePlayer.OnInitializedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we get a instance of our activity_youtube and now we can update it in code. activity_youtube is indeed the Root  so pass null as its root
        ConstraintLayout constraintLayout = (ConstraintLayout)getLayoutInflater().inflate(R.layout.activity_youtube,null,false);
        setContentView(constraintLayout);

        // adding a button to our layout in codes
        Button button1 = new Button(this);  // its constructor needs a context and as our class YoutubeActivity extends activity(indirectly) , so we provided 'this'(object)
        button1.setText("This button is created in codes");
        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300,80));
        constraintLayout.addView(button1);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
