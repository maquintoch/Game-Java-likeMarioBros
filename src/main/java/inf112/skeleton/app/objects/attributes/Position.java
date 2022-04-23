package inf112.skeleton.app.objects.attributes;

public class Position {
    private double x;
    private double y;

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

    public void Add(GameObjectSize boundingBox) {
        this.x += boundingBox.width;
        this.y += boundingBox.height;
    }

    public void Add(TileSize tileSize) {
        this.x += tileSize.width;
        this.y += tileSize.height;
    }
    
    public double getX() {
    	return this.x;
    }
    
    public double getY() {
    	return this.y;
    }
    
    public void setX(double x) {
    	this.x = x;
    }
    
    public void setY(double y) {
    	this.y = y;
    }
}
