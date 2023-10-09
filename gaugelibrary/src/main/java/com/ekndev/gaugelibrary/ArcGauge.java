
package com.ekndev.gaugelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;

import java.util.List;

public class ArcGauge extends FullGauge {

    private float sweepAngle = 240;
    private float startAngle = 150;
    private float currentAngle = 0;
    private Integer angleNext;
    private float gaugeBGWidth = 40f;

    private final Handler handler = new Handler(this.getContext().getMainLooper());

    private boolean enableAnimation = true;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public ArcGauge(Context context) {
        super(context);
        init();
    }

    public ArcGauge(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcGauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ArcGauge(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * Enable or disable animation for needle
     * true will enable animation
     * false will disable animation
     *
     * @param enableAnimation [boolean]
     */
    public void enableAnimation(boolean enableAnimation) {
        this.enableAnimation = enableAnimation;
    }

    /**
     * Check if animation enable or disable
     *
     * @return boolean value
     */
    public boolean isEnableAnimation() {
        return enableAnimation;
    }

    private void init() {
        getGaugeBackGround().setStrokeWidth(gaugeBGWidth);
        getGaugeBackGround().setStrokeCap(Paint.Cap.ROUND);
        getGaugeBackGround().setColor(Color.parseColor("#D6D6D6"));
        getTextPaint().setTextSize(35f);
        setPadding(40f);
        setSweepAngle(sweepAngle);
        setStartAngle(startAngle);
    }

    public int getAnimSweepAngle() {
        if (angleNext != null && enableAnimation) {
            if (angleNext != currentAngle) {
                if (angleNext < currentAngle)
                    currentAngle--;
                else
                    currentAngle++;
                handler.postDelayed(runnable, 5);
            }
        } else {
            currentAngle = super.calculateSweepAngle(getValue(), getMinValue(), getMaxValue());
        }
        return (int) currentAngle;
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
        angleNext = (int) calculateSweepAngle(getValue(), getMinValue(), getMaxValue());
    }

    @Override
    protected void drawValueArcOnCanvas(Canvas canvas, RectF rectF, float startAngle, float sweepAngle, double value, List<Range> ranges) {
        prepareCanvas(canvas);
        canvas.drawArc(rectF, startAngle, getAnimSweepAngle(), false, getRangePaintForValue(value, ranges));
        finishCanvas(canvas);
    }

    @Override
    protected void drawValuePoint(Canvas canvas) {
        if (super.displayValuePoint) {
            prepareCanvas(canvas);
            //draw Value point indicator
            float rotateValue = (float) (getAnimSweepAngle() + getMaxValue() - getMinValue() - 40);
            canvas.rotate(rotateValue, getRectRight() / 2f, getRectBottom() / 2f);
            canvas.drawCircle(400f / 2f, getPadding(), 8f, getRangePaintForValue(getValue(), getRanges()));
            canvas.drawLine(190f + 5f, 11f + 20f, 200f + 5f, 19f + 20f, getArrowPaint());
            canvas.drawLine(200f + 05f, 20f + 19f, 190f + 5f, 27f + 19f, getArrowPaint());
            finishCanvas(canvas);
        }
    }
}
