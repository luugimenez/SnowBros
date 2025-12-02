package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Plataforma extends EntidadEstatica{
    public Plataforma(int x, int y, Sprite sprite){
        super(x, y, sprite);
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public void eliminar(){
        nivel.eliminarPlataforma(this);
    }
}
