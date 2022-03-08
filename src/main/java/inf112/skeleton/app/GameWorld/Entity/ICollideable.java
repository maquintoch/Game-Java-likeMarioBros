package inf112.skeleton.app.GameWorld.Entity;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.CollisionBox;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;

public interface ICollideable {
    Position GetPosition();
    CollisionBox GetCollisionBox();
}
