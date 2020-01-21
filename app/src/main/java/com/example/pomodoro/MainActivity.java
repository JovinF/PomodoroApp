package com.example.pomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 1500;
    private boolean isRunning;
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRunning", isRunning);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        runTimer();
    }

    //Start timer when Start button is clicked
    public  void onClickStart(View view){
        isRunning = true;
    }

    //Stop timer when Stop button is clicked
    public void onClickStop(View view){
        isRunning = false;
    }

    //Reset timer when Reste button is clicked
    public void onClickReset(View view){
        isRunning = false;
        seconds = 1500;
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

        final TextView timeView = (TextView)findViewById(R.id.time_view);
        int hours = seconds/3600;
        int minutes = (seconds%3600)/60;
        int secs = seconds%60;
        String time = String.format("%d:%02d:%02d", hours, minutes, secs);
        timeView.setText(time);
        if(isRunning){
            seconds--;
        }
        handler.postDelayed(this,1000);
            }
        });
    }



}
