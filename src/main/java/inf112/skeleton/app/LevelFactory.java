package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class LevelFactory implements ILevelFactory {

    private Canvas canvas;

    public LevelFactory(Canvas canvas) {
        this.canvas = canvas;
    }

    public ArrayList<Tile> GetTiles() {
        String[] level = {
                "...........................................................................",
                "...........................................................................",
                "...........................................................................",
                "...........................................................................",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                "........................b...................b.........b...................."
            };

        ArrayList<Tile> Tiles = new ArrayList<Tile>();
        var context = canvas.getGraphicsContext2D();
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
