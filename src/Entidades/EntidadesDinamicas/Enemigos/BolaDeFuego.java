package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeFuego extends EntidadDinamica{
    protected Temporizador tiempo;
    private int tiempoCaida;
    private int decrementos;
    private boolean caida;
    private boolean spriteJefeKamakichi=false;
    private boolean spriteJefeMoghera=false;

    public BolaDeFuego(int posX, int posY,Sprite miSprite){
        super(posX, posY, miSprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_FUEGO);
        caida=false;
        velocidad=ConstantesDificultad.VELOCIDAD_BOLADEFUEGO;
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public void visitar(Jugador jugador){
        jugador.perderVida();
        this.eliminar();
    }
    public void mover(){
        switch(direccion){
            case ConstantesEntidades.DERECHA:
                if(!((posX+=5)>ConstantesVistas.PANEL_ANCHO-10)){
                    posX=posX+5;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                else
                    this.eliminar();
                break;
            case ConstantesEntidades.IZQUIERDA:
                if(!((posX-=5)<10)){
                    posX=posX-5;
                    if(spriteJefeMoghera)
                        actualizarBolaMoghera();
                    else
                        this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                else
                    this.eliminar();
                break;
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
                posX -= 6;
                subida();
                if(tiempoCaida<=1000 && !caida)
                    posY -= 4;
                if(tiempoCaida==1000)
                    caida=true;
                if(caida){
                    posY += 2;
                    decrementos++;
                    decrementarY();
                }
                getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                break;
            case ConstantesEntidades.ARRIBA_DERECHA:
                posX += 6;
                subida();
                if(tiempoCaida<=1000 && !caida){
                    posY -= 4;
                }
                if(tiempoCaida==1000)
                    caida=true;
                if(caida){
                    posY += 2;
                    decrementos++;
                    decrementarY();
                }
                getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                break;
        }
    }
    private void subida(){
        if(tiempoCaida<1000 && !caida)
            tiempoCaida+=100;
    }
    private void decrementarY(){
        if(decrementos>=3)
            posY += 3;
    }
    public void setDireccion(int direccionDisparo) {
        direccion=direccionDisparo;
    }
    public void eliminar(){
        nivel.eliminarBolaDeFuego(this);
        super.eliminar();
    }
    public void visitar(Plataforma p){
        eliminar();
    }
    protected void verificarPared(Pared p){
        eliminar();
    }
    public boolean kamakichiBomba(){
        return direccion==ConstantesEntidades.ARRIBA_DERECHA || direccion==ConstantesEntidades.ARRIBA_IZQUIERDA;
    }
    private void actualizarBolaMoghera(){
        this.getHitbox().actualizarHitbox(posX, posY, 67, 58);
    }
    public String generarClaveEstado(){
        String clave=null;
        switch (direccion) {
            case ConstantesEntidades.IZQUIERDA:
                if(spriteJefeKamakichi)
                    clave="bomba";
                else if(spriteJefeMoghera)
                    clave="moverIzquierdaJefe";
                else
                    clave="moverIzquierda";
                break;
            case ConstantesEntidades.DERECHA:
                if(spriteJefeKamakichi)
                    clave="bomba";
                else
                    clave="moverDerecha";
                break;
            case ConstantesEntidades.QUIETO:
                if(spriteJefeKamakichi){
                    clave="bomba";
                }
                else{
                    clave="moverIzquierda";
                }
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
                if(spriteJefeKamakichi)
                    clave="bomba";
                else
                    clave="moverIzquierda";
                break;
            case ConstantesEntidades.ARRIBA_DERECHA:
                if(spriteJefeKamakichi)
                    clave="bomba";
                else
                    clave="moverDerecha";
                break;
        }
        return clave;
    }
    public void setSpriteKamakichi(boolean b){
        spriteJefeKamakichi=b;
    }
    public void setSpriteMoghera(boolean b){
        spriteJefeMoghera=b;
    }
        
}
