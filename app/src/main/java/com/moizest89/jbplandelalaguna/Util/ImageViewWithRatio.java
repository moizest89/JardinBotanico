package com.moizest89.jbplandelalaguna.Util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.moizest89.jbplandelalaguna.R;

/**
 * Created by @moizest89 in SV on 4/17/16.
 */
public class ImageViewWithRatio extends ImageView{


    public float x;
    public float y;


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

    public ImageViewWithRatio(Context context) {
        super(context);
    }

    public ImageViewWithRatio(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewWithRatio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageViewWithRatio(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
