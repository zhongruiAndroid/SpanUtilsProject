package com.github.spanutils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

public class MyBgSpan extends ReplacementSpan implements Cloneable {
    @Override
    public MyBgSpan clone() {
        try {
            return (MyBgSpan) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_TOP = 1;
    public static final int ALIGN_CENTER = 2;

    private float marginLeft;
    private float marginTop;
    private float marginRight;
    private float marginBottom;

    private float paddingLeft;
    private float paddingRight;

    private float radiusLeftTop;
    private float radiusLeftBottom;
    private float radiusRightTop;
    private float radiusRightBottom;
    private int borderColor;
    private float borderWidth;
    private float borderDashGap;
    private float borderDashLength;
    private int bgColor;
    private int showAlign;
    private int tempTextColor;
    private int textColor;

    /******************************************************************************/
    private static final int GRADIENT_TYPE_NONE = 0;
    private static final int GRADIENT_TYPE_LINEAR = 1;
    private static final int GRADIENT_TYPE_RADIAL = 2;
    private static final int GRADIENT_TYPE_SWEEP = 3;
    /*
     * 渐变类型
     * linear:线性渐变
     * radial:放射渐变
     * sweep:扫描性渐变
     * */
    private int gradientType;
    /*渐变角度*/
    private int gradientAngle;
    private Shader shader;

    /*渐变的X轴起始位置,范围0~1,默认0.5*/
    private float gradientCenterX;
    /*渐变的Y轴起始位置,范围0~1,默认0.5*/
    private float gradientCenterY;
    /*渐变起始颜色*/
    private int gradientStartColor;
    /*渐变中间颜色*/
    private int gradientCenterColor;
    /*渐变结束颜色*/
    private int gradientEndColor;
    /*渐变半径,gradientType="radial"适用,默认40*/
    private float gradientRadius;
    private @ColorInt int gradientColors[];
    private float gradientColorPositions[];
    /**********************************************************/

    private int itemWidth;
    private Rect textRect = new Rect();
    private Path path = new Path();
    private Path clipPath = new Path();
    private RectF pathRect;
    private Paint.FontMetrics fontMetrics;

    public MyBgSpan() {
        marginLeft = 0;
        marginTop = 0;
        marginRight = 0;
        marginBottom = 0;
        paddingLeft = 0;
        paddingRight = 0;
        radiusLeftTop = 0;
        radiusLeftBottom = 0;
        radiusRightTop = 0;
        radiusRightBottom = 0;
        borderColor = 0;
        borderWidth = 0;
        borderDashGap = 0;
        borderDashLength = 0;
        bgColor = 0;
        showAlign = ALIGN_BOTTOM;
        tempTextColor = 0;
        textColor = 0;

    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        float v = paint.measureText(text, start, end);
        itemWidth = (int) (v + marginLeft + marginRight + paddingLeft + paddingRight);
        return itemWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        if (pathRect == null) {
            pathRect = new RectF();
        }
        tempTextColor = paint.getColor();
        pathRect.left = x + marginLeft;
        pathRect.top = (top + marginTop);
        pathRect.right = x + itemWidth - marginRight;
        pathRect.bottom = bottom - marginBottom;

        int count = -1;
        if (borderWidth > 0) {
            count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        }
        /*计算背景范围*/
        if (!path.isEmpty()) {
            path.reset();
        }
        path.addRoundRect(pathRect, new float[]{radiusLeftTop, radiusLeftTop, radiusLeftBottom, radiusLeftBottom, radiusRightTop, radiusRightTop, radiusRightBottom, radiusRightBottom,}, Path.Direction.CW);
        /*绘制背景*/
        if (bgColor != Color.TRANSPARENT) {
            if(gradientType>0){
                if(gradientType==GRADIENT_TYPE_LINEAR){
                    float startX=(x+itemWidth/2)*1f*(float)Math.cos(Math.toRadians(gradientAngle));
                    float starY=(top+bottom)/2-(x+itemWidth/2)*1f*(float)Math.sin(Math.toRadians(gradientAngle));
                    float endX=(x+itemWidth)*1f*(float)Math.cos(Math.toRadians(gradientAngle));
                    float endY=(top+bottom)/2+(x+itemWidth)*1f*(float)Math.sin(Math.toRadians(gradientAngle));
                    if(gradientColors!=null&&gradientColorPositions!=null&&gradientColors.length>0&&gradientColors.length==gradientColorPositions.length){
                        shader=new LinearGradient(startX,starY,endX,endY,gradientColors,gradientColorPositions, Shader.TileMode.CLAMP);
                    }else if(gradientCenterColor==Color.TRANSPARENT){
                        shader=new LinearGradient(startX,starY,endX,endY,new int[]{gradientStartColor,gradientEndColor},new float[]{0,1}, Shader.TileMode.CLAMP);
                    }else{
                        shader=new LinearGradient(startX,starY,endX,endY,new int[]{gradientStartColor,gradientCenterColor,gradientEndColor},new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
                    }
                }else if(gradientType==GRADIENT_TYPE_RADIAL){
                    if(gradientRadius<=0){
                        gradientRadius=itemWidth/2;
                    }
                    if(gradientColors!=null&&gradientColorPositions!=null&&gradientColors.length>0&&gradientColors.length==gradientColorPositions.length){
                        shader=new RadialGradient(getGradientCenterX(x),getGradientCenterY(top,bottom),gradientRadius,gradientColors,gradientColorPositions, Shader.TileMode.CLAMP);
                    }else{
                        shader=new RadialGradient(getGradientCenterX(x),getGradientCenterY(top,bottom),gradientRadius,gradientStartColor,gradientEndColor, Shader.TileMode.CLAMP);
                    }
                }else if(gradientType==GRADIENT_TYPE_SWEEP){
                    if(gradientColors!=null&&gradientColorPositions!=null&&gradientColors.length>0&&gradientColors.length==gradientColorPositions.length){
                        shader=new SweepGradient(getGradientCenterX(x),getGradientCenterY(top,bottom),gradientColors,gradientColorPositions);
                    }else{
                        shader=new SweepGradient(getGradientCenterX(x),getGradientCenterY(top,bottom),gradientStartColor,gradientEndColor);
                    }
                }
            }else{
                this.shader=null;
            }
            if(shader!=null){
                paint.setShader(shader);
            }
            paint.setColor(bgColor);
            canvas.drawPath(path, paint);

            paint.setShader(null);
        }
        if (borderWidth > 0) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                canvas.clipPath(path);
            }

            paint.setStyle(Paint.Style.STROKE);

            paint.setStrokeWidth(borderWidth * 2);
            paint.setColor(borderColor);

            if (bgColor != Color.TRANSPARENT||gradientType>0) {
                /*清除边框下的背景*/
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                canvas.drawPath(path, paint);
            }
            /*绘制边框*/
            paint.setXfermode(null);
            if (borderDashGap > 0 && borderDashLength > 0) {
                paint.setPathEffect(new DashPathEffect(new float[]{borderDashLength, borderDashGap}, 0));
            }
            canvas.drawPath(path, paint);
            paint.setPathEffect(null);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                /*去掉内容显示区域外部边框*/
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                if (!clipPath.isEmpty()) {
                    clipPath.reset();
                }
                clipPath.addRect(new RectF(x - borderWidth - 1, top - borderWidth - 1, x + itemWidth + borderWidth + 1, bottom + borderWidth + 1), Path.Direction.CW);
                clipPath.op(clipPath, path, Path.Op.DIFFERENCE);

                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPath(clipPath, paint);
                paint.setXfermode(null);
            }

        }
        if (count != -1) {
            canvas.restoreToCount(count);
        }

        paint.setStrokeWidth(0);

        /*绘制文字*/
        if (textColor == Color.TRANSPARENT) {
            textColor = tempTextColor;
        }
        paint.setColor(textColor);
        if (showAlign == ALIGN_BOTTOM) {
            canvas.drawText(text, start, end, x + paddingLeft + marginLeft, y, paint);
        } else if (showAlign == ALIGN_CENTER) {
            fontMetrics = paint.getFontMetrics();
            float ascent = fontMetrics.ascent;
            float descent = fontMetrics.descent;
            canvas.drawText(text, start, end, x + paddingLeft + marginLeft, pathRect.centerY() - (descent + ascent) * 1f / 2 + 1.5f, paint);
        } else {
            paint.getTextBounds(text.toString(), start, end, textRect);
            int offset = bottom - top - textRect.height();
            canvas.drawText(text, start, end, x + paddingLeft + marginLeft, y - offset / 2, paint);
        }
    }

    /************************************************************************************************************/
    public MyBgSpan setMargin(float left, float top, float right, float bottom) {
        setMarginLeft(left);
        setMarginTop(top);
        setMarginRight(right);
        setMarginBottom(bottom);
        return this;
    }

    public MyBgSpan setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public MyBgSpan setMarginTop(float marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public MyBgSpan setMarginRight(float marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public MyBgSpan setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public MyBgSpan setPadding(float padding) {
        setPadding(padding, padding);
        return this;
    }

    public MyBgSpan setPadding(float left, float right) {
        setPaddingLeft(left);
        setPaddingRight(right);
        return this;
    }

    public MyBgSpan setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    public MyBgSpan setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    public MyBgSpan setRadius(float radius) {
        setRadiusLeftTop(radius);
        setRadiusRightTop(radius);
        setRadiusLeftBottom(radius);
        setRadiusRightBottom(radius);
        return this;
    }

    public MyBgSpan setRadiusLeftTop(float radiusLeftTop) {
        this.radiusLeftTop = radiusLeftTop;
        return this;
    }

    public MyBgSpan setRadiusLeftBottom(float radiusLeftBottom) {
        this.radiusLeftBottom = radiusLeftBottom;
        return this;
    }

    public MyBgSpan setRadiusRightTop(float radiusRightTop) {
        this.radiusRightTop = radiusRightTop;
        return this;
    }

    public MyBgSpan setRadiusRightBottom(float radiusRightBottom) {
        this.radiusRightBottom = radiusRightBottom;
        return this;
    }

    public MyBgSpan setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public MyBgSpan setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public MyBgSpan setBorderDashGap(float borderDashGap) {
        this.borderDashGap = borderDashGap;
        return this;
    }

    public MyBgSpan setBorderDashLength(float borderDashLength) {
        this.borderDashLength = borderDashLength;
        return this;
    }

    public MyBgSpan setBgColor(@ColorInt int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public MyBgSpan setShowAlignBottom() {
        this.showAlign = ALIGN_BOTTOM;
        return this;
    }

    public MyBgSpan setShowAlignTop() {
        this.showAlign = ALIGN_TOP;
        return this;
    }

    public MyBgSpan setShowAlignCenter() {
        this.showAlign = ALIGN_CENTER;
        return this;
    }

    public MyBgSpan setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;
    }

    /**************************************************************/
    public MyBgSpan setGradientNone() {
        this.gradientType = GRADIENT_TYPE_NONE;
        return this;
    }

    public MyBgSpan setGradientLinear() {
        this.gradientType = GRADIENT_TYPE_LINEAR;
        return this;
    }

    public MyBgSpan setGradientRadial() {
        this.gradientType = GRADIENT_TYPE_RADIAL;
        return this;
    }

    public MyBgSpan setGradientSweep() {
        this.gradientType = GRADIENT_TYPE_SWEEP;
        return this;
    }

    public MyBgSpan setGradientAngle(int gradientAngle) {
        int temp = gradientAngle;
        if (temp >= 360) {
            temp = gradientAngle % 360;
        }
        this.gradientAngle = temp;
        return this;
    }

    public float getGradientCenterX(float startX) {
        return startX+itemWidth/2+gradientCenterX;
    }

    public float getGradientCenterY(int top,int bottom) {
        return (bottom-top)/2+top+gradientCenterY;
    }

    public MyBgSpan setGradientCenterX(float gradientCenterX) {
        this.gradientCenterX = gradientCenterX;
        return this;
    }

    public MyBgSpan setGradientCenterY( float gradientCenterY) {
        this.gradientCenterY = gradientCenterY;
        return this;
    }

    public MyBgSpan setGradientStartColor(@ColorInt int gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
        return this;
    }

    public MyBgSpan setGradientCenterColor(@ColorInt int gradientCenterColor) {
        this.gradientCenterColor = gradientCenterColor;
        return this;
    }

    public MyBgSpan setGradientEndColor(@ColorInt int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
        return this;
    }

    public MyBgSpan setGradientRadius(float gradientRadius) {
        if (gradientRadius < 0) {
            gradientRadius = 0;
        }
        this.gradientRadius = gradientRadius;
        return this;
    }

    public void setGradientColors(int[] gradientColors) {
        this.gradientColors = gradientColors;
    }
    public void setGradientColorPositions(float[] gradientColorPositions) {
        this.gradientColorPositions = gradientColorPositions;
    }
}