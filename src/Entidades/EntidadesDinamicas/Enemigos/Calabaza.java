package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.Visitador;

public class Calabaza extends Enemigo{
    protected Temporizador tiempoCreacionFantasmas;
    private long tiempoEstado = 0;
    private int estado = EstadosDePatron.MOVERIZQUIERDA;
    
    public Calabaza(int x, int y, Sprite sprite){
        super(x, y, sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_CALABAZA;
        tiempoCreacionFantasmas=new Temporizador(ConstantesTiempos.TIEMPO_CREACION_FANTASMA);
        tiempoCreacionFantasmas.setAccionAlFinalizar(()->{
            invocarFantasma();
            tiempoCreacionFantasmas.reiniciar();
        });
        tiempoCreacionFantasmas.iniciar();
        tocandoSuelo=ConstantesEntidades.AIRE;
    }
    public void mover(){
        if(!detenido)
            switch (direccion) {
                case ConstantesEntidades.QUIETO:
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        direccion = ConstantesEntidades.IZQUIERDA;
                    else
                        direccion = ConstantesEntidades.DERECHA;
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    dirAnterior=ConstantesEntidades.IZQUIERDA;
                    if(posX-velocidad>0){
                        posX-=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                    break;
                case ConstantesEntidades.DERECHA:
                    dirAnterior=ConstantesEntidades.DERECHA;
                    if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                        posX+=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                break;
            }  
    }
    public void patronDeMovimiento() {
        long ahora = System.currentTimeMillis();
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                direccion = ConstantesEntidades.DERECHA;
                mover();
                if (posX > ConstantesVistas.PANEL_JUEGO_ANCHO - 100) {
                    estado = EstadosDePatron.ESPERAR;
                    tiempoEstado = ahora;
                }
                break;
            case EstadosDePatron.MOVERIZQUIERDA:
                direccion = ConstantesEntidades.IZQUIERDA;
                mover();
                if (posX < 100) {
                    estado = EstadosDePatron.ESPERAR;
                    tiempoEstado = ahora;
                }
                break; 
            case EstadosDePatron.ESPERAR:
                    direccion=ConstantesEntidades.QUIETO;
                    if (ahora - tiempoEstado > 1000)
                        if (dirAnterior == ConstantesEntidades.DERECHA)
                            estado = EstadosDePatron.MOVERIZQUIERDA;
                        else
                            estado = EstadosDePatron.MOVERDERECHA;
                break;
        }
    }
    public String generarClaveEstado(){
        String clave=null;
        if(direccion==ConstantesEntidades.IZQUIERDA)
            clave="moverIzquierda";
        else
            clave="moverDerecha";
        return clave;
    }
    public void invocarFantasma(){
        if(this!=null){
            gestor.crearFantasma(posX, posY);
        }
    }
    public void detenerTemporizadorDeFantasma(){
        tiempoCreacionFantasmas.resetear();
        tiempoCreacionFantasmas.detener();
    }
    public void convertirEnBola(){
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void agregarPowerUp(Boolean agregar){
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
    }
    public int getPuntajeCongelado() {//redefinido pq son indestructibles
        return 0;
    }
    public int getPuntajeEmpujado() {
        return 0;
    }
    public int getSalud(){
        return salud; // es inmortal, no tiene salud
    }
    public void recibirDaño(int daño){
        Temporizador tiempoAturdido= new Temporizador(3);
        tiempoAturdido.setAccionAlFinalizar(() -> {
            avanzar();
        });
        detener();
        tiempoAturdido.iniciar();
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}