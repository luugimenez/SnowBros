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

public class PanelPantallaDominio extends JPanel {
    protected JLabel imagenFondo;
    protected JButton botonOriginal;
    protected JButton botonAlternativo;
    protected GUI miControladorGrafico;
    public PanelPantallaDominio(GUI miControladorGrafico){
        this.miControladorGrafico=miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO,ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonAlternativo();
        agregarBotonOriginal();
    }
    public void agregarImagenFondo(){ 
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Pantallas/PantallaInicio.jpg"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);
    }
    public void agregarBotonOriginal(){
        botonOriginal = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonOriginal.jpg"));
        botonOriginal.setIcon(iconoBoton);
        botonOriginal.setBounds(20, 150, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonOriginal);
        registrarOyenteBotonOriginal();
        add(botonOriginal,0);    
    }
    public void agregarBotonAlternativo(){
        botonAlternativo = new JButton();
		ImageIcon iconoBoton= new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonAlternativo.jpg"));
        botonAlternativo.setIcon(iconoBoton);
        botonAlternativo.setBounds(350, 150, iconoBoton.getIconWidth(), iconoBoton.getIconHeight());
        transparentarBoton(botonAlternativo);
        registrarOyenteBotonAlternativo();
        add(botonAlternativo,0);    
    }                
    public void registrarOyenteBotonOriginal(){        
        botonOriginal.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            miControladorGrafico.setDominio("Original");
            miControladorGrafico.cambiarPantallaModo();
        }
        });
    }
    public void registrarOyenteBotonAlternativo(){
        botonAlternativo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            miControladorGrafico.setDominio("Alternativo");
            miControladorGrafico.cambiarPantallaModo();
            }
        });
    }
    public void transparentarBoton(JButton boton){  
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);    
    }
}

