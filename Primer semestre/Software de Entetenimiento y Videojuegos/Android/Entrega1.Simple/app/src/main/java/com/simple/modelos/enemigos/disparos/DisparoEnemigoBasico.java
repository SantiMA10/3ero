package com.simple.modelos.enemigos.disparos;

import android.content.Context;
import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.modelos.naves.disparos.DisparoNave;
import com.simple.utilidades.Ar;

/**
 * Created by UO237040 on 22/09/2015.
 */
public class DisparoEnemigoBasico extends DisparoEnemigo {

    public DisparoEnemigoBasico(Context context, double x, double y) {
        super(context, x, y);

        altura = Ar.altura(20);
        ancho = Ar.ancho(20);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.disparo_enemigo);
        aceleracioY = Ar.y(6);

    }

}
