package inf112.skeleton.app.objects.attributes;

import inf112.skeleton.app.objects.IGameObject;

public interface ICollidable {
    CollisionBox getCollisionBox();
    void collide(IGameObject gameObject);
}
