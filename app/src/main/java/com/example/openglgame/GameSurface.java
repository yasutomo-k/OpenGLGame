package com.example.openglgame;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GameSurface extends GLSurfaceView {

    GameRenderer mRenderer;

    public GameSurface(Context context){
        super(context);
        mRenderer = new GameRenderer(context);

        this.setEGLContextClientVersion(2);
        this.setEGLConfigChooser(8, 8, 8, 8, 8, 0);
        this.setRenderer(mRenderer);

    }

    public GameSurface(Context context,AttributeSet attrs){
        super(context, attrs);

        mRenderer = new GameRenderer(context);

        this.setEGLContextClientVersion(2);
        this.setEGLConfigChooser(8, 8, 8, 8, 8, 0);
        this.setRenderer(mRenderer);
    }

    @Override
    public boolean onTouchEvent(@NonNull final MotionEvent event) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                mRenderer.onTouchEvent(event);
            }
        });
        return true;
    }
}
