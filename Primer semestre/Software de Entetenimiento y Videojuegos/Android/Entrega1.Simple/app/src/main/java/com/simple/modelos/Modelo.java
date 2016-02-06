package com.simple.modelos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.simple.utilidades.Ar;

public abstract class Modelo implements ModeloInterface{

    // Propiedades del canvas
    public Context context;
    protected double mCanvasAltura;
    protected double mCanvasAncho;

    // Propiedades del Modelo
    protected double x;
    protected double y;
    protected int altura;
    protected int ancho;
    protected Drawable imagen;

    public Modelo(Context context, double x, double y){
        this.context = context;
        this.x = x;
        this.y = y;
        this.mCanvasAltura = Ar.pantallaAltura;
        this.mCanvasAncho = Ar.pantallaAncho;
    }

    public void dibujarEnPantalla(Canvas canvas){
        int yArriba = (int) y - altura / 2;
        int xIzquierda = (int) x - ancho / 2;
        imagen.setBounds(xIzquierda, yArriba, xIzquierda
                + ancho, yArriba + altura);
        imagen.draw(canvas);
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public boolean colisiona (ModeloInterface modelo){
        boolean colisiona = false;
        if (modelo.getX() - modelo.getAncho() / 2 <= (x + ancho / 2)
                && (modelo.getX() + modelo.getAncho() / 2) >= (x - ancho / 2)
                && (y + altura / 2) >= (modelo.getY() - modelo.getAltura() / 2)
                && (y - altura / 2) < (modelo.getY() + modelo.getAltura() / 2)) {
            colisiona = true;
        }
        return colisiona;
    }

    public int estaEnPantalla(){

        if( y + altura/2 < 0 ){
            return 0;
        }
        else if(y + altura/2 >= 0 && y - altura/2 < mCanvasAltura){
            return 1;
        }
        return -1;

    }

}
