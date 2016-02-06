package com.flee.modelo.obstaculo;

import android.content.Context;

import com.flee.modelo.Modelo;

public abstract class Obstaculo extends Modelo {

    public Obstaculo(Context context, double x, double y, int altura, int ancho) {
        super(context, x, y, altura, ancho);

        this.y = y - this.altura/2;
    }

    @Override
    public void actualizar(long tiempo) {}
}
