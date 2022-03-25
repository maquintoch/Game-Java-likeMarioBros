package inf112.skeleton.app.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.objects.Coin;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.GridPosition;
import inf112.skeleton.app.objects.attributes.TileSize;

public class LevelFactory{

    private Canvas canvas;
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
            "................c..............",
            ".........bb....................",
            "..b..............b.............",
            "..bbbbb...bbbbbbbb..bbbbbbb.bbb",
            "...............................",
            "..bbb..........cc..............",
            "...............................",
            "...........b..c..............bb",
            ".....c.........................",
            ".....b.......b.......bb........",
            ".....bcbbbbbbbb...c............",
            ".b...bbb..................bbb..",
            ".b.........bbbb..c.............",
            ".b..........c..................",
            "..b.....b...bbbbbbb............",
            "..bb...b....................b..",
            ".....bbb........bbbb....b...b..",
            "...bbbb..c........b.....c..bbb.",
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

    public LevelFactory(Canvas canvas, IDrawBehavior coinDrawBehavior, IDrawBehavior tileDrawBehavior) {
        this.canvas = canvas;
        this.coinDrawBehavior = coinDrawBehavior;
        this.tileDrawBehavior = tileDrawBehavior;
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }

    public ArrayList<Tile> getTiles(List<String> level) {
        Collections.reverse(level);

        ArrayList<Tile> Tiles = new ArrayList<Tile>();
        GraphicsContext context = canvas.getGraphicsContext2D();
        for(int y = 0; y < level.size(); y++) {
            for(int x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'b') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var tile = new Tile(gridPosition, tileSize, context, tileDrawBehavior);
                    Tiles.add(tile);
                }
            }
        }
        return Tiles;
    }
    public ArrayList<Coin> getCoins(List<String> level) {
        ArrayList<Coin> Coins = new ArrayList<Coin>();
        for(int y = 0; y < level.size(); y++) {
            for(int x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'c') {
                    var gridPosition = new GridPosition(x, y);
                    var tileSize = new TileSize(16, 16);
                    var coin = new Coin(gridPosition, tileSize, coinDrawBehavior);
                    Coins.add(coin);
                }
            }
        }
        return Coins;
    }
}