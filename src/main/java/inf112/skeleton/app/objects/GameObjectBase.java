package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.game.gameworld.GameWorldObserver;
import inf112.skeleton.app.objects.attributes.*;

import java.util.LinkedList;
import java.util.List;

public abstract class GameObjectBase implements IGameObject {

    protected Position position;
    protected GameObjectSize size;

    protected final LinkedList<GameWorldObserver> gameWorldObservers = new LinkedList<GameWorldObserver>();

    protected Speed velocity;
    protected Speed acceleration;

    protected float bounceAmountX;
    protected float bounceAmountY;

    protected float airDragAmountX;
    protected float airDragAmountY;

    protected float maxSpeedX;
    protected float maxSpeedY;

    public GameObjectBase(Position position) {
        this.position = position;
        this.size = new GameObjectSize(16, 16);

        velocity = new Speed(0, 0);
        acceleration = new Speed(0, 0);

        bounceAmountX = 0f;
        bounceAmountY = 0f;

        airDragAmountX = 0f;
        airDragAmountY = 0f;

        maxSpeedX = Float.POSITIVE_INFINITY;
        maxSpeedY = Float.POSITIVE_INFINITY;
    }

    @Override
    public Speed getVelocity() {
        return velocity;
    }

    @Override
    public Speed getAcceleration() {
        return acceleration;
    }

    @Override
    public void update(List<IGameObject> gameObjects) {
        //apply acceleration
        velocity.velocityY += acceleration.velocityY;
        velocity.velocityX += acceleration.velocityX;

        //air resistance
        velocity.velocityY += -Math.signum(velocity.velocityY) * Math.min(airDragAmountY, Math.abs(velocity.velocityY));
        velocity.velocityX += -Math.signum(velocity.velocityX) * Math.min(airDragAmountX, Math.abs(velocity.velocityX));

        //clamp speed
        this.velocity.velocityX = Math.signum(velocity.velocityX) * Math.min(maxSpeedX, Math.abs(velocity.velocityX));
        this.velocity.velocityY = Math.signum(velocity.velocityY) * Math.min(maxSpeedY, Math.abs(velocity.velocityY));

        //Apply move and check if entity collide with other GameObjects:
        position.setX(position.getX() + velocity.velocityX);
        tileCollisionX(gameObjects);
        position.setY(position.getY() + velocity.velocityY);
        tileCollisionY(gameObjects);
    };


    private void tileCollisionX(List<IGameObject> collidables){
        for(IGameObject collidable : collidables) {
            if (collidable == this) continue;
            if (!getCollisionBox().overlap(collidable)) continue;

            var itemType = collidable.getItemType();
            if (itemType.isSolid()) {
                position.setX(position.getX() - velocity.velocityX);
                while(!overlap(collidable)) {
                    position.setX(position.getX() + Math.signum(velocity.velocityX));
                }
                position.setX(position.getX() - Math.signum(velocity.velocityX));
                velocity.velocityX = -velocity.velocityX * bounceAmountX;

                position.setX(getClosestXPosition(position));
            }

            this.collide(collidable);
            collidable.collide(this);
        }
    }
    private void tileCollisionY(List<IGameObject> collidables){
        for(IGameObject collidable : collidables) {
            if (collidable == this) continue;
            if (!getCollisionBox().overlap(collidable)) continue;

            var itemType = collidable.getItemType();
            if (itemType.isSolid()) {
                position.setY(position.getY() - velocity.velocityY);
                while(!overlap(collidable)) {
                    position.setY(position.getY() + Math.signum(velocity.velocityY));
                }
                position.setY(position.getY() - Math.signum(velocity.velocityY));
                velocity.velocityY = -velocity.velocityY * bounceAmountY;

                position.setY(getClosestYPosition(position));
            }

            this.collide(collidable);
            collidable.collide(this);
        }
    }

    private double getClosestYPosition(Position position) {
        var bottomY = position.getY();
        var topY = position.getY() + size.height;

        if(Math.abs(bottomY - position.getY()) > Math.abs(topY - position.getY())) {
            return topY;
        } else {
            return bottomY;
        }
    }

    private double getClosestXPosition(Position position) {
        var bottomX = position.getX();
        var topX = position.getX() + size.width;

        if(Math.abs(bottomX - position.getX()) > Math.abs(topX - position.getX())) {
            return topX;
        } else {
            return bottomX;
        }
    }

    @Override
    public void destory() {
        this.gameWorldObservers.forEach(observer -> observer.removeGameObject(this));
    }

    @Override
    public abstract ItemType getItemType();

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public abstract void collide(IGameObject gameObject);

    @Override
    public abstract void draw(GameWorld gameWorld);

    @Override
    public CollisionBox getCollisionBox() {
        var positionA = position;
        var positionB = new Position(positionA);
        positionB.Add(this.size);
        return new CollisionBox(positionA, positionB);
    }

    @Override
    public boolean overlap(IGameObject gameObject) {
        var collisionBox = getCollisionBox();
        return collisionBox.overlap(gameObject);
    }

    @Override
    public GameObjectSize getSize() {
        return size;
    }

    @Override
    public boolean isAbove(IGameObject gameObject) {
        var gameObjectPosition = gameObject.getPosition();
        var gameObjectSize = gameObject.getSize();
        return (position.getY()) >= gameObjectPosition.getY() + gameObjectSize.height;
    }

    @Override
    public void addGameWorldObserver(GameWorldObserver observer) {
        this.gameWorldObservers.add(observer);
    }
}
