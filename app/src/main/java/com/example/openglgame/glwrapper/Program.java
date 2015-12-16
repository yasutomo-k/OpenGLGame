package com.example.openglgame.glwrapper;

import android.opengl.GLES20;
import android.util.Log;

import java.io.InputStream;

public class Program {

    private Shader mVertexShader, mFragmentShader;
    private int mProgramObject;

    public Program(InputStream vertex_stream, InputStream fragment_stream) {
        mVertexShader = new Shader(GLES20.GL_VERTEX_SHADER, vertex_stream);
        mFragmentShader = new Shader(GLES20.GL_FRAGMENT_SHADER, fragment_stream);
    }

    public void build() {
        mProgramObject = GLES20.glCreateProgram();

        GLES20.glAttachShader(mProgramObject, mVertexShader.build());
        GLES20.glAttachShader(mProgramObject, mFragmentShader.build());

        GLES20.glLinkProgram(mProgramObject);

        check();
    }

    public void enable() {
        GLES20.glUseProgram(mProgramObject);
    }

    public int getAttributeLocation(String name) {
        return GLES20.glGetAttribLocation(mProgramObject, name);
    }

    public int getUniformLocation(String name) {
        return GLES20.glGetUniformLocation(mProgramObject, name);
    }

    private void check() {
        int[] check = new int[1];

        GLES20.glGetProgramiv(mProgramObject, GLES20.GL_LINK_STATUS, check, 0);

        if (check[0] == GLES20.GL_FALSE) {
            Log.d("Error: ", GLES20.glGetProgramInfoLog(mProgramObject));
        }
    }
}