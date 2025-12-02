package SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class TrampaPinchos extends Obstaculo{
    public TrampaPinchos(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}
