package inf112.skeleton.app;

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
}
