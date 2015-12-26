package com.example.openglgame.graphics;

import java.nio.FloatBuffer;

public class ColoredRectangle {
    private static final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;

    private float mX, mY, mWidth, mHeight, mHalfWidth, mHalfHeight;
    private float mLeft, mRight, mTop, mBottom;
    private GLColor mColor;
    private float[] mElements;

    public ColoredRectangle(float x, float y, float width, float height, GLColor color){
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

        mColor = color;
        mElements = new float[4 * (2 + 3)];
    }

    public static int getSize(){
        return 4 * (2+3) * FLOAT_SIZE;
    }

    public static int getStride(){
        return FLOAT_SIZE * 5;
    }

    public static int getColorOffset(){
        return FLOAT_SIZE * 2;
    }

    public void getCoords(FloatBuffer float_buffer){
        mElements[0] = mLeft;
        mElements[1] = mTop;
        mElements[2] = mColor.getRed();
        mElements[3] = mColor.getGreen();
        mElements[4] = mColor.getBlue();
        mElements[5] = mLeft;
        mElements[6] = mBottom;
        mElements[7] = mColor.getRed();
        mElements[8] = mColor.getGreen();
        mElements[9] = mColor.getBlue();
        mElements[10] = mRight;
        mElements[11] = mBottom;
        mElements[12] = mColor.getRed();
        mElements[13] = mColor.getGreen();
        mElements[14] = mColor.getBlue();
        mElements[15] = mRight;
        mElements[16] = mTop;
        mElements[17] = mColor.getRed();
        mElements[18] = mColor.getGreen();
        mElements[19] = mColor.getBlue();

        float_buffer.put(mElements);
    }
}
