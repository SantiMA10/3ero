package com.simple.modelos.controles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.simple.modelos.Modelo;

/**
 * Created by santiagomartin on 27/9/15.
 */
public class Barra extends Modelo{

    private int valorMaximo;
    private int valorActual;


    public Barra(Context context, double x, double y, int valorMaximo, int valorActual) {
        super(context, x, y);
        this.valorActual = valorActual;
        this.valorMaximo = valorMaximo;
    }

    public int getValorMaximo() {return valorMaximo; }

    public int getValorActual() {
        return valorActual;
    }

    public void setValorActual(int valorActual) {
        if(valorActual <= valorMaximo){
            this.valorActual = valorActual;
        }
        else{
            this.valorActual = this.valorMaximo;
        }
    }

    @Override
    public void dibujarEnPantalla(Canvas canvas) {

        Paint linea = new Paint();
        linea.setColor(Color.BLACK);
        linea.setStrokeWidth(30);
        canvas.drawLine((int)x, (int) y - 10, (int) mCanvasAncho, (int) y - 10, linea);
        linea.setColor(Color.RED);
        linea.setStrokeWidth(20);
        canvas.drawLine((int) x + 5, (int) y - 10, (int) mCanvasAncho - 2, (int) y - 10,
                linea);
        linea.setColor(Color.GREEN);
        linea.setStrokeWidth(20);
        canvas.drawLine((int) x + 5, (int) y - 10, (int) ((x + 5) + (( mCanvasAncho - 2) /
                (valorMaximo + 1)) * valorActual + 1), (int) y - 10, linea);

    }

}
