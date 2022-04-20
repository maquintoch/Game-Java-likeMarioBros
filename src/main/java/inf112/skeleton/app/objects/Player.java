package inf112.skeleton.app.objects;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Player extends BaseCollidableTile implements IEntity {
    private Speed acceleration;
    private Speed speed;

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
    public boolean isStanding;

    public Player(GameWorld gameWorld, int xPosition, int yPosition) {
        super(gameWorld, xPosition, yPosition);
        this.speed = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.2f);

        this.boundingBox = new Rectangle(14, 14);

        setPlayerImage();

        Media hitHurtUri = new Media(Paths.get("src/main/java/inf112/skeleton/app/assets/audio/hitHurt.wav").toUri().toString());
        Media jumpUri = new Media(Paths.get("src/main/java/inf112/skeleton/app/assets/audio/jump.wav").toUri().toString());
        hitHurtMediaPlayer = new MediaPlayer(hitHurtUri);
        jumpMediaPlayer = new MediaPlayer(jumpUri);
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

    public void checkKeyCode(IInputHandler inputHandler){
        if(inputHandler.isActive(KeyCode.W) && isStanding){
            jump();
        }
        if(inputHandler.isActive(KeyCode.A)){
            moveLeft();
        }
        else if(inputHandler.isActive(KeyCode.D)){
            moveRight();
        }
        else{
            if (left == 50) // last move was in right side
                right = 0;
            else // last move was in left side
                left = 0;
            speed.velocityX = 0;

        }
    }

    public void moveRight() {
        speed.velocityX = 2;
        left = 50;
        right++;
        if(right>=50) right=10;
    }

    public void moveLeft() {
        speed.velocityX = -2;
        right = 50;
        left++;
        if(left>=50) left=10;

    }

    public void jump() {
        jumpMediaPlayer.play();
        jumpMediaPlayer.seek(jumpMediaPlayer.getStartTime());
        speed.velocityY = 7;
        isStanding = false;
    }

    @Override
    public void update() {

    	if (position.getY() < -200) gameWorld.setHealth(0); //If player falls off map set health to 0.

        var inputHandler = gameWorld.getInputHandler();
        checkKeyCode(inputHandler); //Move player based on keycode pressed.

        speed.velocityY += acceleration.velocityY;
        speed.velocityX += acceleration.velocityX;

        var collidables = gameWorld.getCollidables();

        //Check if player collide with tiles:
        position.setX(position.getX() + speed.velocityX);
        tileCollisionX(collidables);
        position.setY(position.getY() + speed.velocityY);
        tileCollisionY(collidables);

        //Check if player collide with another item (enemy, coin or trampoline):
        itemCollision(collidables);
    }

    public void tileCollisionX(ArrayList<ICollidable> collidables){
        for(ICollidable collidable : collidables) {
            if (collidable.getItemType() != ItemType.Tile) continue;
            if (getCollisionBox().overlap(collidable)) {
                timeSinceCollide = LocalTime.now();
                position.setX(position.getX() - speed.velocityX);
                while(!overlap(collidable)) {
                    position.setX(position.getX() + Math.signum(speed.velocityX));
                }
                position.setX(position.getX() - Math.signum(speed.velocityX));
                speed.velocityX = 0;

                position.setX(getClosestXPosition(position));
            }
        }
    }
    public void tileCollisionY(ArrayList<ICollidable> collidables){
        for(ICollidable collidable : collidables) {
            if (collidable.getItemType() != ItemType.Tile) continue;
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
                position.setY(getClosestYPosition(position));
            }
        }

    }
    public void itemCollision(ArrayList<ICollidable> collidables){
        for(ICollidable collidable : collidables) {
            if (!getCollisionBox().overlap(collidable)) continue;
            if (collidable.getItemType() == ItemType.Enemy) {
                if (isFalling()) {
                    collidable.collide(ItemType.Player);
                    speed.velocityY = 10;
                } else if (!isInvinsible()) {
                    this.collide(ItemType.Enemy);
                }
            }
            if (collidable.getItemType() == ItemType.Coin) {
                gameWorld.addScore(1);
                collidable.collide(ItemType.Player);
            }
            if (collidable.getItemType() == ItemType.Trampoline) {
                this.speed.velocityY += 5;
                collidable.collide(ItemType.Player);
            }
        }
    }

    private boolean isInvinsible() {
        return invinsibilityTime.plusSeconds(2).isAfter(LocalTime.now());
    }

    private boolean isFalling() {
        return speed.velocityY < 0;
    }

    public void draw() {
        var imageHandler = gameWorld.getDrawImageBehavior();
        imageHandler.draw(position, boundingBox, getImage());
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
    public ItemType getItemType() {
        return ItemType.Player;
    }

    @Override
    public void collide(ItemType itemType) {
        hitHurtMediaPlayer.play();
        hitHurtMediaPlayer.seek(hitHurtMediaPlayer.getStartTime());
        gameWorld.addHealth(-1);
        invinsibilityTime = LocalTime.now();
    }
}
