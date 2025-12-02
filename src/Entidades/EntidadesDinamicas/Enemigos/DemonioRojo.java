package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class DemonioRojo extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;
    protected int contadorAnimacion=0;
    private long tiempoEstado = 0;
    private int estado = EstadosDePatron.MOVERIZQUIERDA;
    public DemonioRojo(int posX, int posY, Sprite miSprite){
        super(posX, posY, miSprite);
        salud=ConstantesDificultad.SALUD_DEMONIO_ROJO;
        velocidad=ConstantesDificultad.VELOCIDAD_DEMONIO_ROJO;
    }
    public void mover(){
        if(!detenido)
            switch (direccion) {
                case ConstantesEntidades.QUIETO:
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
                if (posX > ConstantesVistas.PANEL_JUEGO_ANCHO - 100) {
                    estado = EstadosDePatron.ESPERAR;
                    tiempoEstado = ahora;
                }
                break;
        case EstadosDePatron.MOVERIZQUIERDA:
            direccion = ConstantesEntidades.IZQUIERDA;
            mover();
            if (posX < 100) {
                estado = EstadosDePatron.ESPERAR;
                tiempoEstado = ahora;
            }
            break;
        case EstadosDePatron.ESPERAR:
                direccion=ConstantesEntidades.QUIETO;
                if (ahora - tiempoEstado > 2000) { // espera 2 segundos
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        estado = EstadosDePatron.MOVERIZQUIERDA;
                    else
                        estado = EstadosDePatron.MOVERDERECHA;
                }
            break;
        }
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
    public String generarClaveEstado(){
        String clave=null;
        if(contadorAnimacion==3)
            contadorAnimacion=0;
        if(direccion==ConstantesEntidades.QUIETO){
            contadorAnimacion=0;
            switch (dirAnterior) {
                case ConstantesEntidades.DERECHA:
                    clave="quietoDerecha";
                    break;
            
                case ConstantesEntidades.IZQUIERDA:
                    clave="quietoIzquierda";
                    break;
                case ConstantesEntidades.QUIETO:
                    clave="quietoIzquierda";
                    break;
            }
        }else{
            contadorAnimacion++;
            switch (contadorAnimacion) {
                case 1:
                    if(direccion==ConstantesEntidades.IZQUIERDA)
                        clave="moverIzquierda1";
                    else
                        clave="moverDerecha1";
                    break;
            
                case 2:
                    if(direccion==ConstantesEntidades.IZQUIERDA)
                        clave="moverIzquierda2";
                    else
                        clave="moverDerecha2";
                    break;
                case 3:
                     if(direccion==ConstantesEntidades.IZQUIERDA)
                        clave="moverIzquierda3";
                    else
                        clave="moverDerecha3";
                    break;
            }
        }
        return clave;
    }
    public int getSalud(){
        salud= ConstantesDificultad.SALUD_DEMONIO_ROJO;
        return salud;
    }
    public void aceptar(Visitador visitador) {
        visitador.visitar(this);
    }
}