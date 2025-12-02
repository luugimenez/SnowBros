package SnowBros.src.Visitor;

import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;

public interface Visitador {
    public void visitar(Enemigo e);
    public void visitar(Plataforma e);
    public void visitar(PlataformaMovil e);
    public void visitar(PlataformaQuebradiza e);
    public void visitar(BotellaAzul b);
    public void visitar(BotellaRoja b);
    public void visitar(BotellaVerde b);
    public void visitar(Fruta f);
    public void visitar(VidaExtra v);
    public void visitar(Pared e);
    public void visitar(ParedDestructible e);
    public void visitar(Escalera e);
    public void visitar(SueloResbaladizo e);
    public void visitar(TrampaPinchos e);
    public void visitar(Jugador e);
}