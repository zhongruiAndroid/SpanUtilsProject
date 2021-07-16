package com.test.spanutilsproject;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.spanutils.MyImageSpan;
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
        SpanBuild spanBuild = SpanBuild.get("例子：").append("粗体").setTextIsBold()
                .append("斜体\n").setTextIsItalic()
                .append("URL链接(可以点击)\n").setUrl("http://www.baidu.com").setUnderLine()
                .append("粗斜体\n").setTextIsBoldItalic()
                .append("字体大小25px\n").setTextSize(25)
                .append("URL链接(黑色)(可以点击)\n").setUnderLine().setUrl("http://www.baidu.com").setTextColor(Color.BLACK)
                .append("字体大小15dp\n").setTextSize(15, true)
                .append("URL链接(红色)无下划线(可以点击)\n").setUrl("http://www.baidu.com").setTextColor(Color.RED).setUnderLine(false)
                .append("字体颜色蓝色\n").setTextColor(Color.BLUE)
                .append("背景黄色\n").setBgColor(Color.YELLOW)
                .append("下划线+换行效果(文字前面有点)").setUnderLine()
                .setNewlinePoint(2, Color.GRAY, 10)
                .append("相对前面字体大小的0.5f\n").setRelativeTextSizeScale(0.5f)
                .append("中间删除线\n").setCenterLine()
                .append("相对前面字体大小的1.5f\n").setRelativeTextSizeScale(1.5f)
                .append("中间线+下划线\n").setCenterLine().setUnderLine()
                .append("文字偏上显示").setTextShowTop()
                .append("中间位置")
                .append("文字偏下显示\n").setTextShowBottom()
                .append("换行效果(文字前面有竖线)\n")
                .setQuoteColor(Color.RED)
                .setQuoteGapWidth(10)
                .setQuoteStripeWidth(20)
                .setLineMarginOther(60)
                .setLineMarginCurrent(130)
                .append("设置点击(可以点击)\n").setTextIsBold().setTextColor(Color.BLUE).setUnderLine(false).setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ApiActivity.this, "点击生效", Toast.LENGTH_SHORT).show();
                    }
                }).setTextColor(Color.RED).setUnderLine(true).setTextIsItalic()
                .append("BlurOuter\n").setBlurOuter()
                .append("BlurInner\n").setBlurInner()
                .append("BlurSolid\n").setBlurSolid()
                .append("X伸缩1.5f\n").setScaleXSize(1.5f)
                .append("X伸缩0.5f\n").setScaleXSize(0.5f)
                .append("serif字体\n").setTextFamily("serif")
                .append("原始大小图片")
                .appendImage(new MyImageSpan(this, R.drawable.test))
                .append("\n50px宽度的图片")
                .appendImage(new MyImageSpan(this, R.drawable.test).setWidth(50))
                .append("\n250px宽度的图片且设置成红色")
                .setTextAlignRight()
                .appendImage(new MyImageSpan(this, R.drawable.test).setHeight(250).setColor(Color.RED));
        //如果需要点击事件或者设置了并需要点击效果，需要在build方法传入该TextView
        SpannableStringBuilder build = spanBuild.build(tvTestSpan);

        tvTestSpan.setText(build);
    }

}
