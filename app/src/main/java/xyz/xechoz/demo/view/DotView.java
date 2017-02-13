package xyz.xechoz.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import xyz.xechoz.demo.R;
import xyz.xechoz.demo.util.Unit;

/**
 * Created by xechoz.zheng on 2/12/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 小红点提示
 * 文档:
 */

public class DotView extends FrameLayout implements ICustomView {
    private static final int GRAVITY_BASE = 0x0001;
    private static final int GRAVITY_LEFT = GRAVITY_BASE;
    private static final int GRAVITY_TOP = GRAVITY_BASE<<1;
    private static final int GRAVITY_RIGHT = GRAVITY_BASE<<2;
    private static final int GRAVITY_BOTTOM = GRAVITY_BASE<<3;

    private Paint dotPaint;
    private String dotText;
    private int dotTextColor;
    private int dotWidth;
    private int dotHeight;
    private Rect dotRect;
    private Paint dotTextPaint;
    private Paint.FontMetrics fontMetrics;
    private DotMargin dotMargin;
    private RectF dotRectF;
    private int dotGravity;

    public DotView(Context context) {
        this(context, null);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs, defStyleAttr, 0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(dotRectF,
                (dotRect.right - dotRect.left)/2f, (dotRect.bottom - dotRect.top)/2f, dotPaint);

        canvas.drawText(dotText,
                (dotRect.left + dotRect.right)/2f ,
                (dotRect.top + dotRect.bottom)/2 + fontMetrics.bottom,
                dotTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth() + Math.abs(dotMargin.left + dotMargin.right);
        int height =  getMeasuredHeight() + Math.abs(dotMargin.top + dotMargin.bottom);

        if ((dotGravity&GRAVITY_LEFT) != 0) {
            dotRect.left = 0;
            dotRect.right = dotWidth;
        }

        if ((dotGravity&GRAVITY_TOP) != 0) {
            dotRect.top = 0;
            dotRect.bottom = dotHeight;
        }

        if ((dotGravity&GRAVITY_RIGHT) != 0) {
            dotRect.right = width;
            dotRect.left = width - dotWidth;
        }

        if ((dotGravity&GRAVITY_BOTTOM) != 0) {
            dotRect.bottom = height;
            dotRect.top = height - dotHeight;
        }

        dotRectF.set(dotRect);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void init(@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setWillNotDraw(false);

        TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.DotView, defStyleAttr, defStyleRes);
        dotPaint = new Paint();
        dotPaint.setAntiAlias(true);
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(typedArray.getColor(R.styleable.DotView_dotBackground, Color.RED));

        dotTextPaint = new Paint();
        dotTextPaint.setStyle(Paint.Style.STROKE);
        dotTextPaint.setTextSize(typedArray.getDimension(R.styleable.DotView_dotTextSize, 12*3f));
        dotTextPaint.setColor(typedArray.getColor(R.styleable.DotView_dotTextColor, Color.WHITE));
        dotTextPaint.setAntiAlias(true);
        dotTextPaint.setTextAlign(Paint.Align.CENTER);
        dotText = typedArray.getString(R.styleable.DotView_dotText);
        fontMetrics = dotTextPaint.getFontMetrics();

        dotWidth = typedArray.getDimensionPixelSize(R.styleable.DotView_dotWidth, Unit.dp2px(48));
        dotHeight = typedArray.getDimensionPixelSize(R.styleable.DotView_dotHeight, Unit.dp2px(48));

        dotMargin = new DotMargin();
        dotMargin.left = typedArray.getDimensionPixelSize(R.styleable.DotView_dotMarginLeft, 0);
        dotMargin.top = typedArray.getDimensionPixelSize(R.styleable.DotView_dotMarginTop, 0);
        dotMargin.right = typedArray.getDimensionPixelSize(R.styleable.DotView_dotMarginRight, 0);
        dotMargin.bottom = typedArray.getDimensionPixelSize(R.styleable.DotView_dotMarginBottom, 0);

        dotGravity = typedArray.getInteger(R.styleable.DotView_dotGravity, GRAVITY_RIGHT|GRAVITY_TOP);
        typedArray.recycle();

        dotRect = new Rect();
        dotRectF = new RectF();

        post(new Runnable() {
            @Override
            public void run() {
                View view = getChildAt(0);
                FrameLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();

                params.gravity = params.gravity == LayoutParams.UNSPECIFIED_GRAVITY ? 0: params.gravity;

                if ((dotGravity&GRAVITY_LEFT) != 0) {
                    params.gravity += Gravity.RIGHT;
                }

                if ((dotGravity&GRAVITY_TOP) != 0) {
                    params.gravity += Gravity.BOTTOM;
                }

                if ((dotGravity&GRAVITY_RIGHT) != 0) {
                    params.gravity += Gravity.LEFT;
                }

                if ((dotGravity&GRAVITY_BOTTOM) != 0) {
                    params.gravity += Gravity.TOP;
                }

                view.setLayoutParams(params);
            }
        });
    }

    private class DotMargin {
        int left;
        int top;
        int right;
        int bottom;
    }
}

