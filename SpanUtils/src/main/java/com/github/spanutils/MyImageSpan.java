package com.github.spanutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;

import android.text.style.ImageSpan;

public   class MyImageSpan extends ImageSpan {
        private int defaultValue = -666666;
        private Context ctx;
        private Drawable newDrawable;
        private int width;
        private int height;
        private int color = defaultValue;
        private PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;

        public MyImageSpan setColor(int color) {
            this.color = color;
            return this;
        }

        public MyImageSpan setMode(PorterDuff.Mode mode) {
            if (mode == null) {
                return this;
            }
            this.mode = mode;
            return this;
        }

        public MyImageSpan setWidth(int width) {
            this.width = width;
            return this;
        }

        public MyImageSpan setHeight(int height) {
            this.height = height;
            return this;
        }

        public MyImageSpan(@NonNull Bitmap b) {
            super(b);
        }

        public MyImageSpan(@NonNull Bitmap b, int verticalAlignment) {
            super(b, verticalAlignment);
        }

        public MyImageSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
            super(context, bitmap);
            ctx = context;
        }

        public MyImageSpan(@NonNull Context context, @NonNull Bitmap bitmap, int verticalAlignment) {
            super(context, bitmap, verticalAlignment);
            ctx = context;
        }

        public MyImageSpan(@NonNull Drawable drawable) {
            super(drawable);
        }

        public MyImageSpan(@NonNull Drawable drawable, int verticalAlignment) {
            super(drawable, verticalAlignment);
        }

        public MyImageSpan(@NonNull Drawable drawable, @NonNull String source) {
            super(drawable, source);
        }

        public MyImageSpan(@NonNull Drawable drawable, @NonNull String source, int verticalAlignment) {
            super(drawable, source, verticalAlignment);
        }

        public MyImageSpan(@NonNull Context context, @NonNull Uri uri) {
            super(context, uri);
            ctx = context;
        }

        public MyImageSpan(@NonNull Context context, @NonNull Uri uri, int verticalAlignment) {
            super(context, uri, verticalAlignment);
            ctx = context;
        }

        public MyImageSpan(@NonNull Context context, int resourceId) {
            super(context, resourceId);
            ctx = context;
        }

        public MyImageSpan(@NonNull Context context, int resourceId, int verticalAlignment) {
            super(context, resourceId, verticalAlignment);
            ctx = context;
        }

        public Drawable getDrawable() {
            if (newDrawable != null) {
                return newDrawable;
            }
            Drawable drawable = super.getDrawable();
            if (color != defaultValue) {
                drawable.mutate().setColorFilter(color, mode);
            }
            if (width <= 0 && height <= 0) {
                return drawable;
            }
            try {
                int rectW = drawable.getBounds().width();
                int rectH = drawable.getBounds().height();
                Bitmap bitmap = null;
                if (width > 0) {
                    int newHeight = width * rectH / rectW;
                    bitmap = Bitmap.createBitmap(width, newHeight, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    float scale = width * 1f / rectW;
                    canvas.scale(scale, scale);
                    drawable.draw(canvas);
                } else if (height > 0) {
                    int newWidth = height * rectW / rectH;
                    bitmap = Bitmap.createBitmap(newWidth, height, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    float scale = height * 1f / rectH;
                    canvas.scale(scale, scale);
                    drawable.draw(canvas);
                }
                if (bitmap != null) {
                    newDrawable = ctx != null
                            ? new BitmapDrawable(ctx.getResources(), bitmap)
                            : new BitmapDrawable(bitmap);

                    newDrawable.setBounds(0, 0, newDrawable.getIntrinsicWidth(), newDrawable.getIntrinsicHeight());
                    if (color != defaultValue) {
                        newDrawable.mutate().setColorFilter(color, mode);
                    }
                    return newDrawable;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawable;
        }
    }
