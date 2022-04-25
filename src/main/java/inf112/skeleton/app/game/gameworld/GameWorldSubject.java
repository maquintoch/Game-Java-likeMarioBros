package inf112.skeleton.app.game.gameworld;

public interface GameWorldSubject {
    void addScoreObserver(ScoreObserver observer);
    void addHealthObserver(IHealthObserver observer);
}
