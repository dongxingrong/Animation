package com.dongxingrong.animation;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scaleAnimation;
    private Button curvesAnimation;
    private Button objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scaleAnimation = (Button) findViewById(R.id.scaleAnimation);
        scaleAnimation.setOnClickListener(this);

        curvesAnimation = (Button) findViewById(R.id.curvesAnimation);
        curvesAnimation.setOnClickListener(this);

        objectAnimator = (Button) findViewById(R.id.objectAnimator);
        objectAnimator.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.scaleAnimation:
               startActivity(new Intent(MainActivity.this, ScaleAnimationActivity.class));
               break;
           case R.id.curvesAnimation:
               startActivity(new Intent(MainActivity.this, CurvesActivity.class));
               break;
           case R.id.objectAnimator:
               startActivity(new Intent(MainActivity.this, ObjectAnimatorActivity.class));
               break;
       }
    }
}
