package inf112.skeleton.app.DrawBehavior;


import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Rectangle;

public interface IDrawBehavior {
    void Draw(Position position, Rectangle boundingBox);
}
