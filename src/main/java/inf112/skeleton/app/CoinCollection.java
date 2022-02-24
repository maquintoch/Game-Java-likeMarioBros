package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

public class CoinCollection implements IDrawable, IDestroyable, IWorldRemoveable<Coin>, IWorldGettable<Coin>, IWorldSettable<Coin>{
	
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

    @Override
    public void Draw() {
        for(Coin coin : coins.values()) {
            coin.Draw();
        }
    }

	@Override
	public Coin Get(GridPosition position) {
		return this.coins.get(position);
		
	}

	@Override
	public void Set(Coin coin) {
		coins.put(coin.getGridPosition(), coin);
		
	}

	@Override
	public void Remove(GridPosition position) {
		coins.remove(position);
		
	}

	@Override
	public void Remove(Coin object) {
		coins.remove(object.getGridPosition());
		
	}

	@Override
	public void destroy() {
		for(Coin coin : coins.values()) {
			coin.destroy();
		}
		
	}

}
