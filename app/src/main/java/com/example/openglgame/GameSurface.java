package com.example.openglgame;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

public class GameSurface extends GLSurfaceView {

    GameRenderer mRenderer;

    public GameSurface(Context context, GameRenderer renderer){
        super(context);
        mRenderer = renderer;

        this.setEGLContextClientVersion(2);
        this.setEGLConfigChooser(8, 8, 8, 8, 8, 0);
        this.setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(@NonNull final MotionEvent event) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                mRenderer.onTouchEvent(event);
            }
        });
        return super.onTouchEvent(event);
    }
}
