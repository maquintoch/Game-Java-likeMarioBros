package inf112.skeleton.app.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;

import javafx.scene.image.Image;


public class Enemy extends BaseCollidableTile implements IEntity {
    private Speed speed;
	private Speed acceleration;

    public Enemy(GameWorld gameWorld, int xPosition, int yPosition) {
        super(gameWorld, xPosition, yPosition);

        this.speed = new Speed(0.5f, 0);
        this.acceleration = new Speed(0, -0.5f);

        try {
            image = new Image(new FileInputStream("src\\main\\java\\inf112\\skeleton\\app\\assets\\image\\enemy.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.Enemy;
    }

    @Override
	public Speed getSpeed() {
		return speed;
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

        var collidables = (ArrayList<ICollidable>)gameWorld.getCollidables().clone();
        collidables.remove(this);

		position.setX(position.getX() + speed.velocityX);
        for(var collidable : collidables) {
            if (collidable.getItemType() != ItemType.Tile) continue;
            if (getCollisionBox().overlap(collidable)) {
                position.setX(position.getX() - speed.velocityX);
                while(!overlap(collidable)) {
                	position.setX(position.getX() + Math.signum(speed.velocityX));
                }
                position.setX(position.getX() - Math.signum(speed.velocityX));
                position.setX(getClosestXPosition(position));
                this.collide(ItemType.Tile);
            }
        }

        for(var collidable : collidables) {
            if (collidable.getItemType() != ItemType.Enemy) continue;
            if (getCollisionBox().overlap(collidable)) {
                position.setX(position.getX() - speed.velocityX);
                while(!overlap(collidable)) {
                    position.setX(position.getX() + Math.signum(speed.velocityX));
                }
                position.setX(position.getX() - Math.signum(speed.velocityX));
                this.collide(ItemType.Enemy);
                //collidable.collide(ItemType.Enemy);
                position.setX(getClosestXPosition(position));
            }
        }

        position.setY(position.getY() + speed.velocityY);
        for(var collidable : collidables) {
            if (collidable.getItemType() != ItemType.Tile) continue;
            if (getCollisionBox().overlap(collidable)) {
                position.setY(position.getY() - speed.velocityY);
                while(!overlap(collidable)) {
                	position.setY(position.getY() + Math.signum(speed.velocityY));
                }
                position.setY(position.getY() - Math.signum(speed.velocityY));
                speed.velocityY = 0;
                position.setY(getClosestYPosition(position));
            }
        }

        for(var collidable : collidables) {
            if (collidable.getItemType() != ItemType.Enemy) continue;
            if (getCollisionBox().overlap(collidable)) {
                position.setY(position.getY() - speed.velocityY);
                while(!overlap(collidable)) {
                    position.setY(position.getY() + Math.signum(speed.velocityY));
                }
                position.setY(position.getY() - Math.signum(speed.velocityY));
                speed.velocityY = 0;
                position.setY(getClosestYPosition(position));
            }
        }

        for(ICollidable collidable : collidables) {
            if (!getCollisionBox().overlap(collidable)) continue;
            if (collidable.getItemType() == ItemType.Trampoline) {
                this.speed.velocityY += 5;
            }
        }
	}

    @Override
    public void collide(ItemType itemType) {
        if(itemType == ItemType.Player) {
            this.gameWorld.removeCollidable(this);
            this.gameWorld.removeEntity(this);
        }
        if(itemType == ItemType.Enemy) {
            this.speed.velocityX = -this.speed.velocityX;
        }
        if(itemType == ItemType.Tile) {
            this.speed.velocityX = -this.speed.velocityX;
        }
    }
}

