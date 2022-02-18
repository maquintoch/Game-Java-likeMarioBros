package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameBackgroundDrawService implements IDrawable {

    private Canvas canvas;

    public GameBackgroundDrawService(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void Draw() {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
    }
}
