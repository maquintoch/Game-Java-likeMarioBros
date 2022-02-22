package inf112.skeleton.app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    private GameWorld gameWorld;
    private IInputHandler inputHandler;
    private IDrawable healthUI;
    private IDrawable coinUI;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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
        coinUI = new CoinUIService(context); //Keeping track of coins picked up. 

        var timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                gameWorld.Update();
                gameWorld.Draw();
                healthUI.Draw();
                coinUI.Draw();
            }

        };

        timer.start();
//		stage.setFullScreen(true);
        stage.show();
    }
}
