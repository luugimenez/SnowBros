package SnowBros.src.Juego;

import java.awt.Rectangle;
import SnowBros.src.Datos.ConstantesEntidades;

public class HitBox {
    private Rectangle hitbox;
    private int ancho;
    private int alto;
    public HitBox(int x, int y, int ancho, int alto){
        this.ancho=ancho;
        this.alto=alto;
        hitbox=new Rectangle(x-(ancho/2),y-(alto/2),ancho,alto);
    }
    public void actualizarHitbox(int x, int y, int ancho, int alto){
        this.alto=alto;
        this.ancho=ancho;
        hitbox.setBounds(x - (ancho/2), y - (alto/2), this.ancho, this.alto);
    }
    public Rectangle getRectangle(){
        return hitbox;
    }
    public Rectangle getTopHitBox(){
        return new Rectangle(hitbox.x, hitbox.y-ConstantesEntidades.MARGEN_HIT_BOX, hitbox.width, ConstantesEntidades.MARGEN_HIT_BOX);
    }
    public Rectangle getBottomHitBox(){
        return new Rectangle(hitbox.x, hitbox.y+hitbox.height, hitbox.width, ConstantesEntidades.MARGEN_HIT_BOX);
    }
    public Rectangle getLeftHitBox(){
        return new Rectangle(hitbox.x-ConstantesEntidades.MARGEN_HIT_BOX, hitbox.y+2, ConstantesEntidades.MARGEN_HIT_BOX, hitbox.height-15);
    }
    public Rectangle getRightHitBox(){
        return new Rectangle(hitbox.x+hitbox.width, hitbox.y+2, ConstantesEntidades.MARGEN_HIT_BOX, hitbox.height-15);
    }
    public int getX(){
        return hitbox.x;
    }
    public int getY(){
        return hitbox.y;
    }
    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
}