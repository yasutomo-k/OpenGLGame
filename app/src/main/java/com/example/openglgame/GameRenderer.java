package com.example.openglgame;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.openglgame.glwrapper.Program;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class GameRenderer implements GLSurfaceView.Renderer{

    private final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;

    private Program mProgram;
    private FloatBuffer mFloatBuffer;

    private float[] mPoint = {0.0f,0.0f};

    public GameRenderer(Context context){
        Resources resources = context.getResources();

        InputStream vertex_stream = resources.openRawResource(R.raw.point_vertex);
        InputStream fragment_stream = resources.openRawResource(R.raw.point_fragment);

        mProgram = new Program(vertex_stream, fragment_stream);
        mFloatBuffer = ByteBuffer.allocateDirect(2 * FLOAT_SIZE).order(ByteOrder.nativeOrder()).asFloatBuffer();

    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        mProgram.build();
        mProgram.enable();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mFloatBuffer.put(mPoint);
        mFloatBuffer.flip();

        int a_Position = mProgram.getAttributeLocation("a_Position");
        GLES20.glVertexAttribPointer(a_Position, 2 , GLES20.GL_FLOAT, false, 0 , mFloatBuffer);
        GLES20.glEnableVertexAttribArray(a_Position);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0 , 1);
    }
}
