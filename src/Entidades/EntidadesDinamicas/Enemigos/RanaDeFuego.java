package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.LocalizadorJugador;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Visitor.Visitador;

public class RanaDeFuego extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;
    protected boolean lanzandoBolas;
    protected int direccionDisparo;
    private boolean bolaLanzada=false;
    private LocalizadorJugador jugador;
    public RanaDeFuego(int x, int y, Sprite sprite){
        super(x,y,sprite);
        lanzandoBolas=false;
        salud=ConstantesDificultad.SALUD_RANA_DE_FUEGO;
        velocidad=ConstantesDificultad.VELOCIDAD_RANA_DE_FUEGO;
    }
    public void mover(){
        if(!detenido)
            switch (direccion) {
                case ConstantesEntidades.QUIETO:
                    lanzarFuego(); 
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        direccion = ConstantesEntidades.IZQUIERDA;
                    else
                        direccion = ConstantesEntidades.DERECHA;
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    dirAnterior=ConstantesEntidades.IZQUIERDA;
                    if(posX-velocidad>0){
                        posX-=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                    break;
                case ConstantesEntidades.DERECHA:
                    dirAnterior=ConstantesEntidades.DERECHA;
                    if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                        posX+=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                    break;
            }  
    }
    public void patronDeMovimiento() {
        long ahora = System.currentTimeMillis();
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                direccion = ConstantesEntidades.DERECHA;
                mover();
                bolaLanzada=false;
                if (posX > ConstantesVistas.PANEL_JUEGO_ANCHO - 100) {
                    estado = EstadosDePatron.ESPERAR;
                    tiempoEstado = ahora;
                }
                break;
        case EstadosDePatron.MOVERIZQUIERDA:
            direccion = ConstantesEntidades.IZQUIERDA;
            mover();
            bolaLanzada=false;
            if (posX < 100) {
                estado = EstadosDePatron.ESPERAR;
                tiempoEstado = ahora;
            }
            break;
        case EstadosDePatron.ESPERAR:
                direccion=ConstantesEntidades.QUIETO;
                mover();
                if (ahora - tiempoEstado > 2000) { // espera 2 segundos
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        estado = EstadosDePatron.MOVERIZQUIERDA;
                    else
                        estado = EstadosDePatron.MOVERDERECHA;
                }
            break;
        }
    }
    public BolaDeFuego lanzarFuego(){
        BolaDeFuego bola=null;
        if(direccion==ConstantesEntidades.QUIETO){
            lanzandoBolas=true;
            int posicionXBola;
            if(jugador.getJugadorX()>this.getPosX())
                direccionDisparo=ConstantesEntidades.DERECHA;
            else
                direccionDisparo=ConstantesEntidades.IZQUIERDA;
            direccion=direccionDisparo;
            if (direccionDisparo==ConstantesEntidades.DERECHA) 
                posicionXBola= this.getPosX()+15;
            else
                posicionXBola=this.getPosX()-15;
            if(!bolaLanzada){
                gestor.crearBolaDeFuego(posicionXBola, posY, direccionDisparo);
                bolaLanzada=true;
            }
        }
        return bola;
    }
    public String generarClaveEstado(){
        String clave=null;
        if(direccion==ConstantesEntidades.QUIETO){
            if (lanzandoBolas) {
                switch (dirAnterior) {
                    case ConstantesEntidades.IZQUIERDA:
                        clave="disparoIzquierda";
                        break;
                    case ConstantesEntidades.DERECHA:
                        clave="disparoDerecha";
                        break;
                }
            }else{
                switch (dirAnterior) {
                    case ConstantesEntidades.IZQUIERDA:
                        clave="quietoIzquierda";
                        break;
                    case ConstantesEntidades.DERECHA:
                        clave="quietoDerecha";
                        break;
                    case ConstantesEntidades.QUIETO:
                        clave="quietoIzquierda";
                        break;
                }
            }
        } else{
            if(direccion==ConstantesEntidades.IZQUIERDA)
                clave="moverIzquierda";
            else
                clave="moverDerecha";
        }
        return clave;
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
    public int getSalud(){
        salud=ConstantesDificultad.SALUD_RANA_DE_FUEGO;
        return salud;
    }
    public void aceptar(Visitador visitador) {
        visitador.visitar(this);
    }
    public void setNivel(Nivel n){
        super.setNivel(n);
        jugador=n;
    }      
}