package inf112.skeleton.app.DrawBehavior;

import inf112.skeleton.app.Camera.ICamera;
import inf112.skeleton.app.Entity.EntityAttributes.Position;
import inf112.skeleton.app.Entity.EntityAttributes.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

public class DrawImageBehavior implements IDrawBehavior {
	
	private GraphicsContext context;
    private Canvas canvas;
    private Image image;
    private ICamera camera;
    
    public DrawImageBehavior(Canvas canvas, Image image, ICamera camera) {
    	this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.image = image;
        this.camera = camera;
    }

	@Override
	public void Draw(Position position, Rectangle boundingBox) {
		context.save();
		context.drawImage(image, position.x - camera.getx(), canvas.getHeight() - position.y - boundingBox.height + camera.gety(), boundingBox.width, boundingBox.height);
		context.restore();
		
	}
	
	

}
