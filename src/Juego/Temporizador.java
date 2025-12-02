package SnowBros.src.Juego;

import SnowBros.src.Datos.ConstantesDificultad;

public class Temporizador {
    private int tiempoActual;
    private int tope;
    private Thread hilo;
    private boolean enMarcha;
    private Runnable accionAlFinalizar;
    public Temporizador(){
        tope=Integer.MAX_VALUE;
        tiempoActual=0;
        enMarcha=false;
    }
    public Temporizador(int tMaximo){
        tope=tMaximo;
        tiempoActual=0;
        enMarcha=false;
    }
    public void iniciar(){
        if(enMarcha) 
            return;
        hilo=new Thread(()->{
            enMarcha=true;
            while (enMarcha && !llegoAlTope()){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
                incrementar();
            }
            enMarcha=false;
            if(accionAlFinalizar!=null && hilo!=null && !hilo.isInterrupted()){
                accionAlFinalizar.run();
            }
        });
        hilo.start();
    }
    public void setAccionAlFinalizar(Runnable accion){
        accionAlFinalizar = accion;
    }
    public void detener(){
        enMarcha=false;
        if(hilo!=null){
            hilo.interrupt();
            hilo=null;
        }
    }
    public void reiniciar(){
        detener();
        tiempoActual=0;
        iniciar();
    }
    public void resetear(){
        tiempoActual=0;
    }
    public void incrementar(){
        if(tiempoActual<tope)
            tiempoActual++;
    }
    public boolean llegoAlTope(){
        return tiempoActual==tope;
    }
    public int getTiempoActual(){
        return tiempoActual;
    }
    public String getStringDeTiempo(){
        int minutos=tiempoActual/60;
        int segundos=tiempoActual%60;
        return String.format("%02d:%02d", minutos, segundos);
    }
    public String getStringTiempoContrarreloj(){
        int restante = ConstantesDificultad.TOPE_TIEMPO_CONTRARRELOJ - tiempoActual;
        if(restante < 0) 
            restante = 0;
        int minutos = restante / 60;
        int segundos = restante % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
