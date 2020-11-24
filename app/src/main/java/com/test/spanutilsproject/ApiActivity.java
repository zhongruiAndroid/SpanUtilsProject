package com.test.spanutilsproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.spanutils.SpanBuild;

public class ApiActivity extends AppCompatActivity {

    private TextView tvTestSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        tvTestSpan = findViewById(R.id.tvTestSpan);
        initData();
    }

    private void initData() {
        SpannableStringBuilder build = SpanBuild.get("例子").append("粗体").setTextIsBold()
                .append("斜体\n").setTextIsItalic()
                .append("URL链接\n").setUrl("http://www.baidu.com").setUnderLine()
                .append("粗斜体\n").setTextIsBoldItalic()
                .append("字体大小25px\n").setTextSize(25)
                .append("URL链接(黑色)\n").setUnderLine().setUrl("http://www.baidu.com").setTextColor(Color.BLACK)
                .append("字体大小15dp\n").setTextSize(15, true)
                .append("URL链接(红色)无下划线\n").setUrl("http://www.baidu.com").setTextColor(Color.RED).setUnderLine(false)
                .append("字体颜色蓝色\n").setTextColor(Color.BLUE)
                .append("背景黄色\n").setBgColor(Color.YELLOW)
                .append("下划线").setUnderLine()
                .setNewlinePoint(2,Color.GRAY,10)
                .setSpan(new BulletSpan())
                .setSpan(new BulletSpan())
                .setSpan(new BulletSpan())
                .append("相对前面字体大小的0.5f\n").setRelativeTextSizeScale(0.5f)
                .append("中间删除线\n").setCenterLine()
                .append("相对前面字体大小的1.5f\n").setRelativeTextSizeScale(1.5f)
                .append("中间线+下划线\n").setCenterLine().setUnderLine()
                .append("文字偏上显示\n").setTextShowTop()
                .setQuoteColor(Color.RED)
                .setQuoteGapWidth(10)
                .setQuoteStripeWidth(20)
                .setLineMarginOther(60)
                .setLineMarginCurrent(30)
                .append("设置点击\n").setTextIsBold().setTextColor(Color.BLUE).setUnderLine(false).setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ApiActivity.this,"点击生效",Toast.LENGTH_SHORT).show();
                    }
                }).setTextColor(Color.RED).setUnderLine(true).setTextIsItalic()
                .append("文字偏下显示\n").setTextShowBottom()
                .append("BlurOuter\n").setBlurOuter()
                .append("BlurInner\n").setBlurInner()
                .append("BlurSolid\n").setBlurSolid()
                .append("X伸缩1.5f\n").setScaleXSize(1.5f)
                .append("X伸缩0.5f\n").setScaleXSize(0.5f)
                .append("serif字体").setTextFamily("serif")
                .append("原始大小图片")
                .appendImage(new SpanBuild.MyImageSpan(this,R.drawable.test))
                .append("\n50px宽度的图片")
                .appendImage(new SpanBuild.MyImageSpan(this,R.drawable.test).setWidth(50))
                .append("\n250px宽度的图片且设置成红色")
                .setTextAlignRight()
                .appendImage(new SpanBuild.MyImageSpan(this,R.drawable.test).setHeight(250).setColor(Color.RED))
                .build(tvTestSpan);//如果需要点击事件或者设置了并需要点击效果，需要在build方法传入该TextView

        tvTestSpan.setText(build);
    }

}
