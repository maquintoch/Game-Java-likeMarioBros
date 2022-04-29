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
import javafx.stage.Stage;

import java.util.LinkedList;

public class StartScreen implements IStartGameSubject {
    private final Stage stage;
    private final LinkedList<IGameObserver> startGameObservers = new LinkedList<IGameObserver>();

    public StartScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("Hello Super World!");
        Button btnOnePlayer = new Button();
        btnOnePlayer.setText("One Player");
        btnOnePlayer.setBackground(null);
        btnOnePlayer.setStyle("-fx-text-fill: #ffffff;-fx-font-size: 40;");
        Button btnMultiplayer = new Button();
        btnMultiplayer.setText("Multiplayer");
        btnMultiplayer.setBackground(null);
        btnMultiplayer.setStyle("-fx-text-fill: #ffffff;-fx-font-size: 40;");
        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setStyle("-fx-text-fill: #ffffff;-fx-font-size: 40;");
        btnExit.setBackground(null);
        btnOnePlayer.setOnAction(new EventHandler<ActionEvent>() {

            @Overridepackage inf112.skeleton.app.game;

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

public class StartScreen implements IStartGameSubject {
    private final Stage stage;
    private final LinkedList<IGameObserver> startGameObservers = new LinkedList<IGameObserver>();
    Image image;
    
    public StartScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("Hello Super World!");
        Button btnOnePlayer = new Button();
        btnOnePlayer.setText("One Player");
        Button btnMultiplayer = new Button();
        btnMultiplayer.setText("Multiplayer");
        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnOnePlayer.setOnAction(new EventHandler<ActionEvent>() {

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
        btnExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        btnMultiplayer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    startGameObservers.forEach(observer -> observer.startGame(true));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                } 
            }
        });
        Canvas canvas = new Canvas(500,500);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/startScreen.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        //Draw image on screen
        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);
        Group root = new Group();
        
        root.getChildren().add(canvas);
        btnOnePlayer.setStyle("-fx-text-fill: #7e7eed;-fx-font-size: 40;");
        btnOnePlayer.setBackground(null);

        btnMultiplayer.setStyle("-fx-text-fill: #7e7eed;-fx-font-size: 40;");
        btnMultiplayer.setBackground(null);

        btnExit.setStyle("-fx-text-fill: #7e7eed;-fx-font-size: 40;");
        btnExit.setBackground(null);
        
        btnOnePlayer.setLayoutX(150);
        btnOnePlayer.setLayoutY(100);
        btnMultiplayer.setLayoutX(150);
        btnMultiplayer.setLayoutY(175);
        btnExit.setLayoutX(150);
        btnExit.setLayoutY(250);
        root.getChildren().add(btnOnePlayer);
        root.getChildren().add(btnMultiplayer);
        root.getChildren().add(btnExit);

        Scene scene = new Scene(root, 500, 500, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void addGameObserver(IGameObserver observer) {
        this.startGameObservers.add(observer);
    }
}
