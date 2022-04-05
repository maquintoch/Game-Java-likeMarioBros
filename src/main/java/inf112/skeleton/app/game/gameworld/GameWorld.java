package inf112.skeleton.app.game.gameworld;

import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.camera.*;
import inf112.skeleton.app.draw.*;
import inf112.skeleton.app.game.LevelFactory;
import inf112.skeleton.app.objects.*;
import inf112.skeleton.app.objects.attributes.GridPosition;

public class GameWorld implements IGameWorld<Coin> {
    private List<IPlayer> items = new ArrayList<IPlayer>(); 
    
    private Background background;
    private TileCollection tiles;
    private CoinCollection coins;
    private CoinUI coinUI;
    private HealthUI healthUI;
    private HealthUI healthUI2;  // health for player 2
    private Camera camera;

    public GameWorld(Canvas canvas, IInputHandler inputHandler, HealthUI healthUI, HealthUI healthUI2, CoinUI coinUI, int levelCount) {
    	
    	this.camera = new Camera(canvas);
    	var CoinDrawBehavior = new DrawImageBehavior(canvas, camera);
    	var tileDrawBehavior = new DrawImageBehavior(canvas, camera);
    	var enemyDrawBehavior = new DrawColorBehavior(canvas, Color.RED, camera);
        var levelFactory = new LevelFactory(canvas, CoinDrawBehavior, tileDrawBehavior);
        
        var factoryTiles = levelFactory.getTiles(levelFactory.levels.get(levelCount));
        var factoryCoins = levelFactory.getCoins(levelFactory.levels.get(levelCount));
        
        this.tiles = new TileCollection(factoryTiles);
        this.coins = new CoinCollection(factoryCoins);
        this.background = new Background(canvas);
        this.coinUI = coinUI;
        this.healthUI = healthUI;  //Player.health
        this.healthUI2 = healthUI2;  //Player2.health
        
        //Adding enemies to the game:
        var enemy1 = new Enemy(canvas, factoryTiles, enemyDrawBehavior, 100, 10);
        var enemy2 = new Enemy(canvas, factoryTiles, enemyDrawBehavior, 100, 400);
        var enemy3 = new Enemy(canvas, factoryTiles, enemyDrawBehavior, 400, 200);
        var enemies = new ArrayList<Enemy>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        //Adding player:
        Player player = new Player(canvas);
        player.setUp(factoryTiles, enemies, coins, coinUI, healthUI, inputHandler, camera);
        camera.setTargetEntity(player);
          
        PlayerSecond player2 = new PlayerSecond(canvas);
        player2.setUp(factoryTiles, enemies, coins, coinUI, healthUI2, inputHandler, camera);
        camera.setTargetEntity(player2);
        
        items.add(player);
        items.add(player2);
        for(Enemy enemy : enemies) {
        	items.add(enemy);
        }
    }

    public void draw() {
    	background.draw();
    	tiles.draw();
    	coins.draw();
    	coinUI.draw();
    	healthUI.draw(0, 0);
    	healthUI2.draw(400, 0);
    	for (IPlayer item : items) item.draw();
    }

    public void update() {
    	for (IPlayer item : items) item.update();
    	camera.update();
    }

	@Override
	public void remove(GridPosition position) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(Coin coin) {
		this.coins.remove(coin);		
	}

	@Override
	public Coin get(GridPosition position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(Coin object) {
		// TODO Auto-generated method stub
	}
}
