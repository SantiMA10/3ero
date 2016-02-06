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

/**
 * Created by santiagomartin on 10/10/15.
 */
public class MarcadorMonedas extends Modelo {

    public MarcadorMonedas(Context context, double x, double y) {
        super(context, x, y);
        ItemAbstractMoneda.monedas = 0;
        altura = Ar.altura(20);
        ancho = Ar.ancho(20);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.coin_bronze);
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
        canvas.drawText(String.valueOf(ItemAbstractMoneda.monedas), (int) x + ancho, (int) y + (altura/2), paint);
    }

}
