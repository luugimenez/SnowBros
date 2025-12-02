package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Grafica.ObserverPlataformaMovil;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class PlataformaMovil extends Plataforma{
    private static final int puntaje=200;
    protected boolean horizontal;
    protected int velocidad;
    protected ObserverPlataformaMovil observer;
    protected int distanciaRecorrida;
    protected int anteriorX;
    protected int deltaX;
    private int estado=EstadosDePatron.MOVERDERECHA;
    private int distaciaMaxRecorrida;
    protected boolean usada;

    public PlataformaMovil(int x, int y, Sprite sprite){
        super(x,y,sprite);
        observer= new ObserverPlataformaMovil(this);
        velocidad=ConstantesDificultad.VELOCIDAD_PLATAFORMA_MOVIL;
        distaciaMaxRecorrida=40;
        distanciaRecorrida=0;
        anteriorX=posX;
        deltaX=0;
        usada=false;
    }
    public void mover(){
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                    if(distaciaMaxRecorrida>distanciaRecorrida){
                        distanciaRecorrida=velocidad+distanciaRecorrida;
                        anteriorX=posX;
                        posX=posX+velocidad;
                        deltaX = posX - anteriorX;
                        this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                    }
                    else{
                        distanciaRecorrida=0;
                        estado=EstadosDePatron.MOVERIZQUIERDA;
                    }
                    break;
            case EstadosDePatron.MOVERIZQUIERDA:
                if(distaciaMaxRecorrida>distanciaRecorrida){
                    distanciaRecorrida=velocidad+distanciaRecorrida;
                    anteriorX=posX;
                    posX=posX-velocidad;
                    deltaX = posX - anteriorX;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                else{
                    distanciaRecorrida=0;
                    estado=EstadosDePatron.MOVERDERECHA;
                }
                break;
        }
    }
    public int getPuntaje(){
        return puntaje;
    }
    public int getDeltaX(){
        return deltaX;
    }
    public ObserverPlataformaMovil getObserver() {
        return observer;
    }
    public void registrarObserver(ObserverPlataformaMovil obs){
        observer=obs;
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public void setUsada(){
        usada=true;
    }
    public boolean getUsada(){
        return usada;
    } 
}
