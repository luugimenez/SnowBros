package SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Pared extends Obstaculo{
    public Pared(int x, int y, Sprite sprite ){
        super(x,y,sprite);
        hitbox.actualizarHitbox(x,y,ancho,alto);
    }
    public void eliminar(){
        nivel.eliminarObstaculo(this);
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}