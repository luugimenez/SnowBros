package SnowBros.src.Juego;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Grafica.Sprite;

public class Silueta extends Entidad {
    public Silueta(Sprite sprite){
        super(0,0,sprite);
    }
    public String generarClaveEstado() {
        return "normal";
    }
    public void eliminar(){
        nivel.eliminarSilueta(this);
    }
}
