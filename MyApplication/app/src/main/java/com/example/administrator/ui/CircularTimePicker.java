package com.example.administrator.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wcyhp on 2016/12/19.
 */

public class CircularTimePicker extends View {
    private Paint paint_arc_bg;
    private Paint paint_arc;
    private Paint paint_text;
    private RectF innerRectf;
    private Paint paint_grey;
    private RectF outterRectf;
    /** 基准标尺 */
    private float common_size;
    /** 内环左部 */
    private float innerLeft;
    private float innerTop;
    private float innerRight;
    private float innerBottom;
    /** 外环左部 */
    private float outterLeft;
    private float outterTop;
    private float outterRight;
    private float outterBottom;

    /** 字体大小 */
    private float textSize;

    /** 画字体时的X坐标 */
    private float textX;
    private float textY;

    /** 圆心坐标 */
    private float cX;
    private float cY;
    /**外圆半径*/
    private float circleRadius;
    /**内圆半径*/
    private float innerCircleRadius;

    /**整个总布局长宽度 */
    private int totalSize;
    /**控件类型，默认是时间器类型 */
    private ViewType viewType = ViewType.TimePiker;
    DashPathEffect effect = new DashPathEffect(new float[] { 4, 4}, 0);


    private int arcBgColor = Color.parseColor("#40f9c515");
    private int arcColor = Color.parseColor("#fff9c515");
    private int outerCircleColor = Color.parseColor("#d4d4d4");
    private Bitmap bitmap;

    /** 进度变更的监听对象 */
    private OnSeekChangeListener mListener;

    public OnSeekChangeListener getmListener() {
        return mListener;
    }

    public void setSeekChangeListener(OnSeekChangeListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 构造器
     * @param context
     * @param srcId 手柄图片资源ID
     * @param viewType 外观类型
     */
    public CircularTimePicker(Context context, int srcId, ViewType viewType) {
        super(context);
        this.viewType = viewType;
        bitmap = BitmapFactory.decodeResource(context.getResources(), srcId);
        init();
    }

    private void init(){
        innerRectf = new RectF();
        outterRectf = new RectF();

        paint_arc_bg = new Paint();
        paint_arc_bg.setColor(arcBgColor);
        paint_arc_bg.setAntiAlias(true);
        paint_arc_bg.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint_arc_bg.setStyle(Paint.Style.STROKE);

        paint_arc = new Paint();
        paint_arc.setColor(arcColor);
        paint_arc.setAntiAlias(true);
        paint_arc.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint_arc.setStyle(Paint.Style.STROKE);


        paint_arc_bg.setPathEffect(effect);
        paint_arc.setPathEffect(effect);

        paint_grey = new Paint();
        paint_grey.setColor(outerCircleColor);
        paint_grey.setAntiAlias(true);
        paint_grey.setStyle(Paint.Style.STROKE);

        paint_text = new Paint();
        paint_text.setAntiAlias(true);
        paint_text.setTextAlign(Paint.Align.CENTER);
        paint_text.setStyle(Paint.Style.STROKE);
        paint_text.setColor(arcColor);
    }

    /**手柄中心的X坐标 */
    private double curBitmapX;
    /**手柄中心的Y坐标 */
    private double curBitmapY;
    /**手柄左上角的X轴坐标 */
    private double dx;
    /**手柄左上角的Y轴坐标 */
    private double dy;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //int width = getWidth();
        //int height = getHeight();
        int size = (width > height) ? height : width;
        /** 整个总布局宽度 */
        totalSize = size;
        common_size = totalSize/18f;
        /** 内环左部 */
        innerLeft = common_size * 3f;
        innerTop = common_size * 3f;
        innerRight = common_size * 15f;
        innerBottom = common_size * 15f;

        arcStrokeWidth = common_size * 1f;
        paint_arc.setStrokeWidth(arcStrokeWidth);

        switch (viewType) {
            case Clock:
                /** 字体大小 */
                textSize = common_size * 1.2f;
                innerCircleRadius = (innerBottom - innerTop) / 2f;
                /**外环左部 */
                outterLeft = innerLeft-bitmap.getWidth()/2-arcStrokeWidth/2-10;
                outterTop = innerTop-bitmap.getHeight()/2-arcStrokeWidth/2-10;
                outterRight = innerRight+bitmap.getWidth()+arcStrokeWidth/2/2+10;
                outterBottom = innerBottom+bitmap.getHeight()/2+arcStrokeWidth/2+10;

                /** 画字体时的X坐标 */
                textX = (innerRight - innerLeft) / 2f + innerLeft;
                textY = (innerBottom - innerTop) / 2f + innerTop - textSize;

                break;
            case TimePiker:
                /** 字体大小 */
                textSize = common_size * 2f;
                paint_arc_bg.setStrokeWidth(arcStrokeWidth);
                /** 外环左部 */
                outterLeft = innerLeft-bitmap.getWidth()/2-arcStrokeWidth/2-5;
                outterTop = innerTop-bitmap.getHeight()/2-arcStrokeWidth/2-5;
                outterRight = innerRight+bitmap.getWidth()/2+arcStrokeWidth/2+5;
                outterBottom = innerBottom+bitmap.getHeight()/2+arcStrokeWidth/2+5;

                /** 画字体时的X坐标 */
                textX = (innerRight - innerLeft) / 2f + innerLeft;
                textY = (innerBottom - innerTop) / 2f + innerTop + textSize
                        / 2f;
                break;
        }

        innerRectf.set(innerLeft, innerTop, innerRight, innerBottom);
        outterRectf.set(outterLeft, outterTop, outterRight, outterBottom);

        paint_grey.setStrokeWidth(3.0f);
        paint_text.setTextSize(textSize);
        paint_text.setStrokeWidth(4);

        /** 圆心坐标 */
        cX = (innerRight - innerLeft) / 2f + innerLeft;
        cY = (innerBottom - innerTop) / 2f + innerTop;
        circleRadius = (outterBottom - outterTop) / 2f;

        curBitmapX = cX; // 12点方向
        curBitmapY = cY - circleRadius;//  12点方向
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDial(canvas);
        String text = getTextToDraw();
        canvas.drawText(text, textX, textY, paint_text);
        // 画外环
        canvas.drawCircle(cX, cY, circleRadius, paint_grey);
        dx = getXFromAngle();
        dy = getYFromAngle();
        drawMarkerAtProgress(canvas);
    }

    private String getTextToDraw() {
        String text = "";
        switch (viewType) {
            case Clock:
                int minutes = (int) (currentAngle*2);
                int hours = minutes/60;
                int minute = minutes%60;
                text = hours+"小时"+minute+"分钟";
                break;
            case TimePiker:
                int Tminutes = this.progress;
                int Thours = Tminutes/60;
                int Tminute = Tminutes%60;
                if(Tminute <10){
                    text = "0"+ Thours +":" + "0" + Tminute;
                }else {
                    text = "0"+ Thours +":" + Tminute;
                }

//                if(this.progress<10){
//                    text = "00:0"+this.progress;
//                }else if(this.progress<60){
//                    text = "00:"+this.progress;
//                }else {
//                    text = "01:0"+(this.progress-60);
//                }
                break;
        }
        return text;
    }

    private void drawMarkerAtProgress(Canvas canvas) {
        canvas.drawBitmap(bitmap, (float)dx, (float)dy, null);
    }

    /**
     * 画短线、表盘刻度
     *
     * @param canvas
     */
    public void drawDial(Canvas canvas) {
        switch (viewType) {
            case Clock:
                DashPathEffect  effect2 = new DashPathEffect(new float[] { 3, (float) (Math.PI*innerCircleRadius/6.0-3)}, 3);
                paint_arc.setPathEffect(effect2);
                canvas.drawCircle(cX, cY, 8, paint_text);
                canvas.drawCircle(cX, cY, innerCircleRadius+arcStrokeWidth/2, paint_text);
                canvas.drawCircle(cX, cY, innerCircleRadius, paint_arc);
                canvas.drawText("3", cX+innerCircleRadius-arcStrokeWidth/2-textSize/2, cY+textSize/3, paint_text);
                canvas.drawText("6", cX, cY+innerCircleRadius-arcStrokeWidth/2-textSize/3, paint_text);
                canvas.drawText("9", cX-innerCircleRadius+arcStrokeWidth/2+textSize/2, cY+textSize/2, paint_text);
                canvas.drawText("12", cX, cY-innerCircleRadius+arcStrokeWidth/2+textSize, paint_text);
                //画表针
                float x1 = (float) (cX + (innerCircleRadius - arcStrokeWidth * 2)* Math.sin(currentAngle * (Math.PI / 180)));
                float y1 = (float) (cY - (innerCircleRadius - arcStrokeWidth * 2)* Math.cos(currentAngle * (Math.PI / 180)));
                canvas.drawLine(cX, cY, x1, y1, paint_text);
                break;

            case TimePiker:
                paint_arc.setPathEffect(effect);
                canvas.drawArc(innerRectf, 270f+currentAngle, 360f-currentAngle, false, paint_arc_bg);
                canvas.drawArc(innerRectf, 270f, currentAngle, false, paint_arc);
                break;
        }
    }

    /**当前指针的角度（0----360）*/
    private float currentAngle = 0;
    /** 此标志用于表示setAngle方法被调用还是setProgress被调用了 */
    private boolean CALLED_FROM_ANGLE = false;
    private int maxProgress =60 ;
    private int progress;
    private int progressPercent;
    private float arcStrokeWidth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        boolean up = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                moveArm(x, y, up);
                break;
            case MotionEvent.ACTION_UP:
                up = true;
                mListener.onProgressChange(this, this.getProgress());
                break;
        }
        return true;
    }

    private void moveArm(float x, float y, boolean up) {
        curBitmapX = cX + circleRadius* Math.cos(Math.atan2(x - cX, cY - y) - (Math.PI / 2d));
        curBitmapY = cY + circleRadius* Math.sin(Math.atan2(x - cX, cY - y) - (Math.PI / 2d));
        float degrees = (float) ((float) ((Math.toDegrees(Math.atan2(
                x - cX, cY - y)) + 360.0)) % 360.0);
        if (degrees < 0) {
            degrees += 2 * Math.PI;
        }
        invalidate();
        setAngle(degrees);
    }

    private void setAngle(float angle) {
        currentAngle = angle;
        float donePercent =  this.currentAngle / 360 * 100;
        float progress = this.currentAngle / 360 * getMaxProgress();
        setProgressPercent(Math.round(donePercent));
        CALLED_FROM_ANGLE = true;
        setProgress(Math.round(progress));

    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (this.progress != progress) {
            this.progress = progress;
            if (!CALLED_FROM_ANGLE) {
                int newPercent = (this.progress * 100) / this.maxProgress;
                int newAngle = (this.progress * 360) / this.maxProgress;
                this.setAngle(newAngle);
                this.setProgressPercent(newPercent);
            }
            CALLED_FROM_ANGLE = false;
        }
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    /**
     * 获取手柄左上角的X轴坐标
     * @return
     */
    public double getXFromAngle() {
        int size = bitmap.getWidth();
        double x = curBitmapX - (size / 2d);
        return x;
    }

    /**
     * 获取手柄左一角的Y轴坐标
     * @return
     */
    public double getYFromAngle() {
        int size = bitmap.getHeight();
        double y = curBitmapY - (size / 2d);
        return y;
    }

    /**
     * 进度改变的监听类，用于接收当前的进度
     * @author wjk
     *
     */
    public interface OnSeekChangeListener {
        public void onProgressChange(CircularTimePicker view, int newProgress);
    }

    public static enum ViewType {
        Clock,
        TimePiker
    };
}
