package com.example.openglgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.openglgame.glwrapper.Index;
import com.example.openglgame.glwrapper.Program;
import com.example.openglgame.graphics.ColoredRectangle;
import com.example.openglgame.graphics.GLColor;
import com.example.openglgame.graphics.Rectangle;
import com.example.openglgame.utils.GameTime;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class GameRenderer implements GLSurfaceView.Renderer{

    private Program mProgram;
    private Index mIndex;

    private FloatBuffer mFloatBuffer;

    private GameTime mTime;

    private Random mRand;

    private List<ColoredRectangle> mObjects;

    private int mMax;

    private int[] mVertex;
    private int mPositionHandler,mColorHandler;

    public GameRenderer(Context context){
        mMax = 10000;
        mTime = new GameTime();
        mRand = new Random();

        mObjects = new ArrayList<>();

        for(int i = 0;i<mMax;i++){
            mObjects.add(new ColoredRectangle((2*mRand.nextFloat()-1),2*mRand.nextFloat()-1,0.1f,0.1f,new GLColor(mRand.nextFloat(),mRand.nextFloat(),mRand.nextFloat())));
        }

        Resources resources = context.getResources();

        InputStream vertex_stream = resources.openRawResource(R.raw.color_vertex);
        InputStream fragment_stream = resources.openRawResource(R.raw.color_fragment);

        mProgram = new Program(vertex_stream, fragment_stream);
        mIndex = new Index(mMax);


        mFloatBuffer = ByteBuffer.allocateDirect(ColoredRectangle.getSize()*mMax).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        mProgram.build();
        mProgram.enable();

        mPositionHandler = mProgram.getAttributeLocation("a_Position");
        mColorHandler = mProgram.getAttributeLocation("a_Color");

        mVertex = new int[1];
        GLES20.glGenBuffers(1, mVertex, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertex[0]);

        mIndex.bind();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mTime.update();
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        for(ColoredRectangle each:mObjects){
            each.getCoords(mFloatBuffer);
        }
        mFloatBuffer.flip();


        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER,ColoredRectangle.getSize()*mMax, mFloatBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glVertexAttribPointer(mPositionHandler, 2, GLES20.GL_FLOAT, false, ColoredRectangle.getStride(), 0);
        GLES20.glVertexAttribPointer(mColorHandler,3,GLES20.GL_FLOAT,false,ColoredRectangle.getStride(),ColoredRectangle.getColorOffset());
        GLES20.glEnableVertexAttribArray(mPositionHandler);
        GLES20.glEnableVertexAttribArray(mColorHandler);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6 * mMax, GLES20.GL_UNSIGNED_SHORT, 0);

        mTime.logFPS();
    }

}
