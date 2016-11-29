package com.example.administrator.ui;

/**
 * Created by Administrator on 2016/11/28.
 */
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.DragFrameLayout;
import android.content.Context;
import android.graphics.Rect;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DragFrameLayout extends FrameLayout {

    private View view;

    private int width, heigh;

    private int screenWid, screenHei;

    private boolean isClickDrag = false;

    private boolean isTouchDrag = false;

    private float startX, startY;

    private CheckClick checkClick = new CheckClick();

    private DragImageClickListener dragImageListener;

    public DragImageClickListener getDragImageListener() {
        return dragImageListener;
    }

    public void setDragImageListener(DragImageClickListener dragImageListener) {
        this.dragImageListener = dragImageListener;
    }

    public interface DragImageClickListener {
        public abstract void onClick();
    }

    private class CheckClick implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            isClickDrag = false;
            Log.i("drag", "=====checkTap====");
        }

    }

    public DragFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public DragFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public void dragInit(View view) {
        screenWid = getWidth();
        screenHei = getHeight();
        width = view.getWidth();
        heigh = view.getHeight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = ev.getX();
                float y = ev.getY();
                Rect frame = new Rect();
                if (view == null) {
                    view = findViewById(R.id.dragImg);
                    dragInit(view);
                }
                view.getHitRect(frame);
                if (frame.contains((int) (x), (int) (y))) {

                    isTouchDrag = true;
                    startX = x;
                    startY = y;
                    return true;
                }
                break;

        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {

        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        float x = event.getX();
        float y = event.getY();
        Rect frame = new Rect();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                view.getHitRect(frame);
                if (frame.contains((int) (x), (int) (y))) {
                    startX = x;
                    startY = y;
                    isTouchDrag = true;
                    isClickDrag = true;
                    postDelayed(checkClick, ViewConfiguration.getTapTimeout());
                }
                break;
            case MotionEvent.ACTION_MOVE:

                float distanX = Math.abs(x - startX);
                float distanY = Math.abs(y - startY);

                if (Math.sqrt(distanY * distanY + distanX * distanX) > 10) {
                    isClickDrag = false;
                }
                move(x, y);
                break;

            case MotionEvent.ACTION_CANCEL:
                isClickDrag = false;
                isTouchDrag = false;
                break;
            case MotionEvent.ACTION_UP:
                if (isClickDrag == true) {
//                    dragImageListener.onClick();
//                    removeCallbacks(checkClick);
                    Toast.makeText(getContext(),"xianshi",Toast.LENGTH_SHORT).show();
                }
                isClickDrag = false;
                isTouchDrag = false;

                // 这段是把控件吸附四周
//          if (x > width && x < screenWid - width && y > heigh
//                  && y < screenHei -  heigh) {
//              int minType = minDIstance(x, y);
//              Log.i("tags", screenHei + "==mintype=" + minType);
//              switch (minType) {
//              case LEFT:
//                  x = width;
//                  break;
//              case RIGHT:
//                  x = screenWid - width;
//                  break;
//              case TOP:
//                  y = heigh;
//                  break;
//              case BOTTOM:
//                  y = screenHei - heigh;
//                  break;
//              default:
//                  break;
//              }
//              move(x, y);
//          }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                isClickDrag = false;
                isTouchDrag = false;
                break;
        }
        return true;
    }

    private final static int LEFT = 1;
    private final static int RIGHT = 2;
    private final static int TOP = 3;
    private final static int BOTTOM = 4;

    private int minDIstance(float x, float y) {
        Log.i("tags", "x=" + x + "==y=" + y);
        boolean left, top;

        if (x <= (screenWid - x)) {
            left = true;
        } else {
            left = false;
        }
        if (y <= (screenHei - y)) {
            top = true;
        } else {
            top = false;
        }

        if(left&&top){
            if(x<=y){
                return LEFT;
            }else{
                return TOP;
            }
        }
        if(left&&(!top)){
            if(x<=(screenHei-y)){
                return LEFT;
            }else{
                return BOTTOM;
            }
        }

        if((!left)&top){
            if((screenWid-x)<= y){
                return RIGHT;
            }else{
                return TOP;
            }
        }

        if((!left)&(!top)){
            if((screenWid-x)<= (screenHei-y)){
                return RIGHT;
            }else{
                return BOTTOM;
            }
        }
        return 0;

    }

    private void move(float x, float y) {
        int left = (int) (x - width / 2);
        int top = (int) (y - heigh / 2);
        if (left <= 0)
            left = 0;
        if (top <= 0)
            top = 0;

        if (left > screenWid - width)
            left = screenWid - width;
        if (top > screenHei - heigh)
            top = screenHei - heigh;

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
                .getLayoutParams();

        params.setMargins(left, top, (screenWid - left - width), (screenHei
                - top - heigh));

        view.setLayoutParams(params);
        requestLayout();
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        float[] results = new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0];
    }

}
