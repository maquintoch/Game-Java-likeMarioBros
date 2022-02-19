package inf112.skeleton.app;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Game extends Application {

//<<<<<<< HEAD
//=======
    private GraphicsContext context;
//>>>>>>> 647752630c418469d8d8916453ebbdff15d4c475
    private GameWorld gameWorld;
	private AnimationTimer timer;
	private Canvas canvas;
    private Random random = new Random();
	private String direction = "RIGHT";

	private double xAxis = 231;  
	private double yAxis = 435;  // on the groud when yAxis = 435

	
    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	stage.setTitle("Mario");
    	
//        var canvasFactory = new CanvasFactory(stage);
//        canvas = canvasFactory.getCanvas();
    	
		double width = 856;
		double height = 550;
		
		Group root = new Group();
		Scene scene = new Scene(root, width, height, Color.BLACK);
		stage.setScene(scene);
		canvas = new Canvas(width, height);
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());

		root.getChildren().add(canvas);
        
		
        //gameWorld = new GameWorld(canvas);

		
        // create image for background and mario right walking
        FileInputStream inputBackground = new FileInputStream("src/main/java/inf112/skeleton/app/background.png");
        FileInputStream inputMarioRight = new FileInputStream("src/main/java/inf112/skeleton/app/marioRight0Lvl0.png");
        FileInputStream inputMarioLeft = new FileInputStream("src/main/java/inf112/skeleton/app/marioLeft0Lvl0.png");
        Image background = new Image(inputBackground);
        System.out.println("### background load ###");
        Image marioRight = new Image(inputMarioRight);
        System.out.println("### mario right load ###");
        Image marioLeft = new Image(inputMarioLeft);
        System.out.println("### mario left load ###");
        

        // Creating array list object
        List<String> arrayListObjectString = new ArrayList<String>();
        // setting keyword pressing event by setOnKeyPressed method
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent keyEvent) {
		        String contnet = keyEvent.getCode().toString();
		        // overcomes duplicate data
		        if (!arrayListObjectString.contains(contnet)) arrayListObjectString.add(contnet);
	        }
        });
        // setting keyword pressing event by setOnKeyReleased method
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent keyEvet) {
		        String content = keyEvet.getCode().toString();
		        arrayListObjectString.remove(content);
	        }
        });
        
        context = canvas.getGraphicsContext2D();

        
        // setup the mario, other actors and items
        //setup();
        //final long tempNanoTIme = System.nanoTime();
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
            	context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            	double stepLength = 2;
            	// Draw the background firstly
            	context.drawImage(background, 0, 0);

            	// checking whether left key is pressed
            	if(arrayListObjectString.contains("LEFT")) {
            		direction = "LEFT";
            		xAxis -= stepLength;
            		context.drawImage(marioLeft, xAxis, yAxis);
            	}
            	else if(arrayListObjectString.contains("RIGHT")) {
            		direction = "RIGHT";
            		xAxis += stepLength;
               		context.drawImage(marioRight, xAxis, yAxis);
               	}
            	else if(arrayListObjectString.contains("UP")) {
            		
               		if(direction=="RIGHT") context.drawImage(marioRight, xAxis, yAxis-30);
               		else context.drawImage(marioLeft, xAxis, yAxis-30);
               		
               	}
            	
            	
               	else {
               		if(direction == "LEFT") context.drawImage(marioLeft, xAxis, yAxis);
               		else context.drawImage(marioRight, xAxis, yAxis);
               	}
               	
            }

        };

        timer.start();
//		stage.setFullScreen(true);
        stage.show();
    	System.out.println("## ## ## after show");

    }
 
}
