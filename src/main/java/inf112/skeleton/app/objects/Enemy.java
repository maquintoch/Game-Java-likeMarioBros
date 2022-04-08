package inf112.skeleton.app.objects;

import java.util.ArrayList;

import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.canvas.Canvas;

import inf112.skeleton.app.draw.IDrawBehavior;


public class Enemy implements IPlayer {
    private final ArrayList<Enemy> enemies = new ArrayList<Enemy>();
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

	public void setOtherEnemies (ArrayList<Enemy> enemies){
	    this.enemies.addAll(enemies);
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

        for(var collidable : enemies) {
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
                if(collidable.speed.velocityX == 0.5f) {
                    collidable.speed.velocityX = -0.5f;
                }
                else {
                    collidable.speed.velocityX = 0.5f;
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
        for(var collidable : enemies) {
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

    private double getClosestYPosition(Position position) {
        var bottomY = position.getY();
        var topY = position.getY() + boundingBox.height;

        if(Math.abs(bottomY - position.getY()) > Math.abs(topY - position.getY())) {
            return topY;
        } else {
            return bottomY;
        }
    }

    private double getClosestXPosition(Position position) {
        var bottomX = position.getX();
        var topX = position.getX() + boundingBox.width;

        if(Math.abs(bottomX - position.getX()) > Math.abs(topX - position.getX())) {
            return topX;
        } else {
            return bottomX;
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

    public void destroy() {
        this.position = new Position(0, -5000);
    }

}

