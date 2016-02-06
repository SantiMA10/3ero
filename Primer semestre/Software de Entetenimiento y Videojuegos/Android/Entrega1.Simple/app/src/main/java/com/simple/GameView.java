package com.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.simple.gestores.GestorAudio;
import com.simple.gestores.GestorNaves;
import com.simple.gestores.GestorNiveles;
import com.simple.modelos.controles.BotonDisparar;
import com.simple.modelos.controles.MarcadorMonedas;
import com.simple.modelos.enemigos.disparos.DisparoEnemigo;
import com.simple.modelos.items.Item;
import com.simple.modelos.items.ItemAbstractMoneda;
import com.simple.modelos.items.ItemPausa;
import com.simple.modelos.items.ItemMonedaBronze;
import com.simple.modelos.items.ItemMonedaGold;
import com.simple.modelos.items.ItemMonedaSilver;
import com.simple.modelos.items.powerUps.PowerUp;
import com.simple.modelos.items.powerUps.PowerUpDisparoRafaga;
import com.simple.modelos.naves.*;
import com.simple.modelos.naves.disparos.DisparoJugador;
import com.simple.modelos.enemigos.Enemigo;
import com.simple.modelos.enemigos.EnemigoBasico;
import com.simple.global.Estados;
import com.simple.modelos.enemigos.EnemigoHelicoptero;
import com.simple.modelos.enemigos.EnemigoGlobo;
import com.simple.modelos.Fondo;
import com.simple.modelos.controles.Pad;
import com.simple.modelos.items.powerUps.PowerUpHalfLife;
import com.simple.modelos.items.powerUps.PowerUpEscondido;
import com.simple.modelos.items.powerUps.PowerUpVida;
import com.simple.modelos.controles.Barra;
import com.simple.modelos.controles.BotonPausa;
import com.simple.modelos.controles.Marcador;
import com.simple.modelos.controles.MensajePausa;
import com.simple.utilidades.Ar;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameView extends View {

    int NO_ACTION = 0;
    int ACTION_MOVE = 1;
    int ACTION_UP = 2;
    int ACTION_DOWN = 3;
    int accion[] = new int[6];
    float x[] = new float[6];
    float y[] = new float[6];

    private boolean finJuego = false;
    public static boolean pausa = false;
    public static boolean infinito = false;
    private GestorAudio gestorAudio;

    Context context;
    GameLoop gameLoop;

    //Elementos del juego
    private Nave nave;
    private List<Enemigo> enemigos;
    private Fondo fondo;
    private Pad pad;
    private List<DisparoJugador> disparosJugador;
    private List<DisparoEnemigo> disparosEnemigo;
    private List<Item> items;
    private BotonDisparar botonDisparar;
    private Marcador marcador;
    private MarcadorMonedas marcadorMonedas;
    private Barra barraVidas;
    private BotonPausa botonPausa;
    private MensajePausa mensajePausa;
    private boolean trescientostreintaytres;

    public int tiempoEntreEnemigos = 100;
    private int tiempoEntreEnemigosDefecto = 100;
    private long tiempoEnemigos;

    public GameView(Context context){
        super(context);
        setFocusable(true);
        this.context=context;
        inicializarGestorAudio(context);
        inicializar(context);

    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(botonDisparar.estaPulsado(event.getX(),event.getY())){

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                disparoNave = naveBasica.disparar();
            }
        }

        if(pad.estaPulsado(event.getX(),event.getY())){
            float orientacion = pad.getOrientacionX(event.getX());

            if(event.getAction() == MotionEvent.ACTION_DOWN
                    || event.getAction() == MotionEvent.ACTION_MOVE){
                naveBasica.acelerar(orientacion);
            }

            if(event.getAction() == MotionEvent.ACTION_UP){
                naveBasica.frenar();
            }
        }
        else{
            naveBasica.frenar();
        }

        return true;
    }
    */

    private void inicializar(Context context){

        fondo = new Fondo(context, Ar.x(160), Ar.y(240));
        pad = new Pad(context, Ar.x(70), Ar.y(410));
        botonDisparar = new BotonDisparar(context, Ar.x(250), Ar.y(410));
        marcador = new Marcador(context, Ar.x(13), Ar.y(15));
        marcadorMonedas = new MarcadorMonedas(context, Ar.x(13), Ar.y(35));
        botonPausa = new BotonPausa(context, Ar.x(300), Ar.y(45));
        mensajePausa = new MensajePausa(context, Ar.x(320/2), Ar.y(480 / 2));

        nave = GestorNaves.getInstancia().getNave(context);
        disparosJugador = new LinkedList<DisparoJugador>();
        barraVidas = new Barra(context, Ar.x(80), Ar.y(25), nave.getVida(), nave.getVida());

        enemigos = GestorNiveles.getInstancia().getEnemigosNivelActual(context);
        disparosEnemigo = new LinkedList<DisparoEnemigo>();
        items = GestorNiveles.getInstancia().getItemsNivelActual(context);
        trescientostreintaytres = false;

        pausa = false;

        gameLoop = new GameLoop();
        gameLoop.start();
    }

    public void inicializarGestorAudio(Context context){

        gestorAudio = GestorAudio.getInstancia(context, R.raw.musica_fondo);
        gestorAudio.reproducirMusicaAmbiente();
        gestorAudio.registrarSonido(GestorAudio.SONIDO_DISPARO_NAVE, R.raw.nave_disparo);
        gestorAudio.registrarSonido(GestorAudio.SONIDO_DISPARO_ENEMIGO, R.raw.enemigo_disparo);
        gestorAudio.registrarSonido(GestorAudio.SONIDO_EXPLOSION_ENEMIGO, R.raw.explosion_avion);
        gestorAudio.registrarSonido(GestorAudio.SONIDO_HIT_NAVE, R.raw.nave_hit_2);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        try {

            fondo.dibujarEnPantalla(canvas);

            for(DisparoJugador disparoJugador : disparosJugador){
                disparoJugador.dibujarEnPantalla(canvas);
            }
            for (DisparoEnemigo disparoEnemigo : disparosEnemigo) {
                if ( disparoEnemigo.estaEnPantalla() == 1){
                    disparoEnemigo.dibujarEnPantalla(canvas);
                }
            }

            nave.dibujarEnPantalla(canvas);

            for (Enemigo enemigo : enemigos) {
                if (enemigo.estaEnPantalla() == 1) {
                    enemigo.dibujarEnPantalla(canvas);
                }
            }

            for (Item item : items) {
                if (item.estaEnPantalla() == 1) {
                    item.dibujarEnPantalla(canvas);
                }
            }

            if(nave.getPowerUp() != null){
                PowerUp powerUp = nave.getPowerUp();
                powerUp.setX(Ar.x(250));
                powerUp.setY(Ar.y(45));
                powerUp.dibujarEnPantalla(canvas);
            }

            pad.dibujarEnPantalla(canvas);
            botonDisparar.dibujarEnPantalla(canvas);
            marcador.dibujarEnPantalla(canvas);
            marcadorMonedas.dibujarEnPantalla(canvas);
            barraVidas.dibujarEnPantalla(canvas);
            botonPausa.dibujarEnPantalla(canvas);

            if(pausa){
                mensajePausa.dibujarEnPantalla(canvas);
            }

        }catch (Exception ex) { }

    }

    private void modoInfinito() {


        if(System.currentTimeMillis() > tiempoEnemigos + (long)tiempoEntreEnemigos){

            int maxEnemigos = ((marcador.getPuntos() - ItemAbstractMoneda.monedas*5) / 70) < 4
                    ? ((marcador.getPuntos() - ItemAbstractMoneda.monedas*5) / 70) + 1 : 4;
            tiempoEntreEnemigos = tiempoEntreEnemigosDefecto;
            tiempoEntreEnemigosDefecto += maxEnemigos*10;
            gl_generarEnemigos(maxEnemigos);
            gl_generarItems();

        }

    }

    private void gl_generarEnemigos(int maxEnemigos){


        if(enemigos.size() < maxEnemigos) {

            int nuevosEnemigos = new Random().nextInt(maxEnemigos) + 2;

            for (int i = 0; i < nuevosEnemigos; i++) {

                int ene = new Random().nextInt(3);

                if (ene == 1) {
                    enemigos.add(new EnemigoHelicoptero(context, new Random().nextInt(320), (50 + new Random().nextInt(100) * -1)));
                } else if (ene == 2) {
                    enemigos.add(new EnemigoBasico(context, new Random().nextInt(320), (50 + new Random().nextInt(100) * -1)));
                } else {
                    enemigos.add(new EnemigoGlobo(context, new Random().nextInt(320), (50 + new Random().nextInt(100) * -1)));
                }

            }
        }

    }

    private void gl_generarItems(){

        int random, puntos = marcador.getPuntos();

        if(puntos % 5 == 0){
            random = new Random().nextInt(2);
            if(random == 1 && barraVidas.getValorActual() != barraVidas.getValorMaximo()){
                items.add(new PowerUpVida(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
            }
            else{
                random = new Random().nextInt(4);
                if(random == 0){
                    items.add(new ItemMonedaGold(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
                }
                else if(random == 1){
                    items.add(new ItemMonedaSilver(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
                }
                else{
                    items.add(new ItemMonedaBronze(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
                }
            }
        }

        if(puntos >= 333 && !trescientostreintaytres){
            items.add(new PowerUpHalfLife(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
            trescientostreintaytres = true;
        }

        if(puntos % 15 == 0){
            random = new Random().nextInt(2);
            if(random == 1){
                items.add(new PowerUpEscondido(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
            }
            else{
                items.add(new PowerUpDisparoRafaga(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
            }

        }

        random = new Random().nextInt(20);
        if(random == 5){
            items.add(new ItemPausa(context, new Random().nextInt(320),(50 + new Random().nextInt(100) * -1)));
        }

    }

    public void gl_mover(){

        for(Enemigo enemigo : enemigos) {
            enemigo.mover();
        }
        for(DisparoEnemigo disparoEnemigo : disparosEnemigo){
            disparoEnemigo.mover();
        }
        for(DisparoJugador disparoJugador : disparosJugador){
            disparoJugador.mover();
        }
        for(Item item : items){
            item.mover();
        }
        fondo.mover();
        nave.mover();

    }

    public void gl_comprobarColisiones(){

        Enemigo enemigoSacarLista = null;
        DisparoJugador disparoJugadorSacarLista = null;
        DisparoEnemigo disparoEnemigoSacarLista = null;
        Item itemSacarLista = null;


            if(enemigos.size() == 0 && infinito){
                modoInfinito();
            }

        for(Enemigo enemigo: enemigos){

            if (enemigo.estaEnPantalla() == -1 || enemigo.getEstado() == Estados.INACTIVO){
                enemigoSacarLista = enemigo;
                if(enemigoSacarLista.estaEnPantalla() == -1)
                    marcador.setPuntos(marcador.getPuntos() - 5);
            }

            for(DisparoJugador disparoJugador: disparosJugador){

                if (enemigo.estaEnPantalla() == 1 && enemigo.colisiona(disparoJugador) && enemigo.getEstado() == Estados.ACTIVO){

                    enemigo.destruir(disparoJugador.getDaño());
                    gestorAudio.reproducirSonido(GestorAudio.SONIDO_EXPLOSION_ENEMIGO);
                    marcador.setPuntos(marcador.getPuntos() + 1);
                    disparoJugadorSacarLista = disparoJugador;

                }
                if(disparoJugador.estaEnPantalla() != 1){
                    disparoJugadorSacarLista = disparoJugador;
                }

            }

        }

        for(Item item : items){

            if(item.colisiona(nave)){
                item.accion(this);
                itemSacarLista = item;
            }

        }

        for (DisparoEnemigo disparoEnemigo : disparosEnemigo) {
            if(disparoEnemigo.colisiona(nave)){

                gestorAudio.reproducirSonido(GestorAudio.SONIDO_HIT_NAVE);

                if(barraVidas.getValorActual() > disparoEnemigo.getDaño()){
                    nave.impactado();
                    disparoEnemigoSacarLista = disparoEnemigo;
                    barraVidas.setValorActual(barraVidas.getValorActual() - disparoEnemigo.getDaño());
                }
                else{
                    nave.impactado();
                    barraVidas.setValorActual(barraVidas.getValorActual() - disparoEnemigo.getDaño());
                    finJuego = true;
                }

            }

            if (disparoEnemigo.estaEnPantalla() == -1){
                disparoEnemigoSacarLista = disparoEnemigo;
            }
        }

        if(enemigoSacarLista != null){
            enemigos.remove(enemigoSacarLista);
            if(infinito){
                modoInfinito();
            }
        }
        if (disparoJugadorSacarLista != null){

            disparosJugador.remove(disparoJugadorSacarLista);
        }
        if(disparoEnemigoSacarLista != null){
            disparosEnemigo.remove(disparoEnemigoSacarLista);
        }
        if(itemSacarLista != null){
            items.remove(itemSacarLista);
        }

    }

    private void gl_comprovarNivelFinalizado() {

        if(enemigos.size() == 0 && !infinito){

            finJuego = true;
            ((MainActivity)context).finish();

        }

    }

    public void gl_comprobarDisparos(){

        DisparoJugador disparoJugador = nave.disparar(System.currentTimeMillis());

        if(disparoJugador != null){
            gestorAudio.reproducirSonido(GestorAudio.SONIDO_DISPARO_NAVE);
            disparosJugador.add(disparoJugador);
        }

        long tiempo = System.currentTimeMillis();
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaEnPantalla() == 1 &&
                    enemigo.getEstado() == Estados.ACTIVO && !nave.isEscondido()) {

                DisparoEnemigo disparo = enemigo.disparar(tiempo);
                if (disparo != null) {
                    gestorAudio.reproducirSonido(GestorAudio.SONIDO_DISPARO_ENEMIGO);
                    disparosEnemigo.add(disparo);
                }
            }
        }


    }

    public class GameLoop extends Thread{

        public void run(){

            while(!finJuego && !pausa){

                try {

                    TimeUnit.MILLISECONDS.sleep(25);

                    gl_mover();
                    gl_comprobarColisiones();
                    gl_comprobarDisparos();
                    gl_comprovarNivelFinalizado();

                    //Re-dibujamos
                    postInvalidate();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            pausa = false;
            postInvalidate();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("Tecla", "Tecla pulsada: " + keyCode);

        if( keyCode == 32) {
            nave.acelerar(-15f, 0f);
        }
        if( keyCode == 29) {
            nave.acelerar(15f, 0f);
        }
        if( keyCode == 51) {
            nave.acelerar(0, 15f);
        }
        if( keyCode == 47) {
            nave.acelerar(0, -15f);
        }
        if( keyCode == 62) {
            nave.disparando();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.v("Tecla", "Tecla pulsada: " + keyCode);

        if( keyCode == 32) {
            nave.frenar();
        }
        if( keyCode == 29) {
            nave.frenar();
        }
        if( keyCode == 51) {
            nave.frenar();
        }
        if( keyCode == 47) {
            nave.frenar();
        }
        if( keyCode == 34) {

            if(pausa){
                pausa = false;
                gameLoop = new GameLoop();
                gameLoop.start();
            }
            else{
                pausa = true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // valor a Binario
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        // Indice del puntero
        int pointerIndex = (event.getAction() &
                MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                MotionEvent.ACTION_POINTER_INDEX_SHIFT;

        int pointerId  = event.getPointerId(pointerIndex);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                accion[pointerId] = ACTION_DOWN;
                x[pointerId] = event.getX(pointerIndex);
                y[pointerId] = event.getY(pointerIndex);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                accion[pointerId] = ACTION_UP;
                x[pointerId] = event.getX(pointerIndex);
                y[pointerId] = event.getY(pointerIndex);
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                for(int i =0; i < pointerCount; i++){
                    pointerIndex = i;
                    pointerId  = event.getPointerId(pointerIndex);
                    accion[pointerId] = ACTION_MOVE;
                    x[pointerId] = event.getX(pointerIndex);
                    y[pointerId] = event.getY(pointerIndex);
                }
                break;
        }

        if(action == MotionEvent.ACTION_DOWN && finJuego){

            finJuego = false;
            inicializar(context);

        }

        if(action == MotionEvent.ACTION_DOWN && pausa){

            pausa = false;
            gameLoop = new GameLoop();
            gameLoop.start();

        }

        procesarEventosTouch();
        return true;
    }

    private void procesarEventosTouch(){

        boolean pulsacionMover = false;
        for (int i = 0; i < 6; i++) {
            if (accion[i] != NO_ACTION) {

                if (pad.estaPulsado(x[i],y[i])) {

                    float orientacionX = pad.getOrientacionX((int)x[i]);
                    float orientacionY = pad.getOrientacionY((int) y[i]);

                    if (accion[i] == ACTION_DOWN
                            || accion[i] == ACTION_MOVE) {
                        nave.acelerar(orientacionX, orientacionY);
                        pulsacionMover=true;
                    }

                }

                if(botonPausa.estaPulsado(x[i],y[i])){
                    if(accion[i] == ACTION_DOWN){
                        pausa = true;

                    }
                }

                if (botonDisparar.estaPulsado(x[i],y[i])) {
                    if (accion[i] == ACTION_DOWN) {
                        // Disparar
                        nave.disparando();
                    }
                }
            }
        }

        if(pulsacionMover == false){
            nave.frenar();
        }
    }


    public Nave getNaveBasica() {
        return nave;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public Fondo getFondo() {
        return fondo;
    }

    public Pad getPad() {
        return pad;
    }

    public List<DisparoJugador> getDisparosJugador() {
        return disparosJugador;
    }

    public List<DisparoEnemigo> getDisparosEnemigo() {
        return disparosEnemigo;
    }

    public List<Item> getItems() {
        return items;
    }

    public BotonDisparar getBotonDisparar() {
        return botonDisparar;
    }

    public Marcador getMarcador() {
        return marcador;
    }

    public Barra getBarraVidas() {
        return barraVidas;
    }

    public BotonPausa getBotonPausa() {
        return botonPausa;
    }

    public MensajePausa getMensajePausa() {
        return mensajePausa;
    }
}
