package SnowBros.src.Juego;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesNiveles;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.PowerUp;
import SnowBros.src.Fabricas.*;
import SnowBros.src.Grafica.*;
import SnowBros.src.Parser.ParserNivel;

public class Juego {
    protected Nivel nivel;
    protected int puntajeJugador;
    protected Temporizador tiempo;
    protected boolean reiniciarJugador;
    protected Jugador jugador;
    protected Ranking ranking;
    protected GUI controladorGrafica; 
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidades fabricaEntidades;
    protected ParserNivel parserActual;
    private Temporizador tiempoAntesAparicionCalabaza;
    private Temporizador tiempoAntesAparicionJefeContrarreloj;
    protected Thread hiloColision;
    protected Thread hiloGlobal;
    protected volatile boolean juegoEnEjecucion;
    protected String nombreJugador;
    protected String modoDeJuego;
    private long ultimoDisparo;
    protected String dominio;
    protected GestorEntidad gestor;
    protected boolean jefeCreado;
    private int oleadas;
    private boolean habilitarCondicionClasico;

    public Juego(){
        reiniciarJugador=false;
        ranking=new Ranking();
        tiempo= new Temporizador(); 
        tiempoAntesAparicionCalabaza= new Temporizador(ConstantesTiempos.TIEMPO_CALABAZA);
        tiempoAntesAparicionJefeContrarreloj= new Temporizador(ConstantesTiempos.TIEMPO_JEFE);
        juegoEnEjecucion=false;
        ultimoDisparo=0;
        oleadas=0;
        gestor= new GestorEntidad(this);
    }
    public void setControladorGrafica(GUI miControlador) {
        controladorGrafica=miControlador;
    }
    public void iniciar(){
        if(dominio.equals("Original"))
            fabricaSprites= new Original();
        else if (dominio.equals("Alternativo"))
            fabricaSprites= new Alternativo();
        fabricaEntidades = new FabricaEntidades(fabricaSprites,gestor);
        if ((parserActual == null)||(parserActual!=null && !parserActual.getSuperoNivelAnterior()))
            parserActual = new ParserNivel(fabricaEntidades);
        jefeCreado=false;
        habilitarCondicionClasico=false;
        iniciarNivel();
    }
    private void iniciarHilos(){
        hiloColision = new Thread(() -> {
            while(juegoEnEjecucion&&nivel!=null){
                Jugador jugadorActual=jugador;
                Nivel nivelActual=nivel;
                if(nivel==null||jugador==null)
                    break;
                for(Enemigo enemigo : new ArrayList<>(nivelActual.getEnemigos())){
                    if (jugadorActual.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                        enemigo.aceptar(jugadorActual);
                    if(!nivelActual.getDisparos().isEmpty())
                        for (DisparoJugador disparo:new ArrayList<>(nivelActual.getDisparos()))
                            if (disparo.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                                if (disparo.getObserver()!=null) 
                                    enemigo.aceptar(disparo);
                    for(Pared p: new ArrayList<>(nivelActual.getParedes()))
                        if (enemigo.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())) 
                            p.aceptar(enemigo);
                }
                if (!nivelActual.getFantasmas().isEmpty()){
                    for(Fantasma f : nivelActual.getFantasmas()){
                        if (jugadorActual.getHitbox().getRectangle().intersects(f.getHitbox().getRectangle())) {
                            f.aceptar(jugadorActual);
                            }
                        }
                }
                if (nivelActual.getCalabaza()!=null){
                    if (jugadorActual.getHitbox().getRectangle().intersects(nivelActual.getCalabaza().getHitbox().getRectangle())) {
                        nivelActual.getCalabaza().aceptar(jugadorActual);
                    }
                    if(!nivelActual.getDisparos().isEmpty()){
                        for(DisparoJugador disparo : nivelActual.getDisparos()){
                            if(disparo.getHitbox().getRectangle().intersects(nivelActual.getCalabaza().getHitbox().getRectangle())){
                                nivelActual.getCalabaza().aceptar(disparo);
                            }
                        }
                    }
                }
                for(Pared p: new ArrayList<>(nivelActual.getParedes())){
                    if(jugadorActual.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())){
                            p.aceptar(jugadorActual);
                    }
                    else{
                        jugadorActual.setChocoPared(false);
                        }
                    if(!nivelActual.getDisparos().isEmpty()){
                        for(DisparoJugador disparo : new ArrayList<>(nivelActual.getDisparos())){
                            if(disparo.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())){
                                p.aceptar(disparo);
                            }
                        }
                    }
                }
                for(PowerUp entidad : new ArrayList<>(nivelActual.getPowerUps()))
                    if (jugadorActual.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) 
                        entidad.aceptar(jugadorActual);
                for(Obstaculo entidad : new ArrayList<>(nivelActual.getObstaculos())){
                    if (jugadorActual.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())) 
                        entidad.aceptar(jugadorActual);
                    if(nivelActual.getCalabaza()!=null && nivelActual.getCalabaza().getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())){
                            entidad.aceptar(nivelActual.getCalabaza());
                    }
                    for(Enemigo e:nivelActual.getEnemigos()){
                        if(e.getHitbox().getRectangle().intersects(entidad.getHitbox().getRectangle())){
                            entidad.aceptar(e);
                        }
                    }
                }
                for(Plataforma plataforma: new ArrayList<>(nivelActual.getPlataformas())){
                    if (plataforma!=null && jugadorActual.getHitbox().getLeftHitBox().intersects(plataforma.getHitbox().getRectangle())){
                        jugadorActual.chocaLadoPlataforma(true);
                    } 
                    if (jugadorActual.getHitbox().getRightHitBox().intersects(plataforma.getHitbox().getRectangle())){
                        jugadorActual.chocaLadoPlataforma(false);
                    }
                    if(!nivelActual.getDisparos().isEmpty()){
                        for (DisparoJugador disparo: new ArrayList<>(nivelActual.getDisparos()))
                            if (disparo.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) 
                                plataforma.aceptar(disparo);
                    }
                    if(!nivelActual.getBolasDeFuego().isEmpty()){
                        for (BolaDeFuego bomba: new ArrayList<>(nivelActual.getBolasDeFuego()))
                            if (bomba!=null && bomba.kamakichiBomba() && bomba.getHitbox().getRectangle().intersects(plataforma.getHitbox().getRectangle())) 
                                plataforma.aceptar(bomba);
                    }
                }
                for(PlataformaMovil movil:nivelActual.getPlataformasMovil()){
                    movil.mover();
                    if(jugadorActual.getHitbox().getRectangle().intersects(movil.getHitbox().getRectangle())) 
                        movil.aceptar(jugadorActual);
                }
                if(!nivelActual.getBolasDeFuego().isEmpty())
                    for(BolaDeFuego bola: new ArrayList<>(nivelActual.getBolasDeFuego()))
                        if (bola!=null && bola.getHitbox().getRectangle().intersects(jugadorActual.getHitbox().getRectangle())) 
                            jugadorActual.aceptar(bola);
                boolean colisionBola = false;
                boolean colisionSuelo = false;
                for (BolaDeNieve bola : nivelActual.getBolasDeNieve()) {
                    for(Enemigo enemigo: new ArrayList<>(nivelActual.getEnemigos()))
                        if (bola.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                                enemigo.aceptar(bola);
                    if (bola.getHitbox().getLeftHitBox().intersects(jugadorActual.getHitbox().getRectangle()) ||
                        bola.getHitbox().getRightHitBox().intersects(jugadorActual.getHitbox().getRectangle())) {
                        jugadorActual.aceptar(bola);
                        colisionBola = true;
                    }
                    else{
                        colisionBola=false;
                        controladorGrafica.setEmpujando(false);
                        jugadorActual.setEmpujando(null);
                    }
                    for(Pared p:new ArrayList<>(nivelActual.getParedes())){
                            if(bola.getHitbox().getRectangle().intersects(p.getHitbox().getRectangle())){
                                p.aceptar(bola);
                            }
                    }
                }
                for (SueloResbaladizo suelo : nivelActual.getResbaladizos()) {
                    if (jugadorActual.getHitbox().getRectangle().intersects(suelo.getHitbox().getRectangle())) {
                        suelo.aceptar(jugadorActual);
                        colisionSuelo = true;
                    }
                    for(Enemigo enemigo:nivelActual.getEnemigos())
                        if(suelo.getHitbox().getRectangle().intersects(enemigo.getHitbox().getRectangle())) 
                            suelo.aceptar(enemigo);
                    for(BolaDeNieve bola:nivelActual.getBolasDeNieve()){
                        if(suelo.getHitbox().getRectangle().intersects(bola.getHitbox().getRectangle())) 
                            suelo.aceptar(bola);
                    }
                    if(nivelActual.getCalabaza()!=null && suelo.getHitbox().getRectangle().intersects(nivelActual.getCalabaza().getHitbox().getRectangle())) 
                        suelo.aceptar(nivelActual.getCalabaza());
                }
                if (!colisionBola && !colisionSuelo && jugadorActual.getVelocidad() != ConstantesDificultad.EFECTO_BOTELLA_AZUL) {
                    jugadorActual.setVelocidadBase();
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        hiloGlobal = new Thread(() -> {
            final int FPS = 60;
            final long frameTime = 1000 / FPS;
            while (juegoEnEjecucion&&nivel!=null) {
                Nivel nivelActual=nivel;
                Jugador jugadorActual=jugador;
                if(nivel==null||jugador==null)  
                    break;
                long inicio = System.currentTimeMillis();
                controladorGrafica.moverJugadorSegunInput();
                jugador.actualizarSalto();
                if(!jugadorActual.getSaltando())
                    jugadorActual.verificarSuelo();
                for (Enemigo enemigo : new ArrayList<>(nivelActual.getEnemigos())) {
                   enemigo.patronDeMovimiento();        
                   enemigo.actualizarSalto();
                   if(!enemigo.getSaltando())
                        enemigo.verificarSuelo(); 
                   if(enemigo.getCongelado()){
                        if(enemigo.getDerrotado()){
                            enemigo.setCongelado(true);
                            long muerte=enemigo.getInicioCongelado();
                            long tiempoADescongelar=5000;
                            if(System.currentTimeMillis()>muerte+tiempoADescongelar){
                                enemigo.eliminar();
                                muerte=0;
                            }
                        }
                        else{
                            enemigo.actualizarTiempoCongelado();
                        }
                   }
                }    
                if(cumplirCondicionDelModo()){
                    this.superarNivel();
                }    
                if(modoDeJuego.equals("Supervivencia") && nivelActual.getEnemigos().isEmpty() && oleadas<=3){
                    if(!parserActual.getSuperoNivelAnterior()){
                        parserActual.generarOleada(nivelActual, ConstantesNiveles.OLEADA1);
                        registrarObserversEntidades(nivelActual.getEnemigos());
                        oleadas++;
                        if(oleadas==2){
                            crearMoghera();
                        }
                    }
                    else{
                        parserActual.generarOleada(nivelActual, ConstantesNiveles.OLEADA2);
                        registrarObserversEntidades(nivelActual.getEnemigos());
                        oleadas++;
                        if(oleadas==2){
                            crearKamakichi();
                        }
                    }
                }
                if(modoDeJuego.equals("Contrarreloj") && tiempo.getTiempoActual()==ConstantesDificultad.TOPE_TIEMPO_CONTRARRELOJ){
                    reiniciarNivel();
                }
                if(!nivelActual.getDisparos().isEmpty())
                    for (DisparoJugador disparo: new ArrayList<>(nivelActual.getDisparos()))
                        disparo.mover();
                if(!nivelActual.getBolasDeFuego().isEmpty())
                    for (BolaDeFuego bola:new ArrayList<>(nivelActual.getBolasDeFuego()))
                        bola.mover();
                for(BolaDeNieve bolaDeNieve:nivelActual.getBolasDeNieve()){
                    bolaDeNieve.verificarSuelo();
                    bolaDeNieve.mover();
                }
                Calabaza calabaza= nivelActual.getCalabaza();
                if (calabaza!=null){
                    calabaza.patronDeMovimiento();
                    calabaza.verificarSuelo();
                }
                if (!nivelActual.getFantasmas().isEmpty()){
                    for(Fantasma fantasma : nivelActual.getFantasmas()){
                        fantasma.setPosicionJugador(jugadorActual.getPosX(), jugadorActual.getPosY());
                        fantasma.patronDeMovimiento();
                    }
                }
                if(nivelActual==nivel&&nivelActual.terminoElNivel()&&!jefeCreado&&modoDeJuego.equals("Clasico")){
                    jefeCreado=true;
                    if(parserActual.getSuperoNivelAnterior())
                        crearKamakichi(); //PARA NIVEL 2
                    else
                        crearMoghera(); //PARA NIVEL 1
                }
                nivelActual.notificarObservers();
                long duracion = System.currentTimeMillis() - inicio;
                long dormir = frameTime - duracion;
                if (dormir > 0) 
                    try {
                        Thread.sleep(dormir);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
            }
        });
        hiloGlobal.start();
        hiloColision.start();
    }
    public void detenerJuego(){           
        juegoEnEjecucion = false;
        if (hiloGlobal != null && hiloGlobal.isAlive()) {
            hiloGlobal.interrupt();
        }
        if (hiloColision != null && hiloColision.isAlive()) {
            hiloColision.interrupt();
        }
        try {
            if (hiloGlobal != null) {
                hiloGlobal.join();
            }
            if (hiloColision != null) {
                hiloColision.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        hiloGlobal = null;
        hiloColision = null;
    }
    public void reiniciarNivel(){
        if(jugador.getVidas()==0 || tiempo.getTiempoActual()==ConstantesDificultad.TOPE_TIEMPO_CONTRARRELOJ){
            ranking.setJugador(jugador.getNombre(),puntajeJugador,modoDeJuego);
            detenerJuego();
            nivel=null;
            jugador=null;
            puntajeJugador=0;
            oleadas=0;
            habilitarCondicionClasico=false;
            tiempo.detener();
            tiempo.resetear();
            tiempoAntesAparicionCalabaza.detener();
            tiempoAntesAparicionCalabaza.resetear();
            tiempoAntesAparicionJefeContrarreloj.detener();
            tiempoAntesAparicionJefeContrarreloj.resetear();
            controladorGrafica.cambiarPantallaGameOver(); // gameOver
        }
        else 
            controladorGrafica.cambiarInicioJuego();
    }
    public void iniciarNivel(){
        if (nivel == null){
            nivel = parserActual.generarNivel(modoDeJuego); // o el nivel que correspon
            gestor.setNivel(nivel);
        }
        if(jugador==null){
            jugador = nivel.getJugador();
            jugador.setJuego(this);
            jugador.setNombre(nombreJugador);
        }
        else{//si ya existe el jugador
            if(jugador.getVidas()==0)
                reiniciarJugador=false;
            if(reiniciarJugador){
                nivel=parserActual.generarNivel(modoDeJuego);
                jugador = nivel.getJugador();
                jugador.setJuego(this);
            }
            else{
                nivel=parserActual.generarNivel(modoDeJuego);
                jugador = nivel.getJugador();
                jugador.setVidas();
                jugador.setJuego(this);
                jugador.actualizarPuntaje(0);
            }
            puntajeJugador=0;
        }
        this.registrarObservers();
        jugador.getObserver().actualizar();
        tiempoAntesAparicionCalabaza.setAccionAlFinalizar(()->crearCalabaza());
        tiempoAntesAparicionCalabaza.iniciar();
        tiempo.iniciar();
        if(modoDeJuego.equals("Contrarreloj")){
            if(parserActual.getSuperoNivelAnterior()){
                tiempoAntesAparicionJefeContrarreloj.setAccionAlFinalizar(()-> {
                    crearMoghera();
                });
            }
            else{
                tiempoAntesAparicionJefeContrarreloj.setAccionAlFinalizar(()->{
                    crearKamakichi();
                });
            }
            tiempoAntesAparicionJefeContrarreloj.iniciar();
        }
        juegoEnEjecucion=true;
        iniciarHilos();
    }
    public void superarNivel(){
        detenerJuego();
        if(nivel.getCalabaza()!=null){
            nivel.getCalabaza().detenerTemporizadorDeFantasma();
        }
        tiempo.detener();
        tiempo.resetear();
        tiempoAntesAparicionCalabaza.detener();
        tiempoAntesAparicionCalabaza.resetear();
        tiempoAntesAparicionJefeContrarreloj.detener();
        tiempoAntesAparicionJefeContrarreloj.resetear();
        nivel.eliminarse(); 
        jefeCreado=false;
        habilitarCondicionClasico=false;
        if(modoDeJuego.equals("Supervivencia")){
            puntajeJugador=0;
            oleadas=0;
        }
        if(!parserActual.getSuperoNivelAnterior()){
            parserActual.setSuperoNivelAnterior(true);
            nivel=null;
            jugador=null;
            controladorGrafica.cambiarPantallaTransicion();
        }else{
            controladorGrafica.cambiarPantallaFinDelJuego();
            tiempo.detener();
            tiempo.resetear();
            tiempoAntesAparicionCalabaza.detener();
            tiempoAntesAparicionCalabaza.resetear();
            tiempoAntesAparicionJefeContrarreloj.detener();
            tiempoAntesAparicionJefeContrarreloj.resetear();
            parserActual.setSuperoNivelAnterior(false);
        }
    }
    public boolean cumplirCondicionDelModo(){
        boolean condicion=false;
        switch (modoDeJuego) {
            case "Clasico":
                if(nivel!=null){
                    condicion = habilitarCondicionClasico && nivel.getEnemigos().isEmpty();
                }
                break;
            case "Supervivencia":
                if(nivel!=null){
                    condicion = puntajeJugador>=ConstantesDificultad.CONDICION_SUPERVIVENCIA;
                }
                break;
            case "Contrarreloj":
                if(nivel!=null){
                    condicion = nivel.getEnemigos().isEmpty();
                }
                break;
        }
        return condicion;
    }
    public void actualizarPuntaje(int puntajeASumar){
        puntajeJugador+=puntajeASumar;
        ranking.setJugador(jugador.getNombre(), puntajeJugador, modoDeJuego);
        controladorGrafica.getPantalla().actualizarLabelsPuntaje(puntajeJugador);
    }
    public void setReinicio(boolean t){
        reiniciarJugador=t;
    }
    public GUI getControlador(){
        return controladorGrafica;
    }
    public Nivel getNivelActual(){
        return nivel;
    }
    public int getTiempoActual(){
        return tiempo.getTiempoActual();
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public Ranking getRanking(){
        return ranking;
    }
    public Jugador getJugador(){
        return jugador;
    }
    public void setNombreJugador(String nombre){
        nombreJugador=nombre;
    }
    public void setDominio(String dominio){
        this.dominio=dominio;
    }
    public void setNivel(Nivel nivel){
        this.nivel=nivel;
    }
    public FabricaEntidades getFabricaEntidades(){
        return fabricaEntidades;
    }
    public void registrarObservers(){
        registrarObserverJugador();
        registrarObserversEntidades(nivel.getEnemigos());
        registrarObserversPlataformas(nivel.getPlataformasMovil());
    }
    public void registrarObserverJugador(){
    	controladorGrafica.registrarEntidad(jugador);
	}	
    public void registrarObserverEntidad(EntidadDinamica entidad){
        controladorGrafica.registrarEntidad(entidad);//observerEntidad?
    }
	protected void registrarObserversEntidades(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
            EntidadDinamica e=(EntidadDinamica) entidad;
			controladorGrafica.registrarEntidad(e);//observerEntidad?
		}
    }
    protected void registrarObserversPlataformas(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
            PlataformaMovil p=(PlataformaMovil) entidad;
			ObserverPlataformaMovil observer = controladorGrafica.registrarEntidad(p);//observerEntidad?
			p.registrarObserver(observer);
		}
    }
    public void crearPowerUp(PowerUp p){
            p.setLabel(controladorGrafica.getPantalla().incorporarEntidadEstatica(p));
            nivel.agregarPowerUp(p);   
            p.getTiempoDeVida().iniciar(); 
    }
    public void actualizarVidas(){
        controladorGrafica.getPantalla().actualizarLabelsVidas(jugador);
    }
    public void efectoVidaExtra(){
        controladorGrafica.getPantalla().efectoVidaExtra(jugador);
    }
    public void eliminarPowerUp(PowerUp p){
        controladorGrafica.getPantalla().eliminarLabel(p.getLabel());   
        if(nivel!=null) 
            nivel.eliminarPowerUp(p);
    }
    public void eliminarEntidadGraficamente(EntidadDinamica d){
        controladorGrafica.getPantalla().eliminarLabel((JLabel) d.getObserver());
    }
    public void eliminarPlataformaQuebradiza(PlataformaQuebradiza p){
        nivel.eliminarPlataforma(p);
        controladorGrafica.getPantalla().eliminarLabel(p.getMiLabel());
    }
    public String getModoDeJuego(){
        return modoDeJuego;
    } 
    public void setModoDeJuego(String modo){
        this.modoDeJuego=modo;
    }  
    private void crearCalabaza(){
        Calabaza cala=fabricaEntidades.getCalabaza(ConstantesEntidades.X_CALABAZA, ConstantesEntidades.Y_CALABAZA);
        controladorGrafica.registrarEntidad(cala);
        if(nivel!=null){
            nivel.agregarCalabaza(cala);
            cala.setNivel(nivel);
        }
    }
    public void crearKamakichi(){
        Temporizador timerDeAparicion= new Temporizador(4);
        timerDeAparicion.setAccionAlFinalizar(()-> {
            Sonido.getInstancia().reproducirMusica("temaMusicalKamakichi.wav");
            Kamakichi kamakichi=fabricaEntidades.getKamakichi(ConstantesEntidades.X_KAMAKICHI,ConstantesEntidades.Y_KAMAKICHI);
            controladorGrafica.registrarEntidad(kamakichi);
            kamakichi.setActivo(true);
            nivel.agregarEnemigo(kamakichi);
            habilitarCondicionClasico=true;
        });
        Sonido.getInstancia().frenarMusica();
        Sonido.getInstancia().reproducirEfectoDeSonido("risaKamakichi.wav");
        timerDeAparicion.iniciar();
    }
    public void crearMoghera(){
        Temporizador timerDeAparicion= new Temporizador(4);
        timerDeAparicion.setAccionAlFinalizar(()-> {
            Sonido.getInstancia().reproducirMusica("temaMusicalMoghera.wav");
            Moghera moghera=fabricaEntidades.getMoghera(ConstantesEntidades.X_MOGHERA,ConstantesEntidades.Y_MOGHERA);
            controladorGrafica.registrarEntidad(moghera);
            moghera.setActivo(true);
            nivel.agregarEnemigo(moghera);
            habilitarCondicionClasico=true;
        });
        Sonido.getInstancia().frenarMusica();
        Sonido.getInstancia().reproducirEfectoDeSonido("risaMoghera.wav");
        timerDeAparicion.iniciar();
    }
    public void crearBolaDeNieve(int posX, int posY, int puntajeEmpujado){
        BolaDeNieve bola=fabricaEntidades.getBolaDeNieve(posX, posY);
        bola.setPuntajeEmpujado(puntajeEmpujado);
        nivel.agregarBolaDeNieve(bola);
        controladorGrafica.registrarEntidad(bola);
    }
    public BolaDeFuego crearBolaDeFuego(int posX, int posY, int direccion) {
        BolaDeFuego bola=fabricaEntidades.getBolaDeFuego(posX, posY);
        bola.setDireccion(direccion);
        nivel.agregarBolaDeFuego(bola);
        controladorGrafica.registrarEntidad(bola);
        return bola;
    }
    public void crearFantasma(int posX, int posY){
        Fantasma fantasma= fabricaEntidades.getFantasma(posX, posY);
        nivel.agregarFantasma(fantasma);
        controladorGrafica.registrarEntidad(fantasma);
    }
    public void shootJugador() {
        long tiempoHilo=System.currentTimeMillis();
        if(tiempoHilo-ultimoDisparo>=ConstantesTiempos.COOLDOWN_DISPARO){
            DisparoJugador disparo = jugador.disparar();
            controladorGrafica.getPantalla().incorporarEntidad(disparo);
            nivel.agregarDisparo(disparo);
            ultimoDisparo=tiempoHilo;
        }
    }
    public void empujarBolaDeNieve(){
        if(jugador.getBolaEmpujada()!=null && !jugador.getBolaEmpujada().getEstado()){
            int puntajeASumar = jugador.getBolaEmpujada().disparar();
            actualizarPuntaje(puntajeASumar);
        }
    }
    public void invencibilidad(){
        Thread hiloInv = new Thread(() -> {
            long tiempoUltimoParpadeo = 0;
            while(jugador!=null && jugador.getInvencibilidad()){
                long tiempoHilo = System.currentTimeMillis();
                if(tiempoHilo - tiempoUltimoParpadeo > ConstantesTiempos.TIEMPO_LAPSO_INVENCIBILIDAD){
                    controladorGrafica.setJugadorInvisibleGraficamente();
                    tiempoUltimoParpadeo=tiempoHilo;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }       
                controladorGrafica.setJugadorVisibleGraficamente();
            }
        });
        hiloInv.start();
    }
    public boolean estaCerca(Entidad entidadPrincipal, Entidad entidadEvaluada, double umbralDeRango){
        Rectangle hitboxEntidadPrincipal = entidadPrincipal.getHitbox().getRectangle();
        Rectangle hitboxEntidadEvaluada = entidadEvaluada.getHitbox().getRectangle();
        double distanciaEnX = hitboxEntidadPrincipal.getCenterX() - hitboxEntidadEvaluada.getCenterX();
        double distanciaEnY = hitboxEntidadPrincipal.getCenterY() - hitboxEntidadEvaluada.getCenterY();
        double distancia = (distanciaEnX*distanciaEnX) + (distanciaEnY*distanciaEnY);
        return distancia <= umbralDeRango*umbralDeRango;
    }
}