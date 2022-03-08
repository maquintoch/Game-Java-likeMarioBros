package inf112.skeleton.app.Entity;

import inf112.skeleton.app.Entity.EntityAttributes.CollisionBox;
import inf112.skeleton.app.Entity.EntityAttributes.Position;

public interface ICollideable {
    Position GetPosition();
    CollisionBox GetCollisionBox();
}
