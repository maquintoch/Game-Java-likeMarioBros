package superMario.figures.brick;

import superMario.manager.GameEngine;
import superMario.figures.Afigures;
import superMario.figures.prize.Prize;

import javafx.scene.image.Image;

public abstract class Brick extends Afigures{

    private boolean breakable;

    private boolean empty;

    public Brick(double x, double y, Image style){
        super(x, y, style);
        setDimension(48, 48);
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Prize reveal(GameEngine engine){ return null;}

    public Prize getPrize() {
        return null;
    }
}
