package com.simple.modelos.naves.disparos;

import android.content.Context;

import com.simple.R;
import com.simple.utilidades.Ar;

public class DisparoNave extends DisparoJugador{

    public DisparoNave(Context context, double x, double y, int disparo) {

        super(context, x, y);
        altura= Ar.altura(25);
        ancho= Ar.ancho(25);
        imagen=context.getResources().getDrawable(disparo);
        aceleracioY = Ar.y(-6);
        daño = 1;

    }


}
