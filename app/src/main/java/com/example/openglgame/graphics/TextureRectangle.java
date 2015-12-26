package com.example.openglgame.graphics;


import java.nio.FloatBuffer;

public class TextureRectangle {
    private static final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;

    private float mX, mY, mWidth, mHeight, mHalfWidth, mHalfHeight;
    private float mLeft, mRight, mTop, mBottom;
    private ST mST;
    private float[] mElements;

    public TextureRectangle(float x, float y, float width, float height, ST st){
        mX = x;
        mY = y;
        mWidth  = width;
        mHeight = height;

        mHalfWidth = width * 0.5f;
        mHalfHeight = height * 0.5f;

        mLeft = mX - mHalfWidth;
        mRight = mX + mHalfWidth;
        mTop = mY + mHalfHeight;
        mBottom = mY - mHalfHeight;

        mST = st;
        mElements = new float[4 * (2 + 2)];
    }

    public static int getSize(){
        return 4 * (2+2) * FLOAT_SIZE;
    }

    public static int getStride(){
        return FLOAT_SIZE * 4;
    }

    public static int getColorOffset(){
        return FLOAT_SIZE * 2;
    }

    public void getCoords(FloatBuffer float_buffer){
        mElements[0] = mLeft;
        mElements[1] = mTop;
        mElements[2] = mST.getS0();
        mElements[3] = mST.getT0();
        mElements[4] = mLeft;
        mElements[5] = mBottom;
        mElements[6] = mST.getS0();
        mElements[7] = mST.getT1();
        mElements[8] = mRight;
        mElements[9] = mBottom;
        mElements[10] = mST.getS1();
        mElements[11] = mST.getT1();
        mElements[12] = mRight;
        mElements[13] = mTop;
        mElements[14] = mST.getS1();
        mElements[15] = mST.getT0();

        float_buffer.put(mElements);
    }
}
