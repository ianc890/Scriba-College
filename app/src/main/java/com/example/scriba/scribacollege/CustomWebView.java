package com.example.scriba.scribacollege;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by Ian C on 29/09/2016.
 */

public class CustomWebView extends WebView {

    private Paint _paint = new Paint();
    private Path _path = new Path();

    public CustomWebView (Context context, AttributeSet attrs)
    {
        super (context, attrs);

        _paint.setAntiAlias(true);
        _paint.setStrokeWidth(6f); //setting stroke width, just like pain brush size
        _paint.setColor(Color.BLACK); //choosing the color as BLACK
        _paint.setStyle(Paint.Style.STROKE);
        _paint.setStrokeJoin(Paint.Join.ROUND); //setting the Stroke-join as ROUND
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw (canvas);

        canvas.drawColor(0x00AAAAAA);
        canvas.drawPath(_path, _paint);
    }



    @Override //overRiding the onTouchEvent() method
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX(); //getting the X and Y coordinates of the touch
        float Y = event.getY();

        if(event.getAction()== MotionEvent.ACTION_DOWN) // checks if the new touch is started
        {
            _path.moveTo(X,Y); //if yes then moves to that coordinate and return true
            return true;
        }
        else if(event.getAction()== MotionEvent.ACTION_MOVE ) //checks if the finger is moving on screen
        {
            _path.lineTo(X, Y); // if yes then draw line to that point
        }
        else if(event.getAction()== MotionEvent.ACTION_UP) // checks if the fingers is moved up from screen or touch is finished
        {
            //Have nothing to do

        }
        else
        {
            return false;
        }
        invalidate();
        return true;

    }
}
