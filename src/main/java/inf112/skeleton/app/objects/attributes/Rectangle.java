package inf112.skeleton.app.objects.attributes;

public class Rectangle {
    public double width;
    public double height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(TileSize tileSize) {
        this.width = tileSize.width;
        this.height = tileSize.height;
    }
}
