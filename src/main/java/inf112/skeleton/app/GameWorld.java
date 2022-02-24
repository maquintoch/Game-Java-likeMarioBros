package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collection;

public class GameWorld implements IDrawable, IUpdateable, IWorldRemoveable<Coin> {

    private Collection<IDrawable> drawPipeline;
    private Collection<IUpdateable> updatePipeline;
    private CoinCollection coins;
    //private Collection<IWorldRemoveable<IDestroyable>> destroyPipeline;

    public GameWorld(Canvas canvas, IInputHandler inputHandler) {
        var levelFactory = new LevelFactory(canvas, this);
        var factoryTiles = levelFactory.GetTiles();
        var factoryCoins = levelFactory.GetCoins();
        var Tiles = new TileCollection(factoryTiles);
        this.coins = new CoinCollection(factoryCoins);
        var backgroundDrawService = new GameBackgroundDrawService(canvas);
        var coinUI = new CoinUIService(canvas.getGraphicsContext2D());
        

        var player = new PlayerEntity(canvas, factoryTiles, coins, coinUI, inputHandler);

        drawPipeline = new ArrayList<IDrawable>();
        updatePipeline = new ArrayList<IUpdateable>();
        

        drawPipeline.add(backgroundDrawService);
        drawPipeline.add(Tiles);
        drawPipeline.add(coins);
        drawPipeline.add(coinUI);
        drawPipeline.add(player);

        updatePipeline.add(player);
        
        //destroyPipeline.add(Coins);
        
        //Coins.Remove(new GridPosition(12, 20));
    }

    @Override
    public void Draw() {
        for(IDrawable drawable : drawPipeline) {
            drawable.Draw();
        }
    }

    @Override
    public void Update() {
        for(IUpdateable updateable : updatePipeline) {
            updateable.Update();
        }
    }

	@Override
	public void Remove(GridPosition position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Remove(Coin coin) {
		this.coins.Remove(coin);
		
		
	}
}
