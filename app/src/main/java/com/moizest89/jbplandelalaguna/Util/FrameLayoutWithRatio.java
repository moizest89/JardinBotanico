package com.moizest89.jbplandelalaguna.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by @moizest89 in SV on 5/12/16.
 */
public class FrameLayoutWithRatio extends FrameLayout {

    public float x = 1.0f;
    public float y = 1.0f;


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public FrameLayoutWithRatio(Context context) {
        super(context);
    }

    public FrameLayoutWithRatio(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayoutWithRatio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FrameLayoutWithRatio(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = View.MeasureSpec.getSize(widthMeasureSpec);
//        int height = (int) (width * (1.0f/2.0f));

        int height = (int) (width * (getY()/getX()));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.setMeasuredDimension((int) width, (int) height);
    }
}
