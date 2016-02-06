package com.simple.modelos.items;

import android.content.Context;

import com.simple.R;
import com.simple.gestores.CargadorGraficos;
import com.simple.utilidades.Ar;

/**
 * Created by santiagomartin on 10/10/15.
 */
public class ItemMonedaBronze extends ItemAbstractMoneda {

    public ItemMonedaBronze(Context context, double x, double y) {

        super(context, x, y);
        altura = Ar.altura(25);
        ancho = Ar.ancho(25);
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.coin_bronze);
        valor = 5;

    }

}
