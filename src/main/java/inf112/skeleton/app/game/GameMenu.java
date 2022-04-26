package inf112.skeleton.app.game;

import javafx.stage.Stage;

public class GameMenu implements IStartGameSubject {

    public final WinningScreen winningScreen;
    public final EndScreen endScreen;
    public final StartScreen startScreen;
    public final MultiplayerScreen multiplayerScreen;

    public GameMenu(Stage stage) {
        this.winningScreen = new WinningScreen(this);
        this.endScreen = new EndScreen(this);
        this.startScreen = new StartScreen(this);
        this.multiplayerScreen = new MultiplayerScreen(this);
    }

    public void addGameObserver(IGameObserver observer) {
        this.winningScreen.addGameObserver(observer);
        this.endScreen.addGameObserver(observer);
        this.startScreen.addGameObserver(observer);
        this.multiplayerScreen.addGameObserver(observer);
    }
}
