package inf112.skeleton.app.game;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.game.gameworld.GameWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class Game extends Application {
    private GameWorld gameWorld;
    private IInputHandler inputHandler;
    public int levelCount = 0;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startScreen(stage);
    }

    public void startScreen(Stage stage){
        stage.setTitle("Hello Super World!");
        Button btn = new Button();
        btn.setText("Start Spill!");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    startGame(stage);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        Canvas canvas = new Canvas(500,500);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        String imagePath = "https://freedesignfile.com/upload/2012/04/black-Menu-vector-background-1.jpg";
        Image image = new Image(imagePath);
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        //Draw image on screen
        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);
        Group root = new Group();

        btn.setLayoutX(250);
        btn.setLayoutY(250);
        root.getChildren().add(canvas);
        root.getChildren().add(btn);
 
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
    
    public void EndScreen(Stage stage){
        stage.setTitle("Game over");
        Button btn = new Button();
        btn.setText("Try again");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    startGame(stage);

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
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 500, 500, Color.LIGHTSKYBLUE));
        stage.show();
    }


    public void startGame(Stage stage){
            stage.setTitle("Mario");
            double width = 500;
            double height = 500;
            Group root = new Group();
            Scene scene = new Scene(root, width, height, Color.LIGHTSKYBLUE);
            stage.setScene(scene);
            
            Canvas canvas = new Canvas(width, height);
            canvas.widthProperty().bind(scene.widthProperty());
            canvas.heightProperty().bind(scene.heightProperty());
            root.getChildren().add(canvas);
            HealthUI healthUI = new HealthUI(canvas.getGraphicsContext2D());
            CoinUI coinUI = new CoinUI(canvas.getGraphicsContext2D());

            inputHandler = new InputHandler();

            scene.setOnKeyPressed(event -> {
            	KeyCode keyCode = event.getCode();
                inputHandler.setActive(keyCode);
            });

            scene.setOnKeyReleased(event -> {
            	KeyCode keyCode = event.getCode();
                inputHandler.setInactive(keyCode);
            });
            gameWorld = new GameWorld(canvas, inputHandler, healthUI, coinUI, levelCount);            

            // add game music
            String soundGameTheme = "src/main/java/inf112/skeleton/app/assets/GameTheme.mp3";
            Media media = new Media(new File(soundGameTheme).toURI().toString());
            MediaPlayer mp = new MediaPlayer(media);

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    // cycling music in background
                    mp.setCycleCount(MediaPlayer.INDEFINITE);
                    mp.play();
                    if(healthUI.currentHealth.getHealth() == 0) {
                        levelCount = 0;
                        mp.stop();
                        EndScreen(stage);
                        this.stop();
                    }

                    if(coinUI.currentscore.getScore() == 10){
                        levelCount++;
                        coinUI.currentscore.subtractTenFromScore();
                       gameWorld =  new GameWorld(canvas, inputHandler, healthUI, coinUI, levelCount);
                    }

                    gameWorld.update();
                    gameWorld.draw();
                }
            };

            timer.start();
            stage.show();
    }
}
