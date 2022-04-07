package inf112.skeleton.app.objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.camera.ICamera;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.DrawImageBehavior;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Speed;
import inf112.skeleton.app.objects.attributes.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;

public class PlayerSecond extends Player{
    private Speed acceleration;
    private Speed speed;
    private IInputHandler inputHandler;
    private ArrayList<Tile> collideables;
    private CoinCollection coins;
    private CoinUI coinUI;
    private HealthUI healthUI;
    private ArrayList<Enemy> enemies;
    private int right = 0; // how long does take one animation to right direction
    private int left = 0; // how long does take one animation to left direction
    private int level;
    
    private DrawImageBehavior imageHandler;
    private Canvas canvas;
    private ICamera camera;
    protected Position position;
    protected Rectangle boundingBox;
    private LocalTime timeSinceCollide;

    private Image[] imageRight = new Image[5]; // right images for different states
    private Image[] imageLeft = new Image[5]; // left images for different states

    
	public PlayerSecond(Canvas canvas) {
		super(canvas);
		this.position = new Position(20, 16);
        this.boundingBox = new Rectangle(14, 14);
        this.speed = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.5f);
        this.level = 0;
        this.canvas = canvas;
        setPlayerImage();	
	}
	
	@Override
    public void setUp(ArrayList<Tile> collideables, ArrayList<Enemy> enemies, 
    		CoinCollection coins, CoinUI coinUI, HealthUI healthUI, IInputHandler inputHandler, 
    		ICamera camera) {

        this.inputHandler = inputHandler;
        this.coins = coins;
        this.coinUI = coinUI;
        this.healthUI = healthUI;
        this.enemies = enemies;
        this.collideables = collideables;
        this.camera = camera;
    }

	private void setPlayerImage() {
		try {
	        for (int k = 0; k < 5; k++) {
	        	// change the file path if needed
	    		FileInputStream inputMarioRight = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/player2/luigiRight" + k + "Lvl" + level + ".png");
	    		FileInputStream inputMarioLeft = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/player2/luigiLeft" + k + "Lvl" + level + ".png");	
	            imageRight[k] = new Image(inputMarioRight);
	            imageLeft[k] = new Image(inputMarioLeft);
	        } 
		}
		catch(FileNotFoundException  e) {
			System.out.println("Player image not found!");
			e.printStackTrace();
		}
	}

	@Override
    public Image getImage() {
        if (right < 10)
            return imageRight[0];
        else if (right < 20)
            return imageRight[1];
        else if (right < 30)
            return imageRight[2];
        else if (right < 40)
            return imageRight[3];
        else if (right < 50)
            return imageRight[4];
        else if (left < 10)
            return imageLeft[0];
        else if (left < 20)
            return imageLeft[1];
        else if (left < 30)
            return imageLeft[2];
        else if (left < 40)
            return imageLeft[3];
        else
            return imageLeft[4];
    }
	
    @Override
    public void update() {
    	if (position.getY() < -200) healthUI.currentHealth.setHealth(0);

//    	if(healthUI.currentHealth.getHealth() == 0) {		
//    		//Avslutt spill	
//    	}

        if(inputHandler.isActive(KeyCode.UP) && (speed.velocityY == 0) 
        		&& (timeSinceCollide.plusNanos(90000000).isAfter(LocalTime.now()))){
            speed.velocityY = 8;
        }
        if(inputHandler.isActive(KeyCode.LEFT)) {
        	speed.velocityX = -1;
        	right = 50;
        	left++;
        	if(left>=50) left=10;
        }
        else if(inputHandler.isActive(KeyCode.RIGHT)) {
        	speed.velocityX = 1;
        	speed.velocityX = 1;
        	left = 50;
        	right++;
        	if(right>=50) right=10;
        }
        else {
            if (left == 50) // last move was in right side
                right = 0;
            else // last move was in left side
                left = 0;
        	speed.velocityX = 0;
        }

        speed.velocityY += acceleration.velocityY;
        speed.velocityX += acceleration.velocityX;

        position.setX(position.getX() + speed.velocityX);
        for(Tile collidable : collideables) {
            if (getCollisionBox().overlap(collidable)) {
                timeSinceCollide = LocalTime.now();
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
                timeSinceCollide = LocalTime.now();
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
    	imageHandler = new DrawImageBehavior(canvas, camera);
    	imageHandler.draw(position, boundingBox, getImage());
   }

    @Override
    public Position getPosition(){ 
    	return position; 
    }
}
    
    
    