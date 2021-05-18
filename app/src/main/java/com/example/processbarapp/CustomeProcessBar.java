package com.example.processbarapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create by DXH on 2021/05/18
 */
public class CustomeProcessBar extends View {
    private float mProcessValue;//进度条当前值
    private float mProcessMaxValue;//进度条最大值

    private int mProcessColor;//进度条颜色
    private int mProcessRadiusSize;//进度条圆角半径

    private int mProcessBgColor;//进度条背景颜色
    private int mProcessBgRadiusSize;//进度条背景圆角半径

    public CustomeProcessBar(Context context) {
        this(context, null);
    }

    public CustomeProcessBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomeProcessBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(attrs);//初始化配置
        init();
    }

    private Paint processPaint;
    private Paint processBgPaint;
    private RectF processRectF;
    private RectF processBgRectF;

    private void init() {
        processPaint = new Paint();
        processBgPaint = new Paint();

        processPaint.setAntiAlias(true);
        processPaint.setColor(mProcessColor);
        processPaint.setStyle(Paint.Style.FILL);

        processBgPaint.setAntiAlias(true);
        processBgPaint.setColor(mProcessBgColor);
        processBgPaint.setStyle(Paint.Style.FILL);

        processRectF = new RectF();
        processBgRectF = new RectF();

    }


    private void initAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomeProcessBar);
        mProcessValue = typedArray.getFloat(R.styleable.CustomeProcessBar_processValue, 0);
        mProcessMaxValue = typedArray.getFloat(R.styleable.CustomeProcessBar_processMaxValue, 100);
        mProcessColor = typedArray.getColor(R.styleable.CustomeProcessBar_processColor, Color.parseColor("#ff0000"));
        mProcessRadiusSize = typedArray.getDimensionPixelSize(R.styleable.CustomeProcessBar_processRadiusSize, 0);
        mProcessBgColor = typedArray.getColor(R.styleable.CustomeProcessBar_processBgColor, Color.parseColor("#ffff00"));
        mProcessBgRadiusSize = typedArray.getDimensionPixelSize(R.styleable.CustomeProcessBar_processBgRadiusSize, 0);
        typedArray.recycle();
    }

    private int height;
    private int width;
    private float processRatio;//进度比

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        processRatio = mProcessValue / mProcessMaxValue;

        processPaint.setColor(mProcessColor);
        processBgPaint.setColor(mProcessBgColor);
        setProcessBgRectF(width, height);
        setProcessRectF(width * processRatio, height);
        canvas.save();
        canvas.drawRoundRect(processBgRectF, mProcessBgRadiusSize, mProcessBgRadiusSize, processBgPaint);
        canvas.drawRoundRect(processRectF, mProcessRadiusSize, mProcessRadiusSize, processPaint);
        canvas.restore();
    }

    private void setProcessRectF(float width, float height) {
        processRectF.left = 0;
        processRectF.right = width;
        processRectF.top = 0;
        processRectF.bottom = height;
    }

    private void setProcessBgRectF(float width, float height) {
        processBgRectF.left = 0;
        processBgRectF.right = width;
        processBgRectF.top = 0;
        processBgRectF.bottom = height;
    }

    public void setmProcessValue(float mProcessValue) {
        this.mProcessValue = mProcessValue;
        invalidate();
    }

    public void setmProcessMaxValue(float mProcessMaxValue) {
        this.mProcessMaxValue = mProcessMaxValue;
        invalidate();
    }

    public void setmProcessColor(int mProcessColor) {
        this.mProcessColor = mProcessColor;
        invalidate();
    }

    public void setmProcessRadiusSize(int mProcessRadiusSize) {
        this.mProcessRadiusSize = mProcessRadiusSize;
        invalidate();
    }

    public void setmProcessBgColor(int mProcessBgColor) {
        this.mProcessBgColor = mProcessBgColor;
        invalidate();
    }

    public void setmProcessBgRadiusSize(int mProcessBgRadiusSize) {
        this.mProcessBgRadiusSize = mProcessBgRadiusSize;
        invalidate();
    }
}
