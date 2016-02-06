package com.flee.modelo.enemigo;

import android.content.Context;

import com.flee.modelo.Modelo;
import com.flee.modelo.Tile;

/**
 * Created by santiagomartin on 6/12/15.
 */
public abstract class Enemigo extends Modelo {
    public double velocidadX = Tile.ancho;

    public Enemigo(Context context, double x, double y, int altura, int ancho) {
        super(context, x, y, altura, ancho);

        this.y = y - this.altura/2;
    }

    public void girar(){
        velocidadX *= -1;
        girarImagen();
    }

    @Override
    public void actualizar(long tiempo) {

    }

    protected abstract void girarImagen();
}
