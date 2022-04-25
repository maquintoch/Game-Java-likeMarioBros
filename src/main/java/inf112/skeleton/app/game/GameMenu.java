package inf112.skeleton.app.game;

import javafx.stage.Stage;

public class GameMenu implements IStartGameSubject {

    public final WinningScreen winningScreen;
    public final EndScreen endScreen;
    public final StartScreen startScreen;

    public GameMenu(Stage stage) {
        this.winningScreen = new WinningScreen(stage);
        this.endScreen = new EndScreen(stage);
        this.startScreen = new StartScreen(stage);
    }

    public void addGameObserver(IGameObserver observer) {
        this.winningScreen.addGameObserver(observer);
        this.endScreen.addGameObserver(observer);
        this.startScreen.addGameObserver(observer);
    }
}
