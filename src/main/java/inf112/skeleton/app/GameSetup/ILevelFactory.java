package inf112.skeleton.app.GameSetup;

import java.util.ArrayList;
import java.util.List;

import inf112.skeleton.app.GameWorld.Tiles.Coin;
import inf112.skeleton.app.GameWorld.Tiles.Tile;



public interface ILevelFactory {
	
    ArrayList<Tile> GetTiles(List<String> level);
    
    ArrayList<Coin> GetCoins(List<String> level);
}
