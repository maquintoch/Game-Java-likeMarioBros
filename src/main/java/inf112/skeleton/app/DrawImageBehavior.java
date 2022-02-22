package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

public class DrawImageBehavior implements IDrawBehavior {
	
	private GraphicsContext context;
    private Canvas canvas;
    private Image image;
    
    public DrawImageBehavior(Canvas canvas, Image image) {
    	this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.image = image;
    }

	@Override
	public void Draw(Position position, Rectangle boundingBox) {
		context.save();
		context.drawImage(image, position.x, canvas.getHeight() - position.y - boundingBox.height, boundingBox.width, boundingBox.height);
		context.restore();
		
	}
	
	

}
