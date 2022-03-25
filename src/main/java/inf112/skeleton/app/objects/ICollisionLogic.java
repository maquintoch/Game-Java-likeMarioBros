package inf112.skeleton.app.objects;

import inf112.skeleton.app.objects.attributes.Speed;

public interface ICollisionLogic {
    double ClosestPositionX(Speed speed);
    double ClosestPositionY(Speed speed);
}
