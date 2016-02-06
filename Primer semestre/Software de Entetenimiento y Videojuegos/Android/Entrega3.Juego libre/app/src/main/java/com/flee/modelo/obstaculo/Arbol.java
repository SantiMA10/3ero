package com.flee.modelo.obstaculo;

import android.content.Context;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;

import java.util.Random;

/**
 * Created by santiagomartin on 6/12/15.
 */
public class Arbol extends Obstaculo{

    public Arbol(Context context, double x, double y) {
        super(context, x, y, 70, 70);

        if(new Random().nextInt(2) == 0){
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.tree_small);
        }
        else{
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.tree_large);
        }

    }
}
