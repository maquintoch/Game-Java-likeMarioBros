package inf112.skeleton.app.camera;

import javafx.scene.canvas.Canvas;

import inf112.skeleton.app.objects.IPlayer;
import inf112.skeleton.app.objects.attributes.Position;

public class Camera implements ICamera{
	private IPlayer entity;
	private Canvas canvas;
	private Position cameraPosition;
	
	public Camera(Canvas canvas) {
		this.canvas = canvas;
		cameraPosition = new Position(0,0);
	}
	@Override
	public float getX() {
		return (float) cameraPosition.getX();
	}
	@Override
	public float getY() {
		return (float) cameraPosition.getY();
	}
	
	public void setTargetEntity(IPlayer entity) {
		this.entity = entity;
		
	}
	@Override
	public void update() {
		double newX = cameraPosition.getX() + ((entity.getPosition().getX() 
				- (canvas.getWidth()/2)) - cameraPosition.getX()) * 0.1;
		cameraPosition.setX(newX);
		double newY = cameraPosition.getY() + ((entity.getPosition().getY() 
				- (canvas.getHeight()/2)) - cameraPosition.getY()) * 0.1;
		cameraPosition.setY(newY); 
	}
}
