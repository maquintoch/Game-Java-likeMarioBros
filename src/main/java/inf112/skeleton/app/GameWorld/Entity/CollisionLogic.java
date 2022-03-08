package inf112.skeleton.app.Entity;

import java.util.ArrayList;

import inf112.skeleton.app.Entity.EntityAttributes.Speed;

public class CollisionLogic implements ICollisionLogic {

    private final Entity entity;
    private final ArrayList<ICollideable> collideables;

    public CollisionLogic(Entity entity, ArrayList<ICollideable> collideables) {
        this.entity = entity;
        this.collideables = collideables;
    }

    @Override
    public double ClosestPositionX(Speed speed) {
        entity.position.x += speed.velocityX;
        for(var collidable : collideables) {
            if (entity.GetCollisionBox().overlap(collidable)) {
                entity.position.x -= speed.velocityX;
                while(!entity.GetCollisionBox().overlap(collidable)) entity.position.x += Math.signum(speed.velocityX);
                entity.position.x -= Math.signum(speed.velocityX);
                entity.position.x = 0;
            }
        }
        return 0;
    }

    @Override
    public double ClosestPositionY(Speed speed) {
        return 0;
    }
}
