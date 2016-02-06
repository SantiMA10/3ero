package com.flee.modelo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Marcador extends Modelo{

    public int puntos;

    public Marcador(Context context, double x, double y) {
        super(context, x, y, 10, 10);
        puntos = 0;
    }

    @Override
    public void dibujar(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        canvas.drawText("Puntuacion: " + puntos, (int) x, (int) y, paint);
    }

    @Override
    public void actualizar(long tiempo) {

    }
}
