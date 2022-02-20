package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameBackgroundDrawService implements IDrawable {

    private Canvas canvas;
    private IDrawBehavior drawBehavior;

    public GameBackgroundDrawService(Canvas canvas) {
        this.canvas = canvas;
        drawBehavior = new DrawColorBehavior(canvas, Color.WHITE);
    }

    @Override
    public void Draw() {
        var position = new Position(0, 0);
        var rectangle = new Rectangle(((int) canvas.getWidth()), ((int) canvas.getHeight()));
        drawBehavior.Draw(position, rectangle);
    }
}
