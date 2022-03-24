package inf112.skeleton.app.GameSetup;


import java.io.File;

import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.GameWorld.GameWorld;
import inf112.skeleton.app.Graphics.CoinUIService;
import inf112.skeleton.app.Graphics.HealthUIService;
import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.Image;



public class Game extends Application {

    private GameWorld gameWorld;
    private IInputHandler inputHandler;
    public int levelCount = 0;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StartScreen(stage);
    }

    public void StartScreen(Stage stage){

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
        //startGame(stage);

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
            var canvas = new Canvas(width, height);
            canvas.widthProperty().bind(scene.widthProperty());
            canvas.heightProperty().bind(scene.heightProperty());
            root.getChildren().add(canvas);
            HealthUIService healthUI = new HealthUIService(canvas.getGraphicsContext2D());
            CoinUIService coinUI = new CoinUIService(canvas.getGraphicsContext2D());



            inputHandler = new InputHandler();

            scene.setOnKeyPressed(event -> {
                var keyCode = event.getCode();
                inputHandler.setActive(keyCode);
            });

            scene.setOnKeyReleased(event -> {
                var keyCode = event.getCode();
                inputHandler.setInactive(keyCode);
            });
            gameWorld = new GameWorld(canvas, inputHandler, healthUI, coinUI, levelCount);
            

            // Add sound of background game and game over
            String soundGameTheme = "src/main/java/inf112/skeleton/app/Resources/GameTheme.mp3";
            Media media = new Media(new File(soundGameTheme).toURI().toString());
            MediaPlayer mp = new MediaPlayer(media);

            var timer = new AnimationTimer() {

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
                        coinUI.currentscore.SubtractTenFromScore();
                       gameWorld =  new GameWorld(canvas, inputHandler, healthUI, coinUI, levelCount);
                    }


                    gameWorld.Update();
                    gameWorld.Draw();
                    
                    


                }

            };



            timer.start();


        
//		stage.setFullScreen(true);
            stage.show();

    }

   // @Override
   //public void start(Stage stage) throws Exception {

   //}
}
