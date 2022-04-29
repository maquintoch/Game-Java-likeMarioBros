package inf112.skeleton.app.draw;

import inf112.skeleton.app.camera.Camera;
import inf112.skeleton.app.objects.attributes.GameObjectSize;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawImageBehavior {
	private GraphicsContext context;
    private Canvas canvas;
    private Camera camera;
    
    public DrawImageBehavior(Canvas canvas, Camera camera) {
    	this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.camera = camera;
    }

	
	public void draw(Position position, GameObjectSize boundingBox, Image image) {
		context.save();
		context.drawImage(image, position.getX() - camera.getX(), 
				canvas.getHeight() - position.getY() - boundingBox.height + camera.getY(), 
				boundingBox.width, boundingBox.height);
		context.restore();	
	}

}
