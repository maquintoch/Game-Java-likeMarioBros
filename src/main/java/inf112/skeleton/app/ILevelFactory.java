package inf112.skeleton.app;

import java.util.ArrayList;

public interface ILevelFactory {
	
    ArrayList<Tile> GetTiles();
    
    ArrayList<Coin> GetCoins();
}
