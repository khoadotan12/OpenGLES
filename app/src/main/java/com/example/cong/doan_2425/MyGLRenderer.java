package com.example.cong.doan_2425;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MyGLRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "MyGLRenderer";
    public static List<Cube> mCube;

    private int mPointProgram;
    public static int aaa = MainActivity.cube_size;
    public static int w, h;
    private final String pointVertexShaderCode =
            "uniform mat4 u_MVPMatrix;      \n"
                    + "attribute vec4 a_Position;     \n"
                    + "void main()                    \n"
                    + "{                              \n"
                    + "   gl_Position = u_MVPMatrix   \n"
                    + "               * a_Position;   \n"
                    + "   gl_PointSize = 5.0;         \n"
                    + "}                              \n";

    private final String pointFragmentShaderCode =
            "precision mediump float;       \n"
                    + "void main()                    \n"
                    + "{                              \n"
                    + "   gl_FragColor = vec4(1.0,    \n"
                    + "   1.0, 1.0, 1.0);             \n"
                    + "}                              \n";


    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private static List<float[]> mMVPMatrix = new ArrayList<float[]>();
    private float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    public static List<float[]> mObjectRotationMatrix = new ArrayList<float[]>();
    private final float[] mRotationMatrix = new float[16];
    private float[] mLightModelMatrix = new float[16];

    private static List<float[]> scratch = new ArrayList<float[]>();
    private final float[] mLightPosInModelSpace = new float[]{0.0f, 0.0f, 0.0f, 1.0f};

    private final float[] mLightPosInWorldSpace = new float[4];

    private final float[] mLightPosInEyeSpace = new float[4];

    public float mXAngle = 0;
    public float mYAngle = 0;
    public float scaleValue = 0.5f;
    public static int choice = 0;

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        // Set the background frame color
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        // Use culling to remove back faces.
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        // Enable depth testing
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        // Position the eye in front of the origin.
        final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = -0.5f;

        // We are looking toward the distance
        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -5.0f;

        // Set our up vector. This is where our head would be pointing were we holding the camera.
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;

        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

        mCube = new ArrayList<Cube>();

        final int pointVertexShader = MyGLRenderer.loadShader(
                GLES20.GL_VERTEX_SHADER,
                pointVertexShaderCode);
        final int pointFragmentShader = loadShader(
                GLES20.GL_FRAGMENT_SHADER,
                pointFragmentShaderCode);
        mPointProgram = createAndLinkProgram(pointVertexShader, pointFragmentShader,
                new String[]{"a_Position"});


    }

    @Override
    public void onDrawFrame(GL10 unused) {


        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        if (aaa < MainActivity.cube_size) {
            aaa++;
            createCube();
        }
        Matrix.setIdentityM(mLightModelMatrix, 0);
        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, -5.0f);
        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, 3.5f);

        Matrix.multiplyMV(mLightPosInWorldSpace, 0, mLightModelMatrix, 0, mLightPosInModelSpace, 0);
        Matrix.multiplyMV(mLightPosInEyeSpace, 0, mViewMatrix, 0, mLightPosInWorldSpace, 0);


        if (mCube.size() > 0) {
            for (int i = 0; i < mCube.size(); i++) {
                Matrix.setIdentityM(MyGLRenderer.mObjectRotationMatrix.get(i), 0);
                Matrix.translateM(mObjectRotationMatrix.get(i), 0, 0.0f, 0.0f, -5.0f);
                Matrix.rotateM(MyGLRenderer.mObjectRotationMatrix.get(i), 0, mXAngle, 0.0f, 1.0f, 0.0f);
                Matrix.rotateM(MyGLRenderer.mObjectRotationMatrix.get(i), 0, mYAngle, 1.0f, 0.0f, 0.0f);
                //Matrix.rotateM(MyGLRenderer.mObjectRotationMatrix.get(i), 0, mCube.get(i).zAngle, 0.0f, 0.0f, 1.0f);

                Matrix.rotateM(MyGLRenderer.mObjectRotationMatrix.get(i), 0, mCube.get(i).xAngle, 1.0f + mCube.get(i).OX, 0.0f, 0.0f);
                Matrix.rotateM(MyGLRenderer.mObjectRotationMatrix.get(i), 0, mCube.get(i).yAngle, 0.0f, 1.0f + mCube.get(i).OY, 0.0f);
                Matrix.rotateM(MyGLRenderer.mObjectRotationMatrix.get(i), 0, mCube.get(i).zAngle, 0.0f, 0.0f, 1.0f + mCube.get(i).OZ);

                mCube.get(i).changeColor(mCube.get(i).red, mCube.get(i).green, mCube.get(i).blue);
                if (i == choice) {
                    mCube.get(i).changeColor((float) 135 / 255, (float) 206 / 255, (float) 235 / 255);
                }
                Matrix.multiplyMM(scratch.get(i), 0, mViewMatrix, 0, mObjectRotationMatrix.get(i), 0);


                Matrix.scaleM(scratch.get(i), 0, scaleValue, scaleValue, scaleValue);


                /*if(i>0)
                    mCube.get(i).changeX(3);*/
                mCube.get(i).draw(scratch.get(i), mProjectionMatrix, mLightPosInEyeSpace);
                GLES20.glUseProgram(mPointProgram);
                drawLight(i);
            }
        }
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
        w = width;
        h = height;
    }

    /**
     * Utility method for compiling a OpenGL shader.
     * <p/>
     * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
     * method to debug shader coding errors.</p>
     *
     * @param type       - Vertex or fragment shader type.
     * @param shaderCode - String containing the shader code.
     * @return - Returns an id for the shader.
     */
    public static int loadShader(int type, String shaderCode) {

        int shaderHandle = GLES20.glCreateShader(type);

        if (shaderHandle != 0) {
            // Pass in the shader source.
            GLES20.glShaderSource(shaderHandle, shaderCode);

            // Compile the shader.
            GLES20.glCompileShader(shaderHandle);

            // Get the compilation status.
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            // If the compilation failed, delete the shader.
            if (compileStatus[0] == 0) {
                Log.e(TAG, "Error compiling shader: " + GLES20.glGetShaderInfoLog(shaderHandle));
                GLES20.glDeleteShader(shaderHandle);
                shaderHandle = 0;
            }
        }

        if (shaderHandle == 0) {
            throw new RuntimeException("Error creating shader.");
        }

        return shaderHandle;

    }

    public static int createAndLinkProgram(final int vertexShaderHandle, final int fragmentShaderHandle, final String[] attributes) {
        int programHandle = GLES20.glCreateProgram();

        if (programHandle != 0) {
            // Bind the vertex shader to the program.
            GLES20.glAttachShader(programHandle, vertexShaderHandle);

            // Bind the fragment shader to the program.
            GLES20.glAttachShader(programHandle, fragmentShaderHandle);

            // Bind attributes
            if (attributes != null) {
                final int size = attributes.length;
                for (int i = 0; i < size; i++) {
                    GLES20.glBindAttribLocation(programHandle, i, attributes[i]);
                }
            }

            // Link the two shaders together into a program.
            GLES20.glLinkProgram(programHandle);

            // Get the link status.
            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

            // If the link failed, delete the program.
            if (linkStatus[0] == 0) {
                Log.e(TAG, "Error compiling program: " + GLES20.glGetProgramInfoLog(programHandle));
                GLES20.glDeleteProgram(programHandle);
                programHandle = 0;
            }
        }

        if (programHandle == 0) {
            throw new RuntimeException("Error creating program.");
        }

        return programHandle;
    }

    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     * <p/>
     * <pre>
     * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
     * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
     *
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation - Name of the OpenGL call to check.
     */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    /**
     * Returns the rotation angle of the triangle shape (mTriangle).
     *
     * @return - A float representing the rotation angle.
     */
    private void drawLight(int i) {
        final int pointMVPMatrixHandle = GLES20.glGetUniformLocation(mPointProgram, "u_MVPMatrix");
        final int pointPositionHandle = GLES20.glGetAttribLocation(mPointProgram, "a_Position");

        // Pass in the position.
        GLES20.glVertexAttrib3f(pointPositionHandle, mLightPosInModelSpace[0], mLightPosInModelSpace[1], mLightPosInModelSpace[2]);

        // Since we are not using a buffer object, disable vertex arrays for this attribute.
        GLES20.glDisableVertexAttribArray(pointPositionHandle);

        // Pass in the transformation matrix.
        Matrix.multiplyMM(mMVPMatrix.get(i), 0, mViewMatrix, 0, mLightModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix.get(i), 0, mProjectionMatrix, 0, mMVPMatrix.get(i), 0);
        GLES20.glUniformMatrix4fv(pointMVPMatrixHandle, 1, false, mMVPMatrix.get(i), 0);

        // Draw the point.
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1);
    }

    public static void createCube() {
        Cube cubeTemp = new Cube();
        mCube.add(cubeTemp);
        aaa = mCube.size();
        float[] mRotationTemp = new float[16];
        mObjectRotationMatrix.add(mRotationTemp);
        float[] ScratchTemp = new float[16];
        scratch.add(ScratchTemp);
        float[] mMVPTemp = new float[16];
        mMVPMatrix.add(mMVPTemp);
    }

    public static void deleteCube() {
        mCube.remove(choice);
        mObjectRotationMatrix.remove(choice);
        scratch.remove(choice);
        mMVPMatrix.remove(choice);
        aaa--;
        MainActivity.cube_size--;
        if (choice == mCube.size() && choice > 0) choice--;
    }
}
