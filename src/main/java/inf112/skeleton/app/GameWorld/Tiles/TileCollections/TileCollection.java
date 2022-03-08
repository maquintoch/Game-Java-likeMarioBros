package inf112.skeleton.app.Tiles.TileCollections;

import java.util.ArrayList;

import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.Tiles.Tile;

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
