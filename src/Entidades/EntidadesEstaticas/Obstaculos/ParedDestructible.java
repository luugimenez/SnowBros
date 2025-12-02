package SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class ParedDestructible extends Pared{
    protected static final int puntaje = 150;
    public ParedDestructible(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
    public void destruirse(){
        super.eliminar();
        eliminarPared();
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public void eliminarPared(){
        nivel.eliminarPared(this);
        borrarLabel();
    }
    public int getPuntaje() {
        return puntaje;
    }
}