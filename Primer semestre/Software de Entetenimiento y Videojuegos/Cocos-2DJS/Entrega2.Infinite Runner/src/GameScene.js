
var tipoSuelo = 1;
var tipoJugador = 2;
var tipoMoneda = 3;
var tipoEnemigo = 4;
var tipoPincho = 5;
var tipoMeta = 6;

var nivel = 0;
var niveles = [res.mapa2_tmx, res.mapa1_tmx];

var GameLayer = cc.Layer.extend({
    _emitter: null,
    tiempoEfecto:0,
    space:null,
    mapa: null,
    jugador: null,
    mapaAncho: null,
    formasEliminar:[],
    monedas:[],
    enemigos:[],
    pinchos:[],
    ctor:function () {
        this._super();
        var size = cc.winSize;

        cc.spriteFrameCache.addSpriteFrames(res.moneda_plist);
        cc.spriteFrameCache.addSpriteFrames(res.jugador_subiendo_plist);
        cc.spriteFrameCache.addSpriteFrames(res.jugador_avanzando_plist);
        cc.spriteFrameCache.addSpriteFrames(res.animacion_cuervo_plist);

        // Inicializar Space
        this.space = new cp.Space();
        this.space.gravity = cp.v(0, -650);

        // Depuración
        /*this.depuracion = new cc.PhysicsDebugNode(this.space);
        this.addChild(this.depuracion, 10);*/

        this.jugador = new Jugador(this.space, cc.p(50,250), this);

        this.cargarMapa();
        this.scheduleUpdate();

        // suelo y jugador
        this.space.addCollisionHandler(tipoSuelo, tipoJugador,
              null, null, this.collisionSueloConJugador.bind(this), null);

        this.space.addCollisionHandler(tipoJugador, tipoMoneda,
              null, this.collisionJugadorConMoneda.bind(this), null, null);

        this.space.addCollisionHandler(tipoJugador, tipoEnemigo,
                      null, null, this.collisionQueQuitaVidaAlJugador.bind(this), null);

        this.space.addCollisionHandler(tipoJugador, tipoPincho,
                              null, this.collisionQueQuitaVidaAlJugador.bind(this), null, null);

        this.space.addCollisionHandler(tipoJugador, tipoMeta,
                                     null, this.acabarNivel.bind(this), null, null);

        // Declarar emisor de particulas (parado)
            this._emitter =  new cc.ParticleGalaxy.create();
            this._emitter.setEmissionRate(0);
            //this._emitter.texture = cc.textureCache.addImage(res.fire_png);
            this._emitter.shapeType = cc.ParticleSystem.STAR_SHAPE;
            this.addChild(this._emitter,10);

        return true;
    },update:function (dt) {
        this.space.step(dt);

        for (var i = 0; i < this.enemigos.length; i++) {
            this.enemigos[i].update(dt, this.jugador.getBody().p.x);
        }

        // Control de emisor de particulas
        if (this.tiempoEfecto > 0){
             this.tiempoEfecto = this.tiempoEfecto - dt;
             this._emitter.x =  this.jugador.getBody().p.x;
             this._emitter.y =  this.jugador.getBody().p.y;

        }
        if (this.tiempoEfecto < 0) {
             this._emitter.setEmissionRate(0);
             this.tiempoEfecto = 0;
        }


        // obtener el body del jugador
        var bodyJugador = this.jugador.getBody();
        // Controlar el angulo (son radianes).
        if ( bodyJugador.a > 0.44 ){
            bodyJugador.setAngle(0.44);
        }
        if ( bodyJugador.a < -0.44){
            bodyJugador.setAngle(-0.44);
        }
        // controlar la velocidad
        if (bodyJugador.getVel().x < 350){
            bodyJugador.setVel(cp.v(350, bodyJugador.getVel().y));
        }
        if (bodyJugador.getVel().x > 550){
            bodyJugador.setVel(cp.v(550, bodyJugador.getVel().y));
        }

        // actualizar camara (posición de la capa).
        var posicionXJugador = this.jugador.getBody().p.x - 100;

        if(this.jugador.getBody().p.y > 20){
            var posicionYJugador = this.jugador.getBody().p.y - (this.jugador.getBody().p.y / 2.25);
        }
        else{
            var posicionYJugador = 20;
        }

        this.setPosition(cc.p( -posicionXJugador, -posicionYJugador));

        // Caída, sí cae vuelve a la posición inicial
        if( this.jugador.getBody().p.y < -100){
            this.reiniciarNivel();
        }

        // Eliminar formas:
         for(var i = 0; i < this.formasEliminar.length; i++) {
            var shape = this.formasEliminar[i];

            for (var i = 0; i < this.monedas.length; i++) {
              if (this.monedas[i].getShape() == shape) {
                  this.monedas[i].eliminar();
                  this.monedas.splice(i, 1);
              }
            }
        }
        this.formasEliminar = [];


    },cargarMapa:function () {
        this.mapa = new cc.TMXTiledMap(niveles[nivel]);
        // Añadirlo a la Layer
        this.addChild(this.mapa);
        // Ancho del mapa
        this.mapaAncho = this.mapa.getContentSize().width;

        // Solicitar los objeto dentro de la capa Suelos
        var grupoSuelos = this.mapa.getObjectGroup("Suelos");
        var suelosArray = grupoSuelos.getObjects();

        // Los objetos de la capa suelos se transforman a
        // formas estáticas de Chipmunk ( SegmentShape ).
        for (var i = 0; i < suelosArray.length; i++) {
            var suelo = suelosArray[i];
            var puntos = suelo.polylinePoints;
            for(var j = 0; j < puntos.length - 1; j++){
                var bodySuelo = new cp.StaticBody();

                var shapeSuelo = new cp.SegmentShape(bodySuelo,
                    cp.v(parseInt(suelo.x) + parseInt(puntos[j].x),
                        parseInt(suelo.y) - parseInt(puntos[j].y)),
                    cp.v(parseInt(suelo.x) + parseInt(puntos[j + 1].x),
                        parseInt(suelo.y) - parseInt(puntos[j + 1].y)),
                    10);
                shapeSuelo.setCollisionType(tipoSuelo);

                this.space.addStaticShape(shapeSuelo);
            }
        }

        // Solicitar los objeto dentro de la capa Suelos
        var grupoMeta = this.mapa.getObjectGroup("Meta");
        var metaArray = grupoMeta.getObjects();

        // Los objetos de la capa suelos se transforman a
        // formas estáticas de Chipmunk ( SegmentShape ).
        for (var i = 0; i < metaArray.length; i++) {
            var meta = metaArray[i];
            var puntos = meta.polylinePoints;
            for(var j = 0; j < puntos.length - 1; j++){
                var bodyMeta = new cp.StaticBody();

                var shapeMeta = new cp.SegmentShape(bodyMeta,
                    cp.v(parseInt(meta.x) + parseInt(puntos[j].x),
                        parseInt(meta.y) - parseInt(puntos[j].y)),
                    cp.v(parseInt(meta.x) + parseInt(puntos[j + 1].x),
                        parseInt(meta.y) - parseInt(puntos[j + 1].y)),
                    10);
                shapeMeta.setCollisionType(tipoMeta);

                this.space.addStaticShape(shapeMeta);
            }
        }

        var grupoMonedas = this.mapa.getObjectGroup("Monedas");
        var monedasArray = grupoMonedas.getObjects();
        for (var i = 0; i < monedasArray.length; i++) {
            var moneda = new Moneda(this.space,
                cc.p(monedasArray[i]["x"],monedasArray[i]["y"]),
                this);

            this.monedas.push(moneda);
        }

        var grupoEnemigos = this.mapa.getObjectGroup("Enemigos");
        var enemigosArray = grupoEnemigos.getObjects();
        for (var i = 0; i < enemigosArray.length; i++) {
            var enemigo = new Enemigo(this.space,
                cc.p(enemigosArray[i]["x"],enemigosArray[i]["y"]),
                this);

            this.enemigos.push(enemigo);
        }

        var grupoPinchos = this.mapa.getObjectGroup("Pinchos");
        var pinchosArray = grupoPinchos.getObjects();
        for (var i = 0; i < pinchosArray.length; i++) {
            var pincho = new Pincho(this.space,
                cc.p(pinchosArray[i]["x"],pinchosArray[i]["y"]),
                this);

            this.pinchos.push(pincho);
        }

    },collisionSueloConJugador:function (arbiter, space) {

        this.jugador.tocaSuelo();

    },collisionJugadorConMoneda:function (arbiter, space) {
          // Impulso extra
          this.jugador.getBody().applyImpulse(cp.v(300, 0), cp.v(0, 0));

          // Marcar la moneda para eliminarla
          var shapes = arbiter.getShapes();
          // shapes[0] es el jugador
          this.formasEliminar.push(shapes[1]);
          var capaControles =  this.getParent().getChildByTag(idCapaControles);
                      capaControles.agregarMoneda();

          this._emitter.setEmissionRate(5);
          this.tiempoEfecto = 3;

    },collisionQueQuitaVidaAlJugador:function (arbiter, space) {

        if(new Date().getTime() - this.jugador.tiempoColision > this.jugador.tiempoInvencible){

            this.jugador.vida--;

            if(this.jugador.vida <= 0){
                this.reiniciarNivel();
            }
            else{
                this.getParent().getChildByTag(idCapaControles).setVida(this.jugador.vida);

                var parpadeo = cc.blink(0.75,3);
                this.jugador.sprite.runAction(parpadeo);

                this.jugador.tiempoColision = new Date().getTime();
            }

        }


    }, reiniciarNivel:function(){
         cc.director.pause();
         this.getParent().addChild(new GameOverLayer());
    }, acabarNivel:function (arbiter, space){
        cc.director.pause();
        this.getParent().addChild(new GameVictoryLayer());
    }



});

var idCapaJuego = 1;
var idCapaControles = 2;

var GameScene = cc.Scene.extend({
    onEnter:function () {
        this._super();
        cc.director.resume();
        var layer = new GameLayer();
        this.addChild(layer, 0, idCapaJuego);
        var controlesLayer = new ControlesLayer();
        this.addChild(controlesLayer, 0, idCapaControles);
    }
});