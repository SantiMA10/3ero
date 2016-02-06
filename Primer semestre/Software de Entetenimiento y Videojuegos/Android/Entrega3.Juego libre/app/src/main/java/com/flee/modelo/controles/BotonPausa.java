package com.flee.modelo.controles;

import android.content.Context;
import android.graphics.Canvas;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;
import com.flee.modelo.Modelo;
import com.flee.modelo.Nivel;

public class BotonPausa extends Modelo {

    public BotonPausa(Context context, double x, double y) {
        super(context, x, y, 36, 36);

        this.imagen = CargadorGraficos.cargarDrawable(context, R.drawable.boton_pausa);
    }

    public boolean estaPulsado(float clickX, float clickY){

        boolean estaPulsado = false;

        if(clickX <= (x + ancho/2 ) && clickX >= (x - ancho / 2) && clickY <= (y + altura / 2) && clickY >= (y - altura /2)){

            estaPulsado = true;

        }
        return estaPulsado;

    }

    @Override
    public void dibujar(Canvas canvas){
        int yArriba = (int)  y - altura / 2;
        int xIzquierda = (int) x - ancho / 2;

        imagen.setBounds(xIzquierda, yArriba, xIzquierda + ancho, (yArriba + altura));
        imagen.draw(canvas);
    }

    @Override
    public void actualizar(long tiempo) {}
}
