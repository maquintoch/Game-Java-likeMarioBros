package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawColorBehavior implements IDrawBehavior {

    private GraphicsContext context;
    private Paint color;

    public DrawColorBehavior(GraphicsContext context, Paint color) {
        this.context = context;
        this.color = color;
    }

    public void Draw(Position position, Rectangle boundingBox) {
        context.save();
        context.setFill(color);
        context.fillRect(position.x, position.y, boundingBox.width, boundingBox.height);
        context.restore();
    }
}
