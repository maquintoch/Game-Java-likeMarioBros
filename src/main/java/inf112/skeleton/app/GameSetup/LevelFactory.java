package inf112.skeleton.app.GameSetup;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import inf112.skeleton.app.DrawBehavior.IDrawBehavior;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.TileSize;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.GridPosition;
import inf112.skeleton.app.GameWorld.GameWorld;
import inf112.skeleton.app.GameWorld.Tiles.Coin;
import inf112.skeleton.app.GameWorld.Tiles.Tile;

//import com.sun.javafx.collections.FloatArraySyncer;

public class LevelFactory implements ILevelFactory {

    private Canvas canvas;
    private GameWorld gameWorld;
    private IDrawBehavior coinDrawBehavior;
    private IDrawBehavior tileDrawBehavior;
    public List<List<String>> levels = new ArrayList<>();

    
    public List<String> level1 = Arrays.asList(
            "...............................",
            ".............c.................",
            "...............................",
            "................c..............",
            ".........bb....................",
            "..b..............b.............",
            "..bbbbbbbbbbbbbbbb..bbbbbbb.bbb",
            "...............................",
            "..bbb..........cc..............",
            "...............................",
            "...........b..c..............bb",
            ".....c.........................",
            ".....................bb........",
            ".....bcb..........c............",
            ".....bbb.......................",
            "...........bbbb..c.............",
            "...............................",
            "............b..................",
            "..bb..bb.......................",
            "...b..b.........bbbb....b......",
            "...b.....c........b.....c....b.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
            );
    public List<String> level2 = Arrays.asList(
            "...............................",
            "...............................",
            "...............................",
            "...............................",
            ".........bb..............c.....",
            "..b.......b......b..b.........b",
            "..bbbbb...bbbbbbbb..bbbbbbbbbbb",
            "...............................",
            "..bbb..........cc..............",
            "...............................",
            "...........b..c..............bb",
            ".....c.........................",
            ".....b..c....b.......bb........",
            ".....bbbbbbbbbb...c............",
            ".b...bbb..................bbb..",
            ".b.........bbbb..c.............",
            ".b..........c..................",
            "..b.....b...bbbbbbb............",
            "..bb...b....................b..",
            ".....b..........bbbb....b...b..",
            "...b.....c........b.....c..bbb.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );
    public List<String> level3 = Arrays.asList(
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
            "b.............................b",
            "b.............................b",
            "b...............c.............b",
            "b........bb...................b",
            "b.b..............b............b",
            "b.bbbbb.............bbbbbbb.bbb",
            "b.............................b",
            "b.bbb..........cc.............b",
            "b.....bbb.....................b",
            "b..........b..c..............bb",
            "b....c........................b",
            "b....b.......b.......bb.......b",
            "b....bcbbbbbbbb...c...........b",
            "bb...bbb..................bbb.b",
            "bb.........bbbb..c............b",
            "bb......b...c.................b",
            "b.b....bb.....................b",
            "b.b...bb......b.........b...bbb",
            "b...bb.......bb..........bbbbbb",
            "....b....c..............c.....b",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );


    

    public LevelFactory(Canvas canvas, GameWorld gameWorld,IDrawBehavior coinDrawBehavior, IDrawBehavior tileDrawBehavior) {
        this.canvas = canvas;
        this.gameWorld = gameWorld;
        this.coinDrawBehavior = coinDrawBehavior;
        this.tileDrawBehavior = tileDrawBehavior;
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }

    public ArrayList<Tile> GetTiles(List<String> level) {
        Collections.reverse(level);

        ArrayList<Tile> Tiles = new ArrayList<Tile>();
        var context = canvas.getGraphicsContext2D();
        for(var y = 0; y < level.size(); y++) {
            for(var x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'b') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var tile = new Tile(gridPosition, tileSize, context,canvas, tileDrawBehavior);
                    Tiles.add(tile);
                }
            }
        }
        return Tiles;
    }
    public ArrayList<Coin> GetCoins(List<String> level) {

        //Collections.reverse(level);

        ArrayList<Coin> Coins = new ArrayList<Coin>();
        var context = canvas.getGraphicsContext2D();
        for(var y = 0; y < level.size(); y++) {
            for(var x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'c') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var coin = new Coin(gameWorld, gridPosition, tileSize, coinDrawBehavior);
                    Coins.add(coin);
                }
            }
        }
        return Coins;
    }
}
