package SnowBros.src.Paneles;
import java.awt.Color;
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

public class PanelPantallaPrincipal extends JPanel{
    public static final long serialVersionUID=1L;//ayuda a evitar warnings
    protected GUI miControladorGrafico;
    protected JLabel imagenFondo;
    protected JButton botonIniciar;
    protected JButton botonRanking;
    public PanelPantallaPrincipal(GUI controlador){
        this.miControladorGrafico=controlador;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        setBackground(Color.BLACK);
        setOpaque(true);
        agregarBotonIniciar();
        agregarBotonRanking();
        agregarImagenFondo();
    }
    protected void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Pantallas/pantallaPrincipal.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(iconoImagen.getIconWidth(),ConstantesVistas.VENTANA_ALTO-25,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(9,-14,ConstantesVistas.VENTANA_ANCHO+10,ConstantesVistas.VENTANA_ALTO-10);
        add(imagenFondo);
        imagenFondo.setOpaque(false);
    }
    protected void agregarBotonIniciar(){
        botonIniciar = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonJugar.jpg"));
        botonIniciar.setIcon(iconoBoton);
        botonIniciar.setBounds(15,45,iconoBoton.getIconWidth(),iconoBoton.getIconHeight());
        transparentarBoton(botonIniciar);
        registrarOyenteBotonIniciar();
        add(botonIniciar);
    }
    private void agregarBotonRanking(){
        botonRanking= new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonRanking.jpg"));
        botonRanking.setIcon(iconoBoton);
        botonRanking.setBounds(530,45,iconoBoton.getIconWidth(),iconoBoton.getIconHeight());
        transparentarBoton(botonRanking);
        registrarOyenteBotonRanking();
        add(botonRanking);
    }
    private void registrarOyenteBotonIniciar(){
        botonIniciar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                miControladorGrafico.cambiarPantallaDominio();
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
