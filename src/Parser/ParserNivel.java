package SnowBros.src.Parser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Juego.Silueta;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Datos.ConstantesNiveles;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;

public class ParserNivel{
    private FabricaEntidades fabrica;
    private boolean superoNivelAnterior;

    public ParserNivel(FabricaEntidades f){
        this.fabrica = f;
        superoNivelAnterior=false;
    }
    public Nivel generarNivel(String modoDeJuego){
        return cargarNivel(obtenerArchivoNivel(modoDeJuego));
    }
    private Nivel cargarNivel(String archivo){
        Silueta silueta= fabrica.getSilueta();
        Nivel nivel = new Nivel(silueta);
        try (BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea=br.readLine())!=null){
                linea = linea.trim();
                if (linea.isEmpty()) 
                    continue; 
                String[] partes = linea.split(" ");
                if (partes.length < 3) 
                    continue; 
                String tipo=partes[0];
                int xEntidad=Integer.parseInt(partes[1]);
                int yArchivo=Integer.parseInt(partes[2]);
                int yEntidad=ConstantesVistas.PANEL_ALTO-yArchivo;                
                switch (tipo){
                    case "Jugador":
                        Jugador jugador=fabrica.getJugador(xEntidad, yEntidad);
                        jugador.setNivel(nivel);
                        nivel.agregarJugador(jugador);
                        break;
                    case "Plataforma":
                        Plataforma plataforma = fabrica.getPlataforma(xEntidad, yEntidad);
                        nivel.agregarPlataforma(plataforma);
                        break;
                    case "PlataformaMovil":
                        PlataformaMovil plataformaMovil = fabrica.getPlataformaMovil(xEntidad, yEntidad);
                        plataformaMovil.setNivel(nivel);
                        nivel.agregarPlataformaMovil(plataformaMovil);
                        break;
                    case "PlataformaQuebradiza":
                        PlataformaQuebradiza plataformaQuebradiza= fabrica.getPlataformaQuebradiza(xEntidad, yEntidad);
                        nivel.agregarPlataforma(plataformaQuebradiza);
                        break;
                    case "DemonioRojo":
                        agregarDemonioRojo(nivel, xEntidad, yEntidad);
                        break;
                    case "TrollAmarillo":
                        agregarTrollAmarillo(nivel, xEntidad, yEntidad);
                        break;
                    case "RanaDeFuego":
                        agregarRanaDeFuego(nivel, xEntidad, yEntidad);
                        break;
                    case "Escalera":
                        Escalera escalera=fabrica.getEscalera(xEntidad, yEntidad);
                        nivel.agregarObstaculo(escalera);
                        break;
                    case "TrampaPinchos":
                        TrampaPinchos trampaPinchos=fabrica.getTrampaPinchos(xEntidad, yEntidad);
                        nivel.agregarObstaculo(trampaPinchos);
                        break;                
                    case "Pared":
                        Pared pared=fabrica.getPared(xEntidad, yEntidad);
                        nivel.agregarObstaculo(pared);
                        nivel.agregarPared(pared);
                        break;
                    case "ParedDestructible":
                        ParedDestructible paredDestructible=fabrica.getParedDestructible(xEntidad, yEntidad);
                        nivel.agregarObstaculo(paredDestructible);
                        nivel.agregarPared(paredDestructible);
                        break;
                    case "SueloResbaladizo":
                        SueloResbaladizo sueloResbaladizo=fabrica.getSueloResbaladizo(xEntidad, yEntidad);
                        nivel.agregarResbaladizo(sueloResbaladizo);
                        break;                
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nivel;
    }
    public void generarOleada(Nivel nivel, String archivo){
        try (BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea=br.readLine())!=null){
                linea = linea.trim();
                if (linea.isEmpty()) continue; 
                String[] partes = linea.split(" ");
                if (partes.length < 3) continue; 
                String tipo=partes[0];
                int xEntidad=Integer.parseInt(partes[1]);
                int yArchivo=Integer.parseInt(partes[2]);
                int yEntidad=ConstantesVistas.PANEL_ALTO-yArchivo;                
                switch (tipo){
                    case "DemonioRojo":
                        agregarDemonioRojo(nivel, xEntidad, yEntidad);
                        break;
                    case "TrollAmarillo":
                        agregarTrollAmarillo(nivel, xEntidad, yEntidad);
                        break;
                    case "RanaDeFuego":
                        agregarRanaDeFuego(nivel, xEntidad, yEntidad);
                        break;             
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void agregarDemonioRojo(Nivel nivel, int x, int y){
        DemonioRojo demonioRojo=fabrica.getDemonioRojo(x, y);
        demonioRojo.setNivel(nivel);
        nivel.agregarEnemigo(demonioRojo);
    }
    private void agregarTrollAmarillo(Nivel nivel, int x, int y){
        TrollAmarillo trollAmarillo=fabrica.getTrollAmarillo(x,y);
        trollAmarillo.setNivel(nivel);
        nivel.agregarEnemigo(trollAmarillo);
    }
    private void agregarRanaDeFuego(Nivel nivel, int x, int y){
        RanaDeFuego ranaFuego=fabrica.getRanaDeFuego(x, y);
        ranaFuego.setNivel(nivel);
        nivel.agregarEnemigo(ranaFuego);
    }
    public String obtenerArchivoNivel(String modo) {
        String nivelACargar=null;
        if(modo.equals("Clasico"))
            if(!superoNivelAnterior){
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel1.wav");
                nivelACargar = ConstantesNiveles.NIVEL1;
            }else{
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel2.wav");
                nivelACargar = ConstantesNiveles.NIVEL2;
            }
        else if(modo.equals("Supervivencia"))
            if(!superoNivelAnterior){
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel3.wav");
                nivelACargar = ConstantesNiveles.NIVEL2;
            }else{
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel4.wav");
                nivelACargar = ConstantesNiveles.NIVEL3;
            }
        else if(modo.equals("Contrarreloj"))
            if(!superoNivelAnterior){
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel5.wav");
                nivelACargar = ConstantesNiveles.NIVEL3;
            }else{
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel6.wav");
                nivelACargar = ConstantesNiveles.NIVEL1;
            }
        return nivelACargar;
       }
    public void setSuperoNivelAnterior(boolean b){
        superoNivelAnterior=b;
    }
    public boolean getSuperoNivelAnterior(){
        return superoNivelAnterior;
    }
}
