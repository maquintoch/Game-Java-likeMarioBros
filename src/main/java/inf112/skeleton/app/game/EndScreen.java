package inf112.skeleton.app.game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;

public class EndScreen implements IStartGameSubject {

    private final GameMenu gameMenu;
    private final LinkedList<IGameObserver> startGameObservers = new LinkedList<IGameObserver>();

    public EndScreen(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    public void show(Stage stage) {
        stage.setTitle("Game over");
        Button btnTryAgain = new Button();
        btnTryAgain.setText("Try again");
        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnTryAgain.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    startGameObservers.forEach(observer -> observer.startGame());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        Canvas canvas = new Canvas(500,500);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        String imagePath = "https://i.pinimg.com/originals/d5/52/85/d55285388e843b575b4b89986ad65ef2.jpg";
        Image image = new Image(imagePath);
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        //Draw image on screen
        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);


        Group root = new Group();
        root.getChildren().add(canvas);
        btnTryAgain.setStyle("-fx-text-fill: #ff0000;-fx-font-size: 40;");
        btnTryAgain.setBackground(null);
        btnExit.setStyle("-fx-text-fill: #ff0000;-fx-font-size: 40;");
        btnExit.setBackground(null);
        btnTryAgain.setLayoutY(235);
        btnTryAgain.setLayoutX(150);
        btnExit.setLayoutX(175);
        btnExit.setLayoutY(375);
        root.getChildren().add(btnTryAgain);
        root.getChildren().add(btnExit);
        stage.setScene(new Scene(root, 500, 500, Color.LIGHTSKYBLUE));
        stage.show();

    }

    @Override
    public void addGameObserver(IGameObserver observer) {
        this.startGameObservers.add(observer);
    }
}
