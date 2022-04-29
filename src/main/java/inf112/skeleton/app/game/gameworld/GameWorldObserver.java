package inf112.skeleton.app.game.gameworld;

import inf112.skeleton.app.objects.IGameObject;

public interface GameWorldObserver {
    void removeGameObject(IGameObject gameObject);
    void addScore(int amount);

    void addHealth(int health);

    void setHealth(int health);
}
