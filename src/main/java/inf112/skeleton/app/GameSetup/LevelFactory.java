package inf112.skeleton.app.GameSetup;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.GridPosition;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.TileSize;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import javafx.scene.Scene;
import java.lang.StringBuilder;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;

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
    
    public List<String> level = Arrays.asList(
            "...............................",
            "...........ccc.................",
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
    

    public LevelFactory(Canvas canvas, GameWorld gameWorld,IDrawBehavior coinDrawBehavior, IDrawBehavior tileDrawBehavior) {
        this.canvas = canvas;
        this.gameWorld = gameWorld;
        this.coinDrawBehavior = coinDrawBehavior;
        this.tileDrawBehavior = tileDrawBehavior;
    }
    
    public List<String> removeCoin(List<String> inputLevel, int y, int x){
    	List<String> newlevel = Arrays.asList(
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

    	int i = 0;
    	for(String s : inputLevel) {
    		if(i == y && s.charAt(x) == 'c') {
    			
    			StringBuilder sb = new StringBuilder(s);
    			
    			sb.setCharAt(x, '.');
    			String newString = sb.toString();
    			newlevel.set(i, newString);
    			i++;
    		
    		}
    		else {
    			newlevel.set(i, s);
    			i++;
    			
    		}
    		
    	
    	}
    	//Collections.reverse(newlevel);
    	return newlevel;
    	
    	
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
                    var tile = new Tile(gridPosition, tileSize, context,canvas, tileDrawBehavior);
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
                    var coin = new Coin(gameWorld, gridPosition, tileSize, coinDrawBehavior);
                    Coins.add(coin);
                }
            }
        }
        return Coins;
    }
}
