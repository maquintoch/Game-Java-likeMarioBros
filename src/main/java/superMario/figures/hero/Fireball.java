package superMario.figures.hero;

import superMario.figures.Afigures;
import javafx.scene.image.Image;

public class Fireball extends Afigures {

    public Fireball(double x, double y, Image style, boolean toRight) {
        super(x, y, style);
        setDimension(24, 24);
        setFalling(false);
        setJumping(false);
        setVelX(10);

        if(!toRight)
            setVelX(-5);
    }
}
