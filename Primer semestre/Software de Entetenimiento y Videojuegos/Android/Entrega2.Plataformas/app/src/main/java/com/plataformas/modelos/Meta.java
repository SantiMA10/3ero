package com.plataformas.modelos;

import android.content.Context;
import android.graphics.Canvas;

import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;

/**
 * Created by UO237040 on 13/10/2015.
 */
public class Meta extends Modelo {
    public Meta(Context context,double x, double y) {
        super(context, x, y, 32,40);
        this.y = y - altura/2;;
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.exit);
    }
    public void dibujar(Canvas canvas){
        int yArriva = (int) y - altura / 2;
        int xIzquierda = (int) x - ancho / 2 - Nivel.scrollEjeX;
        imagen.setBounds(xIzquierda, yArriva, xIzquierda
                + ancho, yArriva + altura);
        imagen.draw(canvas);
    }
}
