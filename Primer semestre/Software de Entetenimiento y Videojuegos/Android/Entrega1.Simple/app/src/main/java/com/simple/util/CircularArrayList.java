package com.simple.util;

/**
 * Created by santiagomartin on 8/10/15.
 */
import android.util.Log;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E>
{
    private static final long serialVersionUID = 1L;
    private int index = 0;

    public E getUp(){
        E nave = get(index);
        index++;
        if(index >= size()){
            index = 0;
        }
        Log.v("Circular", "" + index);
        return nave;
    }

    public E getDown(){
        E nave = get(index);
        index--;
        if(index < 0){
            index = size() - 1;
        }
        Log.v("Circular", "" + index);
        return nave;
    }

}
