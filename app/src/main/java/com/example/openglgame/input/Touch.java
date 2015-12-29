package com.example.openglgame.input;


public class Touch {

    private float mX, mY;

    public Touch(float x, float y){
        mX = x;
        mY = y;
    }

    public void move(float x, float y){
        mX = x;
        mY = y;
    }

    public float getX(){
        return mX;
    }

    public float getY(){
        return mY;
    }
}
