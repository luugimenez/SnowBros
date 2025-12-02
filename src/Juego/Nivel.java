package SnowBros.src.Juego;

import java.util.ArrayList;
import java.util.LinkedList;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeFuego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeNieve;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Calabaza;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Fantasma;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Obstaculo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Grafica.Sprite;

public class Nivel implements LocalizadorJugador, InterfazEliminar{
    protected Sprite sprite;
    protected Jugador jugador;
    protected Silueta silueta;
    protected int numeroNivel;
    protected LinkedList<Enemigo> listaEnemigos;
    protected LinkedList<PowerUp> listaPowerUps;
    protected LinkedList<Obstaculo> listaObstaculos;
    protected LinkedList<Plataforma> listaPlataformas;
    protected LinkedList<DisparoJugador> listaDisparoJugador;
    protected LinkedList<BolaDeNieve> listaBolaDeNieve;
    protected LinkedList<BolaDeFuego> listaBolaDeFuego;
    protected LinkedList<Pared> listaParedes;
    protected LinkedList<PlataformaMovil> listaPmovil;
    protected Calabaza calabaza;
    protected LinkedList<Fantasma> listaFantasmas;
    protected LinkedList<SueloResbaladizo> listaResbaladizos;

    public Nivel(Silueta silueta){
        this.silueta=silueta;
        listaEnemigos= new LinkedList<>();
        listaPowerUps= new LinkedList<>();
        listaObstaculos= new LinkedList<>();
        listaParedes= new LinkedList<>();
        listaPlataformas= new LinkedList<>();
        listaPmovil=new LinkedList<>();
        listaDisparoJugador= new LinkedList<>();
        listaBolaDeFuego= new LinkedList<>();
        listaBolaDeNieve= new LinkedList<>();
        listaFantasmas = new LinkedList<>();
        listaResbaladizos= new LinkedList<>();
    }
    public void setNivel(int nivel){
        numeroNivel=nivel;
    }
    public LinkedList<Enemigo> getEnemigos(){
        return listaEnemigos;
    }
    public LinkedList<PowerUp> getPowerUps(){
        return listaPowerUps;
    }
    public LinkedList<Obstaculo> getObstaculos(){
        return listaObstaculos;
    }
    public LinkedList<Pared> getParedes(){
        return listaParedes;
    }
    public LinkedList<Plataforma> getPlataformas(){
        return listaPlataformas;
    }
    public LinkedList<PlataformaMovil> getPlataformasMovil(){
        return listaPmovil;
    }
    public LinkedList<DisparoJugador> getDisparos(){
        return listaDisparoJugador;
    }
    public LinkedList<BolaDeFuego> getBolasDeFuego(){
        return listaBolaDeFuego;
    }
    public LinkedList<BolaDeNieve> getBolasDeNieve(){
        return listaBolaDeNieve;
    }
    public LinkedList<Fantasma> getFantasmas(){
        return listaFantasmas;
    }
    public Calabaza getCalabaza(){
        return calabaza;
    }
    public LinkedList<SueloResbaladizo> getResbaladizos(){
        return listaResbaladizos;
    } 
    public void eliminarPlataforma(Plataforma p){
        listaPlataformas.remove(p);
    }
    public void eliminarJugador(Jugador j){
        jugador=null;
    }
    public void eliminarEnemigo(Enemigo e){
        listaEnemigos.remove(e);
    }
    public void eliminarPowerUp(PowerUp p){
        listaPowerUps.remove(p);
    }
    public void eliminarObstaculo(Obstaculo o){
        listaObstaculos.remove(o);
    }
    public void eliminarEntidadesEstaticas(){
       for(Obstaculo o:listaObstaculos){
            o.borrarLabel();
       }
       for(Plataforma p:listaPlataformas){
            p.borrarLabel();
       }
       for(SueloResbaladizo s:listaResbaladizos){
            s.borrarLabel();
       }
    }
    public void eliminarPared(Pared p){
        listaParedes.remove(p);
    }
    public void eliminarSilueta(Silueta s){
        silueta=null;
    }
    public void eliminarBolaDeFuego(BolaDeFuego b){
        listaBolaDeFuego.remove(b); 
        b.getObserver().actualizar();
    }
    public void eliminarDisparo(DisparoJugador b){
        listaDisparoJugador.remove(b);
    }
    public void eliminarBolaDeNieve(BolaDeNieve b){
        listaBolaDeNieve.remove(b);
    }
    public void eliminarFantasma(Fantasma f){
        listaFantasmas.remove(f);
    }
    public void eliminarCalabaza(){
        calabaza=null;
    }
    public void eliminarPMovil(PlataformaMovil p) {
       listaPmovil.remove(p);
    }
    public void eliminarResbaladizo(SueloResbaladizo s) {
        listaResbaladizos.remove(s);
    }
    public void agregarJugador(Jugador j){
        jugador=j;
        j.setNivel(this);
    }
    public void agregarPlataforma(Plataforma p){
        listaPlataformas.addLast(p);
        p.setNivel(this);
    }
    public void agregarPlataformaMovil(PlataformaMovil p){
        listaPmovil.addLast(p);
        p.setNivel(this);
    }
    public void agregarObstaculo(Obstaculo o){
        listaObstaculos.addLast(o);
        o.setNivel(this);
    }
    public void agregarPared(Pared p){
        listaParedes.addLast(p);
        p.setNivel(this);
    }
    public void agregarPowerUp(PowerUp p){
        listaPowerUps.addLast(p);
        p.setNivel(this);
    }
    public void agregarEnemigo(Enemigo e){
        listaEnemigos.addLast(e);
        e.setNivel(this);
        e.getObserver().actualizar();
    }
    public void agregarDisparo(DisparoJugador b){
        listaDisparoJugador.addLast(b);
        b.setNivel(this);
    }
    public void agregarBolaDeNieve(BolaDeNieve b){
        listaBolaDeNieve.addLast(b);
        b.setNivel(this);
        b.getObserver().actualizar();
    }
    public void agregarBolaDeFuego(BolaDeFuego b){
        listaBolaDeFuego.addLast(b);
        b.setNivel(this);
        b.getObserver().actualizar();
    }
    public void agregarFantasma(Fantasma f){
        listaFantasmas.addLast(f);
        f.setNivel(this);
        f.getObserver().actualizar();
    }
    public void agregarCalabaza(Calabaza c){
        calabaza=c;
        calabaza.setNivel(this);
        calabaza.getObserver().actualizar();
    }
    public void agregarResbaladizo(SueloResbaladizo s){
        listaResbaladizos.addLast(s);
        s.setNivel(this);
    }
    public void efectoBotellaVerde(boolean efecto){
        if(efecto){
            for(Enemigo enemigo : new ArrayList<>(listaEnemigos))
                enemigo.detener();
            if(calabaza!=null){
                calabaza.detener();
                if(listaFantasmas.size()!=0){
                    for(Fantasma fantasma : new ArrayList<>(listaFantasmas))
                        fantasma.detener();
                }
            }
        }
        else{
            for(Enemigo enemigo : new ArrayList<>(listaEnemigos))
                enemigo.avanzar();
            if(calabaza!=null){
                calabaza.avanzar();
                if(listaFantasmas.size()!=0){
                    for(Fantasma fantasma : new ArrayList<>(listaFantasmas))
                        fantasma.avanzar();
                }
            }
        }
    }
    public Juego getJuego(){
        return jugador.getJuego();
    }
    public int getJugadorX(){
        return jugador.getPosX();
    }
    public Jugador getJugador(){
        return jugador;
    }
    public Silueta getSilueta(){
        return silueta;
    }
    public void notificarObservers(){
        if(jugador!=null)
            jugador.getObserver().actualizar();
        for(Enemigo e:new ArrayList<>(this.getEnemigos()))
            if(e!=null && !e.getMuerto() && !e.getCongelado())
                e.getObserver().actualizar();
        for(BolaDeNieve b:this.getBolasDeNieve()){
            if(b!=null){
                b.getObserver().actualizar();
            }
        }
        for(PlataformaMovil p:this.getPlataformasMovil())
            p.getObserver().actualizar();
        for(BolaDeFuego b : new ArrayList<>(this.getBolasDeFuego()))
                if(b!=null && b.getObserver()!=null)
                    b.getObserver().actualizar();
        if(calabaza!=null){
            calabaza.getObserver().actualizar();
            if(!this.getFantasmas().isEmpty()){
                for(Fantasma f:this.getFantasmas()){
                    f.getObserver().actualizar();
                }
            }
        }
        for(DisparoJugador d:new ArrayList<>(listaDisparoJugador)){
            if(d!=null && d.getObserver()!=null)
                d.getObserver().actualizar();
        }
    }
    public boolean terminoElNivel(){
      boolean condicion=false;
      if(listaEnemigos.isEmpty()){//&&listaBolaDeNieve.isEmpty()){
        condicion=true;
      }
      return condicion;
    }
    public void eliminarse(){
        eliminarEntidadesEstaticas();
        jugador.eliminar();
        silueta=null;
        listaEnemigos.clear();
        listaPowerUps.clear();
        listaObstaculos.clear();
        listaParedes.clear();
        listaPlataformas.clear();
        listaPmovil.clear();
        listaDisparoJugador.clear();
        listaBolaDeFuego.clear();
        listaBolaDeNieve.clear();
        listaFantasmas.clear();
        calabaza=null;
        listaResbaladizos.clear();
    }
    public int getNivelActual() {
        return numeroNivel;
    }
}