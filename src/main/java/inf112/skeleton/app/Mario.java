package inf112.skeleton.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Mario extends PlayerEntity{
	
	// height of the ground, change as needed
    private double ground;
    
    // length Mario can move with one step, change as needed
    private double moveLength = 2;
    // height Mario can jump with one jump, change as needed
    private double jumpHeight = 30;
    // right images for Mario at different states
    private Image[] imageRight = new Image[5];
    // left images for Mario at different states
    private Image[] imageLeft = new Image[5];
    
    // different levels of Mario: small, large, strong
    private int level = 0;
    // direction of Mario
	private String direction = "RIGHT";
	
	// Mario start position x, change as needed
	private double xAxis = 231; 
	// Mario stands on the groud when yAxis = 435, change as needed
	private double yAxis = 435;  
	
    private boolean gameOver;
    private int coins = 0;

    
	public Mario(GraphicsContext context) throws FileNotFoundException {
		super(context);
		
        System.out.println("###### before set level");

		setLevel(0);
		
        System.out.println("###### before set mario");

		setMario();
		
        System.out.println("###### after set mario");

	}
	
	private void setMario() throws FileNotFoundException {
        for (int k = 0; k < 5; k++) {
        	// change the file path as needed
    		FileInputStream inputMarioRight = new FileInputStream("src/main/java/inf112/skeleton/app/marioImages/marioRight" + k + "Lvl" + level + ".png");
    		FileInputStream inputMarioLeft = new FileInputStream("src/main/java/inf112/skeleton/app/marioImages/marioLeft" + k + "Lvl" + level + ".png");
    		
            imageRight[k] = new Image(inputMarioRight);
            imageLeft[k] = new Image(inputMarioLeft);
        } 
	}
	
	public void move(List<String> inputButtons) {
		// check whether RIGHT key is pressed
    	if(inputButtons.contains("RIGHT")) {
    		direction = "RIGHT";
    		xAxis += moveLength;
    		getGraphicsContext().drawImage(imageRight[1], xAxis, yAxis);
       	}
		// check whether LEFT key is pressed
    	else if(inputButtons.contains("LEFT")) {
    		direction = "LEFT";
    		xAxis -= moveLength;
    		getGraphicsContext().drawImage(imageLeft[1], xAxis, yAxis);
    	}
		// check whether UP key is pressed
    	else if(inputButtons.contains("UP")) {
       		if(direction=="RIGHT") getGraphicsContext().drawImage(imageRight[1], xAxis, yAxis-jumpHeight);
       		else getGraphicsContext().drawImage(imageLeft[1], xAxis, yAxis-jumpHeight);
       	}
		// check direction of Mario when no key is pressed
       	else {
       		if(direction == "LEFT") getGraphicsContext().drawImage(imageLeft[0], xAxis, yAxis);
       		else getGraphicsContext().drawImage(imageRight[0], xAxis, yAxis);
       	}
		
	}

    @Override
    public void Update() {
    }
    
	@Override
	public void Draw(){
		
	}
	
    public double getGround() {
        return this.ground;
    }
    
    public void setGround(double ground) {
        this.ground = ground;
    }
    
    public double getMoveLength() {
        return moveLength;
    }
    
    public void setMoveLength(double moveLength) {
        this.moveLength = moveLength;
    }
   
    public double getJumpHeight() {
        return jumpHeight;
    }
    
    public void setJumpHeight(double jumpHeight) {
        this.jumpHeight = jumpHeight;
    }
    
    public int getLevel() {
    	return this.level;
    }
    
    public void setLevel(int level) {
    	this.level = level;
    }
    		
    public boolean getGameOver() {
        return gameOver;
    }
    
    public void setGameOver(Boolean b) {
        this.gameOver = b;
    }
    
    public int getCoins() {
    	return this.coins;
    }
}
