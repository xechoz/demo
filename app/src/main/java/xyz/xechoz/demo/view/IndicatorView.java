package xyz.xechoz.demo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
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
        itemPaint.setAntiAlias(true);
        itemPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        itemPaint.setStrokeWidth(Unit.dp2px(4));

        itemMargin = new Rect(4, 4, 4, 4);

        itemWidth = Unit.dp2px(16);
        itemHeight = Unit.dp2px(16);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        itemWidth += itemMargin.left + itemMargin.right;
        itemHeight += itemMargin.top + itemMargin.bottom;

        setMeasuredDimension(resolveSize(4*itemWidth, widthMeasureSpec),
                resolveSize(4*itemHeight, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=0; i<itemCount; i++) {
            canvas.drawRoundRect(itemRect, itemRect.width() / 2, itemRect.height() / 2, itemPaint);
        }
    }

    public interface IndicatorAdapter {

        Drawable getItem(int position, boolean isActive);

        int getItemCount();

        void onSelect(int position);
    }
}
