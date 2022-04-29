package inf112.skeleton.app.game.gameworld;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

public class Background{
    private Canvas canvas;
    private Image image;
    
    public Background(Canvas canvas) {
        this.canvas = canvas;
        String imagePath = "https://previews.123rf.com/images/vitaliyvill/vitaliyvill1609/vitaliyvill160900011/62999356-seamless-game-background-flat-style-2d-game-application.jpg?fbclid=IwAR3RCBjcR9G8fWr7rEk2YYcwQ5N_LlzQq6XVwgJ5yG0-j-QL0sQJLF4wmAA";
        this.image = new Image(imagePath);
    }

    public void draw() {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        //Draw image on screen
        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);
    }
}
