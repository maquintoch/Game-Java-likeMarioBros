package inf112.skeleton.app;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasFactory {

    private Stage stage;

    public CanvasFactory(Stage stage) {
        this.stage = stage;
    }

    public Canvas getCanvas() {
        double width = 500;
        double height = 500;
        Group root = new Group();
        Scene scene = new Scene(root, width, height, Color.LIGHTSKYBLUE);
        stage.setRenderScaleX(2);
        stage.setRenderScaleY(2);
        stage.setScene(scene);

        var canvas = new Canvas(width, height);
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(canvas);
        return canvas;
    }
}
