package com.plataformas.modelos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.plataformas.GameView;
import com.plataformas.MainActivity;
import com.plataformas.R;
import com.plataformas.gestores.CargadorGraficos;
import com.plataformas.gestores.GestorAudio;
import com.plataformas.gestores.Utilidades;
import com.plataformas.modelos.controles.IconoVida;
import com.plataformas.modelos.controles.Marcador;
import com.plataformas.modelos.enemigos.Enemigo;
import com.plataformas.modelos.enemigos.EnemigoBasico;
import com.plataformas.modelos.enemigos.EnemigoDispara;
import com.plataformas.modelos.enemigos.EnemigoVolador;
import com.plataformas.modelos.enemigos.disparos.DisparoEnemigo;
import com.plataformas.modelos.items.Item;
import com.plataformas.modelos.items.PuntoGuardado;
import com.plataformas.modelos.items.RecolectableItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Nivel {

    private Context context = null;
    private int numeroNivel;
    private Fondo[] fondos;
    public Bitmap mensaje;
    public boolean nivelPausado;
    public boolean perder = false;

    private Tile[][] mapaTiles;
    public static int scrollEjeX = 0;

    public Jugador jugador;
    private List<DisparoJugador> disparosJugador;
    private List<DisparoEnemigo> disparoEnemigos;
    private List<Item> items;
    private List<Enemigo> enemigos;
    boolean movimientoEnemigoAlMorir = false;
    public Marcador marcador;

    public boolean inicializado;
    public float orientacionPad;
    public boolean botonSaltarPulsado;
    public boolean botonDispararPulsado;

    private float velocidadGravedad = 0.8f;
    private float velocidadMaximaCaida = 10 ;

    private IconoVida[] iconosVida;
    private Meta meta;
    public GameView gameView;

    public Nivel(Context context, int numeroNivel) throws Exception {
        inicializado = false;

        this.context = context;
        this.numeroNivel = numeroNivel;
        inicializar();

        inicializado = true;
    }

    public int anchoMapaTiles(){

        return mapaTiles.length;

    }
    public int altoMapaTiles(){

        return mapaTiles[0].length;

    }

    public void inicializar()throws Exception {

        scrollEjeX = 0;

        mensaje = CargadorGraficos.cargarBitmap(context, R.drawable.description);
        nivelPausado = true;

        iconosVida = new IconoVida[3];
        iconosVida[0] = new IconoVida(context, GameView.pantallaAncho*0.75,
                GameView.pantallaAlto*0.1);
        iconosVida[1] = new IconoVida(context, GameView.pantallaAncho*0.85,
                GameView.pantallaAlto*0.1);
        iconosVida[2] = new IconoVida(context, GameView.pantallaAncho*0.95,
                GameView.pantallaAlto*0.1);

        fondos = new Fondo[2];
        fondos[0] = new Fondo(context,CargadorGraficos.cargarBitmap(context,
                R.drawable.capa1), 0);
        fondos[1] = new Fondo(context,CargadorGraficos.cargarBitmap(context,
                R.drawable.capa2), 1f);
        items = new LinkedList<Item>();
        marcador = new Marcador(context);
        enemigos = new LinkedList<Enemigo>();
        disparosJugador = new LinkedList<DisparoJugador>();
        disparoEnemigos = new LinkedList<DisparoEnemigo>();
        inicializarMapaTiles();
    }

    public void gl_colisiona(){
        Item itemSacarLista = null;

        for(Item item: items){
            if(item.colisiona(jugador)){
                item.accion(this);
                itemSacarLista = item;
            }
        }

        if(itemSacarLista != null){
            items.remove(itemSacarLista);
        }
    }

    public void actualizar (long tiempo) throws Exception {
        if (inicializado) {
            jugador.procesarOrdenes(orientacionPad, botonSaltarPulsado, botonDispararPulsado);
            if (botonSaltarPulsado)
                botonSaltarPulsado = false;
            if (botonDispararPulsado) {
                gameView.gestorAudio.reproducirSonido(GestorAudio.SONIDO_DISPARO_JUGADOR);
                disparosJugador.add(new DisparoJugador(context,jugador.x,jugador.y, jugador.orientacion));
                botonDispararPulsado = false;
            }

            for(Item item: items){
                item.actualizar(tiempo);
            }
            for(Enemigo enemigo: enemigos){
                enemigo.actualizar(tiempo);
                if(enemigo.dispara && enemigo.estado == Enemigo.ACTIVO){
                    gameView.gestorAudio.reproducirSonido(GestorAudio.SONIDO_DISPARO_ENEMIGO);
                    disparoEnemigos.add(new DisparoEnemigo(context, enemigo.x, enemigo.y, enemigo.orientacion));
                    enemigo.dispara = false;
                }
            }
            for(DisparoJugador disparoJugador: disparosJugador){
                disparoJugador.actualizar(tiempo);
            }
            for(DisparoEnemigo disparoEnemigo: disparoEnemigos){
                disparoEnemigo.actualizar(tiempo);
            }
            jugador.actualizar(tiempo);
            aplicarReglasMovimiento();
            gl_colisiona();
        }
    }

    private void aplicarReglasMovimiento() throws Exception {

        int tileXJugadorIzquierda = (int) (jugador.x - ((jugador.ancho / 2) / 2)) / Tile.ancho;
        int tileXJugadorDerecha = (int) (jugador.x + ((jugador.ancho / 2) / 2)) / Tile.ancho;
        int tileYJugadorInferior = (int) (jugador.y + ((jugador.altura / 2.1) / 2)) / Tile.altura;
        int tileYJugadorSuperior = (int) (jugador.y - ((jugador.altura / 2.1) / 2)) / Tile.altura;

        if(jugador.enElAire){
            // Recordar los ejes:
            // - es para arriba + es para abajo.
            jugador.velocidadY += velocidadGravedad;
            if(jugador.velocidadY > velocidadMaximaCaida){
                jugador.velocidadY = velocidadMaximaCaida;
            }
        }

        // Hacia arriba
        if(jugador.velocidadY < 0){
            // Tile superior PASABLE
            // Podemos seguir moviendo hacia arriba
            if (tileYJugadorSuperior-1 >= 0 &&
                    mapaTiles[tileXJugadorIzquierda][tileYJugadorSuperior-1].tipoDeColision
                            == Tile.PASABLE
                    && mapaTiles[tileXJugadorDerecha][tileYJugadorSuperior-1].tipoDeColision
                    == Tile.PASABLE){
                jugador.y += jugador.velocidadY;
                // Tile superior != de PASABLE
                // O es un tile SOLIDO, o es el TECHO del mapa
            } else {
                // Si en el propio tile del jugador queda espacio para
                // subir más, subo
                int TileJugadorBordeSuperior = (tileYJugadorSuperior)*Tile.altura;
                double distanciaY = (jugador.y - jugador.altura/2) - TileJugadorBordeSuperior;
                if( distanciaY > 0) {
                    jugador.y += Utilidades.proximoACero(-distanciaY, jugador.velocidadY);
                } else {
                    // Efecto Rebote -> empieza a bajar;
                    jugador.velocidadY = velocidadGravedad;
                    jugador.y += jugador.velocidadY;
                }
            }
        }

        // Hacia abajo
        if (jugador.velocidadY >= 0) {
            // Tile inferior PASABLE
            // Podemos seguir moviendo hacia abajo
            // NOTA - El ultimo tile es especial (caer al vacío )
            if (tileYJugadorInferior + 1 <= altoMapaTiles() - 1 &&
                    mapaTiles[tileXJugadorIzquierda][tileYJugadorInferior + 1].tipoDeColision
                            == Tile.PASABLE
                    && mapaTiles[tileXJugadorDerecha][tileYJugadorInferior + 1].tipoDeColision
                    == Tile.PASABLE) {
                // si los dos están libres cae
                jugador.y += jugador.velocidadY;
                jugador.enElAire = true; // Sigue en el aire o se cae
                // Tile inferior SOLIDO
                // El ULTIMO, es un caso especial
            } else if (tileYJugadorInferior + 1 <= altoMapaTiles() - 1 &&
                    (mapaTiles[tileXJugadorIzquierda][tileYJugadorInferior + 1].tipoDeColision
                            == Tile.SOLIDO ||
                            mapaTiles[tileXJugadorDerecha][tileYJugadorInferior + 1].tipoDeColision ==
                                    Tile.SOLIDO)) {
                // Con que uno de los dos sea solido ya no puede caer
                // Si en el propio tile del jugador queda espacio para bajar más, bajo
                int TileJugadorBordeInferior =
                        tileYJugadorInferior * Tile.altura + Tile.altura;
                double distanciaY =
                        TileJugadorBordeInferior - (jugador.y + jugador.altura / 2);
                jugador.enElAire = true; // Sigue en el aire o se cae
                if (distanciaY > 0) {
                    jugador.y += Math.min(distanciaY, jugador.velocidadY);
                } else {
                    // Toca suelo, nos aseguramos de que está bien
                    jugador.y = TileJugadorBordeInferior - jugador.altura / 2;
                    jugador.velocidadY = 0;
                    jugador.enElAire = false;
                }
                // Esta cayendo por debajo del ULTIMO
                // va a desaparecer y perder.
            } else {
                jugador.y += jugador.velocidadY;
                jugador.enElAire = true;
                if (jugador.y + jugador.altura / 2 > GameView.pantallaAlto) {
                    scrollEjeX = 0;
                    jugador.restablecerPosicionInicial();
                    perder();
                }
            }
        }

        // derecha o parado
        if (jugador.velocidadX > 0) {
            // Tengo un tile delante y es PASABLE
            // El tile de delante está dentro del Nivel
            if (tileXJugadorDerecha + 1 <= anchoMapaTiles() - 1 &&
                    tileYJugadorInferior <= altoMapaTiles() - 1 &&
                    mapaTiles[tileXJugadorDerecha + 1][tileYJugadorInferior].tipoDeColision ==
                            Tile.PASABLE &&
                    mapaTiles[tileXJugadorDerecha + 1][tileYJugadorSuperior].tipoDeColision ==
                            Tile.PASABLE &&
                    mapaTiles[tileXJugadorDerecha][tileYJugadorInferior].tipoDeColision ==
                            Tile.PASABLE &&
                    mapaTiles[tileXJugadorDerecha][tileYJugadorSuperior].tipoDeColision ==
                            Tile.PASABLE) {
                jugador.x += jugador.velocidadX;
                // No tengo un tile PASABLE delante
                // o es el FINAL del nivel o es uno SOLIDO
            } else if (tileXJugadorDerecha <= anchoMapaTiles() - 1 &&
                    tileYJugadorInferior <= altoMapaTiles() - 1 &&
                    mapaTiles[tileXJugadorDerecha][tileYJugadorInferior].tipoDeColision ==
                            Tile.PASABLE &&
                    mapaTiles[tileXJugadorDerecha][tileYJugadorSuperior].tipoDeColision ==
                            Tile.PASABLE) {
                // Si en el propio tile del jugador queda espacio para
                // avanzar más, avanzo
                int TileJugadorBordeDerecho = tileXJugadorDerecha * Tile.ancho + Tile.ancho;
                double distanciaX = TileJugadorBordeDerecho - (jugador.x + jugador.ancho / 2);
                if (distanciaX > 0) {
                    double velocidadNecesaria = Math.min(distanciaX, jugador.velocidadX);
                    jugador.x += velocidadNecesaria;
                } else {
                    // Opcional, corregir posición
                    jugador.x = TileJugadorBordeDerecho - jugador.ancho / 2;
                }
            }
        }
        // izquierda
        if (jugador.velocidadX <= 0) {
            // Tengo un tile detrás y es PASABLE
            // El tile de delante está dentro del Nivel
            if (tileXJugadorIzquierda - 1 >= 0 &&
                    tileYJugadorInferior < altoMapaTiles() - 1 &&
                    mapaTiles[tileXJugadorIzquierda - 1][tileYJugadorInferior].tipoDeColision ==
                            Tile.PASABLE &&
                    mapaTiles[tileXJugadorIzquierda - 1][tileYJugadorSuperior].tipoDeColision ==
                            Tile.PASABLE
                    && mapaTiles[tileXJugadorIzquierda][tileYJugadorInferior].tipoDeColision ==
                    Tile.PASABLE &&
                    mapaTiles[tileXJugadorIzquierda][tileYJugadorSuperior].tipoDeColision ==
                            Tile.PASABLE) {
                jugador.x += jugador.velocidadX;
                // No tengo un tile PASABLE detrás
                // o es el INICIO del nivel o es uno SOLIDO
            } else if (tileXJugadorIzquierda >= 0 && tileYJugadorInferior <= altoMapaTiles() - 1 &&
                    mapaTiles[tileXJugadorIzquierda][tileYJugadorInferior].tipoDeColision
                            == Tile.PASABLE &&
                    mapaTiles[tileXJugadorIzquierda][tileYJugadorSuperior].tipoDeColision
                            == Tile.PASABLE) {
                // Si en el propio tile del jugador queda espacio para
                // avanzar más, avanzo
                int TileJugadorBordeIzquierdo = tileXJugadorIzquierda * Tile.ancho;
                double distanciaX = (jugador.x - jugador.ancho / 2) - TileJugadorBordeIzquierdo;
                if (distanciaX > 0) {
                    double velocidadNecesaria = Utilidades.proximoACero(-distanciaX, jugador.velocidadX);
                    jugador.x += velocidadNecesaria;
                } else {
                    // Opcional, corregir posición
                    jugador.x = TileJugadorBordeIzquierdo + jugador.ancho / 2;
                }
            }
        }

        for (Iterator<Enemigo> iterator = enemigos.iterator();
             iterator.hasNext();) {
            Enemigo enemigo = iterator.next();
            if (enemigo.estado == EnemigoBasico.ELIMINAR){
                iterator.remove();
                continue;
            }

            if(enemigo.estado != EnemigoBasico.ACTIVO && !movimientoEnemigoAlMorir)
                continue;

            int tileXEnemigoIzquierda =
                    (int)(enemigo.x - ((enemigo.ancho/2)/2)) / Tile.ancho;
            int tileXEnemigoDerecha =
                    (int)(enemigo.x + ((enemigo.ancho/2)/2))/ Tile.ancho ;
            int tileYEnemigoInferior =
                    (int) (enemigo.y + ((enemigo.altura/2.1)/2)) / Tile.altura;
            int tileYEnemigoSuperior =
                    (int) (enemigo.y - ((enemigo.altura/2.1)/2)) / Tile.altura;

            if(enemigo.volador){

                if(enemigo.velocidadY > 0){
                    if (tileYEnemigoInferior + 1 < altoMapaTiles() &&
                            mapaTiles[tileXEnemigoDerecha][tileYEnemigoInferior].tipoDeColision ==
                                    Tile.PASABLE &&
                            mapaTiles[tileXEnemigoIzquierda][tileYEnemigoInferior].tipoDeColision ==
                                    Tile.PASABLE) {

                        enemigo.y += enemigo.velocidadY;
                    } else {
                        enemigo.girarY();
                    }
                }

                if(enemigo.velocidadY < 0){
                    if (tileYEnemigoSuperior - 1 > 0 &&
                            mapaTiles[tileXEnemigoIzquierda][tileYEnemigoSuperior].tipoDeColision ==
                                    Tile.PASABLE &&
                            mapaTiles[tileXEnemigoDerecha][tileYEnemigoSuperior].tipoDeColision ==
                                    Tile.PASABLE) {
                        enemigo.y += enemigo.velocidadY;

                    } else {
                        enemigo.girarY();
                    }
                }

                if((tileYEnemigoInferior + 1 > altoMapaTiles() && enemigo.velocidadY > 0)
                        || (tileYEnemigoSuperior - 1 < 0 && enemigo.velocidadY < 0)){

                    enemigo.girarY();

                }

                if(enemigo.velocidadX > 0){
                    if (tileXEnemigoDerecha + 1 < anchoMapaTiles() &&
                            (mapaTiles[tileXEnemigoDerecha + 1][tileYEnemigoInferior].tipoDeColision ==
                                    Tile.PASABLE ||
                            mapaTiles[tileXEnemigoDerecha + 1][tileYEnemigoSuperior].tipoDeColision ==
                                    Tile.PASABLE)) {

                        enemigo.x += enemigo.velocidadX;

                    } else {
                            enemigo.girarX();
                    }
                }

                if(enemigo.velocidadX < 0){
                    if (tileXEnemigoIzquierda - 1 >= 0 &&
                            (mapaTiles[tileXEnemigoIzquierda-1][tileYEnemigoInferior].tipoDeColision ==
                                    Tile.PASABLE ||
                            mapaTiles[tileXEnemigoIzquierda-1][tileYEnemigoSuperior].tipoDeColision ==
                                    Tile.PASABLE)) {
                        enemigo.x += enemigo.velocidadX;
                    } else {
                        enemigo.girarX();
                    }

                }

            }

            if(!enemigo.volador){

                if(enemigo.velocidadX > 0){
                    // Solo una condicion para pasar: Tile delante libre, el de abajo solido
                    if (tileXEnemigoDerecha + 1 <= anchoMapaTiles() - 1 &&
                            mapaTiles[tileXEnemigoDerecha + 1][tileYEnemigoInferior].tipoDeColision ==
                                    Tile.PASABLE &&
                            mapaTiles[tileXEnemigoDerecha + 1][tileYEnemigoSuperior].tipoDeColision ==
                                    Tile.PASABLE &&
                            mapaTiles[tileXEnemigoDerecha + 1][tileYEnemigoInferior + 1].tipoDeColision ==
                                    Tile.SOLIDO) {

                        enemigo.x += enemigo.velocidadX;
                        // Sino, me acerco al borde del que estoy
                    } else if ( tileYEnemigoInferior +1 < altoMapaTiles() && tileXEnemigoDerecha + 1 <= anchoMapaTiles() - 1 ) {
                        int TileEnemigoDerecho = tileXEnemigoDerecha*Tile.ancho + Tile.ancho ;
                        double distanciaX = TileEnemigoDerecho - (enemigo.x + enemigo.ancho/2);
                        if( distanciaX > 0) {
                            double velocidadNecesaria = Math.min(distanciaX, enemigo.velocidadX);
                            enemigo.x += velocidadNecesaria;
                        } else {
                            enemigo.girarX();
                        }
                        // No hay Tile, o es el final del mapa
                    } else {
                        enemigo.girarX();
                    }
                }

                if(enemigo.velocidadX < 0){
                    // Solo una condición para pasar: Tile izquierda pasable y suelo solido.
                    if (tileXEnemigoIzquierda - 1 >= 0 &&
                            mapaTiles[tileXEnemigoIzquierda-1][tileYEnemigoInferior].tipoDeColision ==
                                    Tile.PASABLE &&
                            mapaTiles[tileXEnemigoIzquierda-1][tileYEnemigoSuperior].tipoDeColision ==
                                    Tile.PASABLE &&
                            mapaTiles[tileXEnemigoIzquierda-1][tileYEnemigoInferior +1].tipoDeColision
                                    == Tile.SOLIDO) {
                        enemigo.x += enemigo.velocidadX;
                        // Solido / borde del tile acercarse.
                    } else if (tileYEnemigoInferior +1 < altoMapaTiles() && tileXEnemigoIzquierda -1 >= 0 ) {
                        int TileEnemigoIzquierdo= tileXEnemigoIzquierda*Tile.ancho ;
                        double distanciaX = (enemigo.x - enemigo.ancho/2) - TileEnemigoIzquierdo;
                        if( distanciaX > 0) {
                            double velocidadNecesaria =
                                    Utilidades.proximoACero(-distanciaX, enemigo.velocidadX);
                            enemigo.x += velocidadNecesaria;
                        } else {
                            enemigo.girarX();
                        }
                    } else {
                        enemigo.girarX();
                    }
                }

            }


            int rango = 2;
            if (tileXJugadorIzquierda - rango < tileXEnemigoIzquierda &&
                    tileXJugadorIzquierda + rango > tileXEnemigoIzquierda){

                if(jugador.colisiona(enemigo)){
                    if(jugador.golpeado() <= 0){
                        jugador.restablecerPosicionInicial();
                        scrollEjeX = 0;
                        perder();
                    }
                }
            }

        }

        for (Iterator<DisparoJugador> iterator = disparosJugador.iterator();
             iterator.hasNext();) {
            DisparoJugador disparoJugador = iterator.next();
            int tileXDisparo = (int)disparoJugador.x / Tile.ancho ;
            int tileYDisparoInferior =
                    (int) (disparoJugador.y + disparoJugador.cAbajo) / Tile.altura;
            int tileYDisparoSuperior =
                    (int) (disparoJugador.y - disparoJugador.cArriba) / Tile.altura;
            if(disparoJugador.velocidadX > 0){
                // Tiene delante un tile pasable, puede avanzar.
                if (tileXDisparo+1 <= anchoMapaTiles()-1 &&
                        mapaTiles[tileXDisparo+1][tileYDisparoInferior].tipoDeColision
                                == Tile.PASABLE &&
                        mapaTiles[tileXDisparo+1][tileYDisparoSuperior].tipoDeColision
                                == Tile.PASABLE ){
                    disparoJugador.x += disparoJugador.velocidadX;
                } else if (tileXDisparo <= anchoMapaTiles() - 1){
                    int TileDisparoBordeDerecho = tileXDisparo*Tile.ancho + Tile.ancho ;
                    double distanciaX =
                            TileDisparoBordeDerecho - (disparoJugador.x + disparoJugador.cDerecha);
                    if( distanciaX > 0) {
                        double velocidadNecesaria =
                                Math.min(distanciaX, disparoJugador.velocidadX);
                        disparoJugador.x += velocidadNecesaria;
                    } else {
                        iterator.remove();
                        continue;
                    }
                }
            }
            //izquierda
            if (disparoJugador.velocidadX <= 0){
                if (tileXDisparo-1 >= 0 &&
                        tileYDisparoSuperior < altoMapaTiles()-1 &&
                        mapaTiles[tileXDisparo-1][tileYDisparoSuperior].tipoDeColision ==
                                Tile.PASABLE &&
                        mapaTiles[tileXDisparo-1][tileYDisparoInferior].tipoDeColision ==
                                Tile.PASABLE){
                    disparoJugador.x += disparoJugador.velocidadX;
                    // No tengo un tile PASABLE detras
                    // o es el INICIO del nivel o es uno SOLIDO
                } else if(tileXDisparo >= 0 ){
                    // Si en el propio tile del jugador queda espacio para
                    // avanzar más, avanzo
                    int TileDisparoBordeIzquierdo = tileXDisparo*Tile.ancho ;
                    double distanciaX =
                            (disparoJugador.x - disparoJugador.cIzquierda) - TileDisparoBordeIzquierdo ;
                    if( distanciaX > 0) {
                        double velocidadNecesaria =
                                Utilidades.proximoACero(-distanciaX, disparoJugador.velocidadX);
                        disparoJugador.x += velocidadNecesaria;
                    } else {
                        iterator.remove();
                        continue;
                    }
                }
            }


            for(Enemigo enemigo : enemigos){
                if (enemigo.colisiona(disparoJugador)) {
                    enemigo.destruir();
                    gameView.gestorAudio.reproducirSonido(GestorAudio.SONIDO_ENEMIGO_MUERE);
                    iterator.remove();
                    break;
                }
            }

        }

        for (Iterator<DisparoEnemigo> iterator = disparoEnemigos.iterator();
             iterator.hasNext();) {
            DisparoEnemigo disparoEnemigo = iterator.next();
            int tileXDisparo = (int)disparoEnemigo.x / Tile.ancho ;
            int tileYDisparoInferior =
                    (int) (disparoEnemigo.y + disparoEnemigo.cAbajo) / Tile.altura;
            int tileYDisparoSuperior =
                    (int) (disparoEnemigo.y - disparoEnemigo.cArriba) / Tile.altura;
            if(disparoEnemigo.velocidadX > 0){
                // Tiene delante un tile pasable, puede avanzar.
                if (tileXDisparo+1 <= anchoMapaTiles()-1 &&
                        mapaTiles[tileXDisparo+1][tileYDisparoInferior].tipoDeColision
                                == Tile.PASABLE &&
                        mapaTiles[tileXDisparo+1][tileYDisparoSuperior].tipoDeColision
                                == Tile.PASABLE ){
                    disparoEnemigo.x += disparoEnemigo.velocidadX;
                } else if (tileXDisparo <= anchoMapaTiles() - 1){
                    int TileDisparoBordeDerecho = tileXDisparo*Tile.ancho + Tile.ancho ;
                    double distanciaX =
                            TileDisparoBordeDerecho - (disparoEnemigo.x + disparoEnemigo.cDerecha);
                    if( distanciaX > 0) {
                        double velocidadNecesaria =
                                Math.min(distanciaX, disparoEnemigo.velocidadX);
                        disparoEnemigo.x += velocidadNecesaria;
                    } else {
                        iterator.remove();
                        continue;
                    }
                }
            }
            //izquierda
            if (disparoEnemigo.velocidadX <= 0){
                if (tileXDisparo-1 >= 0 &&
                        tileYDisparoSuperior < altoMapaTiles()-1 &&
                        mapaTiles[tileXDisparo-1][tileYDisparoSuperior].tipoDeColision ==
                                Tile.PASABLE &&
                        mapaTiles[tileXDisparo-1][tileYDisparoInferior].tipoDeColision ==
                                Tile.PASABLE){
                    disparoEnemigo.x += disparoEnemigo.velocidadX;
                    // No tengo un tile PASABLE detras
                    // o es el INICIO del nivel o es uno SOLIDO
                } else if(tileXDisparo >= 0 ){
                    // Si en el propio tile del jugador queda espacio para
                    // avanzar más, avanzo
                    int TileDisparoBordeIzquierdo = tileXDisparo*Tile.ancho ;
                    double distanciaX =
                            (disparoEnemigo.x - disparoEnemigo.cIzquierda) - TileDisparoBordeIzquierdo ;
                    if( distanciaX > 0) {
                        double velocidadNecesaria =
                                Utilidades.proximoACero(-distanciaX, disparoEnemigo.velocidadX);
                        disparoEnemigo.x += velocidadNecesaria;
                    } else {
                        iterator.remove();
                        continue;
                    }
                }
            }


            if (jugador.colisiona(disparoEnemigo)) {
                jugador.golpeado();
                iterator.remove();
                break;
            }

        }

        if( jugador.colisiona(meta) ) {
            gameView.nivelCompleto();
        }

    }


    public void dibujar (Canvas canvas) {
        if(inicializado) {
            fondos[0].dibujar(canvas);
            fondos[1].dibujar(canvas);

            dibujarTiles(canvas);
            for(int i=0; i < jugador.vidas; i++)
                iconosVida[i].dibujar(canvas);
            meta.dibujar(canvas);
            marcador.dibujar(canvas);
            for(DisparoJugador disparoJugador: disparosJugador){
                disparoJugador.dibujar(canvas);
            }
            for(DisparoEnemigo disparoEnemigo: disparoEnemigos){
                disparoEnemigo.dibujar(canvas);
            }
            jugador.dibujar(canvas);
            for(Item item: items){
                item.dibujar(canvas);
            }
            for(Enemigo enemigo: enemigos){
                enemigo.dibujar(canvas);
            }
            if (nivelPausado){
                // la foto mide 480x320
                Rect orgigen = new Rect(0,0 ,
                        480,320);
                Paint efectoTransparente = new Paint();
                efectoTransparente.setAntiAlias(true);
                Rect destino = new Rect((int)(GameView.pantallaAncho/2 - 480/2),
                        (int)(GameView.pantallaAlto/2 - 320/2),
                        (int)(GameView.pantallaAncho/2 + 480/2),
                        (int)(GameView.pantallaAlto/2 + 320/2));
                canvas.drawBitmap(mensaje,orgigen,destino, null);
            }

        }
    }

    private void perder(){
        nivelPausado = true;
        mensaje = CargadorGraficos.cargarBitmap(context, R.drawable.you_lose);
        perder = true;
    }

    private void dibujarTiles(Canvas canvas) {
        /*
        // Calcular que tiles serán visibles en la pantalla
        // La matriz de tiles es más grande que la pantalla
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
                            y * Tile.altura,
                            x * Tile.ancho + Tile.ancho,
                            y * Tile.altura + Tile.altura);
                    mapaTiles[x][y].imagen.draw(canvas);
                }
            }
        }*/
        int tileXJugador = (int) jugador.x / Tile.ancho;
        int izquierda = (int) (tileXJugador - tilesEnDistanciaX(jugador.x - scrollEjeX));
        izquierda = Math.max(0,izquierda); // Que nunca sea < 0, ej -1
        if ( jugador .x <
                (anchoMapaTiles() - tilesEnDistanciaX( GameView.pantallaAncho*0.3)) * Tile.ancho )
            if( jugador .x - scrollEjeX > GameView.pantallaAncho * 0.7 ){
                fondos[0].mover((int) ((jugador .x - scrollEjeX) - GameView.pantallaAncho*
                        0.7));
                fondos[1].mover((int) ((jugador .x - scrollEjeX) - GameView.pantallaAncho*
                        0.7));

                scrollEjeX += (int) ((jugador .x - scrollEjeX) - GameView.pantallaAncho* 0.7);
            }
        if ( jugador .x >
                tilesEnDistanciaX(GameView.pantallaAncho*0.3) * Tile.ancho )
            if( jugador .x - scrollEjeX < GameView.pantallaAncho *0.3 ){
                fondos[0].mover(-(int) (GameView.pantallaAncho*0.3 -(jugador .x -
                        scrollEjeX)));
                fondos[1].mover(-(int) (GameView.pantallaAncho*0.3 -(jugador .x -
                        scrollEjeX)));

                scrollEjeX -= (int) (GameView.pantallaAncho*0.3 -(jugador .x - scrollEjeX));
            }
        int derecha = izquierda +
                GameView.pantallaAncho / Tile.ancho + 1;
// el ultimo tile visible, no puede superar el tamaño del mapa
        derecha = Math.min(derecha, anchoMapaTiles() - 1);

        for (int y = 0; y < altoMapaTiles() ; ++y) {
            for (int x = izquierda; x <= derecha; ++x) {
                if (mapaTiles[x][y].imagen != null) {
                    // Calcular la posici?n en pantalla correspondiente
                    // izquierda, arriba, derecha , abajo
                    mapaTiles[x][y].imagen.setBounds(
                            (x * Tile.ancho) - scrollEjeX,
                            y * Tile.altura,
                            (x * Tile.ancho) + Tile.ancho - scrollEjeX,
                            y * Tile.altura + Tile.altura);
                    mapaTiles[x][y].imagen.draw(canvas);
                }
            }
        }
    }

    private float tilesEnDistanciaX(double distanciaX){
        return (float) distanciaX/Tile.ancho;
    }

    private void inicializarMapaTiles() throws Exception {
        InputStream is = context.getAssets().open(numeroNivel+".txt");
        int anchoLinea;
        
        List<String> lineas = new LinkedList<String>();
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

    private Tile inicializarTile(char codigoTile, int x, int y) {

        int xCentroAbajoTile, yCentroAbajoTile;

        switch (codigoTile) {
            case 'E':
                // EnemigoBasico
                // Posición centro abajo
                int xCentroAbajoTileE = x * Tile.ancho + Tile.ancho/2;
                int yCentroAbajoTileE = y * Tile.altura + Tile.altura;
                enemigos.add(new EnemigoBasico(context,xCentroAbajoTileE,yCentroAbajoTileE));
                return new Tile(null, Tile.PASABLE);
            case 'v':
                // EnemigoBasico
                // Posición centro abajo
                xCentroAbajoTileE = x * Tile.ancho + Tile.ancho/2;
                yCentroAbajoTileE = y * Tile.altura + Tile.altura;
                enemigos.add(new EnemigoVolador(context,xCentroAbajoTileE,yCentroAbajoTileE));
                return new Tile(null, Tile.PASABLE);
            case 'd':
                // EnemigoBasico
                // Posición centro abajo
                xCentroAbajoTileE = x * Tile.ancho + Tile.ancho/2;
                yCentroAbajoTileE = y * Tile.altura + Tile.altura;
                enemigos.add(new EnemigoDispara(context,xCentroAbajoTileE,yCentroAbajoTileE));
                return new Tile(null, Tile.PASABLE);
            case '1':
                // Jugador
                // Posicion centro abajo
                xCentroAbajoTile = x * Tile.ancho + Tile.ancho/2;
                yCentroAbajoTile = y * Tile.altura + Tile.altura;
                jugador = new Jugador(context,xCentroAbajoTile,yCentroAbajoTile);

                return new Tile(null, Tile.PASABLE);
            case 'a':
                // Jugador
                // Posicion centro abajo
                xCentroAbajoTile = x * Tile.ancho + Tile.ancho/2;
                yCentroAbajoTile = y * Tile.altura + Tile.altura;
                items.add(new RecolectableItem(context,xCentroAbajoTile,yCentroAbajoTile));

                return new Tile(null, Tile.PASABLE);
            case '.':
                // en blanco, sin textura
                return new Tile(null, Tile.PASABLE);
            case '#':
                // bloque de musgo, no se puede pasar
                return new Tile(CargadorGraficos.cargarDrawable(context,
                        R.drawable.musgo), Tile.SOLIDO);
            case 'A':
                xCentroAbajoTile = x * Tile.ancho + Tile.ancho/2;
                yCentroAbajoTile = y * Tile.altura + Tile.altura;
                items.add(new PuntoGuardado(context,xCentroAbajoTile,yCentroAbajoTile));
                return new Tile(null, Tile.PASABLE);
            case 'M':
                int xCentroAbajoTileM = x * Tile.ancho + Tile.ancho/2;
                int yCentroAbajoTileM = y * Tile.altura + Tile.altura;
                meta = new Meta(context,xCentroAbajoTileM,yCentroAbajoTileM);
                return new Tile(null, Tile.PASABLE);

            default:
                //cualquier otro caso
                return new Tile(null, Tile.PASABLE);
        }

    }

}

