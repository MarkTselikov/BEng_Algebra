package homework.hw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class Ruler extends View {

    final int SMALL_HEIGHT = 10;
    final int MEDIUM_HEIGHT = 15;
    final int BIG_HEIGHT = 20;
    final int RULER_HEIGHT = 30;

    public Ruler(Context context) {
        super(context);
    }

    public Ruler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Ruler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Ruler(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int textSize = 40;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);

        float width = getWidth();

        canvas.drawLine(0, RULER_HEIGHT, width, RULER_HEIGHT, paint);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float mmUnit = (float) (1.0f * dm.xdpi / 25.4);
        float current = 0.0f;
        int counter = 0;

        while(current < width) {

            if(counter % 10 == 0) {

                canvas.drawLine(current, RULER_HEIGHT - BIG_HEIGHT,
                        current, RULER_HEIGHT + BIG_HEIGHT, paint);

                canvas.drawText(String.valueOf(counter / 10), current,
                        RULER_HEIGHT + BIG_HEIGHT + textSize, paint);
            }
            else if(counter % 5 == 0)
                canvas.drawLine(current, RULER_HEIGHT - MEDIUM_HEIGHT,
                        current, RULER_HEIGHT + MEDIUM_HEIGHT, paint);
            else
                canvas.drawLine(current, RULER_HEIGHT - SMALL_HEIGHT,
                        current, RULER_HEIGHT + SMALL_HEIGHT, paint);

            current += mmUnit;
            counter++;
        }
    }
}
