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

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
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

    // PlaybackEventListener is an interface.
    //Interface definition for callbacks that are invoked when video playback events occur.
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this,"Video is playing :)",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this,"Video is paused :(",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this,"Video got Stopped :?",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBuffering(boolean b) {}

        @Override
        public void onSeekTo(int i) {}
    };
    //Interface definition for callbacks that are invoked when the high-level player state changes.
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {
            Toast.makeText(YoutubeActivity.this,"Wait, Video is Loading!",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLoaded(String s) {}

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Ad is coming -_-",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this,"Hi, Starting Video ;)",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this,"Video ended, Bye ^_^",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {}
    };
}
