package com.flee.modelo.obstaculo;

import android.content.Context;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;
import com.flee.modelo.Tile;

/**
 * Created by santiagomartin on 7/12/15.
 */
public class Agua extends Obstaculo {

    public Agua(Context context, double x, double y) {
        super(context, x, y, Tile.altura, Tile.ancho);

        this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.agua, true);
    }

}
