package SnowBros.src.Fabricas;
import SnowBros.src.Grafica.Sprite;

public abstract class FabricaSprites {
    protected String rutaACarpeta;
    public FabricaSprites(String ruta) {
        this.rutaACarpeta = ruta;
    }
    public abstract Sprite get_silueta();
    public abstract Sprite getSpriteJugador();
    public abstract Sprite getSpritePlataforma();
    public abstract Sprite getSpriteDemonioRojo();
    public abstract Sprite getSpriteTrollAmarillo();
    public abstract Sprite getSpriteRanaDeFuego();
    public abstract Sprite getSpriteCalabaza();
    public abstract Sprite getSpriteFantasma();
    public abstract Sprite getSpriteMoghera();
    public abstract Sprite getSpriteKamakichi();
    public abstract Sprite getSpriteTrampaPinchos();
    public abstract Sprite getSpriteSueloResbaladizo();
    public abstract Sprite getSpriteParedDestuctible();
    public abstract Sprite getSpritePlataformaMovil();
    public abstract Sprite getSpritePlataformaQuebradiza();
    public abstract Sprite getSpritePared();
    public abstract Sprite getSpriteEscalera();
    public abstract Sprite getSpriteBotellaAzul();
    public abstract Sprite getSpriteBotellaRoja();
    public abstract Sprite getSpriteBotellaVerde();
    public abstract Sprite getSpriteFruta();
    public abstract Sprite getSpriteVidaExtra();
    public abstract Sprite getSpriteBolaNieve();
    public abstract Sprite getSpriteBolaFuego();
    public abstract Sprite getSpriteDisparoJugador();
} 