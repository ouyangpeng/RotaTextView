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

        mText = (RotateTextView) findViewById(R.id.rota_textview);
        degrees = (TextView) findViewById(R.id.degrees);
        SeekBar sbLean = (SeekBar) findViewById(R.id.sb_rote);
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
