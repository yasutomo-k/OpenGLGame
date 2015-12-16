package com.example.openglgame.glwrapper;

import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Shader {

    private int mType;
    private String mShaderCode;

    public Shader(int type, InputStream stream){
        mType = type;
        mShaderCode = loadCode(stream);
    }

    public int build(){
        int shader = GLES20.glCreateShader(mType);

        GLES20.glShaderSource(shader, mShaderCode);
        GLES20.glCompileShader(shader);

        check(shader);

        return shader;
    }

    private void check(int shader){
        int[] check = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, check, 0);

        if(check[0] == GLES20.GL_FALSE){
            Log.d("Error: ", GLES20.glGetShaderInfoLog(shader));
        }
    }

    private String loadCode(InputStream stream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String tempString;

        try {
            while ((tempString = reader.readLine()) != null) {
                builder.append(tempString);
                builder.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }
}

