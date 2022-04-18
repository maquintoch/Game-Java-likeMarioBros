package inf112.skeleton.app.game.gameworld;

import inf112.skeleton.app.objects.IEntity;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.ICollidable;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.List;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.camera.*;
import inf112.skeleton.app.draw.*;
import inf112.skeleton.app.game.Level;

public class GameWorld {
    private IInputHandler inputHandler;
    private Canvas canvas;


    private final ArrayList<IEntity> entities = new ArrayList<IEntity>();
    private final ArrayList<ICollidable> collidables = new ArrayList<ICollidable>();

    private Background background;
    private CoinUI coinUI;
    private HealthUI healthUI;
    private Camera camera;

    public List<IEntity> getEntities() {
        return entities;
    }
    public ArrayList<ICollidable> getCollidables() {
        return collidables;
    }
    public void removeEntity(IEntity entity) { entities.remove(entity); }
    public void removeCollidable(ICollidable entity) { collidables.remove(entity); }

    public GameWorld(Canvas canvas, IInputHandler inputHandler, HealthUI healthUI, CoinUI coinUI, int levelCount) {

    	this.canvas = canvas;
        this.background = new Background(canvas);
        this.coinUI = coinUI;
        this.healthUI = healthUI;
        this.inputHandler = inputHandler;

    	this.camera = new Camera(canvas);
        var levelFactory = new Level(this, levelCount);
        var factoryCollidables = levelFactory.getCollidables();
        var factoryEntities = levelFactory.getEntities();
        collidables.addAll(factoryCollidables);
        entities.addAll(factoryEntities);


        var player = new Player(this, 0, 0);
        camera.setTargetEntity(player);

        collidables.add(player);
        entities.add(player);
    }

    public void draw() {
    	background.draw();
    	coinUI.draw();
    	healthUI.draw(0, 0);
        for(ICollidable collidable : collidables) {
           collidable.draw();
        }
    }

    public void update() {
    	for (IEntity item : entities) item.update();
    	camera.update();
    }

    public void addScore(int points) {
       this.coinUI.currentscore.addScore(points);
    }

    public void addHealth(int points) {
        this.healthUI.currentHealth.addHealth(points);
    }

    public void setHealth(int points) {
        this.healthUI.currentHealth.setHealth(points);
    }

    public DrawImageBehavior getDrawImageBehavior() {
        return new DrawImageBehavior(canvas, camera);
    }

    public IInputHandler getInputHandler() {
        return inputHandler;
    }
}
