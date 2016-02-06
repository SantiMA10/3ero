package com.simple.modelos.enemigos.disparos;

import android.content.Context;
import com.simple.modelos.Modelo;
import com.simple.modelos.ModeloMovimiento;

/**
 * Created by UO237040 on 29/09/2015.
 */
public abstract class DisparoEnemigo extends ModeloMovimiento{

    protected int daño = 1;
    protected double aceleracioY;

    public DisparoEnemigo(Context context, double x, double y) {
        super(context, x, y);
    }

    public int getDaño() {
        return daño;
    }

    public void mover() {
        y = y + aceleracioY;
    }

}
