package superMario.figures.prize;

import superMario.manager.GameEngine;
import superMario.figures.hero.Mario;

import javafx.scene.shape.Rectangle;

public interface Prize {

    int getPoint();

    void reveal();

    Rectangle getBounds();

    void onTouch(Mario mario, GameEngine engine);

}
