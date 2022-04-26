package inf112.skeleton.app.game;

public interface IGameObserver {
    void startGame();
    void joinServer(String s);
    void hostServer();
}
