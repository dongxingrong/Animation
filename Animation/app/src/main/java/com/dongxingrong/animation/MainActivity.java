package com.dongxingrong.animation;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dongxingrong.animation.propertyanimation.BezierCurveActivity;
import com.dongxingrong.animation.propertyanimation.ObjectAnimation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scaleAnimation;
    private Button objectAnimator;
    private Button bezierCurve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scaleAnimation = (Button) findViewById(R.id.scaleAnimation);
        scaleAnimation.setOnClickListener(this);

        objectAnimator = (Button) findViewById(R.id.objectAnimator);
        objectAnimator.setOnClickListener(this);

        bezierCurve = (Button) findViewById(R.id.bezierCurve);
        bezierCurve.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.scaleAnimation:
               startActivity(new Intent(MainActivity.this, ScaleAnimationActivity.class));
               break;
           case R.id.objectAnimator:
               startActivity(new Intent(MainActivity.this, ObjectAnimation.class));
               break;
           case R.id.bezierCurve:
               startActivity(new Intent(MainActivity.this, BezierCurveActivity.class));
               break;
       }
    }
}
