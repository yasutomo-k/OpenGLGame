package com.example.openglgame.graphics;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Rectangle {

    private final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;
    private final int SHORT_SIZE = Short.SIZE/Byte.SIZE;
    private final int COORDS_PER_VERTEX = 2;
    private final int VERTEX_COUNT = 4;
    //two triangles 3points + 3points = 6points
    private final int INDEX_COUNT = 6;
    private final int COORDS_COUNT = COORDS_PER_VERTEX * VERTEX_COUNT;
    private final int VERTEX_STRIDE = COORDS_PER_VERTEX * FLOAT_SIZE;

    private float mX, mY;
    private float mWidth, mHeight;

    private float mLeft, mRight, mTop, mBottom;
    private float mHalfWidth, mHalfHeight;

    private float[] mCoords;
    private short[] mIndices;

    public Rectangle(float x, float y, float width, float height){
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;

        mHalfWidth = width * 0.5f;
        mHalfHeight = height * 0.5f;

        mCoords = new float[COORDS_COUNT];
        mIndices = new short[]{0,1,2,0,2,3};
    }

    public int getSize(){
        return COORDS_COUNT * FLOAT_SIZE;
    }

    public int getIndexSize(){return mIndices.length * SHORT_SIZE;}

    public int getCount(){
        return VERTEX_COUNT;
    }

    public int getIndexCount(){return INDEX_COUNT;}

    public void getCoords(FloatBuffer float_buffer,ShortBuffer short_buffer){
        this.mLeft = mX - mHalfWidth;
        this.mRight = mX  + mHalfWidth;
        this.mTop = mY + mHalfHeight;
        this.mBottom = mY - mHalfHeight;

        mCoords[0] = mLeft;
        mCoords[1] = mTop;
        mCoords[2] = mLeft;
        mCoords[3] = mBottom;
        mCoords[4] = mRight;
        mCoords[5] = mBottom;
        mCoords[6] = mRight;
        mCoords[7] = mTop;

        float_buffer.put(mCoords);
        short_buffer.put(mIndices);
    }
}
