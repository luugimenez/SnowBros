package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import java.util.Random;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.GestorEntidad;
import SnowBros.src.Juego.Temporizador;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;
    private Temporizador tiempo;
    private int velocidadMaxima;
    private boolean reboto;
    protected int puntajeEmpujado;
    protected GestorEntidad gestor;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_NIEVE);
        velocidad=1;
        estaActiva=false;
        reboto=false;
        velocidadMaxima=ConstantesDificultad.VELOCIDAD_MAXIMA_BOLADENIEVE;
        velocidad=ConstantesDificultad.VELOCIDAD_BOLADENIEVE;
    }
    public boolean getEstado(){
        return estaActiva;
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public int disparar(){
        estaActiva=true;
        return puntajeEmpujado;
    }
    public void mover(){
        if(estaActiva){
            switch(direccion){
                case ConstantesEntidades.DERECHA:
                    posX=posX+velocidadMaxima;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                    if(!reboto && posX>ConstantesVistas.PANEL_JUEGO_ANCHO-20){
                        direccion=ConstantesEntidades.IZQUIERDA;
                        reboto=true;
                    }
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    posX=posX-velocidadMaxima;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                    if(!reboto && posX<20){
                    direccion=ConstantesEntidades.DERECHA;
                    reboto=true;
                    }
                    break;
            }
            terminaRebotar();
        }
    }
    private void terminaRebotar(){
        if(reboto && posX<=0 || posX>=ConstantesVistas.PANEL_JUEGO_ANCHO)
            this.eliminar(); 
    }
    protected void verificarPared(Pared p){
        if(direccion==ConstantesEntidades.IZQUIERDA)
            direccion=ConstantesEntidades.DERECHA;
        else
            direccion=ConstantesEntidades.IZQUIERDA;
    }
    public void visitar(Enemigo e){
        if(estaActiva){
            aparecerPowerUp();
            e.eliminar();
        }  
    }
    private void aparecerPowerUp(){
        Random random=new Random();
        int probabilidad= random.nextInt(100);
        PowerUp p;
        if(probabilidad<50){
            p= gestor.getFabrica().getFruta(posX,posY);
        }
        else if(probabilidad<65){
            p= gestor.getFabrica().getBotellaAzul(posX,posY);
        }
        else if(probabilidad<80){
            p= gestor.getFabrica().getBotellaRoja(posX,posY);
        }
        else if(probabilidad<95){
            p= gestor.getFabrica().getBotellaVerde(posX,posY);
        }
        else{
            p= gestor.getFabrica().getVidaExtra(posX,posY);
        }            
        gestor.agregarPowerUp(p);
    }
    public void visitar(ParedDestructible p){
        p.destruirse();
    }
    public void eliminar(){
        if(observer!=null){
            nivel.eliminarBolaDeNieve(this);
            super.eliminar();
            aparecerPowerUp();
        }
    }
    public void visitar(Jugador j){
        if(!estaActiva){  
            if (j.getPosX() < this.posX)
                direccion = ConstantesEntidades.DERECHA;
            else 
                direccion = ConstantesEntidades.IZQUIERDA;
            j.setEmpujando(this);        
            empujar();
            j.setVelocidad(velocidad);
        }
    }
    public void empujar(){
        switch(direccion){
            case ConstantesEntidades.DERECHA:
                if(!(posX>ConstantesVistas.PANEL_ANCHO-5)){
                    posX=posX+velocidad;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.IZQUIERDA:
                if(posX>-5){
                    posX=posX-velocidad;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
        }
    }
    public int getPuntajeEmpujado(){
        return puntajeEmpujado;
    }
    public void setPuntajeEmpujado(int puntaje){
        puntajeEmpujado=puntaje;
    }
    public void setGestor(GestorEntidad g){
        gestor=g;
    }
}
