package superMario.figures.prize;

import superMario.manager.GameEngine;
import superMario.figures.hero.Mario;
import superMario.figures.hero.MarioForm;
import superMario.view.Animation;
import superMario.view.ImageLoader;

import javafx.scene.image.Image;

public class SuperMushroom extends BoostItem{

    public SuperMushroom(double x, double y, Image style) {
        super(x, y, style);
        setPoint(125);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());

        ImageLoader imageLoader = new ImageLoader();

        if(!mario.getMarioForm().isSuper()){
            Image[] leftFrames = imageLoader.getLeftFrames(MarioForm.SUPER);
            Image[] rightFrames = imageLoader.getRightFrames(MarioForm.SUPER);

            Animation animation = new Animation(leftFrames, rightFrames);
            MarioForm newForm = new MarioForm(animation, true, false);
            mario.setMarioForm(newForm);
            mario.setDimension(48, 96);

            engine.playSuperMushroom();
        }
    }
}
