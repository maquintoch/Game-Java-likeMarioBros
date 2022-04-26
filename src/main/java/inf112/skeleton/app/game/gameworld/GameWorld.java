package inf112.skeleton.app.game.gameworld;

import inf112.skeleton.app.game.LevelLoader;
import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.scene.canvas.Canvas;

import java.util.LinkedList;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.camera.*;
import inf112.skeleton.app.draw.*;

public class GameWorld implements GameWorldObserver, GameWorldSubject {
    private final LevelLoader levelLoader;
    private IInputHandler inputHandler;
    private Canvas canvas;
    private final LinkedList<IPlayersObserver> playerobservers = new LinkedList<IPlayersObserver>();

    private int score = 0;
    private int health = 10;

    private Background background;

    private Camera camera;

    private final LinkedList<ScoreObserver> scoreObservers = new LinkedList<ScoreObserver>();
    private final LinkedList<IHealthObserver> healthObservers = new LinkedList<IHealthObserver>();

    private final LinkedList<IGameObject> gameObjects = new LinkedList<IGameObject>();
    public LinkedList<IGameObject> getGameObjects() {
        return gameObjects;
    }


    public GameWorld(Canvas canvas, LevelLoader levelLoader, IInputHandler inputHandler) {

    	this.canvas = canvas;
        this.background = new Background(canvas);
        this.inputHandler = inputHandler;
        this.levelLoader = levelLoader;

    	this.camera = new Camera(canvas);

        Load(0);
    }

    public void draw() {
    	background.draw();
        gameObjects.forEach(drawable -> {drawable.draw(this);});
    }

    public void update() {
        gameObjects.forEach(updateable -> {updateable.update(this.gameObjects);});
    	camera.update();
    }

    public DrawImageBehavior getDrawImageBehavior() {
        return new DrawImageBehavior(canvas, camera);
    }

    @Override
    public void removeGameObject(IGameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    @Override
    public void addScore(int amount) {
        this.score += amount;
        this.scoreObservers.forEach(observer -> observer.setScore(this.score));
    }

    @Override
    public void addPlayer(Player player) {
        this.gameObjects.add(player);
    }

    @Override
    public void addScoreObserver(ScoreObserver observer) {
        this.scoreObservers.add(observer);
    }

    @Override
    public void addHealthObserver(IHealthObserver observer) {
        this.healthObservers.add(observer);
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public void Load(int levelIndex) {
        this.gameObjects.clear();
        this.score = 0;

        var factoryCollidables = levelLoader.getLevelGameObjects(levelIndex);
        gameObjects.addAll(factoryCollidables);

        var startPosition = new Position(0, 0);
        var player = new Player(startPosition, inputHandler);
        camera.setTargetEntity(player);

        gameObjects.add(player);

        gameObjects.forEach(gameObject -> gameObject.addGameWorldObserver(this));
        playerobservers.forEach(observer -> observer.addPlayer(player));
    }

    public void addPlayerObserver(IPlayersObserver observer){
        playerobservers.add(observer);


    }
}
