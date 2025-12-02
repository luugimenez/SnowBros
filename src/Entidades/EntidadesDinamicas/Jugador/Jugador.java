package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import java.awt.Rectangle;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeNieve;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaQuebradiza;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;

public class Jugador extends EntidadDinamica implements Visitable{
    private static final int vidaDeInicio = 3;
    protected Juego miJuego;
    protected int vidas;
    protected String nombre;
    protected boolean disparoAumentado;
    protected int posXoriginal;
    protected int posYoriginal;
    protected boolean habilitadoASubirEscalera;
    protected boolean invencible;
    protected BolaDeNieve bolaEmpujada;
    protected boolean chocoParedIzq=false;
    protected boolean chocoParedDer=false;
    private int contadorAnimacion;
    
    public Jugador(int x,int y, Sprite mISprite){//no se usa observerJugador y en entidadDinamica se le asigna un observer a cada uno
        super(x,y,mISprite);
        vidas=ConstantesDificultad.VIDAS_INICIO;
        tocandoSuelo=ConstantesEntidades.SUELO;
        velocidad=ConstantesDificultad.VELOCIDAD_JUGADOR;
        posXoriginal = x;
        posYoriginal = y;
        invencible=false;
        bolaEmpujada=null;
        contadorAnimacion=0;

    }
    public void mover(){}
    public void mover(int direccionAMover){
        switch (direccionAMover){
            case ConstantesEntidades.QUIETO:
                habilitadoASubirEscalera=false;
                direccion=direccionAMover;
                break;
            case ConstantesEntidades.DERECHA:
                habilitadoASubirEscalera=false;
                dirAnterior=ConstantesEntidades.DERECHA;
                direccion=direccionAMover;
                if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                    posX+=velocidad;
                    getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.IZQUIERDA:
                habilitadoASubirEscalera=false;
                dirAnterior=ConstantesEntidades.IZQUIERDA;
                direccion=direccionAMover;
                if(posX-velocidad>0){
                    posX-=velocidad;
                    getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.ARRIBA:
                if(tocandoSuelo==ConstantesEntidades.SUELO && !saltando){
                    this.iniciarSalto();
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                }
                break;
            case ConstantesEntidades.ABAJO:
                posY+=64;
                getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                break;
        }   
    }
    public DisparoJugador disparar(){
        Sonido.getInstancia().reproducirEfectoDeSonido("disparo.wav");
        int aumento=0;
        if(direccion == ConstantesEntidades.DERECHA)
            aumento=20;
        else if(direccion == ConstantesEntidades.IZQUIERDA)
            aumento=-20;
        DisparoJugador disparo=miJuego.getFabricaEntidades().getDisparoJugador(posX + aumento, posY);
        disparo.setDireccion(this.direccion, this.dirAnterior);
        disparo.setPotenciaAumentada(disparoAumentado);
        return disparo;
    }
    public void setVelocidad(int v){
        velocidad=v;
    }
    public void setVelocidadBase(){
        velocidad= ConstantesDificultad.VELOCIDAD_JUGADOR;
    }
    public void setEmpujando(BolaDeNieve b){
        bolaEmpujada=b;
    }
    public void eliminar(){
        nivel.eliminarJugador(this);
    }
    public void actualizarPuntaje(int puntaje){
        miJuego.actualizarPuntaje(puntaje);
    }
    public String generarClaveEstado(){
        String claveADevolver=null;
        if(direccion==ConstantesEntidades.QUIETO){
            if (dirAnterior == ConstantesEntidades.IZQUIERDA)
            claveADevolver="quietoIzquierda";
                else
            claveADevolver="quietoDerecha";
        }
        else if(direccion==ConstantesEntidades.ARRIBA){
            if(dirAnterior==ConstantesEntidades.IZQUIERDA)
                    claveADevolver="saltandoIzquierda";
                else
                    claveADevolver="saltandoDerecha";
                }
        else{
            if(contadorAnimacion==3){
                contadorAnimacion=0;
            }
            contadorAnimacion++;
            switch (contadorAnimacion) {
                case 1:
                if(direccion==ConstantesEntidades.IZQUIERDA)
                    claveADevolver="moverIzquierda1";
                else
                    claveADevolver="moverDerecha1";
                break;
                
                case 2:
                if(direccion==ConstantesEntidades.IZQUIERDA)
                    claveADevolver="moverIzquierda2";
                else
                    claveADevolver="moverDerecha2";
                break;
                case 3:
                if(direccion==ConstantesEntidades.IZQUIERDA)
                    claveADevolver="moverIzquierda3";
                else
                    claveADevolver="moverDerecha3";
                break;
            }
            }
        
        return claveADevolver;
    }
    public int getVidas(){
        return vidas;
    }
    public Juego getJuego(){
        return miJuego;
    }
    public int getTiempo(){
        return miJuego.getTiempoActual();
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public int getDirAnterior(){
        return dirAnterior;
    }
    public int getVelocidad(){
        return velocidad;
    }
    public BolaDeNieve getBolaEmpujada(){
        return bolaEmpujada;
    }
    public void setDireccion(int direccionActual){
        if(direccionActual!=0)
            dirAnterior=this.direccion;
        this.direccion=direccionActual;
    }
    public void setJuego(Juego j){
        miJuego=j;
    }
    public boolean getChocoParedIzq(){
        return chocoParedIzq;
    }
    public boolean getChocoParedDer(){
        return chocoParedDer;
    }
    public void setChocoPared(boolean b){
        chocoParedIzq=b;
        chocoParedDer=b;
    }
    public void chocaLadoPlataforma(boolean ladoIzq){
        if(tocandoSuelo!=ConstantesEntidades.AIRE){
            return;
        }
        if(ladoIzq){
            posX += 1;
        } else {
            posX -= 1;
        }
    }
    public void perderVida(){
        if (!invencible){
            Sonido.getInstancia().reproducirEfectoDeSonido("muerte.wav");
            vidas--;
            if (vidas > 0){
                miJuego.actualizarVidas();
                posX = posXoriginal;
                posY = posYoriginal;
                this.getObserver().actualizar();
                this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                miJuego.setReinicio(true);
                iniciarInvencibilidad();
            }
            if(vidas==0)
                miJuego.reiniciarNivel();
        }    
    }
    public void iniciarInvencibilidad(){
        invencible=true;
        Temporizador tiempoInvencible = new Temporizador(4);
        tiempoInvencible.setAccionAlFinalizar(() -> {
            invencible=false;
        });
        tiempoInvencible.iniciar();
        miJuego.invencibilidad();
    }
    public boolean getInvencibilidad(){
        return invencible;
    }
    public void setVidas(){
        vidas=vidaDeInicio;
    }
    private void efectoBotellaAzul(BotellaAzul b){
        if(miJuego.getNivelActual()!=null){
            if(velocidad==ConstantesDificultad.EFECTO_BOTELLA_AZUL)
                return;
            this.velocidad=ConstantesDificultad.EFECTO_BOTELLA_AZUL;
            b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
                this.velocidad=ConstantesDificultad.VELOCIDAD_JUGADOR;
                b.getTiempoDeEfecto().resetear();
            } );
            b.getTiempoDeEfecto().iniciar();
        }
    }
    private void efectoBotellaRoja(BotellaRoja b){
        if(miJuego.getNivelActual()!=null){
            if(disparoAumentado)    
                return;
            this.disparoAumentado=true; 
            b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
                this.disparoAumentado=false;
                b.getTiempoDeEfecto().resetear();
            });
            b.getTiempoDeEfecto().iniciar();
        }
    }
    public boolean getDisparoAumentado(){
        return disparoAumentado;
    }
    private void efectoBotellaVerde(BotellaVerde b){
        if(miJuego.getNivelActual()!=null){
            miJuego.getNivelActual().efectoBotellaVerde(true);
            b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
                miJuego.getNivelActual().efectoBotellaVerde(false);
                b.getTiempoDeEfecto().resetear();
            });
            b.getTiempoDeEfecto().iniciar(); 
        }
    }
    protected void verificarPared(Pared p){
        Rectangle paredRectangle=p.getHitbox().getRectangle();
        if(this.getHitbox().getRightHitBox().intersects(paredRectangle)) {
            miJuego.getControlador().setMoverDer(false);
            chocoParedIzq=true;
        }
        else if(this.getHitbox().getLeftHitBox().intersects(paredRectangle)) {
            miJuego.getControlador().setMoverIzq(false);
            chocoParedDer=true;
        }
    }
    public void visitar(Enemigo e){
        if(e.getCongelado())
            e.convertirEnBola();
        else
            this.perderVida();
    }
    public void visitar(Plataforma p){              
        visitarPlataforma(p);
    }
    private void efectoPowerUp(PowerUp b){
        actualizarPuntaje(b.getPuntaje());
        Sonido.getInstancia().reproducirEfectoDeSonido("agarrarObjeto.wav");
        miJuego.eliminarPowerUp(b);
    }
    public void visitar(BotellaAzul b){
        efectoPowerUp(b);
        efectoBotellaAzul(b);
    }
    public void visitar(BotellaRoja b){
        efectoPowerUp(b);
        efectoBotellaRoja(b);
    }
    public void visitar(BotellaVerde b){
        efectoPowerUp(b);
        efectoBotellaVerde(b);
    }
    public void visitar(Fruta f){
        efectoPowerUp(f);
    }
    public void visitar(VidaExtra v){
        if(vidas < vidaDeInicio){
            vidas++;
            miJuego.efectoVidaExtra();
        }
        efectoPowerUp(v);
    }
    public void visitar(PlataformaMovil e) {
        super.visitar(e);
        if(!e.getUsada()){
           actualizarPuntaje(e.getPuntaje());
           e.setUsada();
        }
    }
    public void visitar(PlataformaQuebradiza e) {
        if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
           this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
           this.setPosY(e.getPosY() - this.getHitbox().getAlto());
           tocandoSuelo=ConstantesEntidades.SUELO;
        }
        if(!e.getUsada()){
        actualizarPuntaje(e.getPuntaje());
            e.setUsada();
        }
        e.getTiempoAntesDeRomperse().iniciar();
    }
    public void visitar(Escalera e){
        habilitadoASubirEscalera=true;
        getHitbox().actualizarHitbox(posX, posY, ancho, alto);
        super.visitar(e);
    }
    public boolean estaHabilitadoASubirEscalera(){
        return habilitadoASubirEscalera;
    }
    public void visitar(SueloResbaladizo e){
        super.visitar(e);
        posY=posY-2;
        setVelocidad(1);
    }
    public void visitar(TrampaPinchos t){
        perderVida();    
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}
