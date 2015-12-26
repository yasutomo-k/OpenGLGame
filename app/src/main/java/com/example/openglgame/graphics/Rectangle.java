package com.example.openglgame.graphics;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Rectangle {

    private static final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;
    private static final int SHORT_SIZE = Short.SIZE/Byte.SIZE;
    private static final int COORDS_PER_VERTEX = 2;
    public static final int VERTEX_COUNT = 4;
    //two triangles 3points + 3points = 6points
    private static final int INDEX_COUNT = 6;
    public static final int COORDS_COUNT = COORDS_PER_VERTEX * VERTEX_COUNT;
    private static final int VERTEX_STRIDE = COORDS_PER_VERTEX * FLOAT_SIZE;

    private float mX, mY;
    private float mWidth, mHeight;

    private float mLeft, mRight, mTop, mBottom;
    private float mHalfWidth, mHalfHeight;

    private float[] mCoords;

    public Rectangle(float x, float y, float width, float height){
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;

        mHalfWidth = width * 0.5f;
        mHalfHeight = height * 0.5f;

        this.mLeft = mX - mHalfWidth;
        this.mRight = mX  + mHalfWidth;
        this.mTop = mY + mHalfHeight;
        this.mBottom = mY - mHalfHeight;

        mCoords = new float[COORDS_COUNT];
    }

    public static int getSize(){
        return COORDS_COUNT * FLOAT_SIZE;
    }

    public static int getIndexSize(){return INDEX_COUNT * SHORT_SIZE;}

    public int getCount(){
        return VERTEX_COUNT;
    }

    public static int getIndexCount(){return INDEX_COUNT;}

    public float getLeft(){
        return mLeft;
    }

    public float getRight(){
        return mRight;
    }

    public float getTop(){
        return mTop;
    }

    public float getBotton(){
        return mBottom;
    }

    public void getCoords(FloatBuffer float_buffer){
        mCoords[0] = mLeft;
        mCoords[1] = mTop;
        mCoords[2] = mLeft;
        mCoords[3] = mBottom;
        mCoords[4] = mRight;
        mCoords[5] = mBottom;
        mCoords[6] = mRight;
        mCoords[7] = mTop;
    }
}
