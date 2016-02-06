package com.plataformas.modelos.controles;

import android.content.Context;

import com.plataformas.GameView;
import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.modelos.Modelo;

/**
 * Created by UO237040 on 13/10/2015.
 */
public class BotonDisparar  extends Modelo {
    public BotonDisparar(Context context) {
        super(context, GameView.pantallaAncho*0.85 , GameView.pantallaAlto*0.6,
                70,70);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.buttonfire);
    }
    public boolean estaPulsado(float clickX, float clickY) {
        boolean estaPulsado = false;
        if (clickX <= (x + ancho / 2) && clickX >= (x - ancho / 2)
                && clickY <= (y + altura / 2) && clickY >= (y - altura / 2)) {
            estaPulsado = true;
        }
        return estaPulsado;
    }
}

