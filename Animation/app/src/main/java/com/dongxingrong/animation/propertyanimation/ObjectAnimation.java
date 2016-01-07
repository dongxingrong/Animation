package com.dongxingrong.animation.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.TextView;
import android.widget.Toast;

import com.dongxingrong.animation.R;

public class ObjectAnimation extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);

        tvTest = (TextView) findViewById(R.id.test_objectAnimator);

        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator
                        (ObjectAnimation.this, R.animator.object_animator);
                set.setTarget(tvTest);
                set.start();

            }
        });

    }
}
