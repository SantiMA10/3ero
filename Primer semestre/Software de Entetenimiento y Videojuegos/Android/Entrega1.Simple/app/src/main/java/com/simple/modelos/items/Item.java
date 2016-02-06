package com.simple.modelos.items;

import com.simple.GameView;
import com.simple.modelos.ModeloMovimientoInterface;

/**
 * Created by UO237040 on 29/09/2015.
 */
public interface Item extends ModeloMovimientoInterface {

    public void accion(GameView gameView);

}
