package com.simple.modelos.controles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.simple.modelos.Modelo;

/**
 * Created by santiagomartin on 10/10/15.
 */
public class BarraEnemigo extends Modelo {

    private int valorMaximo;
    private int valorActual;
    private int altoEnemigo;
    private int anchoEnemigo;

    public BarraEnemigo(Context context, double x, double y, int valorMaximo, int valorActual, int alto, int ancho) {
        super(context, x, y);
        this.valorActual = valorActual;
        this.valorMaximo = valorMaximo;
        this.altoEnemigo = alto;
        this.anchoEnemigo = ancho;
    }

    public int getValorActual() {
        return valorActual;
    }

    public void setValorActual(int valorActual) {
        if(valorActual <= valorMaximo){
            this.valorActual = valorActual;
        }
    }

    @Override
    public void dibujarEnPantalla(Canvas canvas) {

        Paint linea = new Paint();
        linea.setColor(Color.BLACK);
        linea.setStrokeWidth(10);
        canvas.drawLine((int) x - ((valorMaximo*20)/2), (int) y - (altoEnemigo / 2), (int) x + ((valorMaximo*20)/2), (int) y - (altoEnemigo / 2), linea);
        linea.setColor(Color.RED);
        linea.setStrokeWidth(7);
        canvas.drawLine((int) x - ((valorMaximo*20)/2), (int) y - (altoEnemigo / 2), (int) x + ((valorMaximo*20)/2), (int) y - (altoEnemigo / 2), linea);
        linea.setColor(Color.GREEN);
        linea.setStrokeWidth(7);
        if(valorActual > 0)
            canvas.drawLine((int) x - ((valorActual*20)/2), (int) y - (altoEnemigo / 2), (int) x + ((valorActual*20)/2), (int) y - (altoEnemigo / 2), linea);
        else
            canvas.drawLine((int) x, (int) y - (altoEnemigo / 2), (int) x, (int) y - (altoEnemigo / 2), linea);


    }
}
