package superMario.figures;

import javafx.scene.image.Image;

public class EndFlag extends Afigures{

    private boolean touched = false;

    public EndFlag(double x, double y, Image style) {
        super(x, y, style);
    }

    @Override
    public void updateLocation() {
        if(touched){
            if(getY() + getDimension().getHeight() >= 576){
                setFalling(false);
                setVelY(0);
                setY(576 - getDimension().getHeight());
            }
            super.updateLocation();
        }
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }
}
