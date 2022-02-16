package superMario.figures.brick;

import javafx.scene.image.Image;

public class GroundBrick extends Brick{

    public GroundBrick(double x, double y, Image style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
    }

}
