package com.lumivote.lumivote.ui.candidate_tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lumivote.lumivote.R;

public class Circle extends View {

    private static final int START_ANGLE_POINT = 90;

    private final Paint paint;
    private final RectF rect;

    private float angle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = getResources().getInteger(R.integer.candidate_outline_stroke_width);
        final int halfStrokeWidth = strokeWidth / 2;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(getResources().getColor(R.color.blue));

        float diameter = getResources().getDimension(R.dimen.candidate_picture_diameter);
        rect = new RectF(halfStrokeWidth, halfStrokeWidth, diameter - halfStrokeWidth, diameter - halfStrokeWidth);

        //Initial Angle
        angle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}