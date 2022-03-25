package inf112.skeleton.app.objects;

import java.util.ArrayList;

import java.util.HashMap;

import inf112.skeleton.app.game.gameworld.IGameWorld;
import inf112.skeleton.app.objects.attributes.GridPosition;

import java.util.Collection;

public class CoinCollection implements IGameWorld<Coin>{
	private final HashMap<GridPosition, Coin> coins;

    public CoinCollection(ArrayList<Coin> coins) {		
        this.coins = new HashMap<GridPosition, Coin>();
        for(Coin coin : coins) {
            this.coins.put(coin.getGridPosition(), coin);
        }
    }
    
    public Collection<Coin> getAll(){
    	return coins.values();
    }

    public void draw() {
        for(Coin coin : coins.values()) {
            coin.draw();
        }
    }

	@Override
	public Coin get(GridPosition position) {
		return this.coins.get(position);
		
	}

	@Override
	public void set(Coin coin) {
		coins.put(coin.getGridPosition(), coin);
		
	}

	@Override
	public void remove(GridPosition position) {
		coins.remove(position);
		
	}

	@Override
	public void remove(Coin object) {
		coins.remove(object.getGridPosition());
		
	}

	public void destroy() {
		for(Coin coin : coins.values()) {
			coin.destroy();
		}
	}
}
