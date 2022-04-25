package inf112.skeleton.app.objects.attributes;

public class GameObjectSize {
    public double width;
    public double height;

    public GameObjectSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GameObjectSize(TileSize tileSize) {
        this.width = tileSize.width;
        this.height = tileSize.height;
    }
}
