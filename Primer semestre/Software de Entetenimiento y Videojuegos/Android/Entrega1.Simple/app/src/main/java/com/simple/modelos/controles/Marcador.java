package com.simple.modelos.controles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.Modelo;
import com.simple.modelos.items.ItemAbstractMoneda;
import com.simple.utilidades.Ar;

public class Marcador extends Modelo {

    private int puntos;

    public Marcador(Context context, double x, double y) {
        super(context, x, y);
        altura = Ar.altura(20);
        ancho = Ar.ancho(20);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.icono_puntos);
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public void dibujarEnPantalla(Canvas canvas){
        int yArriva = (int)  y - altura / 2;
        int xIzquierda = (int) x - ancho / 2;

        imagen.setBounds(xIzquierda, yArriva, xIzquierda
                + ancho, yArriva + altura);
        imagen.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        canvas.drawText(String.valueOf(puntos), (int) x + ancho, (int) y + (altura/2), paint);
    }

}
