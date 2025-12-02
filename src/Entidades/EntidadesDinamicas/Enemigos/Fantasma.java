package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Fantasma extends Enemigo{
    private int xJugador;
    private int yJugador;
    public Fantasma(int x, int y, Sprite sprite){
        super(x, y, sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_FANTASMA;
        direccion=0;
    }
    public void mover(){
        if(!detenido){
            if(xJugador> posX){
                posX+= velocidad;
                direccion=ConstantesEntidades.DERECHA;
            }
            else {
                posX-=velocidad;
                direccion=ConstantesEntidades.IZQUIERDA;
            }
            if(yJugador > posY)
                posY+= velocidad;
                else posY-=velocidad;
            this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
        }
    }
    public void setPosicionJugador(int x, int y){
        xJugador=x;
        yJugador=y;
    }
    public void patronDeMovimiento(){
        mover();
    }
    public int getSalud(){
        return salud; // es inmortal, no tiene salud
    }
    public int getPuntajeCongelado() {
        return 0;
    }
    public int getPuntajeEmpujado() {
        return 0;//indestructible
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}