package com.simple.modelos.controles;

import android.content.Context;

import com.simple.R;
import com.simple.modelos.Modelo;
import com.simple.utilidades.Ar;

public class BotonDisparar extends Modelo {

    public BotonDisparar(Context context, double x, double y) {
        super(context, x, y);
        altura = Ar.altura(100);
        ancho = Ar.ancho(100);
        imagen=context.getResources().getDrawable(R.drawable.boton_disparar);
    }

    public boolean estaPulsado(float clickX, float clickY) {
        boolean estaPulsado = false;
        if (clickX <= (x + ancho / 2) &&
                clickX >= (x - ancho / 2) &&
                clickY <= (y + altura / 2) &&
                clickY >= (y - altura / 2)) {

            estaPulsado = true;
        }

        return estaPulsado;
    }

    public int getOrientacionX(float clickX){

        return (int) (x-clickX);

    }

}
