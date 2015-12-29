package com.example.openglgame.input;


public class Touch {

    private float mX, mY;
    private float mTime;

    public Touch(float x, float y){
        mX = x;
        mY = y;
        mTime = 0f;
    }

    public void move(float x, float y){
        mX = x;
        mY = y;
    }

    public void update(float deltaTime){
        mTime += deltaTime;
    }

    public float getX(){
        return mX;
    }

    public float getY(){
        return mY;
    }

    public float getTime(){
        return mTime;
    }
}
