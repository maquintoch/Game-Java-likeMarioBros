package superMario.figures.prize;

import superMario.manager.GameEngine;
import superMario.figures.hero.Mario;

import javafx.scene.image.Image;

public class OneUpMushroom extends BoostItem{

    public OneUpMushroom(double x, double y, Image style) {
        super(x, y, style);
        setPoint(200);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());
        mario.setRemainingLives(mario.getRemainingLives() + 1);
        engine.playOneUp();
    }
}
