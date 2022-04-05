package inf112.skeleton.app.draw;

import inf112.skeleton.app.camera.ICamera;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawImageBehavior implements IDrawBehavior {
	private GraphicsContext context;
    private Canvas canvas;
    private Image image;
    private ICamera camera;
    
    public DrawImageBehavior(Canvas canvas, ICamera camera) {
    	this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.camera = camera;
    }

	@Override
	public void draw(Position position, Rectangle boundingBox) {
		context.save();
		context.drawImage(image, position.getX() - camera.getX(), 
				canvas.getHeight() - position.getY() - boundingBox.height + camera.getY(), 
				boundingBox.width, boundingBox.height);
		context.restore();	
	}

	
	public void draw(Position position, Rectangle boundingBox, Image image) {
		context.save();
		context.drawImage(image, position.getX() - camera.getX(), 
				canvas.getHeight() - position.getY() - boundingBox.height + camera.getY(), 
				boundingBox.width, boundingBox.height);
		context.restore();	
	}

}
