package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.HitBox;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Visitor.Visitador;

public class Moghera extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;
    private int altoJefe;
    protected boolean congelado;
    private boolean bolaLanzada;
    private int recorrido;

    public Moghera(int x, int y, Sprite sprite){
        super(x, y, sprite);
        ancho = 32;      
        alto = 32;       
        altoJefe = 79;    
        posY= posicionarHitbox(posY);
        hitbox = new HitBox(posX,posY, ancho, alto);
        tocandoSuelo=ConstantesEntidades.SUELO;
        salud = ConstantesDificultad.SALUD_MOGHERA;
        velocidad = ConstantesDificultad.VELOCIDAD_MOGHERA;
        alturaMax = ConstantesDificultad.ALTURA_MAX_SALTO*3;
        recorrido=0;
        estado=EstadosDePatron.ESPERAR;
    }
    public void mover(){
        if(!detenido){
            switch (direccion){
                case ConstantesEntidades.QUIETO:
                    lanzarFuego(); 
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    dirAnterior=ConstantesEntidades.IZQUIERDA;
                    recorrido+=ConstantesDificultad.VELOCIDAD_MOGHERA;
                    posX-=ConstantesDificultad.VELOCIDAD_MOGHERA;
                    this.getHitbox().actualizarHitbox(this.posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.DERECHA:
                    dirAnterior=ConstantesEntidades.DERECHA;
                    recorrido+=ConstantesDificultad.VELOCIDAD_MOGHERA;
                    posX+=ConstantesDificultad.VELOCIDAD_MOGHERA;
                    this.getHitbox().actualizarHitbox(this.posX, posY, ancho, alto);
                    break;
                case ConstantesEntidades.ARRIBA:
                    tocandoSuelo=ConstantesEntidades.SUELO;
                    iniciarSalto();
                    break;
                case ConstantesEntidades.ABAJO:
                    this.setBajarPlataforma(true);
                    break;  
            } 
        }
    }
    public void patronDeMovimiento(){
        long ahora = System.currentTimeMillis();
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                direccion = ConstantesEntidades.DERECHA;
                bolaLanzada=false;
                if(recorrido>40){
                    estado = EstadosDePatron.SALTAR;
                    tiempoEstado = ahora;
                    recorrido=0;
                }
                else
                    mover();
                break;
            case EstadosDePatron.MOVERIZQUIERDA:
                direccion = ConstantesEntidades.IZQUIERDA;
                bolaLanzada=false;
                if(recorrido>40){
                    recorrido=0;
                    tiempoEstado = ahora;
                    estado = EstadosDePatron.MOVERABAJO;
                }
                else
                    mover();
                break;
            case EstadosDePatron.ESPERAR:
                direccion=ConstantesEntidades.QUIETO;
                mover();
                if (ahora - tiempoEstado > 2000) {
                    mover();
                    if(dirAnterior==ConstantesEntidades.IZQUIERDA)
                        estado=EstadosDePatron.MOVERDERECHA;
                    else
                        estado = EstadosDePatron.MOVERIZQUIERDA;
                    tiempoEstado=ahora;
                }
                break;
            case EstadosDePatron.SALTAR:
                direccion=ConstantesEntidades.ARRIBA;
                mover();
                estado=EstadosDePatron.ESPERAR;
                break;
            case EstadosDePatron.MOVERABAJO:
                direccion=ConstantesEntidades.ABAJO;
                mover();
                if(ahora-tiempoEstado>600){
                    this.setBajarPlataforma(false);
                    tiempoEstado=ahora;
                    estado=EstadosDePatron.ESPERAR;
                }
                break;
        } 
    }
    public BolaDeFuego lanzarFuego(){
        BolaDeFuego bola=null;
        if(direccion==ConstantesEntidades.QUIETO){
            int posicionXBola= posX+15;
            if(!bolaLanzada){
                bola=gestor.crearBolaDeFuego(posicionXBola, posY, ConstantesEntidades.IZQUIERDA);
                bola.setSpriteMoghera(true);
                bolaLanzada=true;
            }
        }
        return bola;
    }
    public void setActivo(boolean t){
        activarJefe=t;
    }
    public void convertirEnBola(){
       if(!congelado){
            gestor.actualizarPuntaje(puntajeEmpujado);
            congelado=true;
        }
    }
    public void eliminar(){
        super.eliminar();
        /*Hablamos con Stefano y decidimos que los jefes no debian hacerse bola, 
        asi que para seguir el enunciado, dan el puntaje empujado al morir */
        gestor.actualizarPuntaje(puntajeEmpujado);
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public String generarClaveEstado(){
        String clave=null;
        switch (direccion) {
            case ConstantesEntidades.IZQUIERDA:
                if(congelado){
                    clave="congeladoIzquierda";
                }else{
                    clave="quietoIzquierda";
                }
                hitbox.actualizarHitbox(posX, posY, 32, 32);
                break;
            case ConstantesEntidades.DERECHA:
                if(congelado){
                    clave="congeladoDerecha";
                }
                else{
                    clave="quietoDerecha";
                }
                hitbox.actualizarHitbox(posX, posY, 32, 32);
                break;
            case ConstantesEntidades.QUIETO:
                if(congelado){
                    clave="congeladoDerecha";
                }
                else{
                    clave="quietoDerecha";
                }
                hitbox.actualizarHitbox(posX, posY, 32, 32);
        }
        return clave;
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
    public int getSalud(){
        salud=ConstantesDificultad.SALUD_MOGHERA;
        return salud;
    }
    public void setNivel(Nivel n){
        super.setNivel(n);
    }
    public int posicionarHitbox(int y){
        return (y+altoJefe-alto);
    }
    public void seguirCaminando(Entidad e){
        if(this.getHitbox().getRectangle().intersects(e.getHitbox().getTopHitBox())){
            this.hitbox.actualizarHitbox(posX, posicionarHitbox(posY)-32, ancho, alto);
            this.setPosY(e.getPosY()-altoJefe);
        }
    }
}