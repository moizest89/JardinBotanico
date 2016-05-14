package com.moizest89.jbplandelalaguna.Util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by @moizest89 in SV on 5/12/16.
 */
public class ViewPagerWithRatio extends ViewPager{

    public float x = 1.0f;
    public float y = 1.0f;

    public ViewPagerWithRatio(Context context) {
        super(context);
    }

    public ViewPagerWithRatio(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


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



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = View.MeasureSpec.getSize(widthMeasureSpec);
//        int height = (int) (width * (1.0f/2.0f));

        int height = (int) (width * (getY()/getX()));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.setMeasuredDimension((int) width, (int) height);
    }
}
