package inf112.skeleton.app.objects.attributes;

import inf112.skeleton.app.objects.IItem;

public class CollisionBox {
    private Position CornerAPosition;
    private Position CornerBPosition;

    public CollisionBox(Position positionA, Position positionB) {
        this.CornerAPosition = positionA;
        this.CornerBPosition = positionB;
    }

    public boolean overlap(IItem collideable) {
    	CollisionBox collisionBoxB = collideable.getCollisionBox();
        return this.overlap(collisionBoxB);
    }

    public boolean overlapX(CollisionBox collisionBox) {
        return this.CornerAPosition.getX() < collisionBox.CornerAPosition.getX() + (collisionBox.getWidth())
                && this.CornerAPosition.getX() + getWidth() > collisionBox.CornerAPosition.getX();
    }

    public boolean overlapY(CollisionBox collisionBox) {
        return this.CornerAPosition.getY() < collisionBox.CornerAPosition.getY() + (collisionBox.getHeight())
                && this.CornerAPosition.getY() + getHeight() > collisionBox.CornerAPosition.getY();
    }

    private double getHeight() {
        return Math.abs(CornerAPosition.getY() - CornerBPosition.getY());
    }

    private double getWidth() {
        return Math.abs(CornerAPosition.getX() - CornerBPosition.getX());
    }

    public boolean overlap(CollisionBox collisionBox) {
        boolean isOverlapHorizontal = overlapX(collisionBox);
        boolean isOverlapVertical = overlapY(collisionBox);
        return isOverlapHorizontal && isOverlapVertical;

    }
}
