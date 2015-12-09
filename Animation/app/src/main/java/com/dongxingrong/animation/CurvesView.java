package com.dongxingrong.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by HP1 on 2015/12/9.
 */
public class CurvesView extends View {
    private int width = 0;
    private int height = 0;
    public CurvesView(Context context) {
        super(context);
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        width = manager.getDefaultDisplay().getWidth();
        height = manager.getDefaultDisplay().getHeight();
    }

    public CurvesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        width = manager.getDefaultDisplay().getWidth();
        height = manager.getDefaultDisplay().getHeight();
    }

    public CurvesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        width = manager.getDefaultDisplay().getWidth();
        height = manager.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.BLACK);

        canvas.drawLine(100, 210, width - 100, 210, p);
        p.setStyle(Paint.Style.FILL_AND_STROKE);//设置空心
        RectF oval1=new RectF(150,20, width - 150,400);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
//        oval1.set(190, 20, 220, 40);
//        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
//        oval1.set(160, 30, 210, 60);
//        canvas.drawArc(oval1, 0, 180, false, p);
    }
}
