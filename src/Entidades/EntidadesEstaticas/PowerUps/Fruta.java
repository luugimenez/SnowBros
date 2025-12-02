package SnowBros.src.Entidades.EntidadesEstaticas.PowerUps;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Fruta extends PowerUp{
    private final static int puntaje = 500;
    public Fruta(int x, int y, Sprite sprite){
        super(x, y, sprite);
    }
    public int getPuntaje(){
        return puntaje;
    }
    public void aceptar(Visitador v){
        v.visitar(this);
        eliminar();
    }
}
