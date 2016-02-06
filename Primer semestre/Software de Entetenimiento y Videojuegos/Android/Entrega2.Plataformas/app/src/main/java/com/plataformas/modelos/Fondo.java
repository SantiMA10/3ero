package com.plataformas.modelos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.plataformas.GameView;


public class Fondo extends Modelo{

    Bitmap fondo;
    Bitmap fondoAux;
    float velocidadX;

    public Fondo(Context context, Bitmap imagen, float velocidadX) {
        super(context,
                GameView.pantallaAncho/2,
                GameView.pantallaAlto/2,
                GameView.pantallaAlto,
                GameView.pantallaAncho );
        this.fondo = imagen;
        if (velocidadX > 0) {
            fondoAux = imagen;
        }
        if ( ancho%2f != 0f){
            ancho++;
        }
        this.velocidadX = velocidadX;
    }
    public void mover(double movimientoScroll){
        if( velocidadX > 0) {
            if (movimientoScroll > 0.1) {
                x -= velocidadX;
            } else if (movimientoScroll < -0.1) {
                x += velocidadX;
            }
            if (x > ancho / 2) {
                x = -ancho / 2;
            }
            if (x < -ancho / 2) {
                x = ancho / 2;
            }
        }
    }
    public void dibujar(Canvas canvas) {
        int xIzquierda = (int) x - ancho / 2;
        Rect orgigen = new Rect(0,0 ,
                fondo.getWidth(),fondo.getHeight());
        Rect destino = new Rect((int) (x - ancho / 2),
                (int) (y - altura / 2),
                (int) (x + ancho / 2),
                (int) (y + altura / 2));
        canvas.drawBitmap(fondo,orgigen,destino,null);
        if (velocidadX > 0){
            // colocar detras
            if (xIzquierda > 0) {
                destino = new Rect((int) (x - ancho / 2) - ancho,
                        (int) (y - altura / 2),
                        (int) (x + ancho / 2 - ancho),
                        (int) (y + altura / 2));
                canvas.drawBitmap(fondoAux, orgigen, destino, null);
            }
            // colocar delante
            if (xIzquierda < 0) {
                destino = new Rect((int) (x - ancho / 2) + ancho,
                        (int) (y - altura / 2),
                        (int) (x + ancho / 2 + ancho),
                        (int) (y + altura / 2));
                canvas.drawBitmap(fondoAux, orgigen, destino, null);
            }
        }
    }
}

