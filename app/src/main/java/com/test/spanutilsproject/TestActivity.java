package com.test.spanutilsproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.github.selectcolordialog.SelectColorDialog;
import com.github.selectcolordialog.SelectColorListener;
import com.github.spanutils.MyBgSpan;
import com.github.spanutils.SpanBuild;

public class TestActivity extends AppCompatActivity  implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private android.widget.TextView tvTestSpan;
    private android.support.v7.widget.AppCompatSeekBar sbMarginLeft;
    private android.support.v7.widget.AppCompatSeekBar sbMarginTop;
    private android.support.v7.widget.AppCompatSeekBar sbMarginRight;
    private android.support.v7.widget.AppCompatSeekBar sbMarginBottom;
    private android.support.v7.widget.AppCompatSeekBar sbPaddingLeft;
    private android.support.v7.widget.AppCompatSeekBar sbPaddingRight;
    private android.support.v7.widget.AppCompatSeekBar sbRadiusLeftTop;
    private android.support.v7.widget.AppCompatSeekBar sbRadiusLeftBottom;
    private android.support.v7.widget.AppCompatSeekBar sbRadiusRightTop;
    private android.support.v7.widget.AppCompatSeekBar sbRadiusRightBottom;
    private View borderColor;
    private android.support.v7.widget.AppCompatSeekBar sbBorderWidth;
    private android.support.v7.widget.AppCompatSeekBar sbBorderDashGap;
    private android.support.v7.widget.AppCompatSeekBar sbBorderDashLength;
    private View bgColor;
    private View textColor;
    private android.widget.RadioGroup rgAlign;
    private android.widget.RadioButton rbBottom;
    private android.widget.RadioButton rbTop;
    private android.widget.RadioButton rbCenter;


    private RadioGroup rgGradient;
    private RadioButton rbGradientNone;
    private RadioButton rbGradientLinear;
    private RadioButton rbGradientSweep;
    private RadioButton rbGradientRadial;
    private View bgStartColor;
    private View bgEndColor;



    private MyBgSpan testSpan;
    private int testSize;
    private AppCompatSeekBar sbGradientLinear;
    private AppCompatSeekBar sbGradientOffset;
    private AppCompatSeekBar sbGradientOffsetY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initData();
    }
    public void initView() {
        testSpan = new MyBgSpan();
        tvTestSpan = findViewById(R.id.tvTestSpan);

        sbMarginLeft = findViewById(R.id.sbMarginLeft);
        sbMarginLeft.setOnSeekBarChangeListener(this);

        sbMarginTop = findViewById(R.id.sbMarginTop);
        sbMarginTop.setOnSeekBarChangeListener(this);

        sbMarginRight = findViewById(R.id.sbMarginRight);
        sbMarginRight.setOnSeekBarChangeListener(this);

        sbMarginBottom = findViewById(R.id.sbMarginBottom);
        sbMarginBottom.setOnSeekBarChangeListener(this);

        sbPaddingLeft = findViewById(R.id.sbPaddingLeft);
        sbPaddingLeft.setOnSeekBarChangeListener(this);

        sbPaddingRight = findViewById(R.id.sbPaddingRight);
        sbPaddingRight.setOnSeekBarChangeListener(this);

        sbRadiusLeftTop = findViewById(R.id.sbRadiusLeftTop);
        sbRadiusLeftTop.setOnSeekBarChangeListener(this);

        sbRadiusLeftBottom = findViewById(R.id.sbRadiusLeftBottom);
        sbRadiusLeftBottom.setOnSeekBarChangeListener(this);

        sbRadiusRightTop = findViewById(R.id.sbRadiusRightTop);
        sbRadiusRightTop.setOnSeekBarChangeListener(this);

        sbRadiusRightBottom = findViewById(R.id.sbRadiusRightBottom);
        sbRadiusRightBottom.setOnSeekBarChangeListener(this);

        borderColor = findViewById(R.id.borderColor);
        borderColor.setOnClickListener(this);

        sbBorderWidth = findViewById(R.id.sbBorderWidth);
        sbBorderWidth.setOnSeekBarChangeListener(this);

        sbBorderDashGap = findViewById(R.id.sbBorderDashGap);
        sbBorderDashGap.setOnSeekBarChangeListener(this);

        sbBorderDashLength = findViewById(R.id.sbBorderDashLength);
        sbBorderDashLength.setOnSeekBarChangeListener(this);


        bgColor = findViewById(R.id.bgColor );
        bgColor.setOnClickListener(this);
        textColor = findViewById(R.id.textColor);
        textColor.setOnClickListener(this);
        rgAlign = findViewById(R.id.rgAlign);
        rgAlign.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbBottom:
                        testSpan.setShowAlignBottom();
                        reset(testSpan);
                        break;
                    case R.id.rbTop:
                        testSpan.setShowAlignTop();
                        reset(testSpan);
                        break;
                    case R.id.rbCenter:
                        testSpan.setShowAlignCenter();
                        reset(testSpan);
                        break;
                }
            }
        });
        rbBottom = findViewById(R.id.rbBottom);
        rbTop = findViewById(R.id.rbTop);
        rbCenter = findViewById(R.id.rbCenter);







        rgGradient = findViewById(R.id.rgGradient);
        rgGradient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbGradientNone:
                        testSpan.setGradientNone();
                        reset(testSpan);
                    break;
                    case R.id.rbGradientLinear:
                        testSpan.setGradientLinear();
                        reset(testSpan);
                    break;
                    case R.id.rbGradientSweep:
                        testSpan.setGradientSweep();
                        reset(testSpan);
                    break;
                    case R.id.rbGradientRadial:
                        testSpan.setGradientRadial();
                        reset(testSpan);
                    break;
                }
            }
        });

        sbGradientLinear = findViewById(R.id.sbGradientLinear);
        sbGradientLinear.setOnSeekBarChangeListener(this);

        sbGradientOffset = findViewById(R.id.sbGradientOffset);
        sbGradientOffset.setOnSeekBarChangeListener(this);

        sbGradientOffsetY = findViewById(R.id.sbGradientOffsetY);
        sbGradientOffsetY.setOnSeekBarChangeListener(this);

        rbGradientNone = findViewById(R.id.rbGradientNone);
        rbGradientLinear = findViewById(R.id.rbGradientLinear);
        rbGradientSweep = findViewById(R.id.rbGradientSweep);
        rbGradientRadial = findViewById(R.id.rbGradientRadial);

        bgStartColor = findViewById(R.id.bgStartColor);
        bgStartColor.setOnClickListener(this);
        bgStartColor.setBackgroundColor(Color.parseColor("#57b7e9"));

        bgEndColor = findViewById(R.id.bgEndColor);
        bgEndColor.setBackgroundColor(Color.parseColor("#E79742"));
        bgEndColor.setOnClickListener(this);


        testSpan.setGradientStartColor(Color.parseColor("#57b7e9"));
        testSpan.setGradientEndColor(Color.parseColor("#E79742"));
    }

    public void initData() {
        testSpan.setBgColor(Color.GREEN).setBorderColor(Color.BLUE).setTextColor(Color.RED);
        borderColor.setBackgroundColor(Color.BLUE);
        bgColor.setBackgroundColor(Color.GREEN);
        textColor.setBackgroundColor(Color.RED);

        reset(testSpan);
    }

    private void reset(MyBgSpan span) {
//        span.setTextColor(Color.GRAY);
        SpannableStringBuilder build1 = SpanBuild.get("测试")
//                .setTextSize(48,false)
                .append("\n")
                .append("加点距离")
                .append("效果区域")
//                .setTextColor(Color.RED)
                .setTextSize(11, true)
                .setSpan(span)
//                .setTextColor(Color.BLUE)
                .append("结束")
//                .setTextSize(65,false)
                .build();
        Log.i("=====","=====color:blue"+Color.BLUE);
        Log.i("=====","=====color:RED"+Color.RED);
        tvTestSpan.setText(build1);
    }

    @Override
    public void onClick(View v) {
        showSelectColorDialog(v.getId());
    }

    private void showSelectColorDialog(int id) {
        SelectColorDialog dialog = new SelectColorDialog(this);
        dialog.setListener(new SelectColorListener() {
            @Override
            public void selectColor(int i) {
                switch (id) {
                    case R.id.borderColor:
                        borderColor.setBackgroundColor(i);
                        testSpan.setBorderColor(i);
                        break;
                    case R.id.bgColor:
                        bgColor.setBackgroundColor(i);
                        testSpan.setBgColor(i);
                        break;
                    case R.id.textColor:
                        textColor.setBackgroundColor(i);
                        testSpan.setTextColor(i);
                        break;
                    case R.id.bgStartColor:
                        bgStartColor.setBackgroundColor(i);
                        testSpan.setGradientStartColor(i);
                        break;
                    case R.id.bgEndColor:
                        bgEndColor.setBackgroundColor(i);
                        testSpan.setGradientEndColor(i);
                        break;
                }
                reset(testSpan);
            }
        });
        dialog.show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sbMarginLeft:
                testSpan.setMarginLeft(progress);
                reset(testSpan);
                break;
            case R.id.sbMarginTop:
                testSpan.setMarginTop(progress);
                reset(testSpan);
                break;
            case R.id.sbMarginRight:
                testSpan.setMarginRight(progress);
                reset(testSpan);
                break;
            case R.id.sbMarginBottom:
                testSpan.setMarginBottom(progress);
                reset(testSpan);
                break;
            case R.id.sbPaddingLeft:
                testSpan.setPaddingLeft(progress);
                reset(testSpan);
                break;
            case R.id.sbPaddingRight:
                testSpan.setPaddingRight(progress);
                reset(testSpan);
                break;
            case R.id.sbRadiusLeftTop:
                testSpan.setRadiusLeftTop(progress);
                reset(testSpan);
                break;
            case R.id.sbRadiusLeftBottom:
                testSpan.setRadiusLeftBottom(progress);
                reset(testSpan);
                break;
            case R.id.sbRadiusRightTop:
                testSpan.setRadiusRightTop(progress);
                reset(testSpan);
                break;
            case R.id.sbRadiusRightBottom:
                testSpan.setRadiusRightBottom(progress);
                reset(testSpan);
                break;
            case R.id.sbBorderWidth:
                testSpan.setBorderWidth(progress);
                reset(testSpan);
                break;
            case R.id.sbBorderDashGap:
                testSpan.setBorderDashGap(progress);
                reset(testSpan);
                break;
            case R.id.sbBorderDashLength:
                testSpan.setBorderDashLength(progress);
                reset(testSpan);
                break;
            case R.id.sbGradientLinear:
                testSpan.setGradientAngle(progress);
                reset(testSpan);
                break;
            case R.id.sbGradientOffset:
                testSpan.setGradientCenterX(progress-sbGradientOffset.getMax()/2);
                reset(testSpan);
                break;
            case R.id.sbGradientOffsetY:
                testSpan.setGradientCenterY(progress-sbGradientOffsetY.getMax()/2);
                reset(testSpan);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }



}
