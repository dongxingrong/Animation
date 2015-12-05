package com.dongxingrong.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scaleAnimation = (Button) findViewById(R.id.scaleAnimation);
        scaleAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.scaleAnimation:
               startActivity(new Intent(MainActivity.this, ScaleAnimationActivity.class));
               break;
       }
    }
}
