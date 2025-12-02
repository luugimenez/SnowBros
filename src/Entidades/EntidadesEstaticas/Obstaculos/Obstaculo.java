package SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;

public abstract class Obstaculo extends EntidadEstatica {
    public Obstaculo(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
    public void eliminar(){
        nivel.eliminarObstaculo(this);
    }
}
