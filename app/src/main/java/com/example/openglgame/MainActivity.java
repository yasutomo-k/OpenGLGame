package com.example.openglgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.openglgame.utils.DebugLog;

public class MainActivity extends Activity {

    GameSurface mSurfaceView;
    DebugLog mDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mSurfaceView = (GameSurface)findViewById(R.id.gameSurface);
        mDebug = new DebugLog(this,(TextView)findViewById(R.id.debugView));
    }

}
