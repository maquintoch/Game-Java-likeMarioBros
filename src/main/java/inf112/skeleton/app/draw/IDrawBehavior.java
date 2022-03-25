package inf112.skeleton.app.draw;

import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;

public interface IDrawBehavior {
    void draw(Position position, Rectangle boundingBox);
}
