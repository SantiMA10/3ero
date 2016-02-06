package com.simple.gestores;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by santiagomartin on 27/9/15.
 */
public class GestorAudio implements MediaPlayer.OnPreparedListener {

    public static final int SONIDO_DISPARO_NAVE = 1;
    public static final int SONIDO_HIT_NAVE = 4;
    public static final int SONIDO_DISPARO_ENEMIGO = 2;
    public static final int SONIDO_EXPLOSION_ENEMIGO = 3;


    private static GestorAudio instancia = null;
    // Pool de sonidos, para efectos de sonido.
    // Suele fallar el utilizar ficheros de sonido demasiado grandes
    private SoundPool poolSonidos;
    private HashMap<Integer, Integer> mapSonidos;
    private Context contexto;
    // Media Player para bucle de sonido de fondo.
    private MediaPlayer sonidoAmbiente;
    private AudioManager gestorAudio;

    private GestorAudio() {

    }

    public void onPrepared(MediaPlayer mp){

        mp.start();

    }

    public static GestorAudio getInstancia(Context contexto, int idMusicaAmbiente) {
        synchronized (GestorAudio.class) {
            if (instancia == null) {
                instancia = new GestorAudio();
                instancia.initSounds(contexto, idMusicaAmbiente);
            }
            return instancia; }
    }

    public static GestorAudio getInstancia() {

        return instancia;

    }

    public void initSounds(Context context, int idMusicaAmbiente) {

        this.contexto = context;
        poolSonidos = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        mapSonidos = new HashMap<Integer, Integer>();
        gestorAudio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        sonidoAmbiente = MediaPlayer.create(context, idMusicaAmbiente);
        sonidoAmbiente.setLooping(true);
        sonidoAmbiente.setVolume(1, 1);

    }

    public void reproducirMusicaAmbiente() {

        try{

            if(!sonidoAmbiente.isPlaying()) {

                sonidoAmbiente.setOnPreparedListener(this);
                sonidoAmbiente.prepareAsync();

            }

        } catch (Exception e) {
        }

    }

    public  void pararMusicaAmbiente(){

        if(sonidoAmbiente.isPlaying()){

            sonidoAmbiente.stop();

        }

    }

    public void registrarSonido(int index, int SoundID) {

        mapSonidos.put(index, poolSonidos.load(contexto, SoundID, 1));

    }

    public void reproducirSonido(int index){

        float volumen = gestorAudio.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumen = volumen / gestorAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        poolSonidos.play((Integer) mapSonidos.get(index), volumen, volumen, 1, 0, 1f);

    }

}
