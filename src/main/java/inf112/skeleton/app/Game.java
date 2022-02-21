package inf112.skeleton.app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    private GameWorld gameWorld;
    private IInputHandler inputHandler;
    private IDrawable healthUI;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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

        inputHandler = new InputHandler();

        scene.setOnKeyPressed(event -> {
            var keyCode = event.getCode();
            inputHandler.setActive(keyCode);
        });

        scene.setOnKeyReleased(event -> {
            var keyCode = event.getCode();
            inputHandler.setInactive(keyCode);
        });
        gameWorld = new GameWorld(canvas, inputHandler);
        var context = canvas.getGraphicsContext2D();
        healthUI = new HealthUIService(context);//Player.health

        // Add sound of background game and game over
        String soundGameTheme = "src/main/java/inf112/skeleton/app/GameTheme.mp3";
        Media media = new Media(new File(soundGameTheme).toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);

        var timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                // cycling music in background
                mp.setCycleCount(MediaPlayer.INDEFINITE);
                mp.play();

                gameWorld.Update();
                gameWorld.Draw();
                healthUI.Draw();
            }

        };

        timer.start();
//		stage.setFullScreen(true);
        stage.show();

    }
}
