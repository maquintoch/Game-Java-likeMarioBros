package inf112.skeleton.app.GameWorld.Entity;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Speed;

public interface ICollisionLogic {
    double ClosestPositionX(Speed speed);
    double ClosestPositionY(Speed speed);
}
