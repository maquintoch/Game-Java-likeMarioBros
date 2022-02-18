package inf112.skeleton.app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    private GraphicsContext context;

    private GameWorld gameWorld;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var canvasFactory = new CanvasFactory(stage);
        var canvas = canvasFactory.getCanvas();

        gameWorld = new GameWorld(canvas);

        var timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                gameWorld.Update();
                gameWorld.Draw();
            }

        };

        timer.start();
//		stage.setFullScreen(true);
        stage.show();
    }
}
