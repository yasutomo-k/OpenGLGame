package com.example.openglgame.graphics;


public class ST {
    // s = top_right t = bottom_left
    private float mS0,mS1;
    private float mT0, mT1;

    public ST(float x, float y, float width, float height){
        mS0 = x;
        mS1 = x + width;
        mT0 = y;
        mT1 = y + height;
    }

    public float getS0(){
        return mS0;
    }

    public float getS1(){
        return mS1;
    }

    public float getT0(){
        return mT0;
    }

    public float getT1(){
        return mT1;
    }
}
