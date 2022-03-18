package inf112.skeleton.app.GameWorld.Entity;

import java.util.ArrayList;

import inf112.skeleton.app.DrawBehavior.IDrawBehavior;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.CollisionBox;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Rectangle;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Speed;
import inf112.skeleton.app.GameWorld.Tiles.Tile;
import javafx.scene.canvas.Canvas;

public class EnemyEntity extends Entity implements ICollideable {

	private Speed speed;
	private ArrayList<Tile> collideableCollection;
	private Speed acceleration;

	public EnemyEntity(Canvas canvas, ArrayList<Tile> collideables, IDrawBehavior drawHandler) {
		super(canvas, drawHandler);
		this.position = new Position(100, 10);
        this.boundingBox = new Rectangle(16, 16);
        this.speed = new Speed(0.5f, 0);
        this.acceleration = new Speed(0, -0.5f);
        this.collideableCollection = collideables;
        
		
	}

	@Override
	public Speed GetSpeed() {
		return speed;
	}

	@Override
	public CollisionBox GetCollisionBox() {
		var edgePosition = new Position(this.position);
        edgePosition.Add(boundingBox);
        return new CollisionBox(position, edgePosition);
	}
	
	private boolean overlap(ICollideable collidable) {
        var collisionBox = GetCollisionBox();
        return collisionBox.overlap(collidable);
    }

	@Override
	public void Update() {
		
		speed.velocityY += acceleration.velocityY;
        speed.velocityX += acceleration.velocityX;
        
		position.x += speed.velocityX;
        for(var collidable : collideableCollection) {
            if (GetCollisionBox().overlap(collidable)) {
                position.x -= speed.velocityX;
                while(!overlap(collidable)) position.x += Math.signum(speed.velocityX);
                position.x -= Math.signum(speed.velocityX);
                if(speed.velocityX == 0.5f) {
                	speed.velocityX = -0.5f;
                }
                else {
                	speed.velocityX = 0.5f;
                }
                position.x = collidable.GetClosestXPosition(position);
            }
        }
        
        position.y += speed.velocityY;
        for(var collidable : collideableCollection) {
            if (GetCollisionBox().overlap(collidable)) {
                position.y -= speed.velocityY;
                while(!overlap(collidable)) position.y += Math.signum(speed.velocityY);
                position.y -= Math.signum(speed.velocityY);
                speed.velocityY = 0;
                position.y = collidable.GetClosestYPosition(position);
            }
        }
		
	}

}
