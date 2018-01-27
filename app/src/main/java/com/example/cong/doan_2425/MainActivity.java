package com.example.cong.doan_2425;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener
{
    /** Hold a reference to our GLSurfaceView */
    private GLSurfaceView mGLSurfaceView;
    public static Button Cube_button;
    public static Button Move_button;
    public static Button X_button;
    public static Button Y_button;
    public static Button Z_button;
    public static Button tabLeft_button;
    public static Button tabRight_button;
    public static Button Delete_button;
    public static Button Color_button;
    public static Button[] colors= new Button[16];
    public static Button Scale_button;
    public static Button Rotate_button;
    public static int cube_size=0;
    public static boolean isX=false;
    public static boolean isY=false;
    public static boolean isZ=false;
    public static boolean isMoving = false;
    public static boolean isScaling = false;
    public static boolean isRotating = false;
    private boolean isColor =false;
    int [] values= {0xFFFF0000,0xFF0000FF,0xFF00FF00,0xFFFFFF00,
            0xFF000000,0xFF808080,0xFFC0C0C0,0xFFFFFFFF,
            0xFF800000,0xFF808000,0xFF008000,0xFF008080,
            0xFF00FFFF,0xFF000080,0xFF800080,0xFFFF00FF,
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mGLSurfaceView = new MyGLSurfaceView(this);


        setContentView(mGLSurfaceView);
        Cube_button= new Button(this);
        Cube_button.setText("Them");
        Cube_button.setX(10);
        Cube_button.setY(10);
        this.addContentView(Cube_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Cube_button.setOnClickListener(this);
        Cube_button.getBackground().setColorFilter(0xFFFF00FF, PorterDuff.Mode.MULTIPLY);

        Move_button = new Button(this);
        Move_button.setText("Di chuyen");
        Move_button.setX(60);
        Move_button.setY(10);
        this.addContentView(Move_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Move_button.setOnClickListener(this);
        Move_button.getBackground().setColorFilter(0xFF00FF00,PorterDuff.Mode.MULTIPLY);

        X_button = new Button(this);
        X_button.setText("X");
        X_button.setX(220);
        X_button.setY(10);
        this.addContentView(X_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        X_button.setOnClickListener(this);

        Y_button = new Button(this);
        Y_button.setText("Y");
        Y_button.setX(260);
        Y_button.setY(10);
        this.addContentView(Y_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Y_button.setOnClickListener(this);

        Z_button = new Button(this);
        Z_button.setText("Z");
        Z_button.setX(300);
        Z_button.setY(10);
        this.addContentView(Z_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Z_button.setOnClickListener(this);

        tabLeft_button = new Button(this);
        tabLeft_button.setText("Tab");
        tabLeft_button.setX(340);
        tabLeft_button.setY(10);
        this.addContentView(tabLeft_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tabLeft_button.setOnClickListener(this);
        tabLeft_button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);

        tabRight_button = new Button(this);
        tabRight_button.setText("rTab");
        tabRight_button.setX(390);
        tabRight_button.setY(10);
        this.addContentView(tabRight_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tabRight_button.setOnClickListener(this);
        tabRight_button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);

        Delete_button = new Button(this);
        Delete_button.setText("Xoa");
        Delete_button.setX(760);
        Delete_button.setY(10);
        this.addContentView(Delete_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Delete_button.setOnClickListener(this);
        Delete_button.getBackground().setColorFilter(0xFFC0C000, PorterDuff.Mode.MULTIPLY);

        Color_button = new Button(this);
        Color_button.setText("To mau");
        Color_button.setX(440);
        Color_button.setY(10);
        this.addContentView(Color_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Color_button.setOnClickListener(this);
        Color_button.getBackground().setColorFilter(0xFFFFC000, PorterDuff.Mode.MULTIPLY);

        Scale_button = new Button(this);
        Scale_button.setText("Co gian");
        Scale_button.setX(165);
        Scale_button.setY(10);
        this.addContentView(Scale_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Scale_button.setOnClickListener(this);
        Scale_button.getBackground().setColorFilter(0xFFFFFF00, PorterDuff.Mode.MULTIPLY);

        for(int i=0;i<16;i++)
        {
            colors[i]=new Button(this);
            if(i%2==0)
                colors[i].setY(0);
            if(i%2==1)
                colors[i].setY(20);
            if(i<2)
                colors[i].setX(500);
            if(i>1 && i<4)
                colors[i].setX(530);
            if(i>3 && i<6)
                colors[i].setX(560);
            if(i>5 && i<8)
                colors[i].setX(590);
            if(i>7 && i<10)
                colors[i].setX(620);
            if(i>9 && i<12)
                colors[i].setX(650);
            if(i>11 && i<14)
                colors[i].setX(680);
            if(i>13)
                colors[i].setX(710);

            colors[i].setLayoutParams (new LinearLayout.LayoutParams(25, 15));
            //colors[i].setWidth(10);
            colors[i].setMaxHeight(5);
            colors[i].setMaxWidth(5);
            //colors[i].setHeight(10);
            colors[i].getBackground().setColorFilter(values[i], PorterDuff.Mode.MULTIPLY);
            colors[i].setEnabled(false);
            colors[i].setVisibility(View.INVISIBLE);

            this.addContentView(colors[i], new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            colors[i].setOnClickListener(this);
        }

        Rotate_button = new Button(this);
        Rotate_button.setText("Xoay");
        Rotate_button.setX(130);
        Rotate_button.setY(10);
        this.addContentView(Rotate_button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Rotate_button.setOnClickListener(this);
        Rotate_button.getBackground().setColorFilter(0xFF0000FF, PorterDuff.Mode.MULTIPLY);

        Move_button.setEnabled(false);
        tabRight_button.setEnabled(false);
        tabLeft_button.setEnabled(false);
        Color_button.setEnabled(false);
        Scale_button.setEnabled(false);
        Rotate_button.setEnabled(false);

        Move_button.setVisibility(View.INVISIBLE);
        tabRight_button.setVisibility(View.INVISIBLE);
        tabLeft_button.setVisibility(View.INVISIBLE);
        Color_button.setVisibility(View.INVISIBLE);
        Scale_button.setVisibility(View.INVISIBLE);
        Rotate_button.setVisibility(View.INVISIBLE);

        X_button.setAlpha(0.5f);
        Y_button.setAlpha(0.5f);
        Z_button.setAlpha(0.5f);

        X_button.setEnabled(false);
        Y_button.setEnabled(false);
        Z_button.setEnabled(false);
        Delete_button.setEnabled(false);

        X_button.setVisibility(View.INVISIBLE);
        Y_button.setVisibility(View.INVISIBLE);
        Z_button.setVisibility(View.INVISIBLE);
        Delete_button.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        if (v == Cube_button) {
            cube_size += 1;
            Move_button.setEnabled(true);
            tabRight_button.setEnabled(true);
            tabLeft_button.setEnabled(true);
            Delete_button.setEnabled(true);
            Color_button.setEnabled(true);
            Scale_button.setEnabled(true);
            Rotate_button.setEnabled(true);

            Move_button.setVisibility(View.VISIBLE);
            tabRight_button.setVisibility(View.VISIBLE);
            tabLeft_button.setVisibility(View.VISIBLE);
            Delete_button.setVisibility(View.VISIBLE);
            Color_button.setVisibility(View.VISIBLE);
            Scale_button.setVisibility(View.VISIBLE);
            Rotate_button.setVisibility(View.VISIBLE);
            mGLSurfaceView.requestRender();
        }

        if (v == Scale_button) {
            isMoving = false;
            isRotating = false;
            isScaling = !isScaling;

            isX = false;
            isY = false;
            isZ = false;

            X_button.setAlpha(0.5f);
            Y_button.setAlpha(0.5f);
            Z_button.setAlpha(0.5f);
            if (isScaling == true) {
                X_button.setEnabled(true);
                Y_button.setEnabled(true);
                Z_button.setEnabled(true);
                X_button.getBackground().setColorFilter(0xFFFFFF00, PorterDuff.Mode.MULTIPLY);
                Y_button.getBackground().setColorFilter(0xFFFFFF00, PorterDuff.Mode.MULTIPLY);
                Z_button.getBackground().setColorFilter(0xFFFFFF00, PorterDuff.Mode.MULTIPLY);
                X_button.setVisibility(View.VISIBLE);
                Y_button.setVisibility(View.VISIBLE);
                Z_button.setVisibility(View.VISIBLE);
                isColor = false;
                for (int i = 0; i < 16; i++) {
                    colors[i].setEnabled(false);
                    colors[i].setVisibility(View.INVISIBLE);
                }
            } else {
                X_button.setEnabled(false);
                Y_button.setEnabled(false);
                Z_button.setEnabled(false);

                isX = false;
                isY = false;
                isZ = false;

                X_button.setAlpha(0.5f);
                Y_button.setAlpha(0.5f);
                Z_button.setAlpha(0.5f);

                X_button.setVisibility(View.INVISIBLE);
                Y_button.setVisibility(View.INVISIBLE);
                Z_button.setVisibility(View.INVISIBLE);
            }
        }

        if (v == Move_button) {
            isScaling = false;
            isRotating = false;
            isMoving = !isMoving;

            isX = false;
            isY = false;
            isZ = false;

            X_button.setAlpha(0.5f);
            Y_button.setAlpha(0.5f);
            Z_button.setAlpha(0.5f);
            if (isMoving == true) {
                X_button.setEnabled(true);
                Y_button.setEnabled(true);
                Z_button.setEnabled(true);
                X_button.getBackground().setColorFilter(0xFFFF00FF, PorterDuff.Mode.MULTIPLY);
                Y_button.getBackground().setColorFilter(0xFFFF00FF, PorterDuff.Mode.MULTIPLY);
                Z_button.getBackground().setColorFilter(0xFFFF00FF, PorterDuff.Mode.MULTIPLY);
                X_button.setVisibility(View.VISIBLE);
                Y_button.setVisibility(View.VISIBLE);
                Z_button.setVisibility(View.VISIBLE);
                isColor = false;
                for (int i = 0; i < 16; i++) {
                    colors[i].setEnabled(false);
                    colors[i].setVisibility(View.INVISIBLE);
                }
            } else {
                X_button.setEnabled(false);
                Y_button.setEnabled(false);
                Z_button.setEnabled(false);

                isX = false;
                isY = false;
                isZ = false;

                X_button.setAlpha(0.5f);
                Y_button.setAlpha(0.5f);
                Z_button.setAlpha(0.5f);

                X_button.setVisibility(View.INVISIBLE);
                Y_button.setVisibility(View.INVISIBLE);
                Z_button.setVisibility(View.INVISIBLE);
            }
        }

        if (v == Rotate_button) {
            isScaling = false;
            isMoving = false;
            isRotating = !isRotating;

            isX = false;
            isY = false;
            isZ = false;

            X_button.setAlpha(0.5f);
            Y_button.setAlpha(0.5f);
            Z_button.setAlpha(0.5f);
            if (isRotating == true) {
                X_button.setEnabled(true);
                Y_button.setEnabled(true);
                Z_button.setEnabled(true);
                X_button.getBackground().setColorFilter(0xFF0000FF, PorterDuff.Mode.MULTIPLY);
                Y_button.getBackground().setColorFilter(0xFF0000FF, PorterDuff.Mode.MULTIPLY);
                Z_button.getBackground().setColorFilter(0xFF0000FF, PorterDuff.Mode.MULTIPLY);
                X_button.setVisibility(View.VISIBLE);
                Y_button.setVisibility(View.VISIBLE);
                Z_button.setVisibility(View.VISIBLE);
                isColor = false;
                for (int i = 0; i < 16; i++) {
                    colors[i].setEnabled(false);
                    colors[i].setVisibility(View.INVISIBLE);
                }
            } else {
                X_button.setEnabled(false);
                Y_button.setEnabled(false);
                Z_button.setEnabled(false);

                isX = false;
                isY = false;
                isZ = false;

                X_button.setAlpha(0.5f);
                Y_button.setAlpha(0.5f);
                Z_button.setAlpha(0.5f);

                X_button.setVisibility(View.INVISIBLE);
                Y_button.setVisibility(View.INVISIBLE);
                Z_button.setVisibility(View.INVISIBLE);
            }

        }

        if (v == X_button) {
            isX = !isX;
            isY = false;
            isZ = false;
            Z_button.setAlpha(0.5f);
            Y_button.setAlpha(0.5f);
            if (isX == true) {
                X_button.setAlpha(1.0f);
            } else {
                X_button.setAlpha(0.5f);
            }

        }

        if (v == Y_button) {
            isY = !isY;
            isZ = false;
            isX = false;
            Z_button.setAlpha(0.5f);
            X_button.setAlpha(0.5f);
            if (isY == true) {
                Y_button.setAlpha(1.0f);

            } else {
                Y_button.setAlpha(0.5f);
            }
        }

        if (v == Z_button) {
            isZ = !isZ;
            isX = false;
            isY = false;
            X_button.setAlpha(0.5f);
            Y_button.setAlpha(0.5f);
            if (isZ == true) {
                Z_button.setAlpha(1.0f);
            } else {
                Z_button.setAlpha(0.5f);
            }
        }
        if (v == tabLeft_button) {
            if (MyGLRenderer.choice > 0) {
                MyGLRenderer.choice--;
                mGLSurfaceView.requestRender();
            }
        }
        if (v == tabRight_button) {
            if (MyGLRenderer.choice < MyGLRenderer.mCube.size() - 1) {
                MyGLRenderer.choice++;
                mGLSurfaceView.requestRender();
            }
        }
        if (v == Delete_button) {
            MyGLRenderer.deleteCube();
            if (MyGLRenderer.mCube.size() == 0) {
                Delete_button.setEnabled(false);
                Delete_button.setVisibility(View.INVISIBLE);

                Move_button.setEnabled(false);
                tabRight_button.setEnabled(false);
                tabLeft_button.setEnabled(false);
                Color_button.setEnabled(false);
                Scale_button.setEnabled(false);
                Rotate_button.setEnabled(false);

                Move_button.setVisibility(View.INVISIBLE);
                tabRight_button.setVisibility(View.INVISIBLE);
                tabLeft_button.setVisibility(View.INVISIBLE);
                Color_button.setVisibility(View.INVISIBLE);
                Scale_button.setVisibility(View.INVISIBLE);
                Rotate_button.setVisibility(View.INVISIBLE);

                X_button.setEnabled(false);
                Y_button.setEnabled(false);
                Z_button.setEnabled(false);

                X_button.setVisibility(View.INVISIBLE);
                Y_button.setVisibility(View.INVISIBLE);
                Z_button.setVisibility(View.INVISIBLE);
            }
            mGLSurfaceView.requestRender();
        }

        if (v == Color_button) {
            isColor = !isColor;
            for (int i = 0; i < 16; i++) {
                if (isColor) {
                    colors[i].setEnabled(true);
                    colors[i].setVisibility(View.VISIBLE);
                } else {
                    colors[i].setEnabled(false);
                    colors[i].setVisibility(View.INVISIBLE);
                }
            }
            isMoving = false;
            X_button.setEnabled(false);
            Y_button.setEnabled(false);
            Z_button.setEnabled(false);

            isX = false;
            isY = false;
            isZ = false;

            X_button.setAlpha(0.5f);
            Y_button.setAlpha(0.5f);
            Z_button.setAlpha(0.5f);

            X_button.setVisibility(View.INVISIBLE);
            Y_button.setVisibility(View.INVISIBLE);
            Z_button.setVisibility(View.INVISIBLE);
        }


        for (int i = 0; i < 16; i++)
            if (v == colors[i]) {
                getColors(i);
            }
    }

    public void getColors(int i)
    {
        int Red = values[i], Green = values[i], Blue = values[i];
        Red = Red << 8;
        Red = Red >> 24;
        Green = Green << 16;
        Green = Green >> 24;
        Blue = Blue << 24;
        Blue = Blue >> 24;
        Red = Red & 0x000000FF;
        Green = Green & 0x000000FF;
        Blue = Blue & 0x000000FF;
        MyGLRenderer.mCube.get(MyGLRenderer.choice).red = (float)Red/255;
        MyGLRenderer.mCube.get(MyGLRenderer.choice).green = (float)Green/255;
        MyGLRenderer.mCube.get(MyGLRenderer.choice).blue = (float)Blue/255;
        //MyGLRenderer.mCube.get(MyGLRenderer.choice).changeColor((float)Red/255, (float)Green/255, (float)Blue/255);
        mGLSurfaceView.requestRender();
    }

    @Override
    protected void onResume()
    {
        // The activity must call the GL surface view's onResume() on activity onResume().
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause()
    {
        // The activity must call the GL surface view's onPause() on activity onPause().
        super.onPause();
        mGLSurfaceView.onPause();
    }
}