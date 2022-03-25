package inf112.skeleton.app.objects;

import java.util.ArrayList;

import inf112.skeleton.app.objects.attributes.Speed;

public class CollisionLogic implements ICollisionLogic {

    private final IItem entity;
    private final ArrayList<IItem> collideables;

    public CollisionLogic(IItem entity, ArrayList<IItem> collideables) {
        this.entity = entity;
        this.collideables = collideables;
    }

    @Override
    public double ClosestPositionX(Speed speed) {
        entity.getPosition().setX(entity.getPosition().getX() + speed.velocityX);
        for(var collidable : collideables) {
            if (entity.getCollisionBox().overlap(collidable)) {
                entity.getPosition().setX(entity.getPosition().getX() - speed.velocityX);
                while(!entity.getCollisionBox().overlap(collidable)) {
                	entity.getPosition().setX(entity.getPosition().getX() + Math.signum(speed.velocityX));
                }
                entity.getPosition().setX(entity.getPosition().getX() - Math.signum(speed.velocityX));
                entity.getPosition().setX(0);
            }
        }
        return 0;
    }

    @Override
    public double ClosestPositionY(Speed speed) {
        return 0;
    }
}
