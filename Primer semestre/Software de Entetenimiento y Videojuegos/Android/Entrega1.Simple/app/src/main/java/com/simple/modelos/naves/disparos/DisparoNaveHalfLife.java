package com.simple.modelos.naves.disparos;

import android.content.Context;

import com.simple.R;
import com.simple.utilidades.Ar;

/**
 * Created by santiagomartin on 6/10/15.
 */
public class DisparoNaveHalfLife extends DisparoJugador {

    public DisparoNaveHalfLife(Context context, double x, double y) {

        super(context, x, y);
        altura= Ar.altura(5);
        ancho= Ar.ancho(25);
        imagen=context.getResources().getDrawable(R.drawable.halflife_disparo);
        aceleracioY = Ar.y(-6);
        daño = 3;

    }

}
