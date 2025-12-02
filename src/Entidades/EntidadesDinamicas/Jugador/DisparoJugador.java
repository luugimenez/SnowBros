package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class DisparoJugador extends EntidadDinamica {
    protected Temporizador tiempoEfecto;
    protected int potenciaDisparo;
    protected int velocidad;
    protected int tiempoCaida;
    protected int aumentos;
    protected boolean efectoBotella;
    public DisparoJugador(int x, int y, Sprite sprite){
        super(x,y,sprite);
        ancho= ancho/2;
        alto= alto/2;
        hitbox.actualizarHitbox(x, y, ancho, alto);
        velocidad=ConstantesDificultad.VELOCIDAD_DISPARO;
        potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_NORMAL;
        tiempoEfecto=new Temporizador(ConstantesTiempos.TIEMPO_DISPARO_JUGADOR);
    }
    public void setDireccion(int direccion, int dirAnt){
        this.direccion = direccion;
        if(dirAnt!=ConstantesEntidades.QUIETO && dirAnt!=ConstantesEntidades.ARRIBA_DERECHA && dirAnt!=ConstantesEntidades.ARRIBA_IZQUIERDA)
            this.dirAnterior = dirAnt;
    }
    public void mover(){
        switch (direccion) {
            case ConstantesEntidades.QUIETO:
                if(dirAnterior==ConstantesEntidades.DERECHA){
                    posX += 8;
                    caida();
                    if(tiempoCaida==ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
                        posY += 2;
                        aumentos++;
                        incrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                }
                else if(dirAnterior==ConstantesEntidades.IZQUIERDA) {
                    posX -= 8;
                    caida();
                    if(tiempoCaida==ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
                        posY += 2;
                        aumentos++;
                        incrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                    break;
                case ConstantesEntidades.DERECHA:
                    posX += 8;
                    caida();
                    if(tiempoCaida==ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
                        posY += 2;
                        aumentos++;
                        incrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    posX -= 8;
                    if(tiempoCaida==ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
                        posY += 2;
                        aumentos++;
                        incrementarY();
                    }
                    caida();
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.ARRIBA_IZQUIERDA:
                    posX -= 8;
                    caida();
                    if(tiempoCaida==ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
                        posY += 2;
                        aumentos++;
                        incrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.ARRIBA_DERECHA:
                    posX += 8;
                    caida();
                    if(tiempoCaida==ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
                        posY += 2;
                        aumentos++;
                        incrementarY();
                    }
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    break;
        }
    }
    private void caida(){
        if(tiempoCaida<ConstantesTiempos.TIEMPO_CAIDA_DISPARO){
            tiempoCaida+=100;
        }
    }
    public void incrementarY(){
        if(aumentos>=3){
            posY += 6;
        }
    }
    public String generarClaveEstado(){
        String estado=null;
        switch (direccion) {
            case ConstantesEntidades.QUIETO:
                if(efectoBotella){
                    if(dirAnterior==ConstantesEntidades.DERECHA)
                        estado="aumentadoDerecha";
                    else if(dirAnterior==ConstantesEntidades.IZQUIERDA)
                        estado="aumentadoIzquierda";
                }
                else{
                    if(dirAnterior==ConstantesEntidades.DERECHA)
                        estado="normalDerecha";
                    else if(dirAnterior==ConstantesEntidades.IZQUIERDA)
                        estado="normalIzquierda";
                }
                break;
            case ConstantesEntidades.DERECHA:
                if(efectoBotella)
                    estado="aumentadoDerecha";
                else
                    estado="normalDerecha";
                break;
            case ConstantesEntidades.IZQUIERDA:
                if(efectoBotella)
                    estado="aumentadoIzquierda";
                else
                    estado="normalIzquierda";
                break;
            case ConstantesEntidades.ARRIBA_DERECHA:
                if(efectoBotella)
                    estado="aumentadoDerecha";
                else
                    estado="normalDerecha";
                break;
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
                if(efectoBotella)
                    estado="aumentadoIzquierda";
                else
                    estado="normalIzquierda";
                break;
        }
        return estado;
    }
    public void setPotenciaAumentada(boolean efectoActivo){
        efectoBotella=efectoActivo;
        if(efectoActivo)
            potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_AUMENTADA;
        else
            potenciaDisparo=ConstantesDificultad.POTENCIA_DISPARO_NORMAL;
    }
    public int getDaño(){
        int dañoADevolver=0;
        if(efectoBotella)
            dañoADevolver=ConstantesDificultad.POTENCIA_DISPARO_AUMENTADA;
        else
            dañoADevolver=ConstantesDificultad.POTENCIA_DISPARO_NORMAL;
        return dañoADevolver;
    }
    public void visitar(Enemigo e) {
        this.eliminar();
        e.recibirDaño(getDaño());
    }
    public void visitar(Plataforma p){
        eliminar();
    }
    protected void verificarPared(Pared p){
        eliminar();
    }
    public void eliminar(){
        nivel.eliminarDisparo(this);
        super.eliminar();
    }
}