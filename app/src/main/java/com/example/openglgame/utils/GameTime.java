package com.example.openglgame.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public class GameTime {

    private long startTime, tempTime;
    private float FPSTime;
    private int frames;

    public GameTime(){
        startTime = System.nanoTime();
        FPSTime = startTime;
    }

    public float update(){
        tempTime = startTime;
        startTime = System.nanoTime();

        return (startTime - tempTime)/ TimeUnit.SECONDS.toNanos(1);
    }

    public void logFPS(){
        frames++;

        if(System.nanoTime() - FPSTime >= TimeUnit.SECONDS.toNanos(1)){
            Log.d("", "FPS: " + frames);
            DebugLog.fps = frames;
            frames = 0;
            FPSTime = System.nanoTime();
        }
    }
}
