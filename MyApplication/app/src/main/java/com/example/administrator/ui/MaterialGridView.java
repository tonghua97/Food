package com.example.administrator.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by Sophia on 2016/12/1.
 */
public class MaterialGridView extends GridView {
    public MaterialGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialGridView(Context context) {
        super(context);
    }

    public MaterialGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
