package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import java.util.Random;
import SnowBros.src.Datos.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class TrollAmarillo extends Enemigo{
    protected static final int puntajeCongelado = 300;
    protected static final int puntajeEmpujado = 500;
    private long tiempoEstado;
    public TrollAmarillo(int x, int y, Sprite spritesTrollAmarillo){
        super(x,y,spritesTrollAmarillo);
        salud=ConstantesDificultad.SALUD_TROLL_AMARILLO;
        velocidad=ConstantesDificultad.VELOCIDAD_TROLL_AMARILLO;
        estado=obtenerPatron();
        tiempoEstado=0;
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
                case ConstantesEntidades.ARRIBA:
                    iniciarSalto();
                    break;
                case ConstantesEntidades.ABAJO:
                    if(posY<ConstantesVistas.PANEL_ALTO-100){
                        setBajarPlataforma(true);
                    }
                    else{
                        setBajarPlataforma(false);
                    }
                    break;
            }  
    }
    public void patronDeMovimiento() {
        long ahora=System.currentTimeMillis();
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                direccion = ConstantesEntidades.DERECHA;
                mover();
                if (posX > ConstantesVistas.PANEL_JUEGO_ANCHO - 100) {
                    estado = obtenerPatron();
                    tiempoEstado = ahora;
                }
                break;
            case EstadosDePatron.MOVERIZQUIERDA:
                direccion = ConstantesEntidades.IZQUIERDA;
                mover();
                if (posX < 100) {
                    estado = obtenerPatron();
                    tiempoEstado = ahora;
                }
                break;
            case EstadosDePatron.ESPERAR:
                direccion=ConstantesEntidades.QUIETO;
                if (ahora - tiempoEstado > 2000) // espera 2 segundos
                    estado=obtenerPatron();
                break;
            case EstadosDePatron.SALTAR:
                direccion=ConstantesEntidades.ARRIBA;
                mover();
                estado=obtenerPatron();
                break;
            case EstadosDePatron.MOVERABAJO:
                direccion=ConstantesEntidades.ABAJO;
                mover();
                if(ahora-tiempoEstado>500){
                    this.setBajarPlataforma(false);
                    tiempoEstado=ahora;
                    estado=obtenerPatron();
                }
                break;
        }
    }
    private int obtenerPatron(){
        Random random=new Random();
        int probabilidad= random.nextInt(100);
        if(probabilidad<20){
            estado=EstadosDePatron.MOVERDERECHA;
        }
        else if(probabilidad<40){
                estado=EstadosDePatron.MOVERABAJO;
            }
            else if(probabilidad<60){
                estado=EstadosDePatron.MOVERIZQUIERDA;
            }
                else if(probabilidad<80){
                    estado=EstadosDePatron.ESPERAR;
                }
                    else{
                        estado=EstadosDePatron.SALTAR;
                    }
        return estado;
    }
    public String generarClaveEstado(){
        String estado=null;
        if(direccion==ConstantesEntidades.QUIETO){
            switch (dirAnterior) {
                case ConstantesEntidades.DERECHA:
                    estado="quietoIzquierda";
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    estado="quietoDerecha";
                    break;
                case ConstantesEntidades.QUIETO:
                    estado="quietoDerecha";
                    break;
            }
        }else{
            if(direccion==ConstantesEntidades.IZQUIERDA)
                estado="moverIzquierda";
            else
                estado="moverDerecha";
        }
        return estado;
    }
    public int getPuntajeEmpujado(){
        return puntajeEmpujado;
    }
    public int getPuntajeCongelado(){
        return puntajeCongelado;
    }
    public int getSalud(){
        salud=ConstantesDificultad.SALUD_TROLL_AMARILLO;
        return salud;
    }
    public void aceptar(Visitador visitador) {
        visitador.visitar(this);
    }
}