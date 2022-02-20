package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile implements IDrawable, ICollideable {

    private IDrawBehavior drawHandler;

    private GridPosition gridPosition;
    private TileSize tileSize;

    public Tile(GridPosition gridPosition, TileSize tileSize, GraphicsContext context) {
        this.gridPosition = gridPosition;
        this.tileSize = tileSize;
        drawHandler = new DrawColorBehavior(context, Color.CORAL);
    }

    public void Draw() {
        var position = new Position(gridPosition, tileSize);
        var boundingBox = new Rectangle(tileSize);
        drawHandler.Draw(position, boundingBox);
    }

    @Override
    public Position GetPosition() {
        return new Position(gridPosition, tileSize);
    }

    @Override
    public CollisionBox GetCollisionBox() {
        var positionA = new Position(gridPosition, tileSize);
        var positionB = new Position(positionA);
        positionB.Add(new Rectangle(tileSize));
        return new CollisionBox(positionA, positionB);
    }

    public double GetClosestYPosition(Position position) {
        var bottomY = position.y;
        var topY = position.y + tileSize.height;

        if(Math.abs(bottomY - position.y) > Math.abs(topY - position.y)) {
            return topY;
        } else {
            return bottomY;
        }
    }

    public double GetClosestXPosition(Position position) {
        var bottomX = position.x;
        var topX = position.x + tileSize.width;

        if(Math.abs(bottomX - position.x) > Math.abs(topX - position.x)) {
            return topX;
        } else {
            return bottomX;
        }
    }
}
