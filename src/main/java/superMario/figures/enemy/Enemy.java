package superMario.figures.enemy;

import superMario.figures.Afigures;

import javafx.scene.image.Image;


public abstract class Enemy extends Afigures{

    public Enemy(double x, double y, Image style) {
        super(x, y, style);
        setFalling(false);
        setJumping(false);
    }
}
