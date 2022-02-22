package inf112.skeleton.app;

import java.util.ArrayList;

public class CoinCollection implements IDrawable {
	
	private final ArrayList<Coin> coins;

    public CoinCollection(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    @Override
    public void Draw() {
        for(Coin coin : coins) {
            coin.Draw();
        }
    }

}
