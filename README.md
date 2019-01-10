# RotaTextView
实现一个可旋转的TextView，用来实现旋转45度的右上角文本角标


# 一、需求描述
最近，公司要做国际化，而且有个界面的右上角要做成类似如下所示的样式，

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110205512942.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNDQ2MjgyNDEy,size_16,color_FFFFFF,t_70)

最开始的这个右上角角标是UI直接出图给我的，然后发现UI给了我好几个国家的角标，每个国家两张图片，分别是 xdpi分辨率下和xxdpi分辨率的。这样的话，关这个角标就得十几张小图片，oh my god，虽然每张图片也不大，才2k左右，但是加起来也有几十k啊，如果以后要支持的国家越来越多咋办？ 因此我决定还是优化一下，减少apk的体积。

# 二、看实现效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110205902265.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNDQ2MjgyNDEy,size_16,color_FFFFFF,t_70)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110205836606.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNDQ2MjgyNDEy,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019011020584577.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNDQ2MjgyNDEy,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110205851548.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNDQ2MjgyNDEy,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110211508473.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxNDQ2MjgyNDEy,size_16,color_FFFFFF,t_70)

# 三、实现过程
## 3.1 自定义属性

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="RotateTextView">
        <!--文本旋转的角度-->
        <attr name="degree" format="integer" />
    </declare-styleable>
</resources>
```

自定义一个属性【degree】，表示文本旋转的角度

## 3.2 自定义旋转的TextView

```java
package com.oyp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * 实现一个可选择的TextView，作用是：右上角的角标45°文本提示
 * </p>
 * created by OuyangPeng at 2019/1/10 下午 08:19
 *
 * @author OuyangPeng
 */
public class RotateTextView extends android.support.v7.widget.AppCompatTextView {
    /**
     * 默认选择45°角
     */
    private static final int DEFAULT_DEGREES = 45;

    /**
     * 文本旋转的角度
     */
    private int mDegrees;

    public RotateTextView(Context context) {
        this(context, null);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RotateTextView);
        mDegrees = a.getInteger(R.styleable.RotateTextView_degree, DEFAULT_DEGREES);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }

    /**
     * 改变文本选择的角度
     *
     * @param degrees 文本旋转的角度
     */
    public void setDegrees(int degrees) {
        mDegrees = degrees;
        invalidate();
    }

}

```

这个自定义的RotateTextView，继承自TextView，将TextView的文本内容旋转指定的degree角度。

## 3.3 引用自定义的RotateTextView
在下面的布局文件中，引用自定义的RotateTextView，并设置好一张默认的背景图，背景图资源如下所示：backgroud.png

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110210913274.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110210951415.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.oyp.view.RotateTextView
        android:id="@+id/rotate_textview"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_alignParentRight="true"
        android:background="@drawable/backgroud"
        android:gravity="center"
        android:paddingBottom="45dp"
        android:text="欧阳鹏"
        android:textColor="#fff"
        android:textSize="20sp"
        app:degree="45" />

    <TextView
        android:id="@+id/rotate_degrees"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/rotate_textview"
        android:gravity="center"
        android:padding="10dp" />

    <SeekBar
        android:id="@+id/sb_rotate"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/rotate_degrees"
        android:layout_marginTop="20dp"
        android:max="100"
        android:progress="30" />

    <Button
        android:id="@+id/bt_change_bg"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/sb_rotate"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"
        android:text="替换TextView背景" />

</RelativeLayout>
```

## 3.4 在Activity中控制旋转角度，以及替换背景

```java
package com.oyp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity {
    RotateTextView mText;
    TextView degrees;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (RotateTextView) findViewById(R.id.rotate_textview);
        degrees = (TextView) findViewById(R.id.rotate_degrees);
        SeekBar sbLean = (SeekBar) findViewById(R.id.sb_rotate);
        sbLean.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mText.setDegrees(progress);
                degrees.setText("倾斜度：" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        findViewById(R.id.bt_change_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.setBackgroundResource(isFirst ? R.drawable.backgroud2 : R.drawable.backgroud);
                isFirst = !isFirst;
            }
        });

    }

}

```

# 四、源代码
+ https://github.com/ouyangpeng/RotaTextView

------

![](https://img-blog.csdn.net/20150708201910089)

>作者：欧阳鹏 欢迎转载，与人分享是进步的源泉！
转载请保留原文地址：https://blog.csdn.net/ouyang_peng/article/details/86255280
☞ 本人QQ: 3024665621
☞ QQ交流群: 123133153
☞ github.com/ouyangpeng
☞ oypcz@foxmail.com
如果本文对您有所帮助，欢迎您扫码下图所示的支付宝和微信支付二维码对本文进行打赏。
--------------------- 
![](https://img-blog.csdn.net/20170413233715262?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvb3V5YW5nX3Blbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
