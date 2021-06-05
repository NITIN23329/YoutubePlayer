package com.nitin.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playInOurApp = findViewById(R.id.playInOurApp);
        Button standaloneMenu = findViewById(R.id.standaloneMenu);
        // this::onClick will refers to the onClick() we wrote inside this class
        // we created a common OnCLickListener() for both button and uses switch statement to get which button was pressed and take particular action
        playInOurApp.setOnClickListener(this::onClick);
        standaloneMenu.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        //  Intent is an description of operation which is going to be performed.
        //	We can start a new activity during runtime by calling the function startActivity() and passing an intent to it.
        switch (view.getId()) {
            case R.id.playInOurApp:
                // intent() takes 1st parameter as the context/activity from which it is being called
                // 2nd parameter is the new activity which will be created
                intent = new Intent(this, YoutubeActivity.class);
                break;
            case R.id.standaloneMenu:
                intent = new Intent(this, StandaloneActivity.class);
        }
        if (intent != null) startActivity(intent);
    }

}