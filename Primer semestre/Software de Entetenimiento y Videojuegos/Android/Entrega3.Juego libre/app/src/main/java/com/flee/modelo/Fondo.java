package com.flee.modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.flee.GameView;

public class Fondo extends Modelo{

    Bitmap fondo;

    public Fondo(Context context, Bitmap imagen) {
        super(context,
                GameView.pantallaAncho/2,
                GameView.pantallaAlto/2,
                GameView.pantallaAlto,
                GameView.pantallaAncho );
        this.fondo = imagen;
    }

    public void dibujar(Canvas canvas) {
        Rect orgigen = new Rect(0,0 ,
                fondo.getWidth(),fondo.getHeight());
        Rect destino = new Rect((int) (x - ancho / 2),
                (int) (y - altura / 2),
                (int) (x + ancho / 2),
                (int) (y + altura / 2));
        canvas.drawBitmap(fondo,orgigen,destino,null);
    }

    @Override
    public void actualizar(long tiempo) {

    }
}
