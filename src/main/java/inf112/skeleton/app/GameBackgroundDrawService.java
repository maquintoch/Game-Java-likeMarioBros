package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.paint.*;
import javafx.stage.Stage;


public class GameBackgroundDrawService implements IDrawable {

    private Canvas canvas;
    
    private Image image;

    public GameBackgroundDrawService(Canvas canvas) {
        this.canvas = canvas;
        String imagePath = "https://previews.123rf.com/images/vitaliyvill/vitaliyvill1609/vitaliyvill160900011/62999356-seamless-game-background-flat-style-2d-game-application.jpg?fbclid=IwAR3RCBjcR9G8fWr7rEk2YYcwQ5N_LlzQq6XVwgJ5yG0-j-QL0sQJLF4wmAA";
        this.image = new Image(imagePath);

    }

    @Override
    public void Draw() {
        
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        //Draw image on screen
        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);
       
        
        
        
    }
}
