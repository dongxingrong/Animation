package com.dongxingrong.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class CurvesActivity extends AppCompatActivity {

    private CurvesView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curves);

        view = (CurvesView) findViewById(R.id.curves);
        view.setAnimation(setAnimation());
    }

    private Animation setAnimation() {
        Animation animation = new ScaleAnimation(1, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 1);
        animation.setDuration(1000);
        return animation;
    }
}
