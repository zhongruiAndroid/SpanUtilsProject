package com.github.spanutils;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class SpanBuild {

    private int defaultValue = -666666;
    /*文字*/
    private CharSequence text;
    /*文字绝对大小*/
    private int textSize = 0;
    private boolean textSizeUseDp = false;
    /*文字相对大小*/
    private float relativeTextSizeScale;
    @ColorInt
    private int textColor;
    @ColorInt
    private int bgColor;
    private float blurRadius = 5;
    private BlurMaskFilter.Blur blur;

    /*竖条纹*/
    @ColorInt
    private int quoteColor;
    private int quoteStripeWidth;
    private int quoteGapWidth;

    private float scaleXSize;

    /*中间划线*/
    private boolean centerLine;
    /*下划线*/
    private boolean underLine;
    private boolean textIsBold;
    private boolean textIsItalic;
    private boolean textIsBoldItalic;
    private boolean textIsNormal;

    /*文字偏下显示*/
    private boolean textShowTop;
    /*文字偏下显示*/
    private boolean textShowBottom;

    /*文字字体样式*/
    private String textFamily;
    private Typeface textTypeface;


    private String url;

    private int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

    /*当前段落缩进*/
    private int currentLineMargin;
    /*其他段落缩进*/
    private int otherLineMargin;


    private boolean isClickable;
    private View.OnClickListener clickListener;

    private SpannableStringBuilder builder;

    private MyImageSpan imageSpan;

    /*换行显示点*/
    private int pointRadius;
    private int pointGap;
    private int pointColor;
    private boolean needShowPoint;

    /*文字位置*/
    private Layout.Alignment alignment;

    /*设置span*/
    private List<Object> spanList = new ArrayList<>();


    private boolean needSetMovementMethod;

    public SpanBuild() {
        this("");
    }

    public SpanBuild(CharSequence text) {
        this.text = text;


        textSize = 0;
        relativeTextSizeScale = 0;
        textColor = defaultValue;
        bgColor = defaultValue;
        blurRadius = 5;
        blur = null;
        quoteColor = defaultValue;
        quoteStripeWidth = 0;
        quoteGapWidth = 0;
        scaleXSize = 0;
        centerLine = false;
        underLine = false;
        textIsBold = false;
        textIsItalic = false;
        textIsBoldItalic = false;
        textIsNormal = false;
        textShowTop = false;
        textShowBottom = false;
        textFamily = "";
        textTypeface = null;
        url = "";
        flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        currentLineMargin = defaultValue;
        otherLineMargin = defaultValue;
        isClickable = false;
        clickListener = null;
        imageSpan = null;

        pointRadius = 4;
        pointGap = 2;
        pointColor = Color.GRAY;
        needShowPoint = false;
        alignment = null;


        builder = new SpannableStringBuilder();
    }

    public static SpanBuild get() {
        return get("");
    }

    public static SpanBuild get(CharSequence text) {
        return new SpanBuild(text);
    }


    public SpanBuild append(CharSequence text) {
        buildSpan();
        this.text = text;
        return this;
    }

    private void buildSpan() {
        int start = builder.length();
        builder.append(text);
        int end = builder.length();
        if (start == end) {
            return;
        }
        if (textSize > 0) {
            builder.setSpan(new AbsoluteSizeSpan(textSize, textSizeUseDp), start, end, flag);
            textSize = 0;
            textSizeUseDp = false;
        } else if (relativeTextSizeScale > 0) {
            builder.setSpan(new RelativeSizeSpan(relativeTextSizeScale), start, end, flag);
            relativeTextSizeScale = 0;
        }
        if (textColor != defaultValue) {
            builder.setSpan(new ForegroundColorSpan(textColor), start, end, flag);
        }
        if (bgColor != defaultValue) {
            builder.setSpan(new BackgroundColorSpan(bgColor), start, end, flag);
            bgColor = defaultValue;
        }
        if (blur != null) {
            builder.setSpan(new MaskFilterSpan(new BlurMaskFilter(blurRadius, blur)), start, end, flag);
            blurRadius = 5;
            blur = null;
        }
        if (scaleXSize > 0) {
            builder.setSpan(new ScaleXSpan(scaleXSize), start, end, flag);
            scaleXSize = 0;
        }
        if (centerLine) {
            builder.setSpan(new StrikethroughSpan(), start, end, flag);
            centerLine = false;
        }
        if (underLine) {
            builder.setSpan(new UnderlineSpan(), start, end, flag);
        }
        if (textIsBold) {
            builder.setSpan(new StyleSpan(Typeface.BOLD), start, end, flag);
            textIsBold = false;
        }
        if (textIsItalic) {
            builder.setSpan(new StyleSpan(Typeface.ITALIC), start, end, flag);
            textIsItalic = false;
        }
        if (textIsBoldItalic) {
            builder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, flag);
            textIsBoldItalic = false;
        }
        if (textIsNormal) {
            builder.setSpan(new StyleSpan(Typeface.NORMAL), start, end, flag);
            textIsNormal = false;
        }
        if (textShowTop) {
            builder.setSpan(new SuperscriptSpan(), start, end, flag);
            textShowTop = false;
        }
        if (textShowBottom) {
            builder.setSpan(new SubscriptSpan(), start, end, flag);
            textShowBottom = false;
        }
        if (textTypeface != null) {
            builder.setSpan(new TypefaceSpan(textTypeface), start, end, flag);
            textTypeface = null;
        } else if (!TextUtils.isEmpty(textFamily)) {
            builder.setSpan(new TypefaceSpan(textFamily), start, end, flag);
            textFamily = "";
        }
        if (!TextUtils.isEmpty(url)) {
            needSetMovementMethod = true;
            builder.setSpan(new MyURLSpan(url, textColor, underLine), start, end, flag);
            url = "";
        }
        if (currentLineMargin > 0 || otherLineMargin > 0) {
            builder.setSpan(new LeadingMarginSpan.Standard(currentLineMargin, otherLineMargin), start, end, flag);
            currentLineMargin = 0;
            otherLineMargin = 0;
        }
        if (isClickable) {
            builder.setSpan(new MyClickSpan(textColor, underLine) {
                @Override
                public void onClick(@NonNull View widget) {
                    super.onClick(widget);
                    if (clickListener != null) {
                        clickListener.onClick(widget);
                    }
                }
            }, start, end, flag);
            isClickable = false;
        }
        if (spanList != null) {
            for (Object obj : spanList) {
                if (obj == null) {
                    continue;
                }
                if (textColor != defaultValue) {
                    if (obj instanceof MyBgSpan) {
                        ((MyBgSpan) obj).setTextColor(textColor);
                    }
                }
                builder.setSpan(obj, start, end, flag);
            }
            spanList.clear();
        }
        if (imageSpan != null) {
            builder.setSpan(imageSpan, start, end, flag);
            imageSpan = null;
        }

        if (quoteColor != defaultValue) {
            Class<QuoteSpan> quoteSpanClass = QuoteSpan.class;
            Constructor<?> constructor = null;
            try {
                constructor = quoteSpanClass.getConstructor(int.class);
                Constructor c = quoteSpanClass.getConstructor(int.class, int.class, int.class);
                if (c != null) {
                    builder.setSpan(new QuoteSpan(quoteColor, quoteStripeWidth, quoteGapWidth), start, end, flag);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (constructor != null) {
                    builder.setSpan(new QuoteSpan(quoteColor), start, end, flag);
                }
            }

            quoteColor = defaultValue;
            quoteStripeWidth = 0;
            quoteGapWidth = 0;
        }
        if (needShowPoint) {
            Class<BulletSpan> bulletSpanClass = BulletSpan.class;
            Constructor<?> constructor = null;
            try {
                constructor = bulletSpanClass.getConstructor(int.class, int.class);
                Constructor c = bulletSpanClass.getConstructor(int.class, int.class, int.class);
                if (c != null) {
                    builder.setSpan(new BulletSpan(pointGap, pointColor, pointRadius), start, end, flag);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (constructor != null) {
                    builder.setSpan(new BulletSpan(pointGap, pointColor), start, end, flag);
                }
            }
            pointRadius = 4;
            pointGap = 2;
            pointColor = Color.GRAY;
            needShowPoint = false;
        }
        if (alignment != null) {
            builder.setSpan(new AlignmentSpan.Standard(alignment), start, end, flag);
            alignment = null;
        }
        flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
        /*因为点击和url也需要设置颜色和下划线，所以下划线和颜色最后重置*/
        underLine = false;
        textColor = defaultValue;
    }

    @Deprecated
    public SpannableStringBuilder build() {
        return build(null);
    }

    public SpannableStringBuilder build(TextView textView) {
        if (textView != null && needSetMovementMethod) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        buildSpan();
        return builder;
    }
    /************************************************************************************/

    public SpanBuild setTextSize(int textSize) {
        return setTextSize(textSize, false);
    }

    public SpanBuild setTextSize(int textSize, boolean sizeIsDp) {
        this.textSize = textSize;
        this.textSizeUseDp = sizeIsDp;
        return this;
    }

    public SpanBuild setRelativeTextSizeScale(float relativeTextSizeScale) {
        this.relativeTextSizeScale = relativeTextSizeScale;
        return this;
    }

    public SpanBuild setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public SpanBuild setBgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public SpanBuild setBlurRadius(float blurRadius) {
        if (blurRadius < 0) {
            blurRadius = 0;
        }
        this.blurRadius = blurRadius;
        return this;
    }

    public SpanBuild setBlurNormal() {
        this.blur = BlurMaskFilter.Blur.NORMAL;
        return this;
    }

    public SpanBuild setBlurSolid() {
        this.blur = BlurMaskFilter.Blur.SOLID;
        return this;
    }

    public SpanBuild setBlurOuter() {
        this.blur = BlurMaskFilter.Blur.OUTER;
        return this;
    }

    public SpanBuild setBlurInner() {
        this.blur = BlurMaskFilter.Blur.INNER;
        return this;
    }

    public SpanBuild setQuoteColor(int quoteColor) {
        this.quoteColor = quoteColor;
        return this;
    }

    public SpanBuild setQuoteStripeWidth(int quoteStripeWidth) {
        this.quoteStripeWidth = quoteStripeWidth;
        return this;
    }

    public SpanBuild setQuoteGapWidth(int quoteGapWidth) {
        this.quoteGapWidth = quoteGapWidth;
        return this;
    }

    public SpanBuild setScaleXSize(float scaleXSize) {
        this.scaleXSize = scaleXSize;
        return this;
    }

    public SpanBuild setCenterLine() {
        this.centerLine = true;
        return this;
    }

    public SpanBuild setUnderLine() {
        return setUnderLine(true);
    }

    public SpanBuild setUnderLine(boolean useUnderLine) {
        this.underLine = useUnderLine;
        return this;
    }

    public SpanBuild setTextIsBold() {
        this.textIsBold = true;
        return this;
    }

    public SpanBuild setTextIsItalic() {
        this.textIsItalic = true;
        return this;
    }

    public SpanBuild setTextIsBoldItalic() {
        this.textIsBoldItalic = true;
        return this;
    }

    public SpanBuild setTextIsNormal() {
        this.textIsNormal = true;
        return this;
    }

    public SpanBuild setTextShowTop() {
        this.textShowTop = true;
        return this;
    }

    public SpanBuild setTextShowBottom() {
        this.textShowBottom = true;
        return this;
    }

    public SpanBuild setTextFamily(String textFamily) {
        this.textFamily = textFamily;
        return this;
    }

    public SpanBuild setTextTypeface(Typeface textTypeface) {
        this.textTypeface = textTypeface;
        return this;
    }

    public SpanBuild setUrl(String url) {
        this.url = url;
        return this;
    }

    public SpanBuild setFlag(int flag) {
        if (flag != Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                && flag != Spanned.SPAN_INCLUSIVE_INCLUSIVE
                && flag != Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                && flag != Spanned.SPAN_EXCLUSIVE_INCLUSIVE) {

            flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        }
        this.flag = flag;
        return this;
    }

    public SpanBuild setLineMarginCurrent(int currentLineMargin) {
        this.currentLineMargin = currentLineMargin;
        return this;
    }

    public SpanBuild setLineMarginOther(int otherLineMargin) {
        this.otherLineMargin = otherLineMargin;
        return this;
    }

    public SpanBuild setClick(View.OnClickListener clickListener) {
        if (clickListener == null) {
            return this;
        }
        this.clickListener = clickListener;
        this.isClickable = true;
        return this;
    }

    public SpanBuild appendImage(MyImageSpan imageSpan) {
        buildSpan();
        this.text = " ";
        this.imageSpan = imageSpan;
        return this;
    }

    public SpanBuild setNewlinePoint() {
        setNewlinePoint(2);
        return this;
    }

    public SpanBuild setNewlinePoint(int gapWidth) {
        setNewlinePoint(gapWidth, Color.GRAY);
        return this;
    }

    public SpanBuild setNewlinePoint(int gapWidth, int pointColor) {
        setNewlinePoint(gapWidth, pointColor, 4);
        return this;
    }

    public SpanBuild setNewlinePoint(int gapWidth, int pointColor, int pointRadius) {
        this.pointRadius = pointRadius;
        this.pointGap = gapWidth;
        this.pointColor = pointColor;
        needShowPoint = true;
        return this;
    }

    public SpanBuild setTextAlignNormal() {
        alignment = Layout.Alignment.ALIGN_NORMAL;
        return this;
    }

    public SpanBuild setTextAlignOpposite() {
        alignment = Layout.Alignment.ALIGN_OPPOSITE;
        return this;
    }

    public SpanBuild setTextAlignCenter() {
        alignment = Layout.Alignment.ALIGN_CENTER;
        return this;
    }

    public SpanBuild setTextAlignLeft() {
        alignment = Layout.Alignment.ALIGN_LEFT;
        return this;
    }

    public SpanBuild setTextAlignRight() {
        alignment = Layout.Alignment.ALIGN_RIGHT;
        return this;
    }

    public SpanBuild setSpan(Object span) {
        this.spanList.add(span);
        return this;
    }


}
