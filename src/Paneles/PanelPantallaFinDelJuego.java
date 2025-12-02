package SnowBros.src.Paneles;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Grafica.GUI;

public class PanelPantallaFinDelJuego extends JPanel {
    protected JLabel imagenFondo;
    protected JButton botonSalir;
    protected JButton botonNuevaPartida;
    protected JButton botonRanking;
    protected GUI miControladorGrafico;
    public PanelPantallaFinDelJuego(GUI miControladorGrafico){
        this.miControladorGrafico=miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarBotonSalir();
        agregarBotonNuevaPartida();
        agregarBotonRanking();
        agregarImagenFondo();
    }
    public void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Pantallas/pantallaFinDelJuego.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo,-1);
    }
    public void agregarBotonSalir(){
        botonSalir = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonSalir.jpg"));
        botonSalir.setIcon(iconoBoton);
        botonSalir.setBounds(50, 100, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonSalir);
        registrarOyenteBotonSalir();
        add(botonSalir,0);
    }
    private void agregarBotonRanking(){
        botonRanking= new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonRanking.jpg"));
        botonRanking.setIcon(iconoBoton);
        botonRanking.setBounds(530,405,iconoBoton.getIconWidth(),iconoBoton.getIconHeight());
        transparentarBoton(botonRanking);
        registrarOyenteBotonRanking();
        add(botonRanking);
    }
    public void agregarBotonNuevaPartida(){
        botonNuevaPartida = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonReinicio.jpg"));
        botonNuevaPartida.setIcon(iconoBoton);
        botonNuevaPartida.setBounds(50, 500, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        botonNuevaPartida.setBorderPainted(false);
        botonNuevaPartida.setContentAreaFilled(false);
        botonNuevaPartida.setFocusPainted(false);
        botonNuevaPartida.setOpaque(false);
        transparentarBoton(botonSalir);
        registrarOyenteBotonNuevaPartida();
        add(botonNuevaPartida,0);
    }
    public void registrarOyenteBotonSalir(){
        botonSalir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                miControladorGrafico.cambiarPantallaPrincipal();
            }
        });
    }
    private void registrarOyenteBotonNuevaPartida(){
        botonNuevaPartida.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                miControladorGrafico.cambiarInicioJuego();
            }
        });
    }    
    private void registrarOyenteBotonRanking(){
        botonRanking.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                miControladorGrafico.cambiarPantallaRanking();
            }
        });
    }
    private void transparentarBoton(JButton boton){
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
}