package com.example.administrator.slidingpanelayout;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.activity_main);

        //设置主屏的颜色一致保持不变。（默认主屏的颜色在滑动时会发生渐变）
        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);


        //设置菜单栏的可滑动的范围。（默认距离右边距32dp，现在设置为0dp）
        try {
            Field overhangSize = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            overhangSize.setAccessible(true);
            overhangSize.set(slidingPaneLayout, 0);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //添加监听事件
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.e(TAG, "slide  +   " + slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {
                Log.e(TAG, "open");
                finish();
            }

            @Override
            public void onPanelClosed(View panel) {
                Log.e(TAG, "closed");
            }
        });

    }
}
