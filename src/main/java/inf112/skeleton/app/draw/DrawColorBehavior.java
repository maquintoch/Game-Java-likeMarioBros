package inf112.skeleton.app.draw;

import inf112.skeleton.app.camera.ICamera;
import inf112.skeleton.app.objects.attributes.GameObjectSize;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DrawColorBehavior implements IDrawBehavior {
    private GraphicsContext context;
    private Canvas canvas;
    private Paint color;
    private ICamera camera;

    public DrawColorBehavior(Canvas canvas, Paint color, ICamera camera) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.color = color;
        this.camera = camera;
    }

    @Override
    public void draw(Position position, GameObjectSize boundingBox) {
        context.save();
        context.setFill(color);
        context.fillRect(position.getX() - camera.getX(), 
        		canvas.getHeight() - position.getY() - boundingBox.height + camera.getY(),
        		boundingBox.width,boundingBox.height);
        context.restore();
    }

}
