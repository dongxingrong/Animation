package com.dongxingrong.animation.propertyanimation;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.dongxingrong.animation.R;

/**
 * Created by cupcake on 16-1-4.
 */
public class DrawView extends View {

    /**地平线两侧间距*/
    private float sidePadding = 50;
    /**地平线到顶部的距离*/
    private float horizonToTop = 320;
    /**圆弧与直线的交点 x 方向的距离*/
    private float distance = 100;
    /**最大半角*/
    private double finalHalfCentralAngle = 60;
    /**圆弧颜色*/
    private int arcColor = Color.WHITE;
    /**圆弧线宽度*/
    private float arcStrokeWidth = 2;
    /**地平线颜色*/
    private int horizonColor = Color.WHITE;
    /**地平线宽度*/
    private float horizonStrokeWidth = 1;
    /**太阳外圈颜色*/
    private int sunStrokeColor = Color.WHITE;
    /**太阳外圈半径*/
    private float sunStrokeR = 20;
    /**太阳颜色*/
    private int sunFillColor = Color.WHITE;
    /**太阳半径*/
    private int sunR = 10;
    /**太阳划过区域的颜色*/
    private int riseBackgroundColor = 0;
    private int textColor = Color.WHITE;
    private float textSize = 20;
    private String dawnTime = "";
    private String duskTime = "";
    private String defaultDawnTime = "06:00";
    private String defaultDuskTime = "18:00";
    private long animationduration = 1000;
    private long animationDelay = 500;

    /**当前半角*/
    private double currentHalfCentralAngle = 0;

    private double halfLine;
    private float fraction = 0;

    /**圆弧中点*/
    private double centralX = 0;
    private double centralY = 0;
    /**圆弧半径*/
    private double r = 0;
    private float sunCentralX = 0;
    private float sunCentralY = 0;
    private Paint paint = new Paint();

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawView);
            finalHalfCentralAngle = array.getFloat(R.styleable.DrawView_finalHalfCentralAngle, 60);
            arcColor = array.getColor(R.styleable.DrawView_arcColor, Color.WHITE);
            arcStrokeWidth = array.getFloat(R.styleable.DrawView_arcStrokeWidth, 2f);
            horizonColor = array.getColor(R.styleable.DrawView_horizonColor, Color.WHITE);
            horizonStrokeWidth = array.getFloat(R.styleable.DrawView_horizonStrokeWidth, 1);
            riseBackgroundColor = array.getColor(R.styleable.DrawView_riseBackgroundColor,
                    getResources().getColor(R.color.translucent_white));
            sunStrokeColor = array.getColor(R.styleable.DrawView_sunStrokeColor, Color.WHITE);
            sunFillColor = array.getColor(R.styleable.DrawView_sunFillColor, Color.WHITE);
            textColor = array.getColor(R.styleable.DrawView_textColor, Color.WHITE);
            textSize = array.getDimension(R.styleable.DrawView_textSize, 20);
            dawnTime = array.getNonResourceString(R.styleable.DrawView_dawnTime);
            if (TextUtils.isEmpty(dawnTime)) {
                dawnTime = defaultDawnTime;
            }
            duskTime = array.getNonResourceString(R.styleable.DrawView_duskTime);
            if (TextUtils.isEmpty(duskTime)) {
                duskTime = defaultDuskTime;
            }

            animationduration = (long)array.getFloat(R.styleable.DrawView_animationDuration, 1000);
            animationDelay = (long) array.getFloat(R.styleable.DrawView_animationDelay, 500);

            array.recycle();

        } else {
            riseBackgroundColor = getResources().getColor(R.color.translucent_white);
            dawnTime = defaultDawnTime;
            duskTime = defaultDuskTime;
        }

        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                setFraction(fraction);
                return null;
            }
        }, 1, finalHalfCentralAngle);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });

        animator.setDuration(animationduration);
        animator.setStartDelay(animationDelay);
        animator.setInterpolator(new LinearInterpolator());
        animator.setTarget(this);
        animator.start();

        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
    }

    /**
     * 通过代码构建view的时候调用
     * @param context
     */
    public DrawView(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * 通过xml文件创建
     * @param context
     * @param attrs
     */
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 通过xml创建，并加载默认风格
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = this.getLayoutParams();
        params.height = (int)(horizonToTop + 20 + paint.getTextSize());
        this.setLayoutParams(params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        halfLine = (double)((getWidth() - distance * 2) / 2);
        currentHalfCentralAngle =  finalHalfCentralAngle * fraction;

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        drawHorizon(canvas, paint);
        drawText(canvas, paint);
        drawArc(canvas, paint);
        drawSun(canvas, paint);
        drawBackground(canvas, paint);
    }


    private void drawHorizon(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(horizonStrokeWidth);
        canvas.drawLine(sidePadding, horizonToTop, getWidth() - sidePadding, horizonToTop, paint);
    }

    private void drawText(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(textSize);
        canvas.drawText(dawnTime, sidePadding, horizonToTop + paint.getTextSize(), paint);
        canvas.drawText(duskTime, getWidth() - paint.measureText(duskTime) - sidePadding,
                horizonToTop + paint.getTextSize(), paint);
    }

    private void drawArc(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(arcColor);
        paint.setStrokeWidth(arcStrokeWidth);

        r = halfLine / Math.sin(currentHalfCentralAngle * Math.PI / 180);
        centralX = distance + halfLine;
        centralY = horizonToTop + Math.sqrt(r * r - halfLine * halfLine);
        RectF oval = new RectF((float)(centralX - r),  (float)(centralY- r),
                (float)(centralX + r), (float)(centralY + r));
        canvas.drawArc(oval, (float) (-90 - currentHalfCentralAngle), (float) (2 *
                currentHalfCentralAngle), false, paint);

    }

    private void drawSun(Canvas canvas, Paint paint) {
        sunCentralX = (distance + (getWidth() - distance * 2) * fraction);
        sunCentralY = (float)(centralY - Math.sqrt(r * r - (sunCentralX - centralX) * (sunCentralX - centralX)));
        if (Float.isNaN(sunCentralY)) { // 初始位置
            sunCentralY = horizonToTop;
        }
        float height = horizonToTop - sunCentralY;

        drawSun(canvas, paint, Paint.Style.STROKE, sunStrokeColor, sunStrokeR, height);
        drawSun(canvas, paint, Paint.Style.FILL, sunFillColor, sunR, height);
    }

    private void drawSun(Canvas canvas, Paint paint, Paint.Style style, int color, float R, float height) {
        paint.setStyle(style);
        paint.setColor(color);
        RectF tem = new RectF(sunCentralX - R, sunCentralY - R, sunCentralX + R, sunCentralY + R);
        if (height < R && height > 0) {
            float angle = (float)Math.asin(height / R);
            float degrees = (float)Math.toDegrees(angle);
            canvas.drawArc(tem, -180 - degrees, 180 + 2 * degrees, false, paint);
        } else if (height <= 0) {
            canvas.drawArc(tem, -180, 180, false, paint);
        }else {
            canvas.drawArc(tem, -270, 360, false, paint);
        }

    }

    private void drawBackground(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(riseBackgroundColor);

        Path backgroundPath = new Path();
        backgroundPath.moveTo(distance, horizonToTop);

        if (sunCentralX > 101) {
            for (int i = 101; i < sunCentralX; i++) {
                float tempY = (float)(centralY - Math.sqrt(r * r - (i - centralX) * (i - centralX)));
                backgroundPath.lineTo((float)i, tempY);
            }
        }
        backgroundPath.lineTo(sunCentralX, horizonToTop);
        backgroundPath.close();
        canvas.drawPath(backgroundPath, paint);
    }
}
