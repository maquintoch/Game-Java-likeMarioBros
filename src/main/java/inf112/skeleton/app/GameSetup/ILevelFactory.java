package inf112.skeleton.app.GameSetup;

import java.util.ArrayList;

import inf112.skeleton.app.Tiles.Coin;
import inf112.skeleton.app.Tiles.Tile;

public interface ILevelFactory {
	
    ArrayList<Tile> GetTiles();
    
    ArrayList<Coin> GetCoins();
}
