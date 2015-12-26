package com.example.openglgame.graphics;


import java.nio.FloatBuffer;

public class GLColor {

    private static final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;
    private static final int ELEMENTS = 3;
    private static final int VERTEX_COUNT = 4;

    private float mR,mG,mB;
    private float[] mColors;

    public GLColor(float r, float g, float b){
        mR = r;
        mG = g;
        mB = b;

        mColors = new float[ELEMENTS * VERTEX_COUNT];
    }

    public static int getSize(){
        return ELEMENTS * VERTEX_COUNT * FLOAT_SIZE;
    }

    public static int getCount(){
        return ELEMENTS;
    }

    public float getRed(){
        return mR;
    }

    public float getGreen(){
        return mG;
    }

    public float getBlue(){
        return mB;
    }

}
