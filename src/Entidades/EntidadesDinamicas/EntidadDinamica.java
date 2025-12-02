package SnowBros.src.Entidades.EntidadesDinamicas;
import java.util.ArrayList;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Grafica.ObserverEntidad; 
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Visitor.Visitador;

public abstract class EntidadDinamica extends Entidad implements Visitador{
  protected int direccion;
  protected int dirAnterior;
  protected int velocidad;
  protected int velocidadY;
  protected boolean saltando;
  protected boolean enAire;
  private boolean bajandoPlataforma;
  private int alturaSalto;
  protected int alturaMax;
  protected int tocandoSuelo;
  protected int velocidadSalto;
  protected long ultimoSalto;
  protected ObserverEntidad observer;
  private Nivel miNivel;
  public EntidadDinamica(int posX, int posY, Sprite mSprite){
    super(posX, posY, mSprite);
    observer=new ObserverEntidad(this);
    velocidad=ConstantesDificultad.VELOCIDAD_ENTIDAD_DINAMICA_X;
    velocidadY=ConstantesDificultad.VELOCIDAD_ENTIDAD_DINAMICA_Y;
    alturaMax=ConstantesDificultad.ALTURA_MAX_SALTO;
    alturaSalto=0;
    ultimoSalto=0;
    enAire=false;
    saltando=false;
    bajandoPlataforma=false;
  }
  public boolean getSaltando(){
    return saltando;
  }
  public void setDireccion(int direccion){
    this.direccion=direccion;
  }
  public void setSaltando(boolean salto){
    saltando=salto;
  } 
  public void setBajarPlataforma(boolean b){
    bajandoPlataforma=b;
  }
  public abstract void mover();
  public void setTocandoSuelo(int estado){
    tocandoSuelo=estado;
  }
  public String generarClaveEstado(){
    String estado=null;
    switch (direccion) {
      case ConstantesEntidades.IZQUIERDA:
        estado="normalIzquierda";
        break;
      case ConstantesEntidades.DERECHA:
        estado="normalDerecha";
        break;
      case ConstantesEntidades.QUIETO:
        estado="normalIzquierda";
        break;
    }
    return estado;
  }
  public int getTocandoSuelo(){
    return tocandoSuelo;
  }
  public ObserverEntidad getObserver(){
    return observer;
  }
  public void verificarSuelo(){
    boolean enSuelo=false;
    for(Plataforma p:this.miNivel.getPlataformas()){
      if(miNivel.getJugador().getJuego().estaCerca(this,p,40)){
        if(hitbox.getBottomHitBox().intersects(p.getHitbox().getTopHitBox()) && !bajandoPlataforma){
          enSuelo=true;
        }
      }
    }
    if(enSuelo){
      tocandoSuelo=ConstantesEntidades.SUELO;
      velocidadY=0;
    }
    else{
      tocandoSuelo=ConstantesEntidades.AIRE;
      actualizarCaida();
    }
  }
  private void actualizarCaida(){
    if(tocandoSuelo==ConstantesEntidades.AIRE){
      velocidadY=ConstantesDificultad.VELOCIDAD_ENTIDAD_DINAMICA_Y;
      posY+=velocidadY;
      this.getHitbox().actualizarHitbox(posX,posicionarHitbox(posY),ancho,alto);
      for(Plataforma p:new ArrayList<>(miNivel.getPlataformas()))
        if(miNivel.getJugador().getJuego().estaCerca(this,p,40) && !bajandoPlataforma)
          p.aceptar(this);
    }
  }
  public void iniciarSalto(){
    long tiempoHilo=System.currentTimeMillis();
    if(tiempoHilo-ultimoSalto>500 && tocandoSuelo==ConstantesEntidades.SUELO && posY<ConstantesVistas.PANEL_JUEGO_ALTO){
      Sonido.getInstancia().reproducirEfectoDeSonido("salto.wav");
      saltando=true;
      enAire=true;
      tocandoSuelo=ConstantesEntidades.AIRE;
      posY-=ConstantesDificultad.VELOCIDAD_SALTO;
    }
  }
  public void actualizarSalto() {
    if (saltando) {
        if (alturaSalto < alturaMax) {
            posY -= ConstantesDificultad.VELOCIDAD_SALTO;
            alturaSalto+=ConstantesDificultad.VELOCIDAD_SALTO;
            this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
        } else {
              saltando = false;
              alturaSalto=0;
         }
    }
}
  public void visitar(Enemigo e) {}
  public void visitar(Plataforma e) {
    visitarPlataforma(e);
  }
  public void visitar(PlataformaMovil e) {
    visitarPlataforma(e);
          this.setPosX(this.getPosX() + e.getDeltaX());
          tocandoSuelo=ConstantesEntidades.SUELO;
  } 
  public void visitar(PlataformaQuebradiza e) {
    visitarPlataforma(e);
  }
  protected void visitarPlataforma(Plataforma e){
    seguirCaminando(e);
  }
  public void visitar(BotellaAzul b) {}
  public void visitar(BotellaRoja b) {}
  public void visitar(BotellaVerde b) {}
  public void visitar(Fruta f) {}
  public void visitar(VidaExtra v) {}
  public void visitar(Pared e){
    this.verificarPared(e);
  }
  public void visitar(ParedDestructible e) {
    verificarPared(e);
  }
  protected abstract void verificarPared(Pared p);
  public void visitar(Escalera e) {
    seguirCaminando(e);
  }   
  public void visitar(SueloResbaladizo e) {
    seguirCaminando(e);
  }
  public void seguirCaminando(Entidad e){
    if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
      posY= e.getPosY() - this.getHitbox().getAlto();
      this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
      tocandoSuelo=ConstantesEntidades.SUELO;
      saltando=false;
      enAire=false;
    }
  }
  public void visitar(TrampaPinchos e) {}
  public void visitar(Jugador e) {}
  public void eliminar(){
    observer.borrarObserver();
    observer=null;
  }
  public void setNivel(Nivel n){
    super.setNivel(n);
    miNivel=n;
  }
  public int posicionarHitbox(int y){
    return y;
  }
}