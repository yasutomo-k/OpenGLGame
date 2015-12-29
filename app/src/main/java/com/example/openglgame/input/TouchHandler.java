package com.example.openglgame.input;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TouchHandler {

    private Map<Integer, Touch> mTouchs;
    private List<Touch> mTouchArray;

    private float mWidth, mHeight;

    public TouchHandler(){
        mTouchs = new HashMap<>();
        mTouchArray = new ArrayList<>();
    }

    public void setScreenSize(int width, int height){
        mWidth = (float)width;
        mHeight = (float)height;

    }

    public void onTouchEvent(MotionEvent event){
        int id = event.getPointerId(event.getActionIndex());

        float x = event.getX();
        float y = event.getY();

        x = 2.0f*(x/mWidth)-1.0f;
        y = (2*(mHeight-y)/mHeight) - 1.0f;

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mTouchs.put(id,new Touch(x, y));
                break;
            case MotionEvent.ACTION_MOVE:
                mTouchs.get(id).move(x, y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mTouchs.remove(id);
                break;
        }
    }

    public boolean isEnpty(){
        return mTouchs.isEmpty();
    }

}
