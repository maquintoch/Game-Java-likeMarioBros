package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;
import javafx.scene.image.Image;

public abstract class BaseTile implements IItem {
    protected final GameWorld gameWorld;
    protected final Position position;
    protected Rectangle boundingBox;

    protected Image image;

    public BaseTile(GameWorld gameWorld, int xPosition, int yPosition) {
        this.gameWorld = gameWorld;
        this.position = new Position(xPosition, yPosition);
        this.boundingBox = new Rectangle(16, 16);
    }

    @Override
    public void draw() {
        var drawBehavior = gameWorld.getDrawImageBehavior();
        drawBehavior.draw(position, boundingBox, image);
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public CollisionBox getCollisionBox() {
        var positionA = position;
        var positionB = new Position(positionA);
        positionB.Add(this.boundingBox);
        return new CollisionBox(positionA, positionB);
    }
}
