package com.example.openglgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Debug;
import android.view.MotionEvent;

import com.example.openglgame.glwrapper.Index;
import com.example.openglgame.glwrapper.Program;
import com.example.openglgame.glwrapper.Texture;
import com.example.openglgame.graphics.ST;
import com.example.openglgame.graphics.TextureRectangle;
import com.example.openglgame.input.TouchHandler;
import com.example.openglgame.utils.DebugLog;
import com.example.openglgame.utils.GameTime;
import com.example.openglgame.input.Touch;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class GameRenderer implements GLSurfaceView.Renderer{

    private Program mProgram;
    private Index mIndex;
    private Texture mTexture;

    private TouchHandler mTouchHandler;

    private FloatBuffer mFloatBuffer;

    private GameTime mTime;
    private Random mRand;

    private List<TextureRectangle> mObjects;
    private Deque<Touch> mTouchs;

    private int mMax,mSize;
    private int mPositionHandler,mColorHandler;

    private float mDeltaTime;


    public GameRenderer(Context context){
        mDeltaTime = 0f;
        mMax = 100;
        mSize = 0;

        mTime = new GameTime();
        mRand = new Random();

        mTouchHandler = new TouchHandler();

        mTexture = new Texture(0, BitmapFactory.decodeResource(context.getResources(),R.drawable.sphere));

        mObjects = new ArrayList<>();
        mTouchs = new ArrayDeque<>();

        Resources resources = context.getResources();

        InputStream vertex_stream = resources.openRawResource(R.raw.texture_vertex);
        InputStream fragment_stream = resources.openRawResource(R.raw.texture_fragment);

        mProgram = new Program(vertex_stream, fragment_stream);
        mIndex = new Index(mMax);



        mFloatBuffer = ByteBuffer.allocateDirect(TextureRectangle.getSize()*mMax).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        mProgram.build();
        mProgram.enable();

        mPositionHandler = mProgram.getAttributeLocation("a_Position");
        mColorHandler = mProgram.getAttributeLocation("a_TexCoord");

        int[] vertex = new int[1];
        GLES20.glGenBuffers(1, vertex, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertex[0]);

        mIndex.bind();
        mTexture.load();
        mTexture.enable(mProgram.getUniformLocation("u_Sampler"));
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        mTouchHandler.setScreenSize(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mDeltaTime = mTime.update();
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mObjects.clear();
        mSize = 0;

        mTouchHandler.update(mDeltaTime);

        for(Integer id:mTouchHandler.keySet()){
            Touch touch = mTouchHandler.get(id);
            mObjects.add(new TextureRectangle(touch.getX(),touch.getY(),touch.getTime(),touch.getTime(),new ST(0f,0f,1f,1f)));
            mSize += 1;
        }


        for(TextureRectangle each:mObjects){
            each.getCoords(mFloatBuffer);
        }

        //clear
        mFloatBuffer.clear();

        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, TextureRectangle.getSize() * mSize, mFloatBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glVertexAttribPointer(mPositionHandler, 2, GLES20.GL_FLOAT, false, TextureRectangle.getStride(), 0);
        GLES20.glVertexAttribPointer(mColorHandler,2,GLES20.GL_FLOAT,false,TextureRectangle.getStride(),TextureRectangle.getColorOffset());
        GLES20.glEnableVertexAttribArray(mPositionHandler);
        GLES20.glEnableVertexAttribArray(mColorHandler);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6 * mSize, GLES20.GL_UNSIGNED_SHORT, 0);

        mTime.logFPS();
        DebugLog.setText();
    }

    public void onTouchEvent(MotionEvent event){
        mTouchHandler.onTouchEvent(event);
    }

}
