package inf112.skeleton.app.game.gameworld;

import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;

public interface GameWorldObserver {
    void removeGameObject(IGameObject gameObject);
    void addScore(int amount);
    void addPlayer(Player player);
}
