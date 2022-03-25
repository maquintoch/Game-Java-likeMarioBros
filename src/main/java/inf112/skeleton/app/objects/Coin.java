package inf112.skeleton.app.objects;

import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.GridPosition;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;
import inf112.skeleton.app.objects.attributes.TileSize;
import javafx.scene.image.Image;

public class Coin implements IItem{
	private IDrawBehavior drawHandler;
    private GridPosition gridPosition;
    private TileSize tileSize;
    
    private String imagePath = "data:image/jpeg;base64,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";
    private Image image = new Image(imagePath);
    
    public Coin(GridPosition gridPosition, TileSize tileSize, IDrawBehavior drawHandler) {
        this.gridPosition = gridPosition;
        this.tileSize = tileSize;
        this.drawHandler = drawHandler;
    }
    
    @Override
	public void draw() {
		var position = new Position(gridPosition, tileSize);
        var boundingBox = new Rectangle(tileSize);
        drawHandler.draw(position, boundingBox);
		
	}
	public GridPosition getGridPosition() {
		return gridPosition;
	}
	
	@Override
    public Position getPosition() {
        return new Position(gridPosition, tileSize);
    }

    @Override
    public CollisionBox getCollisionBox() {
        var positionA = new Position(gridPosition, tileSize);
        var positionB = new Position(positionA);
        positionB.Add(new Rectangle(tileSize));
        return new CollisionBox(positionA, positionB);
    }
    
    public double getClosestYPosition(Position position) {
        var bottomY = position.getY();
        var topY = position.getY() + tileSize.height;

        if(Math.abs(bottomY - position.getY()) > Math.abs(topY - position.getY())) {
            return topY;
        } else {
            return bottomY;
        }
    }

    public double getClosestXPosition(Position position) {
        var bottomX = position.getX();
        var topX = position.getX() + tileSize.width;

        if(Math.abs(bottomX - position.getX()) > Math.abs(topX - position.getX())) {
            return topX;
        } else {
            return bottomX;
        }
    }

	public void destroy() {
//		this.gameWorld.remove(this);
		this.gridPosition = new GridPosition(0, -50);
	}
}
