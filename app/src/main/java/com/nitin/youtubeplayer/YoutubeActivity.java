package com.nitin.youtubeplayer;


import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "YoutubeActivity";

    private final String GOOGLE_API_KEY = "AIzaSyCwbNdunJe1wz06Tjxxoels9nxSESjoCnY";
    private final String YOUTUBE_VIDEO_ID = "xLlvOo611xQ";
    private final String YOUTUBE_PLAYLIST = "PLsEtM1pbiHlh-hbDBZwruDu9y4l4jVT7P";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we get a instance of our activity_youtube and now we can update it in code. activity_youtube is indeed the Root  so pass null as its root
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null, false);
        setContentView(constraintLayout);

//        // adding a button to our layout in codes
//        Button button1 = new Button(this);  // its constructor needs a context and as our class YoutubeActivity extends activity(indirectly) and a activity is a context, so we provided 'this'(object)
//        button1.setText("This button is created in codes");
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(700,120));    // this will set the width and height of the button1
//        constraintLayout.addView(button1);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        constraintLayout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this); // this will initialize our view to play youtube videos.


    }

    @Override  // called when initialization YoutubePlayer View succeeded
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        // youTubePlayer can be used to control video playbacks
        // wasRestored is true if it has restored some previous session(video) and now we can resume the video
        Log.d(TAG, "onInitializationSuccess: provider is :" + provider.toString());
        Toast.makeText(this, "Youtube Player initialization Successful", Toast.LENGTH_SHORT).show();
        if (!wasRestored) {
            // we play a new video is another video was not resuming
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override   // called when initialization YoutubePlayer View failed
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //YouTubeInitializationResult will provide the result of initialization, it will give the reason of failure
        int REQUEST_CODE = 1; // it means we want the errorDialogue.

        if (youTubeInitializationResult.isUserRecoverableError()) {
            // this means the error we got during initialization of YoutubePlayerView can be fixed by user
            // then a dialog is shown to user and on clicking them it will lead the user to somewhere he can fix the problem
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            // the error can't be fixed by user hence display a Toast(fading message at bottom).
            // '(%1$s)' is used to get name of then error message
            String errorMsg = String.format("Cann't initialize Youtube Player due to : (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
        }
    }
}
