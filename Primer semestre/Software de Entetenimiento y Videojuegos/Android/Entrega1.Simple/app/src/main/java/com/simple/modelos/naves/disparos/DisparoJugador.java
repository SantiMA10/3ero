package com.simple.modelos.naves.disparos;

import android.content.Context;
import com.simple.modelos.ModeloMovimiento;

/**
 * Created by santiagomartin on 8/10/15.
 */
public abstract class DisparoJugador extends ModeloMovimiento{

    protected int daño = 1;
    protected double aceleracioY;

    public DisparoJugador(Context context, double x, double y) {
        super(context, x, y);
    }

    public int getDaño() {
        return daño;
    }

    public void mover() {
        y = y + aceleracioY;
    }

}
