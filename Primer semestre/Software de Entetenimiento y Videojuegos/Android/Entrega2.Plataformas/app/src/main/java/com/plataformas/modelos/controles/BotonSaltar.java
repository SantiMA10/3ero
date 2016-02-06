package com.plataformas.modelos.controles;

import android.content.Context;

import com.plataformas.GameView;
import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.modelos.Modelo;

/**
 * Created by UO237040 on 06/10/2015.
 */
public class BotonSaltar  extends Modelo {
    public BotonSaltar(Context context) {
        super(context, GameView.pantallaAncho*0.75 , GameView.pantallaAlto*0.8,
                GameView.pantallaAlto, GameView.pantallaAncho);
        altura = 70;
        ancho = 70;
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.buttonjump);
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