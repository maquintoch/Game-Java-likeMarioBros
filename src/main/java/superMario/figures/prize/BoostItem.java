package superMario.figures.prize;

import superMario.manager.GameEngine;
import superMario.figures.Afigures;
import superMario.figures.hero.Mario;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class BoostItem extends Afigures implements Prize{

    private boolean revealed = false;
    private int point;

    public BoostItem(double x, double y, Image style) {
        super(x, y, style);
        setDimension(48, 48);
    }

    public abstract void onTouch(Mario mario, GameEngine engine);

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public void updateLocation(){
        if(revealed){
            super.updateLocation();
        }
    }

    @Override
    public void draw(GraphicsContext g){
        if(revealed){
            g.drawImage(getStyle(), (int)getX(), (int)getY());
        }
    }

    @Override
    public void reveal(){
        setY(getY()-48);
        revealed = true;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
