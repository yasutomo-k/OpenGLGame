package com.example.openglgame;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    GameSurface mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceView = new GameSurface(this, new GameRenderer(this));
        setContentView(mSurfaceView);
    }

}
