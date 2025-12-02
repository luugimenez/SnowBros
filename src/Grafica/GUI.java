package SnowBros.src.Grafica;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Paneles.*;

public class GUI implements ControladorGrafico, ControladorDeVista {
    protected JFrame ventana;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaDominio panelPantallaDominio;
    protected PanelPantallaModo panelPantallaModo;
    protected PanelPantallaGameOver panelPantallaGameOver;
    protected PanelPantallaTransicion panelPantallaTransicion;
    protected PanelPantallaJuego panelPantallaJuego;
    protected PanelPantallaFinDelJuego panelPantallaFinDelJuego;
    protected Juego miJuego;
    private boolean movDerecha=false;
    private boolean movIzquierda=false;
    private boolean disparando=false;
    private boolean empujando=false;
    private boolean juegoIniciado=false;
    protected String dominio;
    protected String modoDeJuego;

    public GUI(Juego juego){
        this.miJuego=juego;
        panelPantallaPrincipal = new PanelPantallaPrincipal(this);
        panelPantallaDominio= new PanelPantallaDominio(this);
        panelPantallaRanking = new PanelPantallaRanking(this);
        panelPantallaModo = new PanelPantallaModo(this);
        panelPantallaGameOver= new PanelPantallaGameOver(this);
        panelPantallaTransicion=new PanelPantallaTransicion(this);
        panelPantallaFinDelJuego= new PanelPantallaFinDelJuego(this);
        configurarVentana();
        registrarOyenteVentana();
    }
    private void configurarVentana() {
        ventana = new JFrame("✨❄️☃️SnowBros TDP Comision 14 ❄️💙✨");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setSize(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    protected void registrarOyenteVentana() {
        addWindowKeyListener();
    }
    private void addWindowKeyListener() {
        ventana.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if(!juegoIniciado) return;
                int keyCode=event.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_A:
                        movIzquierda=true;
                        break;
                    case KeyEvent.VK_D:
                        movDerecha=true;
                        break;
                    case KeyEvent.VK_W:
                        if(!miJuego.getJugador().getSaltando() && miJuego.getJugador().getTocandoSuelo()==ConstantesEntidades.SUELO){
                            miJuego.getJugador().mover(ConstantesEntidades.ARRIBA);
                            miJuego.getJugador().setSaltando(true);
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(miJuego.getJugador().estaHabilitadoASubirEscalera()){
                            miJuego.getJugador().mover(ConstantesEntidades.ABAJO);
                            miJuego.getJugador().getObserver().actualizar();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(miJuego.getJugador().getBolaEmpujada()!=null){
                            disparando=false;
                            empujando=true;
                        } else{
                            disparando=true;
                        }
                        break;
                }
            }
            public void keyReleased(KeyEvent event) {
                if (!juegoIniciado) return;
                int keyCode=event.getKeyCode();
                if (keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_D)
                    if(keyCode==KeyEvent.VK_A)
                        movIzquierda=false;
                    else
                        movDerecha=false;
                if(keyCode==KeyEvent.VK_SPACE){
                    disparando=false;
                    empujando=false;
                    miJuego.getJugador().setEmpujando(null);
                }
            }
        });
    }
    public void moverJugadorSegunInput(){
        if(movDerecha && !miJuego.getJugador().getChocoParedIzq())
           miJuego.getJugador().mover(ConstantesEntidades.DERECHA);
        else if(movIzquierda && !miJuego.getJugador().getChocoParedDer())
            miJuego.getJugador().mover(ConstantesEntidades.IZQUIERDA);
        else{
            miJuego.getJugador().mover(ConstantesEntidades.QUIETO);
            movDerecha=false;
            movIzquierda=false;
        }
        if(miJuego.getJugador().getSaltando())
           miJuego.getJugador().mover(ConstantesEntidades.ARRIBA);
        if(disparando)
            miJuego.shootJugador(); 
        if(empujando)
            miJuego.empujarBolaDeNieve();
    }
    private void cambiarPantalla(JPanel panel) {
        ventana.setContentPane(panel);
        ventana.requestFocusInWindow();
        refrescar();
    }
    public void cambiarPantallaTransicion(){
        juegoIniciado=false;
        cambiarPantalla(panelPantallaTransicion);
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
                public void run() {
                    cambiarInicioJuego();
                    cambiarPantalla(panelPantallaJuego);  
                }
            },3000//3s
        );

    }
    public void cambiarPantallaGameOver(){
        juegoIniciado=false;
        Sonido.getInstancia().frenarMusica();
        Sonido.getInstancia().reproducirEfectoDeSonido("gameOver.wav");
        cambiarPantalla(panelPantallaGameOver);
    }
    public void cambiarPantallaPrincipal() {
        Sonido.getInstancia().reproducirMusica("temaMusicalMenu.wav");
        cambiarPantalla(panelPantallaPrincipal);
    }
    public void cambiarPantallaFinDelJuego() {
        cambiarPantalla(panelPantallaFinDelJuego);
    }
    public void cambiarPantallaDominio() {
        cambiarPantalla(panelPantallaDominio);
    }
    public void cambiarPantallaModo() {
        cambiarPantalla(panelPantallaModo);
    }    
    public void cambiarPantallaRanking() {
        panelPantallaRanking.actualizarRanking(miJuego.getRanking());
        cambiarPantalla(panelPantallaRanking);
    }
    public void cambiarInicioJuego() {
        this.panelPantallaJuego=new PanelPantallaJuego(this);
        cambiarPantalla(panelPantallaJuego);
        miJuego.iniciar();
        panelPantallaJuego.mostrarPlataformas();
        panelPantallaJuego.mostrarObstaculos();
        panelPantallaJuego.incorporarSilueta(miJuego.getNivelActual().getSilueta());
        panelPantallaJuego.setComponentZOrder(miJuego.getJugador().getObserver().getImagen(),3);
        for(Enemigo enemigo:miJuego.getNivelActual().getEnemigos()){
            panelPantallaJuego.setComponentZOrder(enemigo.getObserver().getImagen(),3);
        }
        refrescar();
        juegoIniciado = true;
        movDerecha = false;
        movIzquierda = false;
        disparando = false;
        empujando = false;
    }
    public void setDominio(String dominio){
        this.dominio=dominio;
        miJuego.setDominio(dominio);
    }
    public void setModoJuego(String modo) {
        modoDeJuego=modo;
        miJuego.setModoDeJuego(modo);
        setNombre(modo);
    }
    public void setNombre(String modo){
        String nombre=null;
        while(nombre==null || nombre.trim().isEmpty()){
            nombre = javax.swing.JOptionPane.showInputDialog(ventana,"Ingresa tu nombre para el ranking:","Registro de Jugador",javax.swing.JOptionPane.PLAIN_MESSAGE);
            if (nombre == null) {
                int opcion = javax.swing.JOptionPane.showConfirmDialog(ventana,"❄️ ¿Seguro que deseas salir del juego?","Confirmar salida",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.QUESTION_MESSAGE);
                if (opcion == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else if (nombre.trim().isEmpty())
                    javax.swing.JOptionPane.showMessageDialog(ventana,"⚠️ Debes ingresar un nombre para jugar.","Nombre inválido",javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        miJuego.setNombreJugador(nombre);
        miJuego.getRanking().setJugador(nombre, 0, modo);
    }
    public void registrarEntidad(EntidadDinamica entidad){
        panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
    }
    public ObserverPlataformaMovil registrarEntidad(PlataformaMovil entidad){
        ObserverPlataformaMovil observerPlataforma = panelPantallaJuego.incorporarEntidad(entidad);
        refrescar();
        return observerPlataforma;
    }
    public void registrarEntidadEstatica(EntidadEstatica e){
        panelPantallaJuego.incorporarEntidadEstatica(e);
    }
    public void registrarEntidad(Jugador jugador) {
        panelPantallaJuego.incorporarEntidadJugador(jugador);
        refrescar();
    }
    public void refrescar() {
        ventana.revalidate();
        ventana.repaint();
    }
    public void setJugadorVisibleGraficamente(){
        if(miJuego.getJugador()!=null){
            miJuego.getJugador().getObserver().setVisible(true);
            panelPantallaJuego.repaint();
        }
    }
    public void setJugadorInvisibleGraficamente(){
        if(miJuego.getJugador()!=null){
            miJuego.getJugador().getObserver().setVisible(false);
            panelPantallaJuego.repaint();
        }
    }
    public Juego getJuego() {
        return miJuego;
    }
    public void setMoverIzq(boolean b){
        movIzquierda=b;
    }
    public void setMoverDer(boolean b){
        movDerecha=b;
    }
    public void setEmpujando(boolean b){
        empujando=b;
    }
    public PanelPantallaJuego getPantalla(){
        return panelPantallaJuego;
    }
}