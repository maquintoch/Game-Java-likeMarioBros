package inf112.skeleton.app;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class LevelFactory implements ILevelFactory {

    private Canvas canvas;

    public LevelFactory(Canvas canvas) {
        this.canvas = canvas;
    }

    public ArrayList<Tile> GetTiles() {
        String[] level = {
            "..............b.....",
            "......bb............",
            "....p......bbbb..b..",
            "..........b.........",
            "bbbbbbbbbbbbbbbbbbbb"
            };

        ArrayList<Tile> Tiles = new ArrayList<Tile>();
        var context = canvas.getGraphicsContext2D();
        for(var y = 0; y < level.length; y++) {
            for(var x = 0; x < level[y].length(); x++) {
                if(level[y].charAt(x) == 'b') {
                    System.out.println(canvas.getHeight());
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(20, 20);
                    var tile = new Tile(gridPosition, tileSize, context,canvas);
                    Tiles.add(tile);
                }
            }
        }
        return Tiles;
    }
}
