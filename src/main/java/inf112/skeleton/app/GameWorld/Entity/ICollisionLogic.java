package inf112.skeleton.app.Entity;

import inf112.skeleton.app.Entity.EntityAttributes.Speed;

public interface ICollisionLogic {
    double ClosestPositionX(Speed speed);
    double ClosestPositionY(Speed speed);
}
