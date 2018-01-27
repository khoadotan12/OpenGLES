package com.example.cong.doan_2425;


import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.SparseArray;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen.
 * This view can also be used to capture touch events, such as a user
 * interacting with drawn objects.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    private SparseArray<PointF> mActivePointers = new SparseArray<PointF>();

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private int count = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        count = event.getPointerCount();
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int maskedAction = event.getActionMasked();
        if (count == 1) {
            if (MainActivity.isX == true)
            {
                if (MyGLRenderer.mCube.size() > 0)
                    switch (maskedAction) {
                        case MotionEvent.ACTION_DOWN:
                            PointF f = new PointF();
                            f.x = event.getX(pointerIndex);
                            f.y = event.getY(pointerIndex);
                            mActivePointers.put(pointerId, f);

                            break;
                        case MotionEvent.ACTION_MOVE:

                            for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                                PointF point = mActivePointers.get(i);
                                if (point != null) {
                                    if (point.x - event.getX(i) < 0) {
                                        if (MainActivity.isMoving)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).MoveX(0.1f);
                                        if (MainActivity.isScaling)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).changeX(1.05f);
                                        if (MainActivity.isRotating)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).xAngle-=3;
                                    }
                                    if (point.x - event.getX(i) > 0){
                                        if (MainActivity.isMoving)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).MoveX(-0.1f);
                                        if (MainActivity.isScaling)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).changeX(0.95f);
                                        if (MainActivity.isRotating)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).xAngle+=3;
                                    }
                                    point.x = event.getX(i);
                                    point.y = event.getY(i);
                                    requestRender();
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            mActivePointers.remove(pointerId);
                            break;
                        }
                    }
            }
            else if (MainActivity.isY ==  true)
            {
                if (MyGLRenderer.mCube.size() > 0)
                    switch (maskedAction) {
                        case MotionEvent.ACTION_DOWN:
                            PointF f = new PointF();
                            f.x = event.getX(pointerIndex);
                            f.y = event.getY(pointerIndex);
                            mActivePointers.put(pointerId, f);

                            break;
                        case MotionEvent.ACTION_MOVE:

                            for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                                PointF point = mActivePointers.get(i);
                                if (point != null) {
                                    if (point.x - event.getX(i) < 0) {
                                        if (MainActivity.isMoving)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).MoveY(0.1f);
                                        if (MainActivity.isScaling)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).changeY(1.05f);
                                        if (MainActivity.isRotating)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).yAngle-=3;
                                    }
                                    if (point.x - event.getX(i) > 0) {
                                        if (MainActivity.isMoving)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).MoveY(-0.1f);
                                        if (MainActivity.isScaling)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).changeY(0.95f);
                                        if (MainActivity.isRotating)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).yAngle+=3;
                                    }
                                    point.x = event.getX(i);
                                    point.y = event.getY(i);
                                    requestRender();
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            mActivePointers.remove(pointerId);
                            break;
                        }
                    }
            }
            else if (MainActivity.isZ == true)
            {
                if (MyGLRenderer.mCube.size() > 0)
                    switch (maskedAction) {
                        case MotionEvent.ACTION_DOWN:
                            PointF f = new PointF();
                            f.x = event.getX(pointerIndex);
                            f.y = event.getY(pointerIndex);
                            mActivePointers.put(pointerId, f);

                            break;
                        case MotionEvent.ACTION_MOVE:

                            for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                                PointF point = mActivePointers.get(i);
                                if (point != null) {
                                    if (point.x - event.getX(i) < 0) {
                                        if (MainActivity.isMoving)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).MoveZ(0.1f);
                                        if (MainActivity.isScaling)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).changeZ(1.05f);
                                        if (MainActivity.isRotating)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).zAngle-=3;
                                    }
                                    if (point.x - event.getX(i) > 0) {
                                        if (MainActivity.isMoving)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).MoveZ(-0.1f);
                                        if (MainActivity.isScaling)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).changeZ(0.95f);
                                        if (MainActivity.isRotating)
                                            MyGLRenderer.mCube.get(MyGLRenderer.choice).zAngle+=3;
                                    }
                                    point.x = event.getX(i);
                                    point.y = event.getY(i);
                                    requestRender();
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            mActivePointers.remove(pointerId);
                            break;
                        }
                    }
            }
            else
            {
                switch (maskedAction) {
                    case MotionEvent.ACTION_DOWN:
                        PointF f = new PointF();
                        f.x = event.getX(pointerIndex);
                        f.y = event.getY(pointerIndex);
                        mActivePointers.put(pointerId, f);

                        break;
                    case MotionEvent.ACTION_MOVE:

                        for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                            PointF point = mActivePointers.get(i);
                            if (point != null) {
                                if (point.x - event.getX(i) > 0)
                                    mRenderer.mXAngle -= 3;
                                if (point.x - event.getX(i) < 0)
                                    mRenderer.mXAngle += 3;
                                if (point.y - event.getY() > 0)
                                    mRenderer.mYAngle += 4;
                                if (point.y - event.getY() < 0)
                                    mRenderer.mYAngle -= 4;
                                point.x = event.getX(i);
                                point.y = event.getY(i);
                                requestRender();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        mActivePointers.remove(pointerId);
                        break;
                    }
                }
            }
        }
        else
        {
            switch (maskedAction) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    PointF f = new PointF();
                    f.x = event.getX(pointerIndex);
                    f.y = event.getY(pointerIndex);
                    mActivePointers.put(pointerId, f);
                    break;
                case MotionEvent.ACTION_MOVE:
                    PointF point1 = mActivePointers.get(event.getPointerId(0));
                    PointF point2 = mActivePointers.get(event.getPointerId(1));
                    if (point1 != null && point2 != null) {
                        double lastDistance = Math.sqrt((double) (point1.x - point2.x) * (point1.x - point2.x) + (double) (double) (point1.y - point2.y) * (point1.y - point2.y));
                        point1.x = event.getX(0);
                        point1.y = event.getX(0);
                        point2.x = event.getX(1);
                        point2.y = event.getX(1);
                        double currentDistance = Math.sqrt((double) (point1.x - point2.x) * (point1.x - point2.x) + (double) (double) (point1.y - point2.y) * (point1.y - point2.y));
                        if (currentDistance - lastDistance < 0)
                        {
                            if (mRenderer.scaleValue > 0.3)
                                mRenderer.scaleValue -= 0.05;
                        }
                        else
                        if (currentDistance - lastDistance > 0)
                        {
                            if (mRenderer.scaleValue < 1.8)
                                mRenderer.scaleValue += 0.05;
                        }
                        requestRender();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: {
                    mActivePointers.remove(pointerId);
                    break;
                }
            }
        }
        return true;
    }


}
