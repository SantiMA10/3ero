package com.simple.gestores;

import android.content.Context;

import com.simple.GameView;
import com.simple.R;
import com.simple.modelos.enemigos.Enemigo;
import com.simple.modelos.enemigos.EnemigoBasico;
import com.simple.modelos.enemigos.EnemigoHelicoptero;
import com.simple.modelos.enemigos.EnemigoGlobo;
import com.simple.modelos.items.Item;
import com.simple.modelos.items.ItemPausa;
import com.simple.modelos.items.ItemMonedaBronze;
import com.simple.modelos.items.ItemMonedaGold;
import com.simple.modelos.items.ItemMonedaSilver;
import com.simple.modelos.items.powerUps.PowerUpDisparoRafaga;
import com.simple.modelos.items.powerUps.PowerUpHalfLife;
import com.simple.modelos.items.powerUps.PowerUpEscondido;
import com.simple.modelos.items.powerUps.PowerUpVida;
import com.simple.utilidades.Ar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by santiagomartin on 27/9/15.
 */
public class GestorNiveles {

    private static GestorNiveles instancia = null;
    private int nivelActual = 1;

    private GestorNiveles() {}

    public static GestorNiveles getInstancia() {
        synchronized (GestorNiveles.class){

            if(instancia == null){
                instancia = new GestorNiveles();
            }
            return instancia;

        }

    }

    public LinkedList<Enemigo> cargarEnemigosXML(Context context, int recursoNivel){

        ParserXML parser = new ParserXML();
        String textoFicheroNivel = "";
        try{

            InputStream inputStream = context.getResources().openRawResource(recursoNivel);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String linea = bufferedReader.readLine();
            while(linea != null){

                textoFicheroNivel += linea;
                linea = bufferedReader.readLine();

            }

            bufferedReader.close();

        }catch (Exception e){}

        Document doc = parser.getDom(textoFicheroNivel);

        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        NodeList nodos = doc.getElementsByTagName("enemy");
        for(int i = 0; i < nodos.getLength(); i++){

            Element elementoActual = (Element) nodos.item(i);
            String x = parser.getValor(elementoActual, "x");
            String y = parser.getValor(elementoActual, "y");
            String tipo = parser.getValor(elementoActual, "type");

            if(tipo.equals("basico")){
                enemigos.add(new EnemigoBasico(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("helicoptero")){
                enemigos.add(new EnemigoHelicoptero(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("globo")){
                enemigos.add(new EnemigoGlobo(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }

        }

        return enemigos;

    }

    public LinkedList<Item> cargarItemsXML(Context context, int recursoNivel){

        ParserXML parser = new ParserXML();
        String textoFicheroNivel = "";
        try{

            InputStream inputStream = context.getResources().openRawResource(recursoNivel);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String linea = bufferedReader.readLine();
            while(linea != null){

                textoFicheroNivel += linea;
                linea = bufferedReader.readLine();

            }

            bufferedReader.close();

        }catch (Exception e){}

        Document doc = parser.getDom(textoFicheroNivel);

        LinkedList<Item> items = new LinkedList<Item>();
        NodeList nodos = doc.getElementsByTagName("item");
        for(int i = 0; i < nodos.getLength(); i++){

            Element elementoActual = (Element) nodos.item(i);
            String x = parser.getValor(elementoActual, "x");
            String y = parser.getValor(elementoActual, "y");
            String tipo = parser.getValor(elementoActual, "type");

            if(tipo.equals("vida")){
                items.add(new PowerUpVida(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("halflife")){
                items.add(new PowerUpHalfLife(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("escondido")){
                items.add(new PowerUpEscondido(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("rafaga")){
                items.add(new PowerUpDisparoRafaga(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("pausa")){
                items.add(new ItemPausa(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("moneda-gold")){
                items.add(new ItemMonedaGold(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("moneda-silver")){
                items.add(new ItemMonedaSilver(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }
            else if(tipo.equals("moneda-bronze")){
                items.add(new ItemMonedaBronze(context, Ar.x(Double.parseDouble(x)), Ar.y(Double.parseDouble(y))));
            }

        }

        return items;

    }

    public List<Enemigo> getEnemigosNivelActual(Context context) {

        List<Enemigo> enemigos = new LinkedList<Enemigo>();

        switch (nivelActual){
            case 1:
                enemigos = cargarEnemigosXML(context, R.raw.level1);
                GameView.infinito = false;
                break;
            case 2:
                enemigos = cargarEnemigosXML(context, R.raw.level2);
                GameView.infinito = false;
                break;
            case 3:
                enemigos = cargarEnemigosXML(context, R.raw.level3);
                GameView.infinito = false;
                break;
            case Integer.MAX_VALUE:
                enemigos = new ArrayList<Enemigo>();
                GameView.infinito = true;
                break;
        }

        return enemigos;
    }

    public List<Item> getItemsNivelActual(Context context) {

        List<Item> items = new LinkedList<Item>();

        switch (nivelActual){
            case 1:
                items = cargarItemsXML(context, R.raw.level1);
                break;
            case 2:
                items = cargarItemsXML(context, R.raw.level2);
                break;
            case 3:
                items = cargarItemsXML(context, R.raw.level3);
                break;
            case Integer.MAX_VALUE:
                items = new ArrayList<Item>();
                break;
        }

        return items;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivel) {
        this.nivelActual = nivel;
    }

}
