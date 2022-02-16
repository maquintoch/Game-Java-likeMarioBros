package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public class GameBackgroundDrawService implements IDrawable {

    GraphicsContext context;

    public GameBackgroundDrawService(GraphicsContext context) {
        this.context = context;
    }

    @Override
    public void Draw() {

    }
}
