package SnowBros.src.Entidades.EntidadesEstaticas.PowerUps;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class BotellaVerde extends PowerUp{
    public BotellaVerde(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public void aceptar(Visitador v){
        v.visitar(this);
        eliminar();
    }
}
