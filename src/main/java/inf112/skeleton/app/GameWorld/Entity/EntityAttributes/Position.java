package inf112.skeleton.app.Entity.EntityAttributes;

public class Position {
    public double x;
    public double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(GridPosition gridPosition, TileSize tileSize) {
        this.x = gridPosition.x * tileSize.width;
        this.y = gridPosition.y * tileSize.height;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public void AddSpeed(Speed speed) {
        this.x += speed.velocityX;
        this.y += speed.velocityY;
    }

    public void Add(Rectangle boundingBox) {
        this.x += boundingBox.width;
        this.y += boundingBox.height;
    }

    public void Add(TileSize tileSize) {
        this.x += tileSize.width;
        this.y += tileSize.height;
    }
}
