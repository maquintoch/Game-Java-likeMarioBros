package inf112.skeleton.app;

import java.util.ArrayList;

public class TileCollection implements IDrawable {
    private final ArrayList<Tile> tiles;

    public TileCollection(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public void Draw() {
        for(Tile tile : tiles) {
            tile.Draw();
        }
    }
}
