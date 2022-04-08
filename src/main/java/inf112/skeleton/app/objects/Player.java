package inf112.skeleton.app.objects;

import javafx.scene.input.KeyCode;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.nio.file.Paths;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;

public class Player implements IPlayer {
    private Speed acceleration;
    private Speed speed;
    private IInputHandler inputHandler;
    private ArrayList<Tile> collideables;
    private CoinCollection coins;
    private CoinUI coinUI;
    private HealthUI healthUI;
    private ArrayList<Enemy> enemies;
    
    private DrawImageBehavior imageHandler;
    private Canvas canvas;
    private ICamera camera;
    protected Position position;
    protected Rectangle boundingBox;
    private LocalTime timeSinceCollide;
    private int level;
    private int right = 0; // how long does take one animation to right direction
    private int left = 0; // how long does take one animation to left direction
    
    private Image[] imageRight = new Image[5]; // right images for different states
    private Image[] imageLeft = new Image[5]; // left images for different states
    private Image imageBlank;
    private LocalTime invinsibilityTime = LocalTime.MAX;
    private boolean isShowingInvinsibilityFrame = false;
    private MediaPlayer hitHurtMediaPlayer;
    private MediaPlayer jumpMediaPlayer;
    private MediaPlayer pickupCoinMediaPlayer;
    private boolean isStanding;

    public Player(Canvas canvas) {
        this.position = new Position(3, 10);
        this.boundingBox = new Rectangle(14, 14);
        this.speed = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.5f);
        this.level = 0;
        this.canvas = canvas;
        setPlayerImage();
    }
       
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


        Media hitHurtUri = new Media(Paths.get("src/main/java/inf112/skeleton/app/assets/audio/hitHurt.wav").toUri().toString());
        Media jumpUri = new Media(Paths.get("src/main/java/inf112/skeleton/app/assets/audio/jump.wav").toUri().toString());
        Media pickupCoinUri = new Media(Paths.get("src/main/java/inf112/skeleton/app/assets/audio/pickupCoin.wav").toUri().toString());
        hitHurtMediaPlayer = new MediaPlayer(hitHurtUri);
        jumpMediaPlayer = new MediaPlayer(jumpUri);
        pickupCoinMediaPlayer = new MediaPlayer(pickupCoinUri);
    }

    private void setPlayerImage() {
		try {
	        for (int k = 0; k < 5; k++) {
	        	// change the file path if needed
	    		FileInputStream inputMarioRight = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/player1/marioRight" + k + "Lvl" + level + ".png");
	    		FileInputStream inputMarioLeft = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/player1/marioLeft" + k + "Lvl" + level + ".png");
                FileInputStream inputblank = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/blank.png");
                imageRight[k] = new Image(inputMarioRight);
	            imageLeft[k] = new Image(inputMarioLeft);
                imageBlank = new Image(inputblank);
	        }
		}
		catch(FileNotFoundException  e) {
			System.out.println("Player image not found!");
			e.printStackTrace();
		}
	}
    
    public Image getImage() {

        if(isInvinsible() && !isShowingInvinsibilityFrame) {
            isShowingInvinsibilityFrame = true;
            return imageBlank;
        }
        isShowingInvinsibilityFrame = false;

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
    	
    	if(healthUI.currentHealth.getHealth() == 0) {
    		//Avslutt spill	
    	}

        if(inputHandler.isActive(KeyCode.W) && isStanding){
            jumpMediaPlayer.play();
            jumpMediaPlayer.seek(jumpMediaPlayer.getStartTime());
            speed.velocityY = 10;
            isStanding = false;
        }

        if(inputHandler.isActive(KeyCode.A)) {
        	speed.velocityX = -1;
        	right = 50;
        	left++;
        	if(left>=50) left=10;
        }
        else if(inputHandler.isActive(KeyCode.D)) {
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
                if(position.getY() > collidable.getPosition().getY()) {
                    isStanding = true;
                }
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
                if(isFalling()) {
                    enemy.destroy();
                } else if(!isInvinsible()) {
                    hitHurtMediaPlayer.play();
                    hitHurtMediaPlayer.seek(hitHurtMediaPlayer.getStartTime());
                    healthUI.currentHealth.loseHealth();
                    invinsibilityTime = LocalTime.now();
                }
            }
        }
        for(Coin coin : coins.getAll()) {
            if (getCollisionBox().overlap(coin)) {
                pickupCoinMediaPlayer.play();
                pickupCoinMediaPlayer.seek(pickupCoinMediaPlayer.getStartTime());
            	coinUI.currentscore.addOneToScore();;
            	coin.destroy();
            	        	
            }
        }
    }

    private boolean isInvinsible() {
        return invinsibilityTime.plusSeconds(2).isAfter(LocalTime.now());
    }

    private boolean isFalling() {
        return speed.velocityY < 0;
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
