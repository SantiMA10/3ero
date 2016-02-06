package com.simple.modelos;

import android.graphics.Canvas;

/**
 * Created by santiagomartin on 8/10/15.
 */
public interface ModeloInterface {

    public void dibujarEnPantalla(Canvas canvas);
    public boolean colisiona (ModeloInterface modelo);
    public int estaEnPantalla();

    public int getAltura();
    public void setAltura(int altura);
    public double getY();
    public void setY(double y);
    public double getX();
    public void setX(double x);
    public int getAncho();
    public void setAncho(int ancho);

}
