package inf112.skeleton.app.Camera;
import inf112.skeleton.app.GameWorld.Entity.Entity;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;
import inf112.skeleton.app.GameWorld.IUpdateable;

import javafx.scene.canvas.Canvas;

public class FollowEntityCamera implements ICamera, IUpdateable{
	private Entity entity;
	private Canvas canvas;
	private Position cameraPosition;
	
	public FollowEntityCamera(Canvas canvas) {
		this.canvas = canvas;
		cameraPosition = new Position(0,0);
	}
	

	@Override
	public float getx() {
		return (float) cameraPosition.x;
	}

	@Override
	public float gety() {
		return (float) cameraPosition.y;
	}
	
	public void setTargetEntity(Entity entity) {
		this.entity = entity;
		
	}


	@Override
	public void Update() {
		cameraPosition.x = cameraPosition.x + ((entity.GetPosition().x - (canvas.getWidth()/2)) - cameraPosition.x) * 0.1;
		cameraPosition.y = cameraPosition.y + ((entity.GetPosition().y - (canvas.getHeight()/2)) - cameraPosition.y) * 0.1;

	}

}
