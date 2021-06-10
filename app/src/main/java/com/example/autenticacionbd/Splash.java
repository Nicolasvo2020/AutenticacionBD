package com.example.autenticacionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    public static final long SPLASH_SCREEN_DELAY = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        music();



        TimerTask time= new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent().setClass(Splash.this, Login.class);
                startActivity(intent);
               
                finish();
            }
        };
        //se ejecurata una vez cerrado
        Timer timer = new Timer();
        timer.schedule(time,SPLASH_SCREEN_DELAY);



    }

    private void music() {
        MediaPlayer mp=MediaPlayer.create(Splash.this,R.raw.inicio);
        mp.start();
    }
}