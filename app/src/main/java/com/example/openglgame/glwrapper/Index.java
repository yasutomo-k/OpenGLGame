package com.example.openglgame.glwrapper;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class Index {

    public static final int SHORT_SIZE = Short.SIZE/Byte.SIZE;

    private ShortBuffer mIndexBuffer;
    private int mMax;

    public Index(int max){
        mMax = max;
        short[] index = new short[6 * max];
        int len = index.length;
        int i,j;

        for(i = 0, j = 0 ; i < len; i += 6, j += 4){
            index[i] = (short)j;
            index[i + 1] = (short)(j + 1);
            index[i + 2] = (short)(j + 2);
            index[i + 3] = (short)j;
            index[i + 4] = (short)(j + 2);
            index[i + 5] = (short)(j + 3);
        }

        mIndexBuffer= ByteBuffer.allocateDirect(6 * SHORT_SIZE * max).order(ByteOrder.nativeOrder()).asShortBuffer();
        mIndexBuffer.put(index);
        mIndexBuffer.flip();
    }

    public void bind(){
        int[] buffers = new int[1];
        GLES20.glGenBuffers(1,buffers,0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffers[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER,mIndexBuffer.limit() * SHORT_SIZE,mIndexBuffer,GLES20.GL_STATIC_DRAW);
    }

}
