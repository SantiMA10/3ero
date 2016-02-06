package com.simple.modelos;

import android.content.Context;

/**
 * Created by santiagomartin on 8/10/15.
 */
public abstract class ModeloMovimiento extends Modelo implements ModeloInterface{

    public ModeloMovimiento(Context context, double x, double y) {
        super(context, x, y);
    }

}
