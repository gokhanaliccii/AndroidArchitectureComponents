package com.gokhanaliccii.flavorhunter.util.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gokhanaliccii.flavorhunter.R;

/**
 * Created by gokhan on 28/12/17.
 */

public class ItemDecorator extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    public ItemDecorator(Context context) {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, R.color.colorListSplitter));
        mPaint.setStrokeWidth(context.getResources()
                .getDimension(R.dimen.venue_splitter_thickness));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null || adapter.getItemCount() == 1) {
            super.onDrawOver(c, parent, state);
        } else {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int count = adapter.getItemCount();
            View v;

            for (int i = 0; i < count - 1; i++) {
                v = parent.getChildAt(i);
                if (v != null) {
                    c.drawLine(left, v.getBottom(), right, v.getBottom(), mPaint);
                }
            }

        }

    }
}
