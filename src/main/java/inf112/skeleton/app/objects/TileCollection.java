package inf112.skeleton.app.objects;

import java.util.ArrayList;

public class TileCollection{
    private final ArrayList<Tile> tiles;

    public TileCollection(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public void draw() {
        for(Tile tile : tiles) {
            tile.draw();
        }
    }
}
