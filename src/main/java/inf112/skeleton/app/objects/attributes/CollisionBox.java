package inf112.skeleton.app.objects.attributes;

import inf112.skeleton.app.objects.IGameObject;

public class CollisionBox {
    private Position CornerAPosition;
    private Position CornerBPosition;

    public CollisionBox(Position positionA, Position positionB) {
        this.CornerAPosition = positionA;
        this.CornerBPosition = positionB;
    }

    public boolean overlap(IGameObject collideable) {
    	CollisionBox collisionBoxB = collideable.getCollisionBox();
        return this.overlap(collisionBoxB);
    }

    private boolean overlapX(CollisionBox collisionBox) {
        return this.CornerAPosition.getX() < collisionBox.CornerAPosition.getX() + (collisionBox.getWidth())
                && this.CornerAPosition.getX() + getWidth() > collisionBox.CornerAPosition.getX();
    }

    private boolean overlapY(CollisionBox collisionBox) {
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
