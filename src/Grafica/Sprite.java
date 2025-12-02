package SnowBros.src.Grafica;
import java.util.HashMap;

public class Sprite {
    public HashMap<String, String> mapeoDeSprites;
    public Sprite(HashMap<String,String> mapeo){
        mapeoDeSprites=mapeo;
    }
    public String getRutaImagen(String clave){
        return mapeoDeSprites.get(clave);
    }
}
