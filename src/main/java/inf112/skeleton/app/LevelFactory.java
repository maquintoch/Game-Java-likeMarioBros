package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class LevelFactory {

    public static ArrayList<Tile> GetTiles(GraphicsContext context) {
        String[] level = {
            "..............b.....",
            "......bb............",
            "....p......bbbb..b..",
            "..........b.........",
            "bbbbbbbbbbbbbbbbbbbb"
            };

        ArrayList<Tile> Tiles = new ArrayList<Tile>();
        for(var y = 0; y < level.length; y++) {
            for(var x = 0; x < level[y].length(); x++) {
                if(level[y].charAt(x) == 'b') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var tile = new Tile(gridPosition, tileSize, context);
                    Tiles.add(tile);
                }
            }
        }
        return Tiles;
    }
}
