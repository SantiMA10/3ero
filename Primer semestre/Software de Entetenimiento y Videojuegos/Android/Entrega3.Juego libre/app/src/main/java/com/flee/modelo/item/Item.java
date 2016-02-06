package com.flee.modelo.item;

import android.content.Context;

import com.flee.modelo.Modelo;
import com.flee.modelo.Nivel;

public abstract class Item extends Modelo {

    public Item(Context context, double x, double y) {
        super(context, x, y, 20, 20);

        this.y = y - this.altura/2;
    }

    public abstract void aplicar(Nivel nivel);

    @Override
    public void actualizar(long tiempo) {

    }
}
