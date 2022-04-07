package inf112.skeleton.app.game;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.game.gameworld.GameWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

            @Override
            public void handle(ActionEvent event) {
                try {
                    Boolean choice = true;
                    startGame(stage,choice);
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
                    Boolean choice = false;
                    startGame(stage,choice);
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
        btnOnePlayer.setLayoutX(150);
        btnOnePlayer.setLayoutY(150);
        btnMultiplayer.setLayoutX(150);
        btnMultiplayer.setLayoutY(225);
        btnExit.setLayoutX(150);
        btnExit.setLayoutY(300);
        root.getChildren().add(canvas);
        root.getChildren().add(btnOnePlayer);
        root.getChildren().add(btnMultiplayer);
        root.getChildren().add(btnExit);
 
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
    
    public void EndScreen(Stage stage){
        stage.setTitle("Game over");
        Button btnTryAgain = new Button();
        btnTryAgain.setText("Try again");
        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnTryAgain.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    startScreen(stage);

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

    
    public void gameOver(MediaPlayer mp, Stage stage){
		levelCount = 0;
        mp.stop();
        EndScreen(stage);
    }

    public void startGame(Stage stage, Boolean choice){
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
            HealthUI healthUI2 = new HealthUI(canvas.getGraphicsContext2D());
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

            gameWorld = new GameWorld(canvas, inputHandler, healthUI, healthUI2, coinUI, levelCount, choice);

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
                    
                    // process game over with single player
                    if(gameWorld.isSinglePlayer()) {
                    	if(healthUI.currentHealth.getHealth() == 0) {
                    		gameOver(mp, stage);
                    		this.stop();
                    	}
                    }
                    // process game over with two player
                    else {
	                    if(healthUI.currentHealth.getHealth() == 0){ 
	                    	gameWorld.getCamera().setTargetEntity (gameWorld.getPlayer2());
	                    	gameWorld.getPlayer().beKilled();
	                        if (healthUI2.currentHealth.getHealth() == 0) {
	                        	gameOver(mp, stage);
	                        	this.stop();
	                        }
	                    }
	
	                    if(healthUI2.currentHealth.getHealth() == 0){ 
	                        gameWorld.getCamera().setTargetEntity (gameWorld.getPlayer());
	                        gameWorld.getPlayer2().beKilled();
	                        if (healthUI.currentHealth.getHealth() == 0) {
	                        	gameOver(mp, stage);
	                        	this.stop();
	                        }
	                    }
                    }
                    if(coinUI.currentscore.getScore() == 10){
                        levelCount++;
                        coinUI.currentscore.subtractTenFromScore();

                       gameWorld =  new GameWorld(canvas, inputHandler, healthUI, healthUI2, coinUI, levelCount, choice);

                    }

                    gameWorld.update();
                    gameWorld.draw();
                }
            };

            timer.start();
            stage.show();
    }
}
