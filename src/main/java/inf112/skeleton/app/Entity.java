package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity implements IDrawable, IUpdateable {

    private IDrawBehavior drawHandler;

    private Position position;
    private Rectangle boundingBox;

    public Entity(GraphicsContext context) {
        drawHandler = new DrawColorBehavior(context);
    }

    @Override
    public void Draw() {
        drawHandler.Draw(position, boundingBox);
    }

    @Override
    public abstract void Update();
}
