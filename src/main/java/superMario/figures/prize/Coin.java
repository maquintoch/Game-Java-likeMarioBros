package superMario.figures.prize;

import superMario.manager.GameEngine;
import superMario.figures.Afigures;
import superMario.figures.hero.Mario;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Coin extends Afigures implements Prize{

    private int point;
    private boolean revealed, acquired = false;
    private double revealBoundary;

    public Coin(double x, double y, Image style, int point){
        super(x, y, style);
        this.point = point;
        revealed = false;
        setDimension(30, 42);
        revealBoundary = getY() - getDimension().getHeight();
    }

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public void reveal() {
        revealed = true;
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        if(!acquired){
            acquired = true;
            mario.acquirePoints(point);
            mario.acquireCoin();
            engine.playCoin();
        }
    }

    @Override
    public void updateLocation(){
        if(revealed){
            setY(getY() - 5);
        }
    }

    @Override
    public void draw(GraphicsContext g){
        if(revealed){
            g.drawImage(getStyle(), (int)getX(), (int)getY());
        }
    }

    public double getRevealBoundary() {
        return revealBoundary;
    }
}
