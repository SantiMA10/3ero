
var SelectorLayer = cc.Layer.extend({
    ctor:function () {
        this._super();
        var size = cc.winSize;

        // Fondo
        var spriteFondoTitulo= new cc.Sprite(res.menu_titulo_png);
        // Asigno posición central
        spriteFondoTitulo.setPosition(cc.p(size.width / 2, size.height / 2));
        // Lo escalo porque es más pequeño que la pantalla
        spriteFondoTitulo.setScale(size.height / spriteFondoTitulo.height);
        // Añado Sprite a la escena
        this.addChild(spriteFondoTitulo);

        //MenuItemSprite para cada botón
        var menuBotonNivel1 = new cc.MenuItemSprite(
            new cc.Sprite(res.boton_nivel_1_png), // IMG estado normal
            new cc.Sprite(res.boton_nivel_2_png), // IMG estado pulsado
            this.pulsarBotonJugarNivel1, this);
        menuBotonNivel1.setPosition(new cc.p(size.width/2,(size.height*0.25)+50));

        var menuBotonNivel2 = new cc.MenuItemSprite(
            new cc.Sprite(res.boton_nivel_2_png), // IMG estado normal
            new cc.Sprite(res.boton_nivel_2_png), // IMG estado pulsado
            this.pulsarBotonJugarNivel2, this);
        menuBotonNivel2.setPosition(new cc.p(size.width/2,size.height*0.25));


        // creo el menú pasándole los botones
        var menu = new cc.Menu(menuBotonNivel1, menuBotonNivel2);
        // Asigno posición central
        menu.setPosition(cc.p(0,0));
        // Añado el menú a la escena
        this.addChild(menu);


        return true;
    }, pulsarBotonJugarNivel1 : function(){
        nivel = 0;
        cc.director.runScene(new GameScene());
    }, pulsarBotonJugarNivel2 : function(){
        nivel = 1;
        cc.director.runScene(new GameScene());
    }

});

var SelectorScene = cc.Scene.extend({
    onEnter:function () {
        this._super();
        var layer = new SelectorLayer();
        this.addChild(layer);
    }
});