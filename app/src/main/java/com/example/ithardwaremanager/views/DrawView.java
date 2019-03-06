package com.example.ithardwaremanager.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    static String textToBeWritten;
    static boolean shouldDraw;

    private void init() {
        paint.setColor(Color.BLACK);
    }

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public static void setText(String text) {
        textToBeWritten = text;
    }

    public static void shouldDraw(boolean bool) {
        shouldDraw = bool;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(shouldDraw) {
            Rect size = new Rect(0,0, 100,100);
            int padding = 10;
            Rect inner = new Rect(padding,padding,size.right - padding,size.right - padding);
            Paint white = new Paint();
            white.setColor(Color.WHITE);
            canvas.drawRect(size, paint);
            canvas.drawRect(inner, white);

            String text = textToBeWritten;
            this.setTextSizeForWidth(paint, size.width() / 4, text);
            canvas.drawText(text,size.right / 2 - paint.measureText(text) / 2,
                    size.height() / 2 + paint.measureText(text) / 2 + padding
                    , paint);
        }

    }

    /**
     * Sets the text size for a Paint object so a given string of text will be a
     * given width.
     *
     * @author Michael Scheper ( https://stackoverflow.com/a/21895626/9496009 )
     * @param paint
     *            the Paint to set the text size for
     * @param desiredWidth
     *            the desired width
     * @param text
     *            the text that should be that width
     */
    private static void setTextSizeForWidth(Paint paint, float desiredWidth,
                                            String text) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        final float testTextSize = 48f;

        // Get the bounds of the text, using our testTextSize.
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();

        // Set the paint for that size.
        paint.setTextSize(desiredTextSize);
    }
}