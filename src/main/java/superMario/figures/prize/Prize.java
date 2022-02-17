package superMario.figures.prize;

import superMario.manager.GameEngine;

import superMario.figures.hero.Mario;
import javafx.geometry.Rectangle2D;

public interface Prize {

    int getPoint();

    void reveal();

    Rectangle2D getBounds();

    void onTouch(Mario mario, GameEngine engine);

}
