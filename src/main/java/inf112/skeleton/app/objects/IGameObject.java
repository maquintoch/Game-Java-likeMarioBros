package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorldObserver;
import inf112.skeleton.app.objects.attributes.*;

public interface IGameObject extends IDrawable, IUpdateable, ICollidable {
    ItemType getItemType();
    Position getPosition();
    Speed getVelocity();
    GameObjectSize getSize();

    boolean overlap(IGameObject gameObject);
    boolean isAbove(IGameObject gameObject);
    void destory();
    void addGameWorldObserver(GameWorldObserver observer);
}
