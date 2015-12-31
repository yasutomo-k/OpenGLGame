package com.example.openglgame.utils;


import android.app.Activity;
import android.widget.TextView;

public class DebugLog {
    public static Activity mActivity;
    public static TextView mTextView;
    public static String log;
    public static int fps;
    public static String touchs;

    public DebugLog(Activity activity, TextView textivew){
        mActivity = activity;
        mTextView = textivew;
    }

    public static void setText(){
        final String fps_log = "FPS: " + fps;
        final String tou_log = "TOUCH: " + touchs;
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(fps_log + "\n" + tou_log);
            }
        });
    }
}
