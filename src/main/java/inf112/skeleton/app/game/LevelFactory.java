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
            "..b..e...c.e...c.b.............",
            "..bbbbbbbbbbbbbbbb..bbbbbbb.bbb",
            "...............................",
            "..bbb......................bbb.",
            "...................b...........",
            "...........b.................b.",
            ".....c................c.........",
            ".....................bb..b.....",
            ".....bcb..........c......b.....",
            ".....bbb....e........bc.eb.....",
            "...........bbbb..c...bbbbb.....",
            "...............................",
            "............b..........bb......",
            "..bb..bb.......................",
            "...b..b.........bbbb....b......",
            "...b..e..ce.......b...e.c..e.b.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );
    public List<String> level2 = Arrays.asList(
            "...........................c...",
            ".........................eb....",
            "........................bbb....",
            "...............................",
            ".........bb..........b.........",
            "..b.........be...b..b..ec.b....",
            "..bbbbb...b.bbbbbb..bbbbbbb.bb.",
            "...............................",
            "..bbb.....................e..b.",
            "......................bbbbbbbb.",
            "...........b..........b......b.",
            "......................b..bec.b.",
            ".....b.c.ee..b........b..bbbbb.",
            ".....bbbbbbbbbb...c...b..b.....",
            ".b...bbb..............b.bbbbb..",
            ".b.........bbbb..c....b..b.....",
            ".b...........e.................",
            "........b...bbbbbbb............",
            "...b.ecb.......bb...........b..",
            "b...bbbb..bbbbb..bb....b....b..",
            "...b.e...c.e....ceb....ec..bbb.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );
    public List<String> level3 = Arrays.asList(
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
            "b...........e........e........b",
            "b.............................b",
            "b...............c.............b",
            "b........bb...................b",
            "b.be.............b..be..ceb...b",
            "b.bbbbb.............bbbbbbb..bb",
            "b.............................b",
            "b.bbb..........c..............b",
            "b.....bbb.....................b",
            "b..........b..c..............bb",
            "b....c................e.......b",
            "b....b.......b.......bb.......b",
            "b....bce.bbbbbb...c......be.e.b",
            "b....bbbb................bbbbbb",
            "b..........bbbb..........ee...b",
            "b.......b...c.eb....bb...bb...b",
            "b......bb...b.b..b...........b",
            "b.....bb....bcb.........b..ee.b",
            "b...bb......b.b.........bbbbbbb",
            "....b.....ee.........b..c.e...b",
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
