package com.moizest89.jbplandelalaguna.Util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by @moizest89 in SV on 4/17/16.
 */
public class MarginDecoration extends RecyclerView.ItemDecoration{

    private int top, right, bottom, left;
    private Context context;

    public MarginDecoration(Context context) {
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.left = 0;
        this.context = context;
    }

    public MarginDecoration(int margin, Context context) {
        this.top = margin;
        this.right = margin;
        this.bottom = margin;
        this.left = margin;
        this.context = context;
    }
    public MarginDecoration( int left , int top, int right, int bottom, Context context) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.context = context;
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(this.left, this.top, this.right, this.bottom);
    }
}
