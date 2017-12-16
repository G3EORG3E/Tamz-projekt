package com.example.g3org3.horoscopes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by G3ORG3 on 16.12.2017.
 */

public class GraphView extends View {
    ArrayList<Sunsign> allHoroscopes;

    public GraphView(Context context, ArrayList<Sunsign> allHoroscopes) {
        super(context);
        this.allHoroscopes = allHoroscopes;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // settings
        int fontSize = 40;
        int screenOffset = 35;
        int barHeight = 20;

        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(fontSize);

        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.rgb(255,64,129));

        Paint rectPaintBack = new Paint();
        rectPaintBack.setColor(Color.LTGRAY);

        int maxWidth = canvas.getWidth() - screenOffset;

        for(int i = 0; i < allHoroscopes.size(); i++) {
            int precetnage = Integer.parseInt(allHoroscopes.get(i).getIntensity().replace("%",""));
            int precentageWidht = (precetnage*maxWidth/100);

            int top = fontSize + barHeight + 60;
            int newI = i+1;
            String graphText =  allHoroscopes.get(i).sunsign.toUpperCase() + " (" + allHoroscopes.get(i).intensity+")";

            canvas.drawText(graphText, screenOffset, newI*top - 10, mTextPaint);
            canvas.drawRect(screenOffset, newI*top, maxWidth, (newI*top)+barHeight, rectPaintBack );
            canvas.drawRect(screenOffset, newI*top, precentageWidht, (newI*top)+barHeight, rectPaint );
        }

    }
}
