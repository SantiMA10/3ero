package com.flee.modelo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.flee.GameView;
import com.flee.R;
import com.flee.gestores.CargadorGraficos;
import com.flee.gestores.GestorAudio;
import com.flee.modelo.item.Item;
import com.flee.modelo.item.Moneda;
import com.flee.modelo.enemigo.Coche;
import com.flee.modelo.enemigo.Enemigo;
import com.flee.modelo.item.powerUp.Invencibilidad;
import com.flee.modelo.item.powerUp.PowerUp;
import com.flee.modelo.obstaculo.Agua;
import com.flee.modelo.obstaculo.Arbol;
import com.flee.modelo.obstaculo.Obstaculo;
import com.flee.modelo.obstaculo.Roca;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Nivel {

    private Context context = null;
    private int numeroNivel;
    public boolean nivelPausado;
    public boolean perder = false;

    private Tile[][] mapaTiles;
    public static int scrollEjeY;
    private Fondo fondo;
    private Fondo fondoPausa;
    private Fondo fondoPerdido;
    public Marcador marcador;

    private List<Obstaculo> obstaculos;
    private List<Enemigo> enemigos;
    private List<Item> items;
    private List<PowerUp> powerUps;
    public Jugador jugador;

    public boolean inicializado;

    public GameView gameView;
    public boolean moverDerecha;
    public boolean moverIzquierda;

    public Nivel(Context context, int numeroNivel) throws Exception {
        inicializado = false;

        this.context = context;
        this.numeroNivel = numeroNivel;
        inicializar();

        inicializado = true;
    }

    public void inicializar()throws Exception {

        fondo = new Fondo(context, CargadorGraficos.cargarBitmap(context, R.drawable.fondo));
        fondoPausa = new Fondo(context, CargadorGraficos.cargarBitmap(context, R.drawable.fondo_pausa));
        fondoPerdido = new Fondo(context, CargadorGraficos.cargarBitmap(context, R.drawable.fondo_perdido));
        marcador = new Marcador(context, 5, GameView.pantallaAlto-18);

        obstaculos = new ArrayList<>();
        enemigos = new ArrayList<>();
        items = new ArrayList<>();
        powerUps = new ArrayList<>();

        inicializarMapaTiles();

        scrollEjeY = (altoMapaTiles()*Tile.altura) - (GameView.pantallaAlto-Tile.altura);
    }

    public void gl_colisiona(){

        for(Obstaculo obstaculo : obstaculos){
            if(obstaculo.colisiona(jugador) && !jugador.invencible){
                perder();
            }
        }
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
            Item item = iterator.next();
            if(item.colisiona(jugador)){
                GestorAudio.getInstancia().reproducirSonido(GestorAudio.SONIDO_ITEM);
                item.aplicar(this);
                iterator.remove();
            }
        }
        for (Iterator<PowerUp> iterator = powerUps.iterator(); iterator.hasNext();) {
            PowerUp powerUp = iterator.next();
            if(powerUp.colisiona(jugador)){
                GestorAudio.getInstancia().reproducirSonido(GestorAudio.SONIDO_POWER_UP);
                powerUp.aplicar(this);
                iterator.remove();
            }
        }

    }

    public void actualizar (long tiempo) throws Exception {
        if (inicializado) {

            jugador.actualizar(tiempo);
            for(Item item : items){
                item.actualizar(tiempo);
            }
            for(PowerUp powerUp : powerUps){
                powerUp.actualizar(tiempo);
            }
            aplicarReglasMovimiento();
            gl_colisiona();
        }
    }

    private void aplicarReglasMovimiento() throws Exception {

        for(Enemigo enemigo: enemigos){

            if(enemigo.y - scrollEjeY > 0 && enemigo.y - scrollEjeY <= GameView.pantallaAlto){

                if(enemigo.x > GameView.pantallaAncho || enemigo.x < 0){
                    enemigo.girar();
                }

                enemigo.x += enemigo.velocidadX;

                if(enemigo.colisiona(jugador) && !jugador.invencible){
                    perder();
                }

            }

        }

        if(jugador.avance()){

            if(moverDerecha){
                if(jugador.x + jugador.velocidadY <= GameView.pantallaAncho){
                    jugador.x += jugador.velocidadX;
                }
                moverDerecha = false;
            }
            else if(moverIzquierda){
                if(jugador.x - jugador.velocidadY >= 0){
                    jugador.x -= jugador.velocidadX;
                }
                moverIzquierda = false;
            }

            marcador.puntos += 1;
            jugador.y -= jugador.velocidadY;

            if(jugador.y < 0){
                gameView.nivelCompleto();
            }

        }

    }


    public void dibujar (Canvas canvas) {
        if(inicializado) {
            fondo.dibujar(canvas);
            dibujarTiles(canvas);
            for(Obstaculo obstaculo: obstaculos){
                obstaculo.dibujar(canvas);
            }
            for(Enemigo enemigo: enemigos){
                enemigo.dibujar(canvas);
            }
            for(Item item : items){
                item.dibujar(canvas);
            }
            for(PowerUp powerUp : powerUps){
                powerUp.dibujar(canvas);
            }
            jugador.dibujar(canvas);
            marcador.dibujar(canvas);
            if(nivelPausado && !perder){
                fondoPausa.dibujar(canvas);
            }
            if(nivelPausado && perder){
                fondoPerdido.dibujar(canvas);
            }
        }
    }

    private void perder(){
        nivelPausado = true;
        perder = true;
        GestorAudio.getInstancia().reproducirSonido(GestorAudio.SONIDO_JUGADOR_MUERE);
    }

    private Tile inicializarTile(char codigoTile, int x, int y) {

        int xTile = x * Tile.ancho + Tile.ancho/2;
        int yTile = y * Tile.altura + Tile.altura;

        switch (codigoTile) {

            case '.':
                return new Tile(null, Tile.PASABLE);
            case 'a':
                //Agua
                obstaculos.add(new Agua(context, xTile, yTile));
                return new Tile(null, Tile.SOLIDO);
            case 'p':
                //Puente
                return new Tile(CargadorGraficos.cargarDrawable(context, R.drawable.puente, true), Tile.PASABLE);
            case 'm':
                //Meta
                return new Tile(CargadorGraficos.cargarDrawable(context, R.drawable.meta, true), Tile.META);
            case 'c':
                //Carretera arriba
                return new Tile(CargadorGraficos.cargarDrawable(context, R.drawable.carretera_arriba, true), Tile.PASABLE);
            case 'e':
                //Carretera arriba con enemigo
                enemigos.add(new Coche(context, xTile, yTile));
                return new Tile(CargadorGraficos.cargarDrawable(context, R.drawable.carretera_arriba, true), Tile.PASABLE);
            case 'b':
                //Carretera abajo
                return new Tile(CargadorGraficos.cargarDrawable(context, R.drawable.carretera_abajo, true), Tile.PASABLE);
            case 'j':
                //Jugador
                jugador = new Jugador(context, xTile, yTile);
                return new Tile(null, Tile.PASABLE);
            case 'i':
                //Moneda
                items.add(new Moneda(context, xTile, yTile));
                return new Tile(null, Tile.PASABLE);
            case 'w':
                //Invencible
                powerUps.add(new Invencibilidad(context, xTile, yTile));
                return new Tile(null, Tile.PASABLE);
            case 'r':
                //Roca
                obstaculos.add(new Roca(context, xTile, yTile));
                return new Tile(null, Tile.SOLIDO);
            case 't':
                //Arbol
                obstaculos.add(new Arbol(context, xTile, yTile));
                return new Tile(null, Tile.SOLIDO);
            default:
                return new Tile(null, Tile.PASABLE);
        }

    }

    private float tilesEnDistanciaY(double distanciaY){
        return (float) distanciaY/Tile.altura;
    }

    private void dibujarTiles(Canvas canvas) {

        if ( jugador.y >  tilesEnDistanciaY(GameView.pantallaAlto*0.2) * Tile.altura )
            if( jugador .y - scrollEjeY < GameView.pantallaAncho *0.2 ){

                scrollEjeY -= (int) (GameView.pantallaAlto*0.7 - (jugador.y - scrollEjeY));
            }

        int izquierda = 0; //El primer tile
        int derecha = izquierda +
                ( GameView.pantallaAncho / Tile.ancho ) + 1;
        // el ultimo tile visible
        derecha = Math.min(derecha, anchoMapaTiles() - 1);
        for (int y = 0; y < altoMapaTiles() ; ++y) {
            for (int x = izquierda; x <= derecha; ++x) {
                if (mapaTiles[x][y].imagen != null) {
                    // Calcular la posición en pantalla correspondiente
                    // izquierda, arriba, derecha , abajo
                    mapaTiles[x][y].imagen.setBounds(
                            x * Tile.ancho,
                            (y * Tile.altura) - scrollEjeY,
                            x * Tile.ancho + Tile.ancho,
                            (y * Tile.altura) + Tile.altura - scrollEjeY);
                    mapaTiles[x][y].imagen.draw(canvas);
                }
            }
        }
    }

    public int anchoMapaTiles(){

        return mapaTiles.length;

    }
    public int altoMapaTiles(){

        return mapaTiles[0].length;

    }

    private void inicializarMapaTiles() throws Exception {
        InputStream is = context.getAssets().open(numeroNivel+".txt");
        int anchoLinea;

        List<String> lineas = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        {
            String linea = reader.readLine();
            anchoLinea = linea.length();
            while (linea != null)
            {
                lineas.add(linea);
                if (linea.length() != anchoLinea)
                {
                    Log.e("ERROR", "Dimensiones incorrectas en la línea");
                    throw new Exception("Dimensiones incorrectas en la línea.");
                }
                linea = reader.readLine();
            }
        }
        // Inicializar la matriz
        mapaTiles = new Tile[anchoLinea][lineas.size()];
        // Iterar y completar todas las posiciones
        for (int y = 0; y < altoMapaTiles(); ++y) {
            for (int x = 0; x < anchoMapaTiles(); ++x) {
                char tipoDeTile = lineas.get(y).charAt(x);//lines[y][x];
                mapaTiles[x][y] = inicializarTile(tipoDeTile,x,y);
            }
        }
    }

}

