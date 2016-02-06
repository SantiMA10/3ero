package com.flee.modelo.enemigo;

import android.content.Context;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;

public class Coche extends Enemigo {

    public Coche(Context context, double x, double y) {
        super(context, x, y, 70, 70);
        this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.audi);
    }

    @Override
    protected void girarImagen() {
        if(velocidadX > 0){
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.audi);
        }
        else{
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.audi_izquierda);
        }
    }
}
