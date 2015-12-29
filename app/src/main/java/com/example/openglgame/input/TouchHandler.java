package com.example.openglgame.input;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        float x = event.getX(index);
        float y = event.getY(index);

        x = 2.0f*(x/mWidth)-1.0f;
        y = (2*(mHeight-y)/mHeight) - 1.0f;

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mTouchs.put(id,new Touch(x, y));
                break;
            case MotionEvent.ACTION_MOVE:
                for(int size = event.getPointerCount(),i=0;i < size; i++){
                    Touch touch = mTouchs.get(event.getPointerId(i));
                    touch.move(event.getX(i),event.getY(i));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mTouchs.remove(id);
                break;
        }
    }

    public void update(float delataTime){
        for(Integer id:mTouchs.keySet()){
            mTouchs.get(id).update(delataTime);
        }
    }

    public boolean isEnpty(){
        return mTouchs.isEmpty();
    }

    public Set<Integer> keyset(){
        return mTouchs.keySet();
    }

    public Touch get(Integer id){
        return mTouchs.get(id);
    }

}
