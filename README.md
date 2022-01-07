# SpanUtilsProject

[//]: >![github](https://github.com/zhongruiAndroid/MyFastshape/blob/master/app/src/main/res/drawable/demo.png=320x640)  
<img src="https://github.com/zhongruiAndroid/SpanUtilsProject/blob/master/shots/test_a.png" alt="image"  width="auto" height="640">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/zhongruiAndroid/SpanUtilsProject/blob/master/shots/test_b.png" alt="image"  width="auto" height="640">  
  
  
## [Demo.apk下载](https://raw.githubusercontent.com/zhongruiAndroid/SpanUtilsProject/master/apk/demo.apk "apk文件")
## [jar下载](https://raw.githubusercontent.com/zhongruiAndroid/SpanUtilsProject/master/apk/spanutils.apk "jar文件")
无法下载Demo.apk时,可以去apk目录手动下载
    

```java
SpanBuild spanBuild= SpanBuild.get("例子：")
.append("粗体\n").setTextIsBold()	//设置文字为粗体

.append("斜体\n").setTextIsItalic()	//设置文字为斜体

.append("URL链接(可以点击)\n")
//(最后的build方法需要传入设置该spannable效果的TextView，否则点击无效)
.setUrl("http://www.baidu.com")		//设置点击跳转的页面地址
.setUnderLine(true)      		//显示下划线,true:显示,false:不显示,不调用方法不显示下划线
              
.append("粗斜体\n")
.setTextIsBoldItalic()			//粗体+斜体
              
.append("字体大小25px\n")
.setTextSize(25)			//设置文字大小，false:单位px,true：单位dp,默认px
              
.append("URL链接(黑色)(可以点击)\n")	
.setUnderLine()				//显示下划线
//(最后的build方法需要传入设置该spannable效果的TextView，否则点击无效)
.setUrl("http://www.baidu.com")		//设置点击跳转的页面地址
.setTextColor(Color.BLACK)		//url文字颜色

.append("字体大小15dp\n")
.setTextSize(15, true)			//设置文字大小，false:单位px,true：单位dp

.append("URL链接(红色)无下划线(可以点击)\n")
//(最后的build方法需要传入设置该spannable效果的TextView，否则点击无效)
.setUrl("http://www.baidu.com")	
.setTextColor(Color.RED)
.setUnderLine(false)

.append("字体颜色蓝色\n")
.setTextColor(Color.BLUE)

.append("背景黄色\n")
.setBgColor(Color.YELLOW)		//设置文字所在区域背景色

.append("下划线+换行效果(文字前面有点)")
.setUnderLine()
.setNewlinePoint(2,Color.GRAY,10)	//如果该文字是在换行之后的开头位置，会显示一个点
					//连续设置多个setNewlinePoint方法就显示多个点
					//第一个参数：文字与点的距离，第二个参数：点的颜色，第三个参数：点的半径
					//26还是28版本以下，设置半径不生效
.append("相对前面字体大小的0.5f\n")
.setRelativeTextSizeScale(0.5f)		//文字大小设置为前面字体大小的0.5倍

.append("中间删除线\n")
.setCenterLine()			//文字显示删除线

.append("相对前面字体大小的1.5f\n")
.setRelativeTextSizeScale(1.5f)		//文字大小设置为前面字体大小的1.5倍

.append("中间线+下划线\n")
.setCenterLine().setUnderLine()		//删除线+下划线

.append("文字偏上显示")
.setTextShowTop()			//文字偏上显示

.append("中间位置")

.append("文字偏下显示\n")
.setTextShowBottom()			//文字偏下显示

.append("换行效果(文字前面有竖线)\n")
.setQuoteColor(Color.RED)		//竖线颜色
.setQuoteGapWidth(10)			//竖线距离文字距离
.setQuoteStripeWidth(20)		//竖线宽度			
.setLineMarginCurrent(30)		//换行后的离左边的距离


.append("设置点击(可以点击)\n")
.setTextIsBold()
.setTextColor(Color.BLUE)
.setUnderLine(false)
//设置点击事件(最后的build方法需要传入设置该spannable效果的TextView，否则点击无效)
.setClick(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Toast.makeText(ApiActivity.this,"点击生效",Toast.LENGTH_SHORT).show();
      }
})
.setTextColor(Color.RED)		//某个append节点中，后设置的属性覆盖前面的属性
.setUnderLine(true)			//某个append节点中，后设置的属性覆盖前面的属性
.setTextIsItalic()			//某个append节点中，后设置的属性覆盖前面的属性

.append("BlurOuter\n")
.setBlurOuter()				//模糊效果

.append("BlurInner\n")
.setBlurInner()				//模糊效果

.append("BlurSolid\n")
.setBlurSolid()				//模糊效果

.append("X伸缩1.5f\n")
.setScaleXSize(1.5f)			//设置文字X方向拉伸倍数

.append("X伸缩0.5f\n")
.setScaleXSize(0.5f)			//设置文字X方向拉伸倍数

.append("serif字体\n")
.setTextFamily("serif")			//设置字体样式，String或者Typeface，同时设置，Typeface优先级更高

.append("原始大小图片")
.appendImage(new MyImageSpan(this,R.drawable.test))
					//添加图片(原始大小)
.append("\n50px宽度的图片")
.appendImage(new MyImageSpan(this,R.drawable.test).setWidth(50))
					//添加宽度为50px的图片，高度自适应
.append("\n250px宽度的图片且设置成红色")
.setTextAlignRight()
.appendImage(new MyImageSpan(this,R.drawable.test).setHeight(250).setColor(Color.RED))
					//添加高度为250px的图片，宽度自适应,并且将图片渲染成红色
								
.append("其他span")			
//可为当前append节点的文字设置其他系统提供的xxxSpan,比如AbsoluteSizeSpan,ClickableSpan等
//下面有提供自定义背景的MyBgSpan
.setSpan(xxxSpan)				

//如果需要点击事件或者URL(设置了并需要点击效果)，需要在build方法传入该TextView
SpannableStringBuilder ssb =spanBuild.build(tvTestSpan);

tvTestSpan.setText(ssb);
```


##### 自定义背景效果的Span
```java
MyBgSpan.java

setMargin()		//设置文字区域四周外边距
setMarginLeft()		//设置文字左外边距
setMarginTop()		//设置文字上外边距
setMarginRight()	//设置文字右外边距
setMarginBottom()	//设置文字下外边距
setPadding()		//设置文字左右内边距
setPaddingLeft()	//设置文字左内边距
setPaddingRight()	//设置文字右内边距
setRadius()		//设置文字所在背景四周圆角
setRadiusLeftTop()	//设置背景左上圆角
setRadiusLeftBottom()	//设置左下圆角
setRadiusRightTop()	//设置右上圆角
setRadiusRightBottom()	//设置右下圆角
setBorderColor()	//设置边框颜色
setBorderWidth()	//设置边框宽度
setBorderDashGap()	//设置边框虚假间隔
setBorderDashLength()	//设置边框每根虚线长度
			//setBorderWidth，setBorderDashGap,setBorderDashLength同时大于0才有效
setBgColor()		//设置背景颜色
setShowAlignBottom()	//设置文字显示在基线位置(默认显示位置)
setShowAlignTop()	//设置文字在背景区域顶部
setShowAlignCenter()	//设置文字在背景区域底部
setTextColor()			//设置文字颜色
setGradientNone()		//设置无颜色渐变效果
setGradientLinear()		//设置背景线性渐变
setGradientRadial()		//设置背景圆圈径向渐变
setGradientSweep()		//设置背景扫描渐变
setGradientAngle()		//设置线性渐变时的旋转角度
setGradientCenterXOffset()	//设置圆圈径向渐变和扫描渐变时X中心的偏移距离(负数想左偏移,正数向右)
setGradientCenterYOffset()	//设置圆圈径向渐变和扫描渐变时Y中心的偏移距离(负数想上偏移,正数向下)
setGradientStartColor()		//渐变起始颜色
setGradientCenterColor()	//渐变中间颜色(可以不设置,一般设置起始和结束即可)
setGradientEndColor()		//渐变结束颜色
setGradientRadius()		//圆圈径向渐变半径
//渐变颜色数据，可以设置多组过渡颜色
setGradientColors(int[] gradientColors)			
//渐变颜色所占位置，0~1,gradientColorPositions数量需要和setGradientColors的gradientColors颜色数量一致
setGradientColorPositions(float[] gradientColorPositions)	
```


