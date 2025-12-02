package SnowBros.src.Fabricas;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.DisparoJugador;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.GestorEntidad;
import SnowBros.src.Juego.Silueta;

public class FabricaEntidades {
    private FabricaSprites fabrica;
    private GestorEntidad gestor;
    
    public FabricaEntidades(FabricaSprites fabricaSprite, GestorEntidad g){
        fabrica=fabricaSprite;
        gestor=g;
    }
    private void asignarGestor(Enemigo e){
        e.setGestor(gestor);

    }
    public Silueta getSilueta(){
        Sprite spriteSilueta= fabrica.get_silueta();
        Silueta silueta=new Silueta(spriteSilueta);
        return silueta;
    }// getters entidades
    public Jugador getJugador(int x, int y){
        Sprite spritesJugador=fabrica.getSpriteJugador();
        Jugador jugador= new Jugador(x,y,spritesJugador);
        return jugador;
    }
    public DisparoJugador getDisparoJugador(int x, int y){
        Sprite spritesDisparos=fabrica.getSpriteDisparoJugador();
        DisparoJugador disparo= new DisparoJugador(x, y, spritesDisparos);
        return disparo;
    }
    public DemonioRojo getDemonioRojo(int x, int y){
        Sprite spritesDemonioRojo=fabrica.getSpriteDemonioRojo();
        DemonioRojo demonio= new DemonioRojo(x,y,spritesDemonioRojo);
        asignarGestor(demonio);
        return demonio;
    }
    public TrollAmarillo getTrollAmarillo(int x, int y){
        Sprite spritesTrollAmarillo=fabrica.getSpriteTrollAmarillo();
        TrollAmarillo troll= new TrollAmarillo(x,y,spritesTrollAmarillo);
        asignarGestor(troll);
        return troll;
    }
    public RanaDeFuego getRanaDeFuego(int x, int y){
        Sprite RanaDeFuego= fabrica.getSpriteRanaDeFuego();
        RanaDeFuego rana= new RanaDeFuego(x, y, RanaDeFuego);
        asignarGestor(rana);
        return rana;
    }
    public Calabaza getCalabaza(int x, int y){
        Sprite spriteCalabaza= fabrica.getSpriteCalabaza();
        Calabaza calabaza= new Calabaza(x, y, spriteCalabaza);
        asignarGestor(calabaza);
        return calabaza;
    }
    public Fantasma getFantasma(int x, int y){
        Sprite spriteFantasma= fabrica.getSpriteFantasma();
        Fantasma fantasma= new Fantasma(x, y, spriteFantasma);
        asignarGestor(fantasma);
        return fantasma;
    }
    public Kamakichi getKamakichi(int x, int y){
        Sprite spriteKamakichi= fabrica.getSpriteKamakichi();
        Kamakichi kamakichi= new Kamakichi(x, y, spriteKamakichi);
        asignarGestor(kamakichi);
        return kamakichi;
    }
    public Moghera getMoghera(int x, int y){
        Sprite spriteMoghera= fabrica.getSpriteMoghera();
        Moghera moghera=new Moghera(x, y, spriteMoghera);
        asignarGestor(moghera);
        return moghera;
    }// getters powerups
    public BotellaAzul getBotellaAzul(int x, int y){
        Sprite botellaAzul= fabrica.getSpriteBotellaAzul();
        BotellaAzul azul= new BotellaAzul(x, y, botellaAzul);
        return azul;
    }
    public BotellaRoja getBotellaRoja(int x, int y){
        Sprite botellaRoja= fabrica.getSpriteBotellaRoja();
        BotellaRoja roja= new BotellaRoja(x, y, botellaRoja);
        return roja;
    }
    public BotellaVerde getBotellaVerde(int x, int y){
        Sprite botellaVerde= fabrica.getSpriteBotellaVerde();
        BotellaVerde verde= new BotellaVerde(x, y, botellaVerde);
        return verde;
    }
    public Fruta getFruta(int x, int y){
        Sprite spriteFruta= fabrica.getSpriteFruta();
        Fruta fruta= new Fruta(x, y, spriteFruta);
        return fruta;
    }
    public VidaExtra getVidaExtra(int x, int y){
        Sprite vidaExtra= fabrica.getSpriteVidaExtra();
        VidaExtra vida= new VidaExtra(x, y, vidaExtra);
        return vida;
    }
    // getters terreno
    public Escalera getEscalera(int x, int y){
        Sprite spriteEscalera= fabrica.getSpriteEscalera();
        Escalera escalera= new Escalera(x,y,spriteEscalera);
        return escalera;
    }
    public Pared getPared(int x, int y){
        Sprite spritePared=fabrica.getSpritePared();
        Pared pared= new Pared(x, y, spritePared);
        return pared;
    }
    public ParedDestructible getParedDestructible(int x, int y){
       Sprite paredDestructibleSprite=fabrica.getSpriteParedDestuctible();
       ParedDestructible pared= new ParedDestructible(x,y,paredDestructibleSprite);
       return pared;
    }
    public SueloResbaladizo getSueloResbaladizo(int x, int y){
        Sprite spriteSuelo= fabrica.getSpriteSueloResbaladizo();
        SueloResbaladizo suelo= new SueloResbaladizo(x, y, spriteSuelo);
        return suelo;
    }
    public TrampaPinchos getTrampaPinchos(int x, int y){
        Sprite spriteTrampaPinchos= fabrica.getSpriteTrampaPinchos();
        TrampaPinchos trampa= new TrampaPinchos(x, y, spriteTrampaPinchos);
        return trampa;
    }
    public Plataforma getPlataforma(int x, int y){
        Sprite spritePlataforma= fabrica.getSpritePlataforma();
        Plataforma plataforma= new Plataforma(x, y,spritePlataforma);
        return plataforma;
    }
    public PlataformaMovil getPlataformaMovil(int x, int y){
        Sprite spritePlataformaMovil= fabrica.getSpritePlataformaMovil();
        PlataformaMovil plataforma= new PlataformaMovil(x, y, spritePlataformaMovil);
        return plataforma;
    }
    public PlataformaQuebradiza getPlataformaQuebradiza(int x, int y){
        Sprite spritePlataformaQuebradiza= fabrica.getSpritePlataformaQuebradiza();
        PlataformaQuebradiza plataforma= new PlataformaQuebradiza(x, y, spritePlataformaQuebradiza);
        return plataforma;
    }
    public BolaDeNieve getBolaDeNieve(int x, int y){
        Sprite spriteBolaDeNieve=fabrica.getSpriteBolaNieve();
        BolaDeNieve bola= new BolaDeNieve(x, y, spriteBolaDeNieve);
        bola.setGestor(gestor);
        return bola;
    }
    public BolaDeFuego getBolaDeFuego(int x, int y){
        Sprite spriteBolaDeFuego=fabrica.getSpriteBolaFuego();
        BolaDeFuego bola= new BolaDeFuego(x, y, spriteBolaDeFuego);
        return bola;
    }
}
