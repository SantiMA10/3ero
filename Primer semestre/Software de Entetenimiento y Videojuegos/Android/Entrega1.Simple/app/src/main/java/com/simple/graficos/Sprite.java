package com.simple.graficos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by UO237040 on 22/09/2015.
 */
public class Sprite {

    // Fichero con los frames.
    private Bitmap bitmap;
    // El rectangulo sobre el que se pinta el dibujo
    private Rect rectanguloDibujo;
    // Número total de frames en el bitmap.
    private int framesTotales;
    // El frame que se esta pintando actualmente
    private int frameActual;
    // El tiempo que ha pasado desde que se ha cambiado de frame
    private long tiempoUltimaActualizacion;
    // Milisegundos, tiempo entre frames (1000/fps)
    private int interavaloEntreFrames;

    // Medidas reales del Bitmap del sprite, el .png.
    private int spriteAncho;
    private int spriteAltura;

    // Medidas en pixeles del modelo que se representara en la pantalla del dispositivo
    private int modeloAncho;
    private int modeloAltura;

    //Indica si el sprite se ejecuta en forma de bucle
    private boolean bucle;

    public Sprite(Bitmap bitmap, int modeloAncho, int modeloAltura, int fps, int framesTotales, boolean bucle) {

        this.bitmap = bitmap;
        this.modeloAncho = modeloAncho;
        this.modeloAltura = modeloAltura;
        this.framesTotales = framesTotales;
        this.bucle = bucle;

        frameActual = 0;
        spriteAncho = bitmap.getWidth() / framesTotales;
        spriteAltura = bitmap.getHeight();
        rectanguloDibujo = new Rect(0, 0, spriteAncho, spriteAltura);
        interavaloEntreFrames = 1000 / fps;
        tiempoUltimaActualizacion = 0l;

    }

    public boolean actualizar (long tiempo) {

        boolean finSprite = false;

        if (tiempo > tiempoUltimaActualizacion + interavaloEntreFrames) {
            tiempoUltimaActualizacion = tiempo;
            // actualizar el frame
            frameActual++;
            if (frameActual >= framesTotales) {
                if(bucle){
                    frameActual = 0;
                }
                else{
                    finSprite = true;
                    frameActual = framesTotales;
                }
            }
        }
        // definir el rectangulo
        this.rectanguloDibujo.left = frameActual * spriteAncho;
        this.rectanguloDibujo.right = this.rectanguloDibujo.left + spriteAncho;

        return finSprite;

    }

    public void dibujarSprite (Canvas canvas, int x, int y) {

        Rect destRect = new Rect(x - modeloAncho/2, y - modeloAltura/2, x
                + modeloAncho/2, y + modeloAltura/2);
        canvas.drawBitmap(bitmap, rectanguloDibujo, destRect, null);

    }

    public void setFrameActual(int frameActual) {
        this.frameActual = frameActual;
    }

}
