package com.example.openglgame;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.openglgame.glwrapper.Program;
import com.example.openglgame.graphics.Rectangle;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class GameRenderer implements GLSurfaceView.Renderer{

    private Program mProgram;
    private FloatBuffer mFloatBuffer;
    private ShortBuffer mShortBuffer;

    private Rectangle mRectangle;

    private int[] mVertex;
    private int mPositionHandler;

    public GameRenderer(Context context){
        int maxSize = 100;
        Resources resources = context.getResources();

        InputStream vertex_stream = resources.openRawResource(R.raw.point_vertex);
        InputStream fragment_stream = resources.openRawResource(R.raw.point_fragment);

        mProgram = new Program(vertex_stream, fragment_stream);
        mRectangle = new Rectangle(0f, 0f, 0.5f, 0.5f);



        mFloatBuffer = ByteBuffer.allocateDirect(maxSize * mRectangle.getSize()).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mShortBuffer = ByteBuffer.allocateDirect(maxSize * mRectangle.getIndexSize()).order(ByteOrder.nativeOrder()).asShortBuffer();
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        mProgram.build();
        mProgram.enable();

        mPositionHandler = mProgram.getAttributeLocation("a_Position");

        mVertex = new int[2];
        GLES20.glGenBuffers(2, mVertex, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertex[0]);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mVertex[1]);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mRectangle.getCoords(mFloatBuffer, mShortBuffer);
        mFloatBuffer.flip();
        mShortBuffer.flip();


        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, mRectangle.getIndexSize(), mShortBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mRectangle.getSize(), mFloatBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glVertexAttribPointer(mPositionHandler, 2 , GLES20.GL_FLOAT, false, 0 , 0);
        GLES20.glEnableVertexAttribArray(mPositionHandler);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES,mRectangle.getIndexCount(),GLES20.GL_UNSIGNED_SHORT,0);
    }
}
