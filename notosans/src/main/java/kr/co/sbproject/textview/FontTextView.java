/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package kr.co.sbproject.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontTextView extends TextView {
    public FontTextView(Context context) {
        super(context);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Regular.otf"));
    }

    private void setType(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NotoTextView, 0, 0);

        int fontWeight = typedArray.getInteger(R.styleable.NotoTextView_fontWeight, 0);
        switch (fontWeight) {
            case 0:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Thin.otf"));
                break;
            case 1:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Light.otf"));
                break;
            case 2:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-DemiLight.otf"));
                break;
            case 3:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Regular.otf"));
                break;
            case 4:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Medium.otf"));
                break;
            case 5:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Bold.otf"));
                break;
            case 6:
                this.setTypeface(Typeface.createFromAsset(context.getAssets(), "NotoSansCJKkr-Black.otf"));
                break;
        }
    }
}
