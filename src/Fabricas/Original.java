package SnowBros.src.Fabricas;

import java.util.HashMap;

import SnowBros.src.Grafica.Sprite;

public class Original extends FabricaSprites{

    public Original() {
        super("SnowBros/src/Imagenes/Original");
    }
    public Sprite get_silueta() {
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/FondoNivelOriginal.jpg");
        return new Sprite(mapeo);	     	
    }
    public Sprite getSpriteJugador(){//podemos reducir sprites de jugadores
        HashMap<String, String> mapeo = new HashMap<String,String>();
        mapeo.put("quietoIzquierda", rutaACarpeta+ "/Jugador/nickIzquierda.png");
        mapeo.put("quietoDerecha", rutaACarpeta+ "/Jugador/nickDerecha.png");
        mapeo.put("moverIzquierda1", rutaACarpeta+ "/Jugador/nickAvanzarIzquierda1.png");
        mapeo.put("moverIzquierda2", rutaACarpeta+ "/Jugador/nickAvanzarIzquierda.png");
        mapeo.put("moverIzquierda3", rutaACarpeta+ "/Jugador/nickAvanzarIzquierda3.png");
        mapeo.put("moverDerecha1", rutaACarpeta+ "/Jugador/nickAvanzarDerecha1.png");
        mapeo.put("moverDerecha2", rutaACarpeta+ "/Jugador/nickAvanzarDerecha.png");
        mapeo.put("moverDerecha3", rutaACarpeta+ "/Jugador/nickAvanzarDerecha3.png");
        mapeo.put("saltandoDerecha", rutaACarpeta+ "/Jugador/JugadorSaltandoDerecha.png");
        mapeo.put("saltandoIzquierda", rutaACarpeta+ "/Jugador/JugadorSaltandoIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataforma(){
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/Plataforma/plataforma.jpg"); 
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDemonioRojo(){
        HashMap<String, String> mapeo= new HashMap<>();//dejamos tanta animacion o la reducimos a uno en cada direccion para caminar y estar quieto?
        mapeo.put("quietoIzquierda", rutaACarpeta + "/DemonioRojo/quietoIzquierda.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/DemonioRojo/quietoDerecha.png");
        mapeo.put("moverDerecha1", rutaACarpeta + "/DemonioRojo/caminarDerecha1.png");
        mapeo.put("moverDerecha2", rutaACarpeta + "/DemonioRojo/caminarDerecha2.png");
        mapeo.put("moverDerecha3", rutaACarpeta + "/DemonioRojo/caminarDerecha3.png");
        mapeo.put("moverIzquierda1", rutaACarpeta + "/DemonioRojo/caminarIzquierda1.png");
        mapeo.put("moverIzquierda2", rutaACarpeta + "/DemonioRojo/caminarIzquierda2.png");
        mapeo.put("moverIzquierda3", rutaACarpeta + "/DemonioRojo/caminarIzquierda3.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieve.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrollAmarillo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("quietoIzquierda", rutaACarpeta + "/TrollAmarillo/quietoIzquierda.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/TrollAmarillo/quietoDerecha.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/TrollAmarillo/caminarDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/TrollAmarillo/caminarIzquierda.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieve.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteRanaDeFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("quietoIzquierda", rutaACarpeta + "/RanaDeFuego/quietaIzquierda.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/RanaDeFuego/quietaDerecha.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/RanaDeFuego/caminarDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/RanaDeFuego/caminarIzquierda.png");
        mapeo.put("disparoDerecha", rutaACarpeta + "/RanaDeFuego/disparoDerecha.png");
        mapeo.put("disparoIzquierda", rutaACarpeta + "/RanaDeFuego/disparoIzquierda.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieve.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteCalabaza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("moverDerecha", rutaACarpeta + "/Calabaza/calabazaDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/Calabaza/calabazaIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFantasma(){
        HashMap<String, String> mapeo= new HashMap<>();
            mapeo.put("normalDerecha", rutaACarpeta + "/Fantasma/fantasmaDerecha.png");
            mapeo.put("normalIzquierda", rutaACarpeta + "/Fantasma/fantasmaIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteMoghera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("quietoIzquierda", rutaACarpeta + "/Moghera/mogheraQuietoIzquierda.png");//76x90
        mapeo.put("quietoDerecha", rutaACarpeta + "/Moghera/mogheraQuietoDerecha.png");        
        mapeo.put("congelado", rutaACarpeta + "/Moghera/mogheraCongelado.png");//42x32
        mapeo.put("disparo", rutaACarpeta + "/Moghera/mogheraDisparo.png");//82x82
        mapeo.put("salto1", rutaACarpeta + "/Moghera/mogheraSalto1.png");//76x96
        mapeo.put("salto2", rutaACarpeta + "/Moghera/mogheraSalto2.png");//76x112
        return new Sprite(mapeo);
    }
    public Sprite getSpriteKamakichi(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Kamakichi/kamakichiIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/Kamakichi/kamakichiDerecha.png");
        mapeo.put("disparo", rutaACarpeta + "/Kamakichi/kamakichiDisparando.png");//44 alto
        mapeo.put("congelado", rutaACarpeta + "/Kamakichi/kamakichiCongelado.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrampaPinchos(){    
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/trampaPinchos.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteSueloResbaladizo(){
        HashMap<String, String> mapeo= new HashMap<>(); 
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/sueloResbaladizo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteParedDestuctible(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredDestructible.jpg");
        return new Sprite(mapeo);   
    }
    public Sprite getSpritePlataformaMovil(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaMovil.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataformaQuebradiza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaQuebradiza.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePared(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/pared.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteEscalera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/escalera.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaAzul(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaAzul.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaRoja(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaRoja.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaVerde(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaVerde.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFruta(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/fruta.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteVidaExtra(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/vidaJugador.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaNieve(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normalIzquierda", rutaACarpeta + "/BolaDeNieve/bolaDeNieve.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("moverIzquierda", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoIzquierda.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoDerecha.png");
        mapeo.put("moverIzquierdaJefe", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoMogheras.png");
        mapeo.put("bomba", rutaACarpeta + "/BolaDeFuego/bombaKamakichi.png");
        mapeo.put("bombaExplotada", rutaACarpeta + "/BolaDeFuego/bombaExplotadaKamakichi.png");
        mapeo.put("explosion1", rutaACarpeta + "/BolaDeFuego/explosion1.png");
        mapeo.put("explosion2", rutaACarpeta + "/BolaDeFuego/explosion2.png");
        mapeo.put("explosion3", rutaACarpeta + "/BolaDeFuego/explosion3.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDisparoJugador(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normalDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalDerecha.gif");
        mapeo.put("normalIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalIzquierda.gif");
        mapeo.put("aumentadoDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoDerecha.gif");
        mapeo.put("aumentadoIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoIzquierda.gif");
        return new Sprite(mapeo);
    }
}
