package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawColorBehavior implements IDrawBehavior {

    private GraphicsContext context;
    private Canvas canvas;
    private Paint color;

    public DrawColorBehavior(Canvas canvas, Paint color) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.color = color;
    }

    public void Draw(Position position, Rectangle boundingBox) {
        context.save();
        context.setFill(color);
        context.fillRect(position.x, canvas.getHeight() - position.y - boundingBox.height,boundingBox.width,boundingBox.height);
        //context.fillRect(position.x, position.y, boundingBox.width, boundingBox.height);
        context.restore();
    }
}
