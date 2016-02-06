package com.plataformas.modelos.items;

import android.content.Context;
import android.graphics.Canvas;

import com.plataformas.graficos.Sprite;
import com.plataformas.modelos.Modelo;
import com.plataformas.modelos.Nivel;

/**
 * Created by UO237040 on 06/10/2015.
 */
public abstract class Item extends Modelo {

    protected Sprite sprite;
    protected boolean usado;

    public Item(Context context, double x, double y, int altura, int ancho) {
        super(context, x, y, altura, ancho);
    }

    public void actualizar (long tiempo) {
        sprite.actualizar(tiempo);
    }

    public void dibujar(Canvas canvas){
        sprite.dibujarSprite(canvas, (int) x - Nivel.scrollEjeX, (int) y);
    }

    public abstract void accion(Nivel nivel);
}
