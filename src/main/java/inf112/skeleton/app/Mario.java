package inf112.skeleton.app.entity;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Mario extends Entity {
	
	// height of the ground, change as needed
    private double ground;
    // length Mario can move with one step, change as needed
    private double moveLength = 10;
    // height Mario can jump with one jump, change as needed
    private double jumpHeight = 10;
    // time of one animation, change as needed
    private int left= 0;
    private int right = 0;
    
    private Image[] imageRight = new Image[5];
    private Image[] imageLeft = new Image[5];
    
    private boolean gameOver;
    private int level;
    
    private int coins;

	public Mario(GraphicsContext context) {
		super(context);
		MarioFigure();
	}

    public void MarioFigure() {
        this.level = 0;
        for (int k = 0; k < 5; k++) {
        	// change the path as needed
            imageRight[k] = new Image(("inf112/skeleton/app/marioImages/marioRight" + k + "Lvl" + level + ".png"));
            imageLeft[k] = new Image(("inf112/skeleton/app/marioImages/marioLeft" + k + "Lvl" + level + ".png"));
        }
        
        // set beginning position of Mario, change x, y as needed
        setGround(20);
        setPosition(50, ground);
 
        setJumping(false);
        setFalling(false);
        setGameOver(false);
    }

	@Override
	public void Update() {
		// TODO Auto-generated method stub
	}

    public double getMoveLength() {
        return moveLength;
    }
   
    public double getJumpHeight() {
        return jumpHeight;
    }
    
    public double getGround(int ground) {
        return this.ground;
    }
    
    public void setGround(double ground) {
        this.ground = ground;
    }
    
    public boolean getGameOver() {
        return gameOver;
    }
    
    public void setGameOver(Boolean b) {
        this.gameOver = b;
    }

    private Image getImage() {
        if (left < 10) return imageLeft[0];
        else if (left < 20) return imageLeft[1];
        else if (left < 30) return imageLeft[2];
        else if (left < 40) return imageLeft[3];
        else if (left < 50) return imageLeft[4];
        else if (right < 10) return imageRight[0];
        else if (right < 20) return imageRight[1];
        else if (right < 30) return imageRight[2];
        else if (right < 40) return imageRight[3];
        else return imageRight[4];
    }
    
    // change the number in move() as needed
    public void move(ArrayList<String> input) {
        if (input.contains("LEFT") && getPosition().x > 0) {
        	double newX = getPosition().x - moveLength;
            setPosition(newX, getPosition().y) ;
            left += 2;
            right = 50;
            if (left >= 50) left = 10;
        }
        if (input.contains("RIGHT") && getPosition().x < 1000) {
        	double newX = getPosition().x + moveLength;
            setPosition(newX, getPosition().y); 
            left = 50;
            right++;
            if (right >= 50) right = 10;
        }
        if (input.contains("UP") && !getJumping() && !getFalling()) {
            setJumping(true);
        }
        
        if (input.isEmpty()) {
        	// last move to the right side
            if (left == 50) right = 0;
            // last move to the left side
            else left = 0;
        }
        if (input.contains("DOWN")) {
            setPosition(getPosition().x, ground);
        }
    }
    
    
    public void getCoins() {
    	// To do
    	coins++;
    }

    public void dead() {
    	// To do
    }
    
    public void power() {
    	// To do: eat
    }
}

