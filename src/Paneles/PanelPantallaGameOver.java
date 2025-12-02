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

public class PanelPantallaGameOver extends JPanel {
    protected JLabel imagenFondo;
    protected JButton botonSalir;
    protected JButton botonRanking;
    protected GUI miControladorGrafico;
    public PanelPantallaGameOver(GUI miControladorGrafico) {
        this.miControladorGrafico = miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarBotonSalir();
        agregarBotonRanking();
        agregarImagenFondo();
    }
    public void agregarImagenFondo() {
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Pantallas/PantallaGameOver.png"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0, 0, ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo, -1);
    }
    private void agregarBotonSalir() {
        botonSalir = new JButton();
        ImageIcon iconoBoton = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonSalir.jpg"));
        botonSalir.setIcon(iconoBoton);
        botonSalir.setBounds(150, 410, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonSalir);
        registrarOyenteBotonSalir();
        add(botonSalir, 0);
    }
    private void agregarBotonRanking() {
        botonRanking = new JButton();
        ImageIcon iconoBoton = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonRanking.jpg"));
        botonRanking.setIcon(iconoBoton);
        botonRanking.setBounds(430, 400, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonRanking);
        registrarOyenteBotonRanking();
        add(botonRanking, 0);
    }
    private void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
    }
    private void registrarOyenteBotonSalir() {
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miControladorGrafico.cambiarPantallaPrincipal();
            }
        });
    }
    private void registrarOyenteBotonRanking() {
        botonRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miControladorGrafico.cambiarPantallaRanking();
            }
        });
    }
}