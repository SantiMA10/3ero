package com.simple.modelos.items.powerUps;

import com.simple.modelos.items.Item;
import com.simple.modelos.naves.AbstractNave;

/**
 * Created by santiagomartin on 10/10/15.
 */
public interface PowerUp extends Item {

    public void revertir(AbstractNave nave);
    public boolean perdidoAlImpactar();

}
