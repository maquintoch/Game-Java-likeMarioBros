package superMario.view;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private Image marioForms;
    private Image brickAnimation;

    public ImageLoader(){
        marioForms = loadImage("/superMario/media/mario-forms.png");
        brickAnimation = loadImage("/superMario/media/brick-animation.png");
    }

    public Image loadImage(String path){
        Image imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(getClass().getResource("/superMario/media" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public Image loadImage(File file){
        Image imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public Image getSubImage(Image image, int col, int row, int w, int h){
        if((col == 1 || col == 4) && row == 3){ //koopa
            return image.getSubimage((col-1)*48, 128, w, h);
        }
        return image.getSubimage((col-1)*48, (row-1)*48, w, h);
    }

    public Image[] getLeftFrames(int marioForm){
        Image[] leftFrames = new Image[5];
        int col = 1;
        int width = 52, height = 48;

        if(marioForm == 1) { //super mario
            col = 4;
            width = 48;
            height = 96;
        }
        else if(marioForm == 2){ //fire mario
            col = 7;
            width = 48;
            height = 96;
        }

        for(int i = 0; i < 5; i++){
            leftFrames[i] = marioForms.getSubimage((col-1)*width, (i)*height, width, height);
        }
        return leftFrames;
    }

    public Image[] getRightFrames(int marioForm){
        Image[] rightFrames = new Image[5];
        int col = 2;
        int width = 52, height = 48;

        if(marioForm == 1) { //super mario
            col = 5;
            width = 48;
            height = 96;
        }
        else if(marioForm == 2){ //fire mario
            col = 8;
            width = 48;
            height = 96;
        }

        for(int i = 0; i < 5; i++){
            rightFrames[i] = marioForms.getSubimage((col-1)*width, (i)*height, width, height);
        }
        return rightFrames;
    }

    public Image[] getBrickFrames() {
        Image[] frames = new Image[4];
        for(int i = 0; i < 4; i++){
            frames[i] = brickAnimation.getSubimage(i*105, 0, 105, 105);
        }
        return frames;
    }
}
