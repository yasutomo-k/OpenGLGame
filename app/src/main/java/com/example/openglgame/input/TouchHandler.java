package com.example.openglgame.input;

import android.view.MotionEvent;

import com.example.openglgame.utils.DebugLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TouchHandler {

    private Map<Integer, Touch> mTouchs;

    private float mWidth, mHeight;

    public TouchHandler(){
        mTouchs = new HashMap<>();
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
                DebugLog.touchs = "Down: id:" + id + " x: " + x + " y: " + y;
                break;
            case MotionEvent.ACTION_MOVE:
                mTouchs.get(id).move(x, y);
                DebugLog.touchs = "Move: id:" + id + "x: " + x + " y: " + y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mTouchs.remove(id);
                DebugLog.touchs = "Up: id:" + id + "x: " + x + " y: " + y;
                break;
        }
    }

    public void update(float deltaTime){
        for(Integer id:mTouchs.keySet()){
            mTouchs.get(id).update(deltaTime);
        }
    }

    public boolean isEnpty(){
        return mTouchs.isEmpty();
    }

    public Set<Integer> keySet(){
        return mTouchs.keySet();
    }

    public Touch get(Integer id){
        return mTouchs.get(id);
    }

}
