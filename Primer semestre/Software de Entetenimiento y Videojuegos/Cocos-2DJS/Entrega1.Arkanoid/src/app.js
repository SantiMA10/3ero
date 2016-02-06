
var numBoques = 5,
    nuevaVelocidadX = 6.
    nuevoVelocidadY = 3;

var GameLayer = cc.Layer.extend({
    spritePelota:null,
    spriteBarra:null,
    keyPulsada:null,
    velocidadX: null,
    velocidadY: null,
    arrayBloques:[],
    ctor:function () {
        this._super();
        var size = cc.winSize;

        this.velocidadX = nuevaVelocidadX;
        this.velocidadY = nuevoVelocidadY;

        this.spritePelota = cc.Sprite.create(res.bola_png);
        this.spritePelota.setPosition(cc.p(size.width/2, size.height/2));
        this.addChild(this.spritePelota);

        this.spriteBarra = cc.Sprite.create(res.barra_1_png);
        this.spriteBarra.setPosition(cc.p(size.width/2, size.height*0.1));
        this.addChild(this.spriteBarra);

        var spriteFondo = cc.Sprite.create(res.fondo_png);
        spriteFondo.setPosition(cc.p(size.width/2 , size.height/2));
        spriteFondo.setScale( size.width / spriteFondo.width );
        this.addChild(spriteFondo, -1);

        cc.spriteFrameCache.addSpriteFrames(res.animacioncocodrilo_plist);
        cc.spriteFrameCache.addSpriteFrames(res.animacionpanda_plist);
        cc.spriteFrameCache.addSpriteFrames(res.animaciontigre_plist);

        this.inicializarBloques();

//        var actionMoverPelota1 = cc.MoveBy.create(1, cc.p(100, 0));
//        var actionMoverPelota2 = cc.MoveBy.create(1, cc.p(0, 100));
//        var secuencia = cc.Sequence.create(actionMoverPelota1,actionMoverPelota2);
//        this.spritePelota.runAction(secuencia);

        cc.eventManager.addListener({
            event: cc.EventListener.MOUSE,
            onMouseDown: this.procesarMouseDown
        }, this);

        cc.eventManager.addListener({
            event: cc.EventListener.KEYBOARD,
            onKeyPressed: function(keyCode, event){

            var actionMoverBarraX = null;
            var instancia = event.getCurrentTarget();

            if(instancia.keyPulsada == keyCode){
                return;
            }
            instancia.keyPulsada = keyCode;

            if( keyCode == 37){
               console.log("Ir izquierda ");
               actionMoverBarraX =
               cc.MoveTo.create(Math.abs(instancia.spriteBarra.x - 0)/500,
               cc.p(0,cc.winSize.height*0.1));
            }
            if( keyCode == 39){
                console.log("Ir derecha ");
                actionMoverBarraX =
                cc.MoveTo.create(Math.abs(instancia.spriteBarra.x -
                cc.winSize.width)/500,
                cc.p(cc.winSize.width,cc.winSize.height*0.1));
            }
                cc.director.getActionManager().
                removeAllActionsFromTarget(instancia.spriteBarra, true);
            if( actionMoverBarraX != null)
                instancia.spriteBarra.runAction(actionMoverBarraX);
            },
                onKeyReleased: function(keyCode, event){
                    if(keyCode == 37 || keyCode == 39){
                        var instancia = event.getCurrentTarget();
                        cc.director.getActionManager().
                        removeAllActionsFromTarget(instancia.spriteBarra, true);
                        instancia.keyPulsada = null;
                    }
                }
            }, this);


        this.scheduleUpdate();

        return true;

},procesarMouseDown:function(event) {
    //alert(":)");
    console.log(event.getLocationX());
    console.log(event.getLocationY());

    var actionMoverPelotaAPunto = cc.MoveTo.create(1, cc.p(event.getLocationX(),event.getLocationY()));
    // Ambito procesarMouseDown
    var instancia = event.getCurrentTarget();
    instancia.spritePelota.runAction(actionMoverPelotaAPunto);

}, inicializarBloques:function() {
     var insertados = 0;
     var fila = 0;
     var columna = 0;

     var animacionesBloque = [];
     var animaciones = ["panda", "tigre", "cocodrilo"];
     for(var j = 0; j < animaciones.length; j++){
        var framesBloque = [];
        for (var i = 1; i <= 8; i++) {
            var str = animaciones[j] + i + ".png";
            var frame = cc.spriteFrameCache.getSpriteFrame(str);
            framesBloque.push(frame);
        }
        animacionesBloque.push(new cc.Animation(framesBloque, 0.1));
     }

    this.arrayBloques = [];
     while (insertados < numBoques ){
        var rand = Math.floor(Math.random() * animaciones.length);
        var accionAnimacionBloque = new cc.RepeatForever(new cc.Animate(animacionesBloque[rand]));

        console.log(animaciones[rand]);

        //sin cachear
        //var spriteBloqueActual = cc.Sprite.create(res.cocodrilo_1_png);
        //cacheado
        var spriteBloqueActual = new cc.Sprite("#" + animaciones[rand] + "1.png");
        spriteBloqueActual.runAction(accionAnimacionBloque);

        var x = ( spriteBloqueActual.width / 2 ) +
        ( spriteBloqueActual.width * columna );
        var y = (cc.winSize.height - spriteBloqueActual.height/2 ) -
        ( spriteBloqueActual.height * fila );

        console.log("Insertado en: "+x+" ,"+y);

        spriteBloqueActual.setPosition(cc.p(x,y));

        this.arrayBloques.push(spriteBloqueActual);
        this.addChild(spriteBloqueActual);

        insertados++;
        columna++;

        if( x + spriteBloqueActual.width / 2 > cc.winSize.width){
            columna = 0;
            fila++;
        }
     }
}, update: function(dt){

    var mitadAncho = this.spritePelota.getContentSize().width/2;
    var mitadAlto = this.spritePelota.getContentSize().height/2;

     // Nuevas posiciones
     this.spritePelota.x = this.spritePelota.x + this.velocidadX;
     this.spritePelota.y = this.spritePelota.y + this.velocidadY;

     var areaPelota = this.spritePelota.getBoundingBox();
     var areaBarra = this.spriteBarra.getBoundingBox();

     if( cc.rectIntersectsRect(areaPelota, areaBarra)){
          console.log("Colision");

          this.velocidadX = ( this.spritePelota.x - this.spriteBarra.x ) / 5;
          this.velocidadY = this.velocidadY*-1;
     }

    destruido = false;
    for(var i = 0; i < this.arrayBloques.length; i++){
         var areaBloque = this.arrayBloques[i].getBoundingBox();
         if( cc.rectIntersectsRect(areaPelota, areaBloque)){
             this.removeChild(this.arrayBloques[i]);
             this.arrayBloques.splice(i, 1);
             console.log("Quedan : "+this.arrayBloques.length);
             destruido = true;
             cc.audioEngine.playEffect(res.grunt_wav);
         }
    }

    if(destruido){
        this.velocidadX = this.velocidadX*-1;
        this.velocidadY = this.velocidadY*-1;
    }

     // Rebote
     if (this.spritePelota.x < 0 + mitadAncho){
         this.spritePelota.x = 0 + mitadAncho;
         this.velocidadX = this.velocidadX*-1;
     }
     if (this.spritePelota.x > cc.winSize.width - mitadAncho){
         this.spritePelota.x = cc.winSize.width - mitadAncho;
         this.velocidadX = this.velocidadX*-1;
     }
     if (this.spritePelota.y < 0 + mitadAlto){
         /*this.spritePelota.y > cc.winSize.height + mitadAlto;
         this.velocidadY = this.velocidadY*-1;*/
         cc.audioEngine.stopMusic();
         cc.director.pause();
         this.addChild(new GameOverLayer());
     }
     if (this.spritePelota.y > cc.winSize.height - mitadAncho){
         this.spritePelota.y > cc.winSize.height - mitadAncho;
         this.velocidadY = this.velocidadY*-1;
     }

     if(this.arrayBloques.length == 0){
        cc.audioEngine.stopMusic();
        cc.director.pause();
        this.addChild(new GameVictoryLayer());
     }
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

