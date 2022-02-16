package superMario.view;

import javafx.scene.image.Image;

public class Animation {

    private int index = 0, count = 0;
    private Image[] leftFrames, rightFrames;
    private Image currentFrame;

    public Animation(Image[] leftFrames, Image[] rightFrames){
        this.leftFrames = leftFrames;
        this.rightFrames = rightFrames;

        currentFrame = rightFrames[1];
    }

    public Image animate(int speed, boolean toRight){
        count++;
        Image[] frames = toRight ? rightFrames : leftFrames;

        if(count > speed){
            nextFrame(frames);
            count = 0;
        }

        return currentFrame;
    }

    private void nextFrame(Image[] frames) {
        if(index + 3 > frames.length)
            index = 0;

        currentFrame = frames[index+2];
        index++;
    }

    public Image[] getLeftFrames() {
        return leftFrames;
    }

    public Image[] getRightFrames() {
        return rightFrames;
    }

}
