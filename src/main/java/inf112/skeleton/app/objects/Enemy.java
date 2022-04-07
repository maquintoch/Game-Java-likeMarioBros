package inf112.skeleton.app.objects;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;

import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;
import inf112.skeleton.app.objects.attributes.Speed;


public class Enemy implements IPlayer {
	private Speed speed;
	private ArrayList<Tile> collideableCollection;
	private Speed acceleration;

    protected Position position;
    protected Rectangle boundingBox;
    private IDrawBehavior drawHandler;

	public Enemy(Canvas canvas, ArrayList<Tile> collideables, IDrawBehavior drawHandler, int xPosition, int yPosition) {
		this.position = new Position(xPosition, yPosition);
        this.boundingBox = new Rectangle(16, 16);
        this.speed = new Speed(0.5f, 0);
        this.acceleration = new Speed(0, -0.5f);
        this.collideableCollection = collideables;
        this.drawHandler = drawHandler;
	}

	@Override
	public Speed getSpeed() {
		return speed;
	}

	@Override
	public CollisionBox getCollisionBox() {
		var edgePosition = new Position(this.position);
        edgePosition.Add(boundingBox);
        return new CollisionBox(position, edgePosition);
	}
	
	@Override
	public boolean overlap(IItem collidable) {
        var collisionBox = getCollisionBox();
        return collisionBox.overlap(collidable);
    }

	@Override
	public void update() {
		speed.velocityY += acceleration.velocityY;
        speed.velocityX += acceleration.velocityX;
        
		position.setX(position.getX() + speed.velocityX);
        for(var collidable : collideableCollection) {
            if (getCollisionBox().overlap(collidable)) {
                position.setX(position.getX() - speed.velocityX);
                while(!overlap(collidable)) {
                	position.setX(position.getX() + Math.signum(speed.velocityX));
                }
                position.setX(position.getX() - Math.signum(speed.velocityX));
                if(speed.velocityX == 0.5f) {
                	speed.velocityX = -0.5f;
                }
                else {
                	speed.velocityX = 0.5f;
                }
                position.setX(collidable.getClosestXPosition(position));
            }
        }
        
        position.setY(position.getY() + speed.velocityY);
        for(var collidable : collideableCollection) {
            if (getCollisionBox().overlap(collidable)) {
                position.setY(position.getY() - speed.velocityY);
                while(!overlap(collidable)) {
                	position.setY(position.getY() + Math.signum(speed.velocityY));
                }
                position.setY(position.getY() - Math.signum(speed.velocityY));
                speed.velocityY = 0;
                position.setY(collidable.getClosestYPosition(position));
            }
        }
	}
    
    @Override
    public void draw() {
        drawHandler.draw(position, boundingBox);
    }

    @Override
    public Position getPosition(){ 
    	return position; 
    }

}

