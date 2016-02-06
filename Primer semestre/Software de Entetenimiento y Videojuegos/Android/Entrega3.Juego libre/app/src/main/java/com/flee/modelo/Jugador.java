package com.flee.modelo;

import android.content.Context;
import android.graphics.Canvas;

import com.flee.R;
import com.flee.gestores.CargadorGraficos;
import com.flee.graficos.Sprite;
import com.flee.modelo.item.powerUp.PowerUp;

public class Jugador extends Modelo {

    private Sprite sprite;
    public boolean invencible;

    public int velocidadY = Tile.altura;
    public int velocidadX = Tile.ancho;

    public long tiempoEntreMovimiento = 250;
    public long ultimoMovimiento;

    public PowerUp powerUp;

    public Jugador(Context context, double x, double y) {
        super(context, x, y, 46, 58);

        this.y = y - this.altura/2;

        inicializar();
    }

    private void inicializar(){
        sprite = new Sprite(CargadorGraficos.cargarDrawable(context, R.drawable.sprite_personaje), 58, 46, 8, 3, true);
    }

    @Override
    public void actualizar(long tiempo) {
        sprite.actualizar(tiempo);

        if(powerUp != null){
            if(System.currentTimeMillis() - powerUp.tiempoAccion > powerUp.tiempoPowerUp){
                if(powerUp.aplicado)
                    powerUp.deshacer();
            }
        }

    }

    public boolean avance(){

        if(System.currentTimeMillis() - ultimoMovimiento > tiempoEntreMovimiento){
            ultimoMovimiento = System.currentTimeMillis();
            return true;
        }
        return false;

    }

    @Override
    public void dibujar(Canvas canvas){
        sprite.dibujarSprite(canvas, (int) x , (int) y - Nivel.scrollEjeY, false);
    }
}
