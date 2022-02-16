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
        double width = 500;
        double height = 500;
        Group root = new Group();
        Scene scene = new Scene(root, width, height, Color.LIGHTSKYBLUE);
        stage.setScene(scene);

        var canvas = new Canvas(width, height);
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

        context = canvas.getGraphicsContext2D();
        gameWorld = new GameWorld(context);

        var timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
            gameWorld.Update();
            gameWorld.Draw();
            }

        };
        root.getChildren().add(canvas);

        timer.start();
//		stage.setFullScreen(true);
        stage.show();
    }
}
