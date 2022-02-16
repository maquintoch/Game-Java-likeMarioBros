package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public class Tile implements IDrawable {

    private IDrawBehavior drawHandler;

    private GridPosition gridPosition;
    private TileSize tileSize;

    public Tile(GridPosition gridPosition, TileSize tileSize, GraphicsContext context) {
        this.gridPosition = gridPosition;
        this.tileSize = tileSize;
        drawHandler = new DrawColorBehavior(context);
    }

    public void Draw() {
        var position = new Position(gridPosition, tileSize);
        var boundingBox = new Rectangle(tileSize);
        drawHandler.Draw(position, boundingBox);
    }
}
