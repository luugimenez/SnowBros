package SnowBros.src.Grafica;

import java.awt.Container;
import java.net.URL;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

public class ObserverEntidad extends JLabel implements Observer{
    protected EntidadDinamica entidadObservada;
	protected PlataformaMovil plataformaObservada;
	private JLabel imagen;
    public ObserverEntidad(EntidadDinamica entidad) {
        entidadObservada=entidad;
    }
    public ObserverEntidad(PlataformaMovil plataformaMovil){
        plataformaObservada=plataformaMovil;
    }
    public void actualizar() {
        if(entidadObservada!=null){
            actualizarImagen(); 
            actualizarPosicionTamano();
        }
    }
    protected void actualizarImagen() {   
        if(entidadObservada!=null){
            String estado= entidadObservada.generarClaveEstado();
            String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
            if (rutaImagen == null || rutaImagen.isBlank()){
                return;
            }
            URL url= getClass().getClassLoader().getResource(rutaImagen);
            if (url == null) {
                System.err.println("No se encontró recurso: " + rutaImagen);
                return;
            }
            ImageIcon icono = new ImageIcon(url);
            setIcon(icono);
            imagen=this;
        }   
    }
    protected void actualizarPosicionTamano(){
		int margenVisual=3;
        int x;
        int y;
        try{
            x = entidadObservada.getPosX();
            y = entidadObservada.getPosY();
            int ancho = this.getIcon().getIconWidth();
            int alto = this.getIcon().getIconHeight();
            setBounds(x, y+margenVisual, ancho, alto);
        } catch(NullPointerException e){
            return;
        }
    }
    protected void actualizarPosicionTamanoPlataforma(){
		int margenVisual=2;
        int x = plataformaObservada.getPosX();
		int y = plataformaObservada.getPosY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y+margenVisual, ancho, alto);
    }
    public void actualizarBolaDeNieve(boolean activo) {	
		SwingUtilities.invokeLater(() -> {
            try{
                String estado=null;
                estado="congelado";
                String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
                if (rutaImagen == null || rutaImagen.isBlank()) {
                    return;
                }
                URL url= getClass().getClassLoader().getResource(rutaImagen);
                if (url == null) {
                    System.err.println("No se encontró recurso: " + rutaImagen);
                    return;
                }
                ImageIcon icono = new ImageIcon(url);
                setIcon(icono);
            }catch(NullPointerException ex){
                return;
            }
	    });
	}
	public void borrarObserver() {
        entidadObservada = null;
        SwingUtilities.invokeLater(() -> {
            Container padre = getParent();
            if (padre != null) {
                padre.remove(this);
                padre.revalidate();
                padre.repaint();
            }
        });
    }
	public JLabel getImagen(){
		return imagen;
	}
}
