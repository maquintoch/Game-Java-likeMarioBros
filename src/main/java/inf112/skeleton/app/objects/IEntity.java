package inf112.skeleton.app.objects;

import inf112.skeleton.app.objects.attributes.IUpdateable;
import inf112.skeleton.app.objects.attributes.Speed;

public interface IEntity extends IGameObject, IUpdateable {
    public Speed getVelocity();
    public Speed getAcceleration();
}
