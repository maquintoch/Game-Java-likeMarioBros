package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Tile implements IDrawable {

    private IDrawBehavior drawHandler;
    private Canvas canvas;
    private GridPosition gridPosition;
    private TileSize tileSize;

    public Tile(GridPosition gridPosition, TileSize tileSize, GraphicsContext context, Canvas cava) {
        this.gridPosition = gridPosition;
        this.tileSize = tileSize;
        this.canvas = cava;
        drawHandler = new DrawColorBehavior(context, canvas);
    }

    public void Draw() {
        var position = new Position(gridPosition, tileSize);
        var boundingBox = new Rectangle(tileSize);
        drawHandler.Draw(position, boundingBox);
    }
}
