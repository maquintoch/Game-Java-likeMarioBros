package inf112.skeleton.app.draw;

import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.GameObjectSize;

public interface IDrawBehavior {
    void draw(Position position, GameObjectSize boundingBox);
}
