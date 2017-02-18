package xyz.xechoz.demo.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import xyz.xechoz.demo.util.Unit;

/**
 * Created by xechoz.zheng on 2/17/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class CounterView extends View implements ICustomView {
    private int count = 0;
    private Paint paint;
    private Paint backgroundPaint;
    private Rect textBounds;
    private Rect roundRect;
    private float rx, ry;
    private Paint roundPaint;
    private float ringWidth = 8;
    private RectF arcRect;
    private float progress;
    private Paint arcPaint;

    public CounterView(Context context) {
        this(context, null);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        roundRect.left = 0;
        roundRect.top = 0;
        roundRect.right = width;
        roundRect.bottom = height;

        arcRect.set(roundRect);
        setMeasuredDimension(width, height);
    }

    @Override
    public void init(@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setTextSize(Unit.dp2px(48));

        textBounds = new Rect();

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLUE);
        backgroundPaint.setStyle(Paint.Style.FILL);

        roundRect = new Rect();
        roundPaint = new Paint();
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.STROKE);
        roundPaint.setStrokeWidth(Unit.dp2px(ringWidth));

        arcPaint = new Paint();
        arcPaint.setColor(Color.GREEN);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setStrokeWidth(Unit.dp2px(8));
        arcPaint.setStyle(Paint.Style.STROKE);

        arcRect = new RectF();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                beginRing();
                return true;
            }
        });
    }

    private void beginRing() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = animation.getAnimatedFraction();

                invalidate();
            }
        });

        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
//        String text = String.valueOf(count);
//        paint.getTextBounds(text, 0, text.length(), textBounds);
//
//        float textWidth = textBounds.width();
//        float textHeight = textBounds.height();
//
//        canvas.save();
//        canvas.rotate(90, getWidth()/2, getHeight()/2);
//        canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, paint);
//        canvas.restore();
//
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(36);
//        canvas.save();
//        canvas.translate(getWidth()/2 - textWidth, getHeight()/2 - textHeight);
//        canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, paint);
//        canvas.restore();
//
//        int radius = Math.min(roundRect.width()/2, roundRect.height()/2) - Unit.dp2px(ringWidth)/2;
//        canvas.drawCircle(roundRect.width()/2, roundRect.height()/2, radius, roundPaint);
//
//        canvas.save();
//        canvas.drawArc(arcRect, 0, 360*progress, false, arcPaint);
//        canvas.restore();
    }
}
