
var tipoMuro = 2;
var tipoBloque = 3;

var GameLayer = cc.Layer.extend({
    space:null,
    spritePelota:null,
    arrayBloques:[],
    formasEliminar: [],
    spriteFondo: null,
    numDisparos:3,
    tiempo:0,
    auxSize:null,
    ctor:function () {
        this._super();
        var size = cc.winSize;
        this.auxSize = size;

        // Inicializar Space
        this.space = new cp.Space();
        this.space.gravity = cp.v(0,0);

        //this.depuracion = new cc.PhysicsDebugNode(this.space); //quitar en produccion
        //this.addChild(this.depuracion, 10);

        // Muros
        var muroIzquierda = new cp.SegmentShape(this.space.staticBody,
            cp.v(0, 0),// Punto de Inicio
            cp.v(0, size.height),// Punto final
            10);// Ancho del muro
        muroIzquierda.setFriction(1);
        this.space.addStaticShape(muroIzquierda);

        var muroArriba = new cp.SegmentShape(this.space.staticBody,
            cp.v(0, size.height),// Punto de Inicio
            cp.v(size.width, size.height),// Punto final
            10);// Ancho del muro
        muroArriba.setFriction(1);
        this.space.addStaticShape(muroArriba);

        var muroDerecha = new cp.SegmentShape(this.space.staticBody,
            cp.v(size.width, 0),// Punto de Inicio
            cp.v(size.width, size.height),// Punto final
            10);// Ancho del muro
        muroDerecha.setFriction(1);
        this.space.addStaticShape(muroDerecha);

        var muroAbajo = new cp.SegmentShape(this.space.staticBody,
            cp.v(0, 0),// Punto de Inicio
            cp.v(size.width, 0),// Punto final
            10);// Ancho del muro
        muroAbajo.setFriction(1);
        muroAbajo.setCollisionType(tipoMuro);
        this.space.addStaticShape(muroAbajo);

        // muro y bloque
        this.space.addCollisionHandler(tipoMuro, tipoBloque,
                    null, null, this.collisionBloqueConMuro.bind(this), null);

        // Fondo
        this.spriteFondo = cc.Sprite.create(res.fondo_png);
        this.spriteFondo.setPosition(cc.p(size.width/2 , size.height/2));
        this.spriteFondo.setScale( size.width / this.spriteFondo.width );
        this.addChild(this.spriteFondo);

        // cache
        cc.spriteFrameCache.addSpriteFrames(res.animacion_bola_plist);
        cc.spriteFrameCache.addSpriteFrames(res.barra_3_plist);
        cc.spriteFrameCache.addSpriteFrames(res.animacioncocodrilo_plist);

        this.inicializarPlataformas();
        this.inicializarBloques();

        this.spritePelota = new cc.PhysicsSprite("#animacion_bola1.png");

        var body = new cp.Body(1, cp.momentForCircle(1, 0, this.spritePelota.width/2, cp.vzero));
        body.p = cc.p(size.width*0.1 , size.height*0.5);

        this.spritePelota.setBody(body);
        this.space.addBody(body);

        var shape = new cp.CircleShape(body, this.spritePelota.width/2, cp.vzero);
        shape.setFriction(1);
        this.space.addShape(shape);

        this.addChild(this.spritePelota);



        // Evento MOUSE
        cc.eventManager.addListener({
            event: cc.EventListener.MOUSE,
            onMouseDown: this.procesarMouseDown
        }, this);

        this.scheduleUpdate();
        return true;

    },procesarMouseDown:function(event) {
             // Ambito procesarMouseDown
             var instancia = event.getCurrentTarget();

             if(instancia.tiempo == 0 ){

                 instancia.space.gravity = cp.v(0, -350);
                 instancia.tiempo = new Date().getTime();

                 var body = instancia.spritePelota.body;
                 body.applyImpulse(cp.v( (event.getLocationX() - body.p.x), (event.getLocationY() - body.p.y)), cp.v(0,0));

                 if(instancia.numDisparos > 0){

                     instancia.numDisparos--;
                     console.log(instancia.numDisparos);
                 }

             }

     },update:function (dt) {
        this.space.step(dt);

        for(var i = 0; i < this.formasEliminar.length; i++) {
                var shape = this.formasEliminar[i];

            for (var i = 0; i < this.arrayBloques.length; i++) {
              if (this.arrayBloques[i].body.shapeList[0] == shape) {
                  // quita la forma
                  this.space.removeShape(shape);
                  // quita el cuerpo *opcional, funciona igual
                  this.space.removeBody(shape.getBody());
                  // quita el sprite
                  this.arrayBloques[i].removeFromParent();
                  // Borrar tambien de ArrayBloques
                  this.arrayBloques.splice(i, 1);
              }
            }
        }
        this.formasEliminar = [];

        if( this.arrayBloques.length > 0){

            var todosSinMoverse = true;
            for(var i = 0; i < this.arrayBloques.length; i++) {
                var velocidadBloque = this.arrayBloques[i].body.getVel();
                if( velocidadBloque.x < -0.09 && velocidadBloque.x > 0.09 ){
                    // NO - ESTE SE MUEVE
                    todosSinMoverse = false;
                }
            }
            if ( this.numDisparos == 0 && this.tiempo != 0 && (new Date().getTime() - this.tiempo) > 10000 && todosSinMoverse ){
                cc.director.pause();
                cc.audioEngine.stopMusic();
                this.getParent().addChild(new GameOverLayer());

            }
            else if( this.numDisparos > 0 && (new Date().getTime() - this.tiempo) > 10000 && todosSinMoverse){
                this.spritePelota.getBody().p = cc.p(this.auxSize.width*0.1 , this.auxSize.height*0.5);
                this.space.gravity = cp.v(0,0);
                this.spritePelota.getBody().w = 0;
                for(var j = 0; j < this.arrayBloques.length; j++){
                    this.arrayBloques[j].getBody().vx = this.arrayBloques[j].getBody().vy = 0;
                }
                this.tiempo = 0;
            }

        } else { //  arrayBloques.length == 0
            cc.director.pause();
            cc.audioEngine.stopMusic();
            this.getParent().addChild(new GameOverLayer());
        }



     },inicializarPlataformas:function () {

            var spritePlataforma = new cc.PhysicsSprite("#barra_3.png");

            var body = new cp.StaticBody();
            body.p = cc.p(cc.winSize.width*0.7 , cc.winSize.height*0.4);
            spritePlataforma.setBody(body);
             // Los cuerpos estáticos no se añaden al espacio
            //this.space.addBody(body);

            var shape = new cp.BoxShape(body, spritePlataforma.width, spritePlataforma.height);
            shape.setFriction(1);
            // addStaticShape en lugar de addShape
            this.space.addStaticShape(shape);

            this.addChild(spritePlataforma);

     },inicializarBloques:function () {

           var altoTorre = 0;
           while(altoTorre < 6){

                 var spriteBloque = new cc.PhysicsSprite("#cocodrilo1.png");

                 // Masa 1
                 var body = new cp.Body(1, cp.momentForBox(1, spriteBloque.width, spriteBloque.height));

                 body.p = cc.p(cc.winSize.width*0.7 , cc.winSize.height*0.4 + 10 + 20 + spriteBloque.height*altoTorre);
                 spriteBloque.setBody(body);
                 // Este si hay que añadirlo
                 this.space.addBody(body);

                 var shape = new cp.BoxShape(body, spriteBloque.width, spriteBloque.height);
                 shape.setFriction(1);
                 shape.setCollisionType(tipoBloque);
                 this.space.addShape(shape);
                 this.addChild(spriteBloque);

                 // agregar el Sprite al array Bloques
                 this.arrayBloques.push(spriteBloque);

                 altoTorre++;

           }

     },collisionBloqueConMuro:function (arbiter, space) {
         var shapes = arbiter.getShapes();
         // shapes[0] es el muro
         this.formasEliminar.push(shapes[1]);
     }


});

var GameScene = cc.Scene.extend({
    onEnter:function () {
        this._super();
        cc.director.resume();
        cc.audioEngine.playMusic(res.sonidobucle_wav, true);
        var layer = new GameLayer();
        this.addChild(layer);
    }
});

