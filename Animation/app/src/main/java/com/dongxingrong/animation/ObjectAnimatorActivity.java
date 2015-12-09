package com.dongxingrong.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class ObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image;
    private ImageView ball;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_acitivyt);

        image = (ImageView) findViewById(R.id.img);
        image.setOnClickListener(this);


        ball = (ImageView) findViewById(R.id.ball);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.img:
//        ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F).setDuration(500).start();

//        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "zhy", 1.0F, 0.0F).setDuration(500);
//        anim.start();
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                float cVal = (Float) animation.getAnimatedValue();
//                v.setAlpha(cVal);
//                v.setScaleX(cVal);
//                v.setScaleY(cVal);
//            }
//        });

                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY, pvhZ).setDuration(1000).start();
                break;
            case R.id.start:
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setDuration(1000);
                valueAnimator.setObjectValues(new PointF(0, 0));
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
                {
                    // fraction = t / duration
                    @Override
                    public PointF evaluate(float fraction, PointF startValue,
                                           PointF endValue)
                    {
                        PointF point = new PointF();
                        point.x = 200 * fraction * 3;
                        point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                        return point;
                    }
                });

                valueAnimator.start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        PointF point = (PointF) animation.getAnimatedValue();
                        ball.setX(point.x);
                        ball.setY(point.y);

                    }
                });
                break;
        }
    }
}
