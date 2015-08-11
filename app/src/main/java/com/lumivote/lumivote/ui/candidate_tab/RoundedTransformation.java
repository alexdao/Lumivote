package com.lumivote.lumivote.ui.candidate_tab;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by alex on 8/11/15.
 */
public class RoundedTransformation implements com.squareup.picasso.Transformation {
    private final int radius;

    // radius is corner radii in dp
    // margin is the board in dp
    public RoundedTransformation(final int radius) {
        this.radius = radius;
    }

    @Override
    public Bitmap transform(final Bitmap source) {

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawCircle(source.getWidth()/2f, source.getHeight()/2f, radius, paint);

        if (source != output) {
            source.recycle();
        }

        return output;
    }

    @Override
    public String key() {
        return "rounded";
    }
}