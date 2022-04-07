package inf112.skeleton.app.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import inf112.skeleton.app.draw.DrawImageBehavior;
import inf112.skeleton.app.objects.Coin;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.GridPosition;
import inf112.skeleton.app.objects.attributes.TileSize;

public class LevelFactory{

    private Canvas canvas;
    private DrawImageBehavior coinDrawBehavior;
    private DrawImageBehavior tileDrawBehavior;
    public List<List<String>> levels = new ArrayList<>();

    
    public List<String> level1 = Arrays.asList(
            "...............................",
            "...............................",
            "...............................",
            "...............................",
            ".........bb....................",
            "..b..e.c...e.....b....e.c....e.",
            "..bbbbbbbbbbbbbbbb..bbbbbbb.bbb",
            "...............................",
            "..bbb..........c...............",
            "...............................",
            "...........b..c..............bb",
            ".....c.........................",
            ".....................bb........",
            ".....bcb..........c............",
            ".....bbb....e..................",
            "...........bbbb..c.............",
            "...............................",
            "............b..................",
            "..bb..bb.......................",
            "...b..b.........bbbb....b......",
            "...b..e..ce.......b...e.c..e.b.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );
    public List<String> level2 = Arrays.asList(
            "...............................",
            "...............................",
            "...............................",
            "................c..............",
            ".........bb....................",
            "..b..............b.............",
            "..bbbbb...bbbbbbbb..bbbbbbb.bb.",
            "...............................",
            "..bbb..........cc............b.",
            "......................bbbbbbbb.",
            "...........b..c.......b......b.",
            ".....c................b..bec.b.",
            ".....b.c.ee..b........b..bbbbb.",
            ".....bbbbbbbbbb...c...b..b.....",
            ".b...bbb..............b..bbbb..",
            ".b.........bbbb..c....b..b.....",
            ".b..........c..................",
            "........b...bbbbbbb............",
            "...b.ecb.......bb...........b..",
            "b...bbbb..bbbbb..bb....b....b..",
            "...b.e...c.e....ceb....ec..bbb.",
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
            "b....bce.bbbbbb...c...........b",
            "be...bbbb.................bbb.b",
            "b..........bbbb..c............b",
            "b.......b...c.................b",
            "b......bb.....................b",
            "b.....bb......b.........b.....b",
            "b...bb.......bb.........bbbbbbb",
            "....b................b..c.e...b",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );

    public LevelFactory(Canvas canvas, DrawImageBehavior coinDrawBehavior, DrawImageBehavior tileDrawBehavior) {
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

    public ArrayList<GridPosition> getEnemies(List<String> level) {
        ArrayList<GridPosition> enemies = new ArrayList<GridPosition>();
        for(int y = 0; y < level.size(); y++) {
            for(int x = 0; x < level.get(y).length(); x++) {
                if(level.get(y).charAt(x) == 'e') {
                    var gridPosition = new GridPosition(x, y);
                    enemies.add(gridPosition);
                }
            }
        }
        return enemies;
    }
}
