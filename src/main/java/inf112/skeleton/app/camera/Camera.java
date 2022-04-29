package inf112.skeleton.app.camera;

import inf112.skeleton.app.objects.IGameObject;
import javafx.scene.canvas.Canvas;

import inf112.skeleton.app.objects.attributes.Position;

public class Camera {
	private IGameObject gameObject;
	private Canvas canvas;
	private Position cameraPosition;
	
	public Camera(Canvas canvas) {
		this.canvas = canvas;
		cameraPosition = new Position(0,0);
	}

	public float getX() {
		return (float) cameraPosition.getX();
	}

	public float getY() {
		return (float) cameraPosition.getY();
	}
	
	public void setTargetEntity(IGameObject gameObject) {
		this.gameObject = gameObject;
		
	}

	public void update() {
		double newX = cameraPosition.getX() + ((gameObject.getPosition().getX()
				- (canvas.getWidth()/2)) - cameraPosition.getX()) * 0.1;
		cameraPosition.setX(newX);
		double newY = cameraPosition.getY() + ((gameObject.getPosition().getY()
				- (canvas.getHeight()/2)) - cameraPosition.getY()) * 0.1;
		cameraPosition.setY(newY); 
	}
}
