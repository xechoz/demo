package xyz.xechoz.demo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import xyz.xechoz.demo.util.Unit;

/**
 * Created by xechoz.zheng on 2/17/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class IndicatorView extends View implements ICustomView {
    private int itemCount;
    private RectF itemRect;
    private Paint itemPaint;
    private Rect itemMargin;
    private List<Drawable> itemDrawables;
    private int itemWidth;
    private int itemHeight;
    private float radius;
    private int strokeWidth;
    private ColorMatrixColorFilter colorFilter;
    private LightingColorFilter lightingColorFilter;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void init(@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        itemCount = 4;
        itemRect = new RectF();
        itemPaint = new Paint();
        itemPaint.setColor(Color.RED);
        itemPaint.setAntiAlias(true);
        itemPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        itemPaint.setStrokeWidth(Unit.dp2px(4));

        itemMargin = new Rect(0, 0, 0, 0);

        itemWidth = Unit.dp2px(16);
        itemHeight = Unit.dp2px(16);

        radius = itemWidth / 2f;

        strokeWidth = Unit.dp2px(32);

        colorFilter = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        }
        ));

        lightingColorFilter = new LightingColorFilter(0x112233, 0x1122ff);
        itemMargin = new Rect(4, 4, 4, 4);

        itemWidth = Unit.dp2px(16);
        itemHeight = Unit.dp2px(16);

        radius = itemWidth / 2f;

        strokeWidth = Unit.dp2px(32);

        colorFilter = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        }
        ));

        lightingColorFilter = new LightingColorFilter(0x112233, 0x1122ff);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = itemWidth + itemMargin.left + itemMargin.right + 2 * strokeWidth;
        int height = itemHeight + itemMargin.top + itemMargin.bottom + 2 * strokeWidth;

        setMeasuredDimension(resolveSize(4 * width, widthMeasureSpec),
                resolveSize(4 * height, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < itemCount; i++) {
            float left = i * (itemWidth + itemMargin.left + itemMargin.right + strokeWidth);
            itemRect.left = left + strokeWidth;
            itemRect.top = itemMargin.top + strokeWidth;
            itemRect.right = left + itemWidth;
            itemRect.bottom = itemRect.top + itemHeight;

            // draw stroke
            itemPaint.setAlpha((int) (0.8 * 255));
            itemPaint.setColor(Color.GRAY);
            itemPaint.setStyle(Paint.Style.STROKE);
            itemPaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle((itemRect.left + itemRect.right) / 2f,
                    (itemRect.top + itemRect.bottom) / 2f, itemWidth / 2f, itemPaint);

            // draw center
            itemPaint.setAlpha(0);
            itemPaint.setColor(Color.GREEN);
            itemPaint.setStyle(Paint.Style.FILL);
            itemPaint.setColorFilter(colorFilter);
            itemPaint.setColorFilter(lightingColorFilter);
            canvas.drawCircle((itemRect.left + itemRect.right) / 2f,
                    (itemRect.top + itemRect.bottom) / 2f, itemWidth / 2f, itemPaint);

            canvas.drawRoundRect(itemRect, itemRect.width() / 2, itemRect.height() / 2, itemPaint);
        }
    }

    public interface IndicatorAdapter {

        Drawable getItem(int position, boolean isActive);

        int getItemCount();

        void onSelect(int position);
    }
}
