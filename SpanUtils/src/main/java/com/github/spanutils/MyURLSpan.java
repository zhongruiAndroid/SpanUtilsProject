package com.github.spanutils;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class MyURLSpan extends URLSpan {
    private int defaultValue = -666666;
    private int color;
    private boolean useUnderLine;

    public MyURLSpan(String url, int color, boolean useUnderLine) {
        super(url);
        this.color = color;
        this.useUnderLine = useUnderLine;
    }

    public MyURLSpan(@NonNull Parcel src) {
        super(src);
    }

    @Override
    public void onClick(View widget) {
        super.onClick(widget);
        if (widget instanceof TextView) {
            ((TextView) widget).setHighlightColor(widget.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        if (color != defaultValue) {
            ds.setColor(color);
        }
        ds.setUnderlineText(useUnderLine);
    }
}