var Pincho = cc.Class.extend({
    space:null,
    sprite:null,
    shape:null,
    layer:null,
ctor:function (space, posicion, layer) {
    this.space = space;
    this.layer = layer;

    // Cuerpo estática , no le afectan las fuerzas
    var body = new cp.StaticBody();
    body.setPos(posicion);
    // Los cuerpos estáticos nunca se añaden al Space
    this.shape = new cp.BoxShape(body, 32, 32);
    this.shape.setCollisionType(tipoPincho);
    // Nunca genera colisiones reales
    this.shape.setSensor(true);
    // forma estática
    this.space.addStaticShape(this.shape);
    // añadir sprite a la capa

}, getShape: function (){
      return this.shape;

 }

});
