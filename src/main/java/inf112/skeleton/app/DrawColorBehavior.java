package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawColorBehavior implements IDrawBehavior {

    private GraphicsContext context;
    private Canvas canvas;
    private Paint color;

    public DrawColorBehavior(GraphicsContext context,Canvas cava) {
        this.context = context;
        color = Color.LIGHTCORAL;
        this.canvas = cava;
    }

    public void Draw(Position position, Rectangle boundingBox) {
        context.save();
        context.setFill(color);
        context.fillRect(canvas.getWidth() - position.x, canvas.getHeight() - position.y,boundingBox.width,boundingBox.height);
        //context.fillRect(position.x, position.y, boundingBox.width, boundingBox.height);
        context.restore();
    }
}
