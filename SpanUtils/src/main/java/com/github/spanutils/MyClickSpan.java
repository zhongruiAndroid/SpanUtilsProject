package com.github.spanutils;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class MyClickSpan extends ClickableSpan {
    private int defaultValue = -666666;
    private int color;
    private boolean useUnderLine;

    public MyClickSpan(int color) {
        this(color, false);
    }

    public MyClickSpan(int color, boolean useUnderLine) {
        this.color = color;
        this.useUnderLine = useUnderLine;
    }

    @Override
    public void onClick(View widget) {
        if (widget instanceof TextView) {
            ((TextView) widget).setHighlightColor(widget.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        if (color != defaultValue) {
            ds.setColor(color);
        }
        ds.setUnderlineText(useUnderLine);
    }
}