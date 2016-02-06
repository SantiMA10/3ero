package com.simple.modelos.controles;

import android.content.Context;

import com.simple.R;
import com.simple.modelos.Modelo;
import com.simple.utilidades.Ar;

/**
 * Created by santiagomartin on 27/9/15.
 */
public class MensajePausa extends Modelo {

    public MensajePausa(Context context, double x, double y) {
        super(context, x, y);
        altura = Ar.altura(480);
        ancho = Ar.ancho(320);
        imagen = context.getResources().getDrawable(R.drawable.menu_pausa);
    }

    public boolean estaPulsado(float clickX, float clickY){

        boolean estaPulsado = false;

        if(clickX <= (x + ancho/2 ) && clickX >= (x - ancho / 2) && clickY <= (y + altura / 2) && clickY >= (y - altura /2)){

            estaPulsado = true;

        }

        return estaPulsado;

    }

}
