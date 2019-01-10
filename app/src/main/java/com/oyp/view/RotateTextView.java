package com.oyp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * 实现一个可选择的TextView，作用是：右上角的角标45°文本提示
 * </p>
 * created by OuyangPeng at 2019/1/10 下午 08:19
 *
 * @author OuyangPeng
 */
public class RotateTextView extends android.support.v7.widget.AppCompatTextView {
    /**
     * 默认选择45°角
     */
    private static final int DEFAULT_DEGREES = 45;

    /**
     * 文本旋转的角度
     */
    private int mDegrees;

    public RotateTextView(Context context) {
        this(context, null);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RotateTextView);
        mDegrees = a.getInteger(R.styleable.RotateTextView_degree, DEFAULT_DEGREES);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }

    /**
     * 改变文本选择的角度
     *
     * @param degrees 文本旋转的角度
     */
    public void setDegrees(int degrees) {
        mDegrees = degrees;
        invalidate();
    }

}
