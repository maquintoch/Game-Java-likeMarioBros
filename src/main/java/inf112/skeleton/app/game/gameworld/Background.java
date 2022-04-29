package inf112.skeleton.app.game.gameworld;

import javafx.scene.canvas.Canvas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

public class Background{
    private Canvas canvas;
    private Image image;
    
    public Background(Canvas canvas) {
        this.canvas = canvas;
        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/background.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
    }

    public void draw() {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        //Draw image on screen
        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);
    }
}
