package inf112.skeleton.app.GameWorld.Tiles;

import inf112.skeleton.app.DrawBehavior.IDrawBehavior;
import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.GameWorld.Entity.ICollideable;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.CollisionBox;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.GridPosition;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Rectangle;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.TileSize;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tile implements IDrawable, ICollideable {

    private IDrawBehavior drawHandler;
    private Canvas canvas;
    private GridPosition gridPosition;
    private TileSize tileSize;
   
    //Image for tiles:
    private String imagePath = "data:image/jpeg;base64,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";
    private Image image = new Image(imagePath);
   

    public Tile(GridPosition gridPosition, TileSize tileSize, GraphicsContext context, Canvas canvas, IDrawBehavior drawHandler) {
        this.canvas = canvas;
        this.gridPosition = gridPosition;
        this.tileSize = tileSize;
        this.drawHandler = drawHandler;
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
