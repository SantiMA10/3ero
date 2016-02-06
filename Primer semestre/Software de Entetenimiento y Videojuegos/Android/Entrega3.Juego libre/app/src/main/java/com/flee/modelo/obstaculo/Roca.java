package com.flee.modelo.obstaculo;

import android.content.Context;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;

import java.util.Random;

public class Roca extends Obstaculo{

    public Roca(Context context, double x, double y) {
        super(context, x, y, 70, 70);

        int random = new Random().nextInt(3);
        if(random == 0){
            this.ancho = 89;
            this.altura = 72;
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.rock1);
        }
        else if(random == 1){
            this.ancho = 87;
            this.altura = 67;
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.rock3);
        }
        else{
            this.ancho = 73;
            this.altura = 68;
            this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.rock2);
        }

    }

}
