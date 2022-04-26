package inf112.skeleton.app.game.gameworld;

import inf112.skeleton.app.objects.Player;

public interface IPlayersObserver {
    void addPlayer(Player player);

    void removePlayer(Player player);
}
