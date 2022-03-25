package inf112.skeleton.app.objects;

import javafx.scene.input.KeyCode;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Speed;
import inf112.skeleton.app.objects.attributes.Rectangle;

public class Player implements IPlayer {

    private Speed acceleration;
    private Speed speed;
    private IInputHandler inputHandler;
    private ArrayList<Tile> collideables;
    private CoinCollection coins;
    private CoinUI coinUI;
    private HealthUI healthUI;
    private ArrayList<Enemy> enemies;
    
    private IDrawBehavior drawHandler;
    protected Position position;
    protected Rectangle boundingBox;


    public Player(Canvas canvas) {
        this.position = new Position(3, 10);
        this.boundingBox = new Rectangle(16, 16);
        this.speed = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.5f);
    }
    
    
    public void setUp(ArrayList<Tile> collideables, ArrayList<Enemy> enemies, 
    		CoinCollection coins, CoinUI coinUI, HealthUI healthUI, IInputHandler inputHandler, 
    		IDrawBehavior drawHandler) {

        this.inputHandler = inputHandler;
        this.coins = coins;
        this.coinUI = coinUI;
        this.healthUI = healthUI;
        this.enemies = enemies;
        this.drawHandler = drawHandler;
        this.collideables = collideables;
    }
    
    

    @Override
    public void update() {
    	if(healthUI.currentHealth.getHealth() == 0) {
    		
    		//Avslutt spill	
    	}
    	
        if(inputHandler.isActive(KeyCode.W)) speed.velocityY = 4;
        if(inputHandler.isActive(KeyCode.A)) speed.velocityX = -1;
        else if(inputHandler.isActive(KeyCode.D)) speed.velocityX = 1;
        else speed.velocityX = 0;

        speed.velocityY += acceleration.velocityY;
        speed.velocityX += acceleration.velocityX;

        position.setX(position.getX() + speed.velocityX);
        for(Tile collidable : collideables) {
            if (getCollisionBox().overlap(collidable)) {
                position.setX(position.getX() - speed.velocityX);
                while(!overlap(collidable)) {
                	position.setX(position.getX() + Math.signum(speed.velocityX));
                }
                position.setX(position.getX() - Math.signum(speed.velocityX));
                speed.velocityX = 0;
                position.setX(collidable.getClosestXPosition(position));
            }
        }


        position.setY(position.getY() + speed.velocityY);
        for(Tile collidable : collideables) {
            if (getCollisionBox().overlap(collidable)) {
                position.setY(position.getY() - speed.velocityY);
                while(!overlap(collidable)) {
                	position.setY(position.getY() + Math.signum(speed.velocityY));
                }
                position.setY(position.getY() - Math.signum(speed.velocityY));
                speed.velocityY = 0;
                position.setY(collidable.getClosestYPosition(position));
            }
        }
        for(Enemy enemy : enemies) {
            if (getCollisionBox().overlap(enemy)) {
                position.setX(position.getX() - speed.velocityX);
                position.setY(position.getY() - speed.velocityY);
                healthUI.currentHealth.loseHealth();
                //position.x = enemy.GetClosestXPosition(position);
            }
        }
        for(Coin coin : coins.getAll()) {
            if (getCollisionBox().overlap(coin)) {
            	coinUI.currentscore.addOneToScore();;
            	coin.destroy();
            	        	
            }
        }
    }

    @Override
    public boolean overlap(IItem collidable) {
        var collisionBox = getCollisionBox();
        return collisionBox.overlap(collidable);
    }

    @Override
    public Speed getSpeed() {
        return speed;
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position edgePosition = new Position(this.position);
        edgePosition.Add(boundingBox);
        return new CollisionBox(position, edgePosition);
    }
    
    @Override
    public void draw() {
        drawHandler.draw(position, boundingBox);
    }

    @Override
    public Position getPosition(){ 
    	return position; 
    }

}
