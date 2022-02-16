package superMario.figures.prize;

import superMario.manager.GameEngine;
import superMario.figures.hero.Mario;
import superMario.figures.hero.MarioForm;
import superMario.view.Animation;
import superMario.view.ImageLoader;

import javafx.scene.image.Image;

public class FireFlower extends BoostItem {

    public FireFlower(double x, double y, Image style) {
        super(x, y, style);
        setPoint(150);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());

        ImageLoader imageLoader = new ImageLoader();

        if(!mario.getMarioForm().isFire()){
            Image[] leftFrames = imageLoader.getLeftFrames(MarioForm.FIRE);
            Image[] rightFrames = imageLoader.getRightFrames(MarioForm.FIRE);

            Animation animation = new Animation(leftFrames, rightFrames);
            MarioForm newForm = new MarioForm(animation, true, true);
            mario.setMarioForm(newForm);
            mario.setDimension(48, 96);

            engine.playFireFlower();
        }
    }

    @Override
    public void updateLocation(){}

}
