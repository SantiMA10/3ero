package com.plataformas.modelos.controles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.plataformas.GameView;
import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.modelos.Modelo;

/**
 * Created by UO237040 on 06/10/2015.
 */
public class Marcador extends Modelo {

    private int puntos = 0;

    public Marcador(Context context) {
        super(context, GameView.pantallaAncho*0.05 , GameView.pantallaAlto*0.1,
                GameView.pantallaAlto, GameView.pantallaAncho);

        altura = 32;
        ancho = 32;
        imagen = CargadorGraficos.cargarDrawable(context, R.drawable.score);
    }

    public void addPuntos(int puntos){
        this.puntos += puntos;
    }
    
    @Override
    public void dibujar(Canvas canvas){
        int yArriva = (int)  y - altura / 2;
        int xIzquierda = (int) x - ancho / 2;

        imagen.setBounds(xIzquierda, yArriva, xIzquierda
                + ancho, yArriva + altura);
        imagen.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        canvas.drawText(String.valueOf(puntos), (int) x + 18, (int) y+8, paint);
    }
}
