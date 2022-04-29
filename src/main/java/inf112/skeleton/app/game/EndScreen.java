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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class EndScreen implements IStartGameSubject {

    private final Stage stage;
    private final LinkedList<IGameObserver> startGameObservers = new LinkedList<IGameObserver>();
    Image image;

    public EndScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("Game over");
        Button btnTryAgain = new Button();
        btnTryAgain.setText("Try again");
        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnTryAgain.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    new Game().start(stage);
//                    startGameObservers.forEach(observer -> observer.startGame(false));
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
        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/endScreen.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
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
