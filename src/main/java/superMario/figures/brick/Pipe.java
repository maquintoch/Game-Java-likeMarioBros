package superMario.figures.brick;

import javafx.scene.image.Image;

public class Pipe extends Brick{

    public Pipe(double x, double y, Image style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
        setDimension(96, 96);
    }
}
