package SnowBros.src.Paneles;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Grafica.GUI;

public class PanelPantallaTransicion extends JPanel {
    protected JLabel imagenFondo;
    protected JLabel textoNivel;
    protected GUI miControladorGrafico;
    public PanelPantallaTransicion(GUI miControladorGrafico) {
        this.miControladorGrafico = miControladorGrafico;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarTextoNivel();
    }
    public void mostrarNivel() {
        refrescar();
        iniciarTemporizadorTransicion();
        miControladorGrafico.cambiarInicioJuego();
    }
    private void agregarImagenFondo() {
        imagenFondo = new JLabel();
        ImageIcon icono = new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Pantallas/PantallaNextLevel.png"));
        Image imagenEscalada = icono.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO, Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);
    }
    private void agregarTextoNivel() {
        textoNivel = new JLabel("", JLabel.CENTER);
        textoNivel.setFont(new Font("Arial", Font.BOLD, 60));
        textoNivel.setForeground(Color.WHITE);
        textoNivel.setBounds(0, ConstantesVistas.VENTANA_ALTO / 2 - 50, ConstantesVistas.VENTANA_ANCHO, 100);
        textoNivel.setOpaque(false);
        add(textoNivel, 0);
    }
    private void iniciarTemporizadorTransicion() {
        int duracion = 2500;
        Timer timer = new Timer(duracion, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                miControladorGrafico.cambiarInicioJuego(); 
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    private void refrescar() {
        revalidate();
        repaint();
    }
}
