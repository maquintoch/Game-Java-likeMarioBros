package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.ICollidable;
import inf112.skeleton.app.objects.attributes.ItemType;
import inf112.skeleton.app.objects.attributes.Position;

public abstract class BaseCollidableTile extends BaseTile implements ICollidable {

    public BaseCollidableTile(GameWorld gameWorld, int xPosition, int yPosition) {
        super(gameWorld, xPosition, yPosition);
    }

    @Override
    public abstract ItemType getItemType();
    @Override
    public abstract void collide(ItemType itemType);

    public double getClosestYPosition(Position position) {
        var bottomY = position.getY();
        var topY = position.getY() + boundingBox.height;

        if(Math.abs(bottomY - position.getY()) > Math.abs(topY - position.getY())) {
            return topY;
        } else {
            return bottomY;
        }
    }

    public double getClosestXPosition(Position position) {
        var bottomX = position.getX();
        var topX = position.getX() + boundingBox.width;

        if(Math.abs(bottomX - position.getX()) > Math.abs(topX - position.getX())) {
            return topX;
        } else {
            return bottomX;
        }
    }
}
