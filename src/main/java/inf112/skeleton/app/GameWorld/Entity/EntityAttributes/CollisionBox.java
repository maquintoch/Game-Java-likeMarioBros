package inf112.skeleton.app.GameWorld.Entity.EntityAttributes;

import inf112.skeleton.app.GameWorld.Entity.ICollideable;

public class CollisionBox {
    private Position CornerAPosition;
    private Position CornerBPosition;

    public CollisionBox(Position positionA, Position positionB) {
        this.CornerAPosition = positionA;
        this.CornerBPosition = positionB;
    }

    public boolean overlap(ICollideable collideable) {
        var collisionBoxB = collideable.GetCollisionBox();
        return this.overlap(collisionBoxB);
    }

    private boolean overlapX(CollisionBox collisionBox) {
        return this.CornerAPosition.x < collisionBox.CornerAPosition.x + (collisionBox.getWidth())
                && this.CornerAPosition.x + getWidth() > collisionBox.CornerAPosition.x;
    }

    private boolean overlapY(CollisionBox collisionBox) {
        return this.CornerAPosition.y < collisionBox.CornerAPosition.y + (collisionBox.getHeight())
                && this.CornerAPosition.y + getHeight() > collisionBox.CornerAPosition.y;
    }

    private double getHeight() {
        return Math.abs(CornerAPosition.y - CornerBPosition.y);
    }

    private double getWidth() {
        return Math.abs(CornerAPosition.x - CornerBPosition.x);
    }

    public boolean overlap(CollisionBox collisionBox) {
        boolean isOverlapHorizontal = overlapX(collisionBox);
        boolean isOverlapVertical = overlapY(collisionBox);
        return isOverlapHorizontal && isOverlapVertical;

    }
}
