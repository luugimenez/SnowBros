package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import java.awt.Rectangle;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.GestorEntidad;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.Visitable;
import SnowBros.src.Visitor.Visitador;

public abstract class Enemigo extends EntidadDinamica implements Visitable {
   protected int salud;
   protected Temporizador temporizadoCongelado;
   protected Temporizador accionMovimientoEnemigo;
   protected boolean rodando;
   protected boolean muerto;
   protected boolean congelado;
   protected boolean detenido;
   protected long tiempoEstado = 0;
   protected long inicioCongelado=0;
   protected int estado = EstadosDePatron.MOVERDERECHA;
   protected boolean activarJefe;
   protected boolean jefeDerrotado;
   protected GestorEntidad gestor;
   public Enemigo(int x, int y, Sprite sprites) {
      //la dificultad va a verse definida por lsa salud
      //de c/u y los tiempos congelado, desp cambiar
      super(x, y, sprites);
      congelado=false;
      muerto=false;
      rodando=false;
      detenido=false;
      activarJefe=false;//para Moghera y kamaki
      jefeDerrotado=false;
      tocandoSuelo=ConstantesEntidades.SUELO;
      direccion=ConstantesEntidades.QUIETO;
   }
   public abstract void mover();
   public void detener(){
      detenido=true;
   }
   public void avanzar(){
      detenido=false;
   }
   public abstract void patronDeMovimiento();
   public abstract int getSalud();
   protected void verificarPared(Pared p){
      Rectangle hitBoxPared= p.getHitbox().getRectangle();
       if (direccion==ConstantesEntidades.DERECHA&&this.getHitbox().getRightHitBox().intersects(hitBoxPared)) {
            this.setPosX(posX - 3);
            direccion=ConstantesEntidades.IZQUIERDA;
        } else if (direccion==ConstantesEntidades.IZQUIERDA&&this.getHitbox().getLeftHitBox().intersects(hitBoxPared)) {
            setPosX(posX+3);
            direccion=ConstantesEntidades.DERECHA;
         }
         this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
   }
   public void recibirDaño(int daño){
      salud -= daño;
      if (salud <= 0){
         if(activarJefe)
            jefeDerrotado=true;
         detener();
         congelar();
         congelado=true;
         inicioCongelado=System.currentTimeMillis();
      }
   }
   public String generarClaveEstado(){
      String estado=null;
      if(congelado )
         estado="congelado";
      else{
         if(direccion==ConstantesEntidades.IZQUIERDA)
            estado="normalIzquierda";
         else
            estado="normalDerecha";
      }
      return estado;
   }
   public void actualizarTiempoCongelado(){
      long tiempoADescongelar=5000;
         if(detenido && System.currentTimeMillis()>inicioCongelado+tiempoADescongelar){
               descongelar();
         }
   }
   public void setCongelado(boolean b){
      congelado=b;
   }
   public boolean getCongelado(){
      return congelado;
   }
   public boolean getRodando(){ 
      return rodando;
   }
   public boolean getMuerto(){ 
      return muerto; 
   }
   public boolean getDerrotado(){
      return jefeDerrotado;
   }
   public long getInicioCongelado(){
      return inicioCongelado;
   }
   public void congelar(){
      if (!congelado){
         Sonido.getInstancia().reproducirEfectoDeSonido("enemigoMuereBolaNieve.wav");
         direccion=ConstantesEntidades.QUIETO;
         gestor.actualizarPuntaje(getPuntajeCongelado());
         if(observer!=null)
            observer.actualizarBolaDeNieve(activarJefe);
      }  
   }  
   public void descongelar(){
      detenido=false;
      congelado=false;
      direccion=this.dirAnterior;
      salud=this.getSalud();
   }
   public void convertirEnBola(){ //metodo llamado al colisionar con la bola
      if(congelado){
         this.eliminar(); 
         gestor.crearBolaDeNieve(posX, posY, getPuntajeEmpujado());
      }
   }
   public abstract int getPuntajeCongelado();
   public abstract int getPuntajeEmpujado();   
   public void eliminar(){
      if(observer!=null){
         nivel.eliminarEnemigo(this);
         super.eliminar();
      }
   }
   public void visitar(Pared p){
      verificarPared(p);
   }
   public void aceptar(Visitador v){
      v.visitar(this);
   }
   public void setGestor(GestorEntidad g){
      gestor=g;
   }
}