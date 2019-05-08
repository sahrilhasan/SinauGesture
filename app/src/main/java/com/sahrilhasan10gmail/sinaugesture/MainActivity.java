package com.sahrilhasan10gmail.sinaugesture;

import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

        private TextView output_text;
   private TextView gestureText;
    private GestureDetectorCompat gDetector;
   private RelativeLayout currentLayout;
    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureText = (TextView)findViewById(R.id.gestureStatusText);
        output_text = (TextView) findViewById(R.id.gestureStatusText);
        currentLayout = (RelativeLayout) findViewById(R.id.activity_main);
        currentLayout.setBackgroundColor(Color.WHITE);
        gDetector =new GestureDetectorCompat(this,this);
        gDetector.setOnDoubleTapListener(this);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureText.setText ("onDown");
        currentLayout.setBackgroundColor(Color.RED);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gestureText.setText("onDoubleTap");
        currentLayout.setBackgroundColor(Color.BLUE);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gestureText.setText("onDoubleTapEvent");
        currentLayout.setBackgroundColor(Color.MAGENTA);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gestureText.setText ("onDown");
        currentLayout.setBackgroundColor(Color.YELLOW);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gestureText.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gestureText.setText("onSingleTapUp");
        currentLayout.setBackgroundColor(Color.LTGRAY);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText("onScroll");
        currentLayout.setBackgroundColor(Color.GREEN);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureText.setText("onFling");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //gestureText.setText("onFling");
        //return true;
        boolean result = false;
        float diffY = e2.getY() - e1.getY();
        float diffX = e2.getX() - e1.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    output_text.setText("onSwipRight");
                } else {
                    output_text.setText("onSwipeLeft");
                }
                result = true;
            }
        } else {
            if (Math.abs(diffY) > SWIPE_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    output_text.setText("onSwipeBottom");
                } else {
                    output_text.setText("onSwipeTop");
                }
                result = true;
            }
        }
        return result;
    }
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
