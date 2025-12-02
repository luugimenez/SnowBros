package SnowBros.src.Paneles;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Grafica.GUI;
import SnowBros.src.Juego.Ranking;

public class PanelPantallaRanking extends JPanel {
    protected JLabel imagenFondo;
    protected JButton botonSalir;
    protected GUI miControladorGrafico;
    public PanelPantallaRanking(GUI miControladorGrafico){
        this.miControladorGrafico=miControladorGrafico;
        setSize(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        setLayout(null);
        agregarBotonSalir();
        agregarRankings();
        agregarImagenFondo();
    }
    public void agregarImagenFondo(){
        imagenFondo = new JLabel();
        ImageIcon iconoImagen=new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Pantallas/PantallaNegra.jpg"));
        Image imagenEscalada=iconoImagen.getImage().getScaledInstance(ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO,Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada=new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0,ConstantesVistas.VENTANA_ANCHO,ConstantesVistas.VENTANA_ALTO);
        add(imagenFondo);    
    }
    public void agregarBotonSalir(){
        botonSalir=new JButton();
		ImageIcon iconoBoton=new ImageIcon(this.getClass().getResource("/SnowBros/src/Imagenes/Botones/botonSalir.jpg"));
        botonSalir.setIcon(iconoBoton);
        botonSalir.setBounds(13,350,iconoBoton.getIconWidth(),iconoBoton.getIconHeight());
        transparentarBoton(botonSalir);
        registrarOyenteBotonSalir();
        add(botonSalir);    
    }
    public void registrarOyenteBotonSalir(){
        botonSalir.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            miControladorGrafico.cambiarPantallaPrincipal();
        }
        });
    }
    private void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
    }
    public void agregarRankings(){
        int columnaClasico=200;
        int columnaSupervivencia=400;
        int columnaContrarreloj=600;
        int tituloY=150;
        int filaInicialY=180;
        int separacionFilas=30;
        agregarColumnaRanking("Clásico",miControladorGrafico.getJuego().getRanking().getTopClasico(),columnaClasico,tituloY,filaInicialY,separacionFilas);
        agregarColumnaRanking("Supervivencia",miControladorGrafico.getJuego().getRanking().getTopSupervivencia(),columnaSupervivencia,tituloY,filaInicialY,separacionFilas);
        agregarColumnaRanking("Contrarreloj",miControladorGrafico.getJuego().getRanking().getTopContrarreloj(),columnaContrarreloj,tituloY,filaInicialY,separacionFilas);
    }
    private void agregarColumnaRanking(String titulo, LinkedHashMap<String,Integer> mapeo, int x, int tituloY, int filaInicialY, int separacion) {
        JLabel labelTitulo=new JLabel(titulo);
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        labelTitulo.setForeground(java.awt.Color.YELLOW);
        labelTitulo.setBounds(x,tituloY,200,30);
        labelTitulo.setOpaque(false);
        add(labelTitulo);
        int filaY=filaInicialY;
        int posicion=1;
        for (Map.Entry<String, Integer> entry:mapeo.entrySet()){
            String nombre=entry.getKey();
            int puntaje=entry.getValue();
            JLabel labelJugador=new JLabel(posicion+")"+nombre+".."+puntaje);
            labelJugador.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,18));
            labelJugador.setForeground(java.awt.Color.YELLOW);
            labelJugador.setBounds(x,filaY,200,25);
            labelJugador.setOpaque(false);
            add(labelJugador);
            filaY+=separacion;
            posicion++;
        }
    }
    public void actualizarRanking(Ranking ranking) {
        removeAll();
        agregarBotonSalir();
        agregarRankings();
        agregarImagenFondo();
        revalidate();
        repaint();
    }
}
