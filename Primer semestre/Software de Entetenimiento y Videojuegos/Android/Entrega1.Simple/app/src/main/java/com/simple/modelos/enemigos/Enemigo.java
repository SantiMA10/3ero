package com.simple.modelos.enemigos;

import com.simple.modelos.ModeloInterface;
import com.simple.modelos.ModeloMovimientoInterface;
import com.simple.modelos.enemigos.disparos.DisparoEnemigo;

/**
 * Created by UO237040 on 29/09/2015.
 */
public interface Enemigo extends ModeloMovimientoInterface{

    public void destruir(int damage);
    public DisparoEnemigo disparar(long milisegundos);
    public int getEstado();

}
