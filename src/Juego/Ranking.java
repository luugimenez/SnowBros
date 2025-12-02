package SnowBros.src.Juego;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Ranking{
    protected LinkedHashMap<String, Integer> topClasico;
    protected LinkedHashMap<String, Integer> topSupervivencia;
    protected LinkedHashMap<String, Integer> topContrarreloj;
    public Ranking(){
        topClasico=new LinkedHashMap<String, Integer>();
        topSupervivencia=new LinkedHashMap<String, Integer>();  
        topContrarreloj=new LinkedHashMap<String, Integer>();
    }
    public void setJugador(String nombre, int puntajeJugador, String modoDeJuego){
        //para agregar al respectivo modo
        if(nombre!=null && !nombre.equals("null")){
            if (modoDeJuego.equals("Clasico"))
                actualizarRanking(topClasico, nombre, puntajeJugador);
            else if (modoDeJuego.equals("Supervivencia")) 
                    actualizarRanking(topSupervivencia, nombre, puntajeJugador);
                else if (modoDeJuego.equals("Contrarreloj")) 
                    actualizarRanking(topContrarreloj, nombre, puntajeJugador);
        }
    }
    private void actualizarRanking(LinkedHashMap<String, Integer> mapa, String nombre, int puntaje) {
        mapa.put(nombre, puntaje);
        ordenar(mapa);
    }
    public LinkedHashMap<String, Integer> getTopClasico(){
        return topClasico;
    }
    public LinkedHashMap<String, Integer> getTopSupervivencia(){
        return topSupervivencia;
    }
    public LinkedHashMap<String, Integer> getTopContrarreloj(){
        return topContrarreloj;
    }
    private void ordenar(LinkedHashMap<String,Integer> mapeo){
        LinkedList<Map.Entry<String, Integer>> lista=new LinkedList<>(mapeo.entrySet());
        for(int i=0;i<lista.size()-1;i++){//burbuja
            for(int j=0;j<lista.size()-i-1;j++){
                if(lista.get(j).getValue()<lista.get(j+1).getValue()){
                    Map.Entry<String,Integer> aux=lista.get(j);
                    lista.set(j,lista.get(j+1));
                    lista.set(j+1,aux);
                }
            }
        }
        while(lista.size()>5)
            lista.removeLast();
        mapeo.clear();
        for(Map.Entry<String,Integer> jugador:lista){
            mapeo.put(jugador.getKey(),jugador.getValue());
        }
    }
}