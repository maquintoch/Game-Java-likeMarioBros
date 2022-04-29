package inf112.skeleton.app.game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;

public class WinningScreen implements IStartGameSubject {
    private final Stage stage;
    private final LinkedList<IGameObserver> startGameObservers = new LinkedList<IGameObserver>();

    public WinningScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("You Won!");
        Text text = new Text("YOU WON!!!");
        text.setLayoutX(150);
        text.setLayoutY(50);
        text.setStyle("-fx-font-size: 40");
        Button btn = new Button();
        btn.setText("Play again?");
        Button btnExit = new Button();
        btnExit.setText("Exit");

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    startGameObservers.forEach(observer -> observer.startGame(false));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        Group root = new Group();
        btn.setLayoutX(250);
        btn.setLayoutY(250);
        btnExit.setLayoutX(250);
        btnExit.setLayoutY(300);
        root.getChildren().add(text);
        root.getChildren().add(btnExit);
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 500, 500, Color.LIGHTSKYBLUE));
        stage.show();
    }

    @Override
    public void addGameObserver(IGameObserver observer) {
        this.startGameObservers.add(observer);
    }
}
