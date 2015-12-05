package com.dongxingrong.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * 模拟百分比进度条
 */
public class ScaleAnimationActivity extends AppCompatActivity {

    private View bg;
    private View transparentBg;
    private View red;

    private int redWidth = 150;
    private int transparentBgWidth = 300;

    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_animation);

        bg = (View) findViewById(R.id.person_progress_one);
        transparentBg = (View) findViewById(R.id.person_progress_two);
        red = (View) findViewById(R.id.person_progress);

        ViewGroup.LayoutParams transparentBgParams = transparentBg.getLayoutParams();
        transparentBgParams.width = transparentBgWidth;
        ViewGroup.LayoutParams redParams = red.getLayoutParams();
        redParams.width = redWidth;

        transparentBg.setLayoutParams(transparentBgParams);
        red.setLayoutParams(redParams);

        animation = ScaleAnimation();

        transparentBg.setAnimation(animation);
        red.setAnimation(animation);
    }

    private Animation ScaleAnimation() {
        Animation animation = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
        animation.setDuration(1000);
        return animation;
    }
}
