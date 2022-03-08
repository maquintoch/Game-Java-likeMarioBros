package inf112.skeleton.app.DrawBehavior;

import inf112.skeleton.app.Entity.EntityAttributes.Position;
import inf112.skeleton.app.Entity.EntityAttributes.Rectangle;

public interface IDrawBehavior {
    void Draw(Position position, Rectangle boundingBox);
}
