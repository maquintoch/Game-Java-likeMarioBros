package inf112.skeleton.app;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelFactory implements ILevelFactory {

    private Canvas canvas;
    
    List<String> level = Arrays.asList(
            "...............................",
            "...........ccc.................",
            "...............................",
            "................c..............",
            "...............................",
            "...............................",
            ".......bbbbbbbbbbb..bbbbbbb.bbb",
            "...............................",
            "..bbb..........cc..............",
            "...............................",
            "...........b..c..............bb",
            ".....c.........................",
            ".....................bb........",
            "..................c............",
            "...............................",
            "...........bbbb..c.............",
            "...............................",
            "...............................",
            "..bbbbbb.......................",
            "...b..b.........bbbb...........",
            "...............................",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
            );
    

    public LevelFactory(Canvas canvas) {
        this.canvas = canvas;
    }

    public ArrayList<Tile> GetTiles() {
        Collections.reverse(level);

        ArrayList<Tile> Tiles = new ArrayList<Tile>();
        var context = canvas.getGraphicsContext2D();
        for(var y = 0; y < level.size(); y++) {
            for(var x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'b') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var tile = new Tile(gridPosition, tileSize, context,canvas);
                    Tiles.add(tile);
                }
            }
        }
        return Tiles;
    }
    public ArrayList<Coin> GetCoins() {

        //Collections.reverse(level);

        ArrayList<Coin> Coins = new ArrayList<Coin>();
        var context = canvas.getGraphicsContext2D();
        for(var y = 0; y < level.size(); y++) {
            for(var x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'c') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var coin = new Coin(gridPosition, tileSize, context,canvas);
                    Coins.add(coin);
                }
            }
        }
        return Coins;
    }
}
