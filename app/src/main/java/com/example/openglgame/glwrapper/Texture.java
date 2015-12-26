package com.example.openglgame.glwrapper;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class Texture {

    private int mIndex, mTextureIndex;
    private Bitmap mImage;

    private final int[] TEXTURE_INDEX = new int[]{
            GLES20.GL_TEXTURE0, GLES20.GL_TEXTURE1, GLES20.GL_TEXTURE2,
            GLES20.GL_TEXTURE3, GLES20.GL_TEXTURE4, GLES20.GL_TEXTURE5,
            GLES20.GL_TEXTURE6, GLES20.GL_TEXTURE7, GLES20.GL_TEXTURE8,
    };

    public Texture(int index, Bitmap image){
        mIndex = index;
        mImage = image;
        mTextureIndex = TEXTURE_INDEX[index];
    }

    public void enable(int sampler){
        GLES20.glUniform1f(sampler, mIndex);
    }

    public void load(){
        int[]texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);

        GLES20.glActiveTexture(mTextureIndex);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);

        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mImage, 0);
    }
}