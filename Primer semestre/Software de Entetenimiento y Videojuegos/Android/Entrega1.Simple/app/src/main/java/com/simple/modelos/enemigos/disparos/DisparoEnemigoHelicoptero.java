package com.simple.modelos.enemigos.disparos;

import android.content.Context;

import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.Modelo;
import com.simple.utilidades.Ar;

/**
 * Created by UO237040 on 29/09/2015.
 */
public class DisparoEnemigoHelicoptero extends  DisparoEnemigo {

    public DisparoEnemigoHelicoptero(Context context, double x, double y) {

        super(context, x, y);
        altura= Ar.altura(20);
        ancho= Ar.ancho(20);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.disparo_jefe_1);
        aceleracioY = Ar.y(6);

    }

}
