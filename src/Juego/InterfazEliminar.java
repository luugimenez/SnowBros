package SnowBros.src.Juego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeFuego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeNieve;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Fantasma;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.DisparoJugador;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;

public interface InterfazEliminar {
    public void eliminarPlataforma(Plataforma p);
    public void eliminarPared(Pared p);
    public void eliminarJugador(Jugador j);
    public void eliminarEnemigo(Enemigo e);
    public void eliminarPowerUp(PowerUp p);
    public void eliminarObstaculo(Obstaculo o);
    public void eliminarBolaDeFuego(BolaDeFuego b);
    public void eliminarBolaDeNieve(BolaDeNieve b);
    public void eliminarDisparo(DisparoJugador d);
    public void eliminarSilueta(Silueta s);
    public void eliminarCalabaza();
    public void eliminarFantasma(Fantasma f);
    public void eliminarPMovil(PlataformaMovil p);
    public void eliminarResbaladizo(SueloResbaladizo s);
    
}
