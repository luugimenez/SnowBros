package SnowBros.src.Fabricas;

import java.util.HashMap;

import SnowBros.src.Grafica.Sprite;

public class Alternativo extends FabricaSprites{

    public Alternativo() {
        super("SnowBros/src/Imagenes/Alternativo");
    }
    public Sprite get_silueta() {
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/FondoNivelAlternativo.jpg");
        return new Sprite(mapeo);	     	
    }
    public Sprite getSpriteJugador(){
        HashMap<String, String> mapeo = new HashMap<String,String>();
        mapeo.put("quietoIzquierda", rutaACarpeta+ "/Jugador/nickIzquierdaAlternativo.png");
        mapeo.put("quietoDerecha", rutaACarpeta+ "/Jugador/nickDerechaAlternativo.png");
        mapeo.put("moverIzquierda1", rutaACarpeta+ "/Jugador/nickAvanzarIzquierdaAlternativo.png");
        mapeo.put("moverIzquierda2", rutaACarpeta+ "/Jugador/nickAvanzarIzquierdaAlternativo.png");
        mapeo.put("moverIzquierda2", rutaACarpeta+ "/Jugador/nickAvanzarIzquierdaAlternativo.png");
        mapeo.put("moverDerecha1", rutaACarpeta+ "/Jugador/nickAvanzarDerechaAlternativo.png");
        mapeo.put("moverDerecha2", rutaACarpeta+ "/Jugador/nickAvanzarDerechaAlternativo.png");
        mapeo.put("moverDerecha3", rutaACarpeta+ "/Jugador/nickAvanzarDerechaAlternativo.png");
        mapeo.put("saltandoDerecha", rutaACarpeta+ "/Jugador/JugadorSaltandoDerechaAlternativo.png");
        mapeo.put("saltandoIzquierda", rutaACarpeta+ "/Jugador/JugadorSaltandoIzquierdaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataforma(){
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/Plataforma/plataformaAlternativo.jpg"); 
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDemonioRojo(){
        HashMap<String, String> mapeo= new HashMap<>();//dejamos tanta animacion o la reducimos a uno en cada direccion para caminar y estar quieto?
        mapeo.put("quietoIzquierda", rutaACarpeta + "/DemonioRojo/quietoIzquierdaAlternativo.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/DemonioRojo/quietoDerechaAlternativo.png");
        mapeo.put("moverDerecha1", rutaACarpeta + "/DemonioRojo/caminarDerecha1Alternativo.png");
        mapeo.put("moverDerecha2", rutaACarpeta + "/DemonioRojo/caminarDerecha2Alternativo.png");
        mapeo.put("moverDerecha3", rutaACarpeta + "/DemonioRojo/caminarDerecha3Alternativo.png");
        mapeo.put("moverIzquierda1", rutaACarpeta + "/DemonioRojo/caminarIzquierda1Alternativo.png");
        mapeo.put("moverIzquierda2", rutaACarpeta + "/DemonioRojo/caminarIzquierda2Alternativo.png");
        mapeo.put("moverIzquierda3", rutaACarpeta + "/DemonioRojo/caminarIzquierda3Alternativo.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrollAmarillo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("quietoIzquierda", rutaACarpeta + "/TrollAmarillo/quietoIzquierdaAlternativo.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/TrollAmarillo/quietoDerechaAlternativo.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/TrollAmarillo/caminarDerechaAlternativo.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/TrollAmarillo/caminarIzquierdaAlternativo.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteRanaDeFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("quietoIzquierda", rutaACarpeta + "/RanaDeFuego/quietaIzquierdaAlternativo.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/RanaDeFuego/quietaDerechaAlternativo.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/RanaDeFuego/AlternativoCaminandoDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/RanaDeFuego/AlternativoCaminandoIzquierda.png");
        mapeo.put("disparoDerecha", rutaACarpeta + "/RanaDeFuego/AlternativodisparoDerecha.png");
        mapeo.put("disparoIzquierda", rutaACarpeta + "/RanaDeFuego/AlternativodisparoIzquierda.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteCalabaza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("moverDerecha", rutaACarpeta + "/Calabaza/calabazaDerechaAlternativo.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/Calabaza/calabazaIzquierdaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFantasma(){
        HashMap<String, String> mapeo= new HashMap<>();
            mapeo.put("normalDerecha", rutaACarpeta + "/Fantasma/fantasmaDerechaAlternativo.png");
            mapeo.put("normalIzquierda", rutaACarpeta + "/Fantasma/fantasmaIzquierdaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteMoghera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("quietoIzquierda", rutaACarpeta + "/Moghera/mogheraIAlternativo.png");
        mapeo.put("quietoDerecha", rutaACarpeta + "/Moghera/mogheraDAlternativo.png");        
        mapeo.put("congelado", rutaACarpeta + "/Moghera/mogheraCongeladoAlternativo.png");
        mapeo.put("disparo", rutaACarpeta+"/Moghera/mogheraDisparoAlternativo.png");
        mapeo.put("salto1", rutaACarpeta + "/Moghera/mogheraSalto1Alternativo.png");
        mapeo.put("salto2", rutaACarpeta + "/Moghera/mogheraSalto2Alternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteKamakichi(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Kamakichi/kamakichiIzquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/Kamakichi/kamakichiDerechaAlternativo.png");
        mapeo.put("disparo", rutaACarpeta + "/Kamakichi/kamakichiDisparandoAlternativo.png");
        mapeo.put("congelado", rutaACarpeta + "/Kamakichi/kamakichiCongeladoAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrampaPinchos(){    
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/trampaPinchosAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteSueloResbaladizo(){
        HashMap<String, String> mapeo= new HashMap<>(); 
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/sueloResbaladizoAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteParedDestuctible(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredDestructibleAlternativo.jpg");
        return new Sprite(mapeo);   
    }
    public Sprite getSpritePlataformaMovil(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaMovilAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataformaQuebradiza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaQuebradizaAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePared(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteEscalera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/escaleraAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaAzul(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaAzulAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaRoja(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaRojaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaVerde(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaVerdeAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFruta(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/frutaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteVidaExtra(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/vidaJugadorAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaNieve(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normalIzquierda", rutaACarpeta + "/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("moverIzquierda", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoIzquierdaAlternativo.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoDerechaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDisparoJugador(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normalDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalDerechaAlternativo.gif");
        mapeo.put("normalIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalIzquierdaAlternativo.gif");
        mapeo.put("aumentadoDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoDerechaAlternativogif.gif");
        mapeo.put("aumentadoIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoIzquierdaAlternativo.gif");
        return new Sprite(mapeo);
    }
}