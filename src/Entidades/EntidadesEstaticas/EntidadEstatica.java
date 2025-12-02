package SnowBros.src.Entidades.EntidadesEstaticas;

import java.awt.Container;
import javax.swing.JLabel;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitable;

public abstract class EntidadEstatica extends Entidad implements Visitable {
    protected JLabel miLabel;
    public EntidadEstatica(int i, int j, Sprite sprite) {
        super(i,j,sprite);
    }
    public String generarClaveEstado(){
        return "normal";
    }
    public void setMiLabel(JLabel j){
        miLabel=j;
    }
    public JLabel getMiLabel(){
        return miLabel;
    }
    public void borrarLabel(){
        Container padre;
        try{
            padre= miLabel.getParent();
            miLabel.getParent().remove(miLabel);
            padre.revalidate();
            padre.repaint();
        } catch (NullPointerException e){
            return;
        }
        
    }
}
