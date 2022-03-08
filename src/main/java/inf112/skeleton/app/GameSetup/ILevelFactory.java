package inf112.skeleton.app.GameSetup;

import java.util.ArrayList;

import inf112.skeleton.app.GameWorld.Tiles.Coin;
import inf112.skeleton.app.GameWorld.Tiles.Tile;



public interface ILevelFactory {
	
    ArrayList<Tile> GetTiles();
    
    ArrayList<Coin> GetCoins();
}
