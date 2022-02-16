package superMario.figures.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class KoopaTroopa extends Enemy{

    private Image rightImage;

    public KoopaTroopa(double x, double y, Image style) {
        super(x, y, style);
        setVelX(3);
    }

    @Override
    public void draw(GraphicsContext g){
        if(getVelX() > 0){
            g.drawImage(rightImage, (int)getX(), (int)getY());
        }
        else
            super.draw(g);
    }

    public void setRightImage(Image rightImage) {
        this.rightImage = rightImage;
    }
}
