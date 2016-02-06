package com.flee.modelo.item.powerUp;

import android.content.Context;
import android.graphics.Canvas;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;
import com.flee.graficos.Sprite;
import com.flee.modelo.Nivel;

public class Invencibilidad extends PowerUp {

    private Sprite sprite;

    public Invencibilidad(Context context, double x, double y) {
        super(context, x, y);
        tiempoPowerUp = 2000;
        sprite = new Sprite(
                    CargadorGraficos.cargarDrawable(context, R.drawable.escudo),
                    32, 32,
                    4, 8, true);
    }

    @Override
    public void actualizar(long tiempo) {
        super.actualizar(tiempo);
        sprite.actualizar(tiempo);
    }

    @Override
    public void dibujar(Canvas canvas){
        sprite.dibujarSprite(canvas, (int) x, (int) y - Nivel.scrollEjeY);
    }

    @Override
    protected void aplicarAbstract() {
        nivel.jugador.invencible = true;
    }

    @Override
    public void deshacer() {
        nivel.jugador.invencible = false;
    }

}
