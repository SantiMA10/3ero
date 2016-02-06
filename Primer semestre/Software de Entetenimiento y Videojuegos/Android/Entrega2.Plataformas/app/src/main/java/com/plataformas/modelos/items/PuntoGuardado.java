package com.plataformas.modelos.items;

import android.content.Context;

import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.graficos.Sprite;
import com.plataformas.modelos.Nivel;

/**
 * Created by UO237040 on 20/10/2015.
 */
public class PuntoGuardado extends Item {

    public PuntoGuardado(Context context, double x, double y) {
        super(context, x , y, 30, 50);

        this.x = x;
        this.y = (y - altura/2) + 10;
        sprite = new Sprite(CargadorGraficos.cargarDrawable(context, R.drawable.guardado),ancho, altura, 1, 1, true);
    }

    @Override
    public void accion(Nivel nivel) {
        nivel.jugador.guardarPosicion();
    }
}
