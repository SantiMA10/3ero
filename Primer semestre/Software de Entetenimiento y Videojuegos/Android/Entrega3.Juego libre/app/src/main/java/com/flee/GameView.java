package com.flee;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.flee.gestores.GestorAudio;
import com.flee.modelo.Nivel;
import com.flee.modelo.controles.BotonPausa;


public class GameView extends SurfaceView implements SurfaceHolder.Callback  {

    boolean iniciado = false;
    Context context;
    GameLoop gameloop;

    public GestorAudio gestorAudio;

    public static int pantallaAncho;
    public static int pantallaAlto;

    private Nivel nivel;
    public int numeroNivel = 0;
    public int numeroNivelMaximo = 2;

    public BotonPausa botonPausa;


    public GameView(Context context) {
        super(context);
        iniciado = true;

        getHolder().addCallback(this);
        setFocusable(true);

        this.context = context;
        gameloop = new GameLoop(this);
        gameloop.setRunning(true);

    }

    public void inicializarGestorAudio(Context context){

        gestorAudio = GestorAudio.getInstancia(context, R.raw.musica_fondo);
        //gestorAudio.reproducirMusicaAmbiente();
        gestorAudio.registrarSonido(GestorAudio.SONIDO_JUGADOR_MUERE, R.raw.jugador_muere);
        gestorAudio.registrarSonido(GestorAudio.SONIDO_POWER_UP, R.raw.power_up);
        gestorAudio.registrarSonido(GestorAudio.SONIDO_ITEM, R.raw.item);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // valor a Binario
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        // Indice del puntero
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;

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

        try {
            procesarEventosTouch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    int NO_ACTION = 0;
    int ACTION_MOVE = 1;
    int ACTION_UP = 2;
    int ACTION_DOWN = 3;
    int accion[] = new int[6];
    float x[] = new float[6];
    float y[] = new float[6];

    public void procesarEventosTouch() throws Exception{

        for(int i=0; i < 6; i++){
            if(accion[i] != NO_ACTION ) {

                if(accion[i] == ACTION_DOWN){

                    if(botonPausa.estaPulsado(x[i], y[i])){
                        nivel.nivelPausado = true;
                    }
                    else{
                        if(!nivel.nivelPausado){

                            if (x[i] < pantallaAncho/2) {
                                nivel.moverIzquierda = true;
                                nivel.moverDerecha = false;
                            }

                            if (x[i] > pantallaAncho/2) {
                                nivel.moverIzquierda = false;
                                nivel.moverDerecha = true;
                            }

                        }

                        if(nivel.nivelPausado && !nivel.perder){
                            nivel.nivelPausado = false;
                        }
                        else if(nivel.nivelPausado && nivel.perder){
                            inicializar();
                            //((GameActivity)context).finish();
                        }
                    }

                }

            }
        }
    }

    protected void inicializar() throws Exception {
        nivel = new Nivel(context, numeroNivel);
        nivel.gameView = this;

        botonPausa = new BotonPausa(context, pantallaAncho-20, 20);
        inicializarGestorAudio(context);
    }

    public void nivelCompleto() throws Exception {
        if (numeroNivel < numeroNivelMaximo){ // Número Máximo de Nivel
            numeroNivel++;
        } else {
            numeroNivel = 0;
        }
        inicializar();

    }


    public void actualizar(long tiempo) throws Exception {
        if (!nivel.nivelPausado) {
            nivel.actualizar(tiempo);
        }
    }

    protected void dibujar(Canvas canvas) {
        nivel.dibujar(canvas);
        botonPausa.dibujar(canvas);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        pantallaAncho = width;
        pantallaAlto = height;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (iniciado) {
            iniciado = false;
            if (gameloop.isAlive()) {
                iniciado = true;
                gameloop = new GameLoop(this);
            }

            gameloop.setRunning(true);
            gameloop.start();
        } else {
            iniciado = true;
            gameloop = new GameLoop(this);
            gameloop.setRunning(true);
            gameloop.start();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        iniciado = false;

        boolean intentarDeNuevo = true;
        gameloop.setRunning(false);
        while (intentarDeNuevo) {
            try {
                gameloop.join();
                intentarDeNuevo = false;
            }
            catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("Tecla","Tecla pulsada: "+keyCode);

        if( keyCode == 32) {
            nivel.moverDerecha = true;
            nivel.moverIzquierda = false;
        }
        if( keyCode == 29) {
            nivel.moverIzquierda = true;
            nivel.moverDerecha = false;
        }
        /*

        if(nivel.nivelPausado)
            nivel.nivelPausado = false;
        */
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event) {
        if( keyCode == 32 || keyCode == 29) {
            //nivel.orientacionPad = 0;
        }
        return super.onKeyDown(keyCode, event);
    }


}

