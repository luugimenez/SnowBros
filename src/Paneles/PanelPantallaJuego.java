package SnowBros.src.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Grafica.*;
import SnowBros.src.Juego.Nivel;

public class PanelPantallaJuego extends JPanel{
    protected JPanel panelPantallaJuego;
    protected JPanel panelInformacion;
    protected JLabel imagenFondoJuego;
    protected JLabel labelNivel;
    protected JLabel labelPuntaje;
    protected JLabel labelTiempo;
    protected JPanel panelVidas;
    protected GUI miControladorGrafico;

    public PanelPantallaJuego(GUI miControladorGrafico){
         this.miControladorGrafico=miControladorGrafico;
         setPreferredSize(new Dimension(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO));
         setLayout(null);//         setLayout(new BorderLayout());
         agregarPanelInformacion();
         agregarPanelJuego();
    }
    protected void agregarPanelInformacion() {
        panelInformacion= new JPanel();
        panelInformacion.setOpaque(true); //true lo deja en blanco
        panelInformacion.setLayout(new GridLayout(1, 4, 20,0)); 
		panelInformacion.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, 42);//defino manualmente el tamaño y pos del panel info
        Font fuente = new Font("Arial", Font.BOLD, 16);
        String stringModo=miControladorGrafico.getJuego().getModoDeJuego();
        labelNivel= new JLabel(stringModo);
        labelNivel.setForeground(Color.BLACK);
        labelNivel.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto horizontalmente dentro del JLabel
        labelNivel.setFont(fuente);
        String puntaje="puntaje";
        labelPuntaje= new JLabel(puntaje);
        labelPuntaje.setForeground(Color.GRAY);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelPuntaje.setFont(fuente);
        labelTiempo = new JLabel();
        labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTiempo.setFont(fuente);
        labelTiempo.setForeground(Color.BLACK);
        Timer tiempoActualizador;
        switch (miControladorGrafico.getJuego().getModoDeJuego()) {
            case "Contrarreloj":
                tiempoActualizador = new Timer(0, e -> {
                    actualizarLabelTiempoContrarreloj();
                });
                tiempoActualizador.start();
                break;
            default://si es clasico o supervivencia mismo reloj en pantalla
                tiempoActualizador = new Timer(0, e -> {
                    actualizarLabelTiempo();
                });
                tiempoActualizador.start();
                break;
            }
        panelVidas= new JPanel();
        panelVidas.setOpaque(false);
        panelVidas.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));// espacio de 5 pixeles horiz y vert entre cada componente
        ImageIcon iconoCorazon= new ImageIcon("SnowBros/src/Imagenes/Original/PowerUps/vidaJugador.png");
        for(int i=0;i<ConstantesDificultad.VIDAS_INICIO;i++){
            JLabel vidaLabel=new JLabel(iconoCorazon);
            panelVidas.add(vidaLabel);
        }
        panelInformacion.add(labelNivel);
        panelInformacion.add(labelPuntaje);
        panelInformacion.add(labelTiempo);
        panelInformacion.add(panelVidas);
        this.add(panelInformacion,BorderLayout.NORTH);
    }
    protected void agregarPanelJuego() {
        imagenFondoJuego= new JLabel();
        imagenFondoJuego.setLayout(null); //null para posicionar sprites manualmente
        imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);//Panel del Juego donde se van a agregar los objetos
        panelPantallaJuego= new JPanel();
        panelPantallaJuego.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO));
        panelPantallaJuego.add(imagenFondoJuego);
        this.add(panelPantallaJuego,BorderLayout.CENTER);
        panelPantallaJuego.setOpaque(false);
    }
    public void mostrarPlataformas() {
        List<Plataforma> plataformas = miControladorGrafico.getJuego().getNivelActual().getPlataformas();
        mostrarEntidades(plataformas, true);
    }
    public void mostrarObstaculos() {
        Nivel nivel = miControladorGrafico.getJuego().getNivelActual();
        mostrarEntidades(nivel.getObstaculos(), true);
        mostrarEntidades(nivel.getResbaladizos(), false);
    }
    public <T extends EntidadEstatica> void mostrarEntidades(List<T> entidades, boolean ajustarZOrder) {
        for (T entidadEstatica : entidades) {
            JLabel label = crearLabelDesdeEntidad(entidadEstatica);
            this.add(label);
            if (ajustarZOrder) this.setComponentZOrder(label, 0);
            entidadEstatica.setMiLabel(label);
        }
        refrescar();
    }
    private JLabel crearLabelDesdeEntidad(Entidad entidad) {
        String ruta = entidad.getSprite().getRutaImagen("normal");
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));
        JLabel label = new JLabel(icono);
        label.setBounds(entidad.getPosX(),entidad.getPosY(),entidad.getHitbox().getAncho(),entidad.getHitbox().getAlto());
        return label;
    }
    public void incorporarEntidad(EntidadDinamica entidad) {
        imagenFondoJuego.add((JLabel) entidad.getObserver());
    }
    public ObserverPlataformaMovil incorporarEntidad(PlataformaMovil entidad) {
        PlataformaMovil aux= entidad;
        imagenFondoJuego.add((JLabel)aux.getObserver());
        return aux.getObserver();
    }
    public JLabel incorporarEntidadEstatica(EntidadEstatica e) {
        String ruta = e.getSprite().getRutaImagen("normal");
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta));//plataforma.getSprite().getRutaImagen("estatico.png"
        JLabel estatica = new JLabel(icono);
        estatica.setBounds(e.getPosX(), e.getPosY(), e.getHitbox().getAncho(), e.getHitbox().getAlto());
        this.add(estatica);
        this.setComponentZOrder(estatica, 0);
        refrescar();
        return estatica;
    }
    public void incorporarEntidadJugador(Jugador jugador) {
        //ObserverJugador observerJugador= new ObserverJugador((jugador));
        imagenFondoJuego.add((JLabel)jugador.getObserver());
    }
    public void incorporarSilueta(Entidad silueta) {
        ImageIcon imagenOriginal= new ImageIcon( getClass().getClassLoader().getResource(silueta.getSprite().getRutaImagen("normal")));
        Image imagenRedimensionada= imagenOriginal.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        imagenFondoJuego.setIcon(new ImageIcon(imagenRedimensionada));
        imagenFondoJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        this.add(imagenFondoJuego);
    }
    public void actualizarLabelsVidas(Jugador jugador) {
        int indice = jugador.getVidas();
        if (indice >= 0 && indice < panelVidas.getComponentCount())
            panelVidas.remove(indice);
        ImageIcon iconoVida = new ImageIcon("SnowBros/src/Imagenes/Original/PowerUps/vidaPerdida.png");
        JLabel label= new JLabel(iconoVida);
        panelVidas.add(label, jugador.getVidas());
        refrescar();
    }   
    public void actualizarLabelTiempo() {
        String tiempoString = miControladorGrafico.getJuego().getTimer().getStringDeTiempo();
        labelTiempo.setText(tiempoString);
    }
    public void actualizarLabelTiempoContrarreloj() {
        String tiempoString = miControladorGrafico.getJuego().getTimer().getStringTiempoContrarreloj();
        labelTiempo.setText(tiempoString);
    }
    public void efectoVidaExtra(Jugador jugador){
        int indice = jugador.getVidas() - 1;
        if (indice >= 0 && indice < panelVidas.getComponentCount()) 
            panelVidas.remove(indice);
        ImageIcon iconoVidaRoja = new ImageIcon("SnowBros/src/Imagenes/Original/PowerUps/vidaJugador.png");
        JLabel labelVida = new JLabel(iconoVidaRoja);
        panelVidas.add(labelVida, indice);
        refrescar();     
    }
    public void actualizarLabelsPuntaje(int puntajeASumar) {
        Component componenteActual = panelInformacion.getComponent(1);
        panelInformacion.remove(componenteActual);
        Font fuente = new Font("Arial", Font.BOLD, 16);
        String puntaje=String.valueOf(puntajeASumar);
        labelPuntaje= new JLabel(puntaje);
        labelPuntaje.setFont(fuente);
        labelPuntaje.setForeground(Color.BLACK);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelInformacion.add(labelPuntaje,1);
        refrescar();
    }
    public void eliminarLabel(JLabel l){
        if(l!=null){
            remove(l);
            imagenFondoJuego.remove(l);
            refrescar();
        }
    }
    private void refrescar(){
        this.revalidate();
        this.repaint();
    }
}
