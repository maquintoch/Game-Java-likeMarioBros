package inf112.skeleton.app;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
	
    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	System.out.println("## ## ## start");
    	stage.setTitle("Mario");
    	
        var canvasFactory = new CanvasFactory(stage);
        canvas = canvasFactory.getCanvas();
		double width = 856;
		double height = 550;
		
		Group root = new Group();
		Scene scene = new Scene(root, width, height, Color.BLACK);
		stage.setScene(scene);
		canvas = new Canvas(width, height);
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());

		root.getChildren().add(canvas);

		      
        gameWorld = new GameWorld(canvas);
        System.out.println("###### game world");
        
        // create image for background
        FileInputStream inputBackground = new FileInputStream("src/main/java/inf112/skeleton/app/background.png");
        Image background = new Image(inputBackground);

        // list of input buttons: LEFT, RIGHT, UP, DOWN, etc.
        List<String> inputButtons = new ArrayList<String>();
 
        
        // setting keyword pressing event by setOnKeyPressed method
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent keyEvent) {
		        String contnet = keyEvent.getCode().toString();
		        // overcomes duplicate data
		        if (!inputButtons.contains(contnet)) inputButtons.add(contnet);
	        }
        });
        // setting keyword pressing event by setOnKeyReleased method
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent keyEvet) {
		        String content = keyEvet.getCode().toString();
		        inputButtons.remove(content);
	        }
        });
        
        context = canvas.getGraphicsContext2D();
        
    	Mario mario = new Mario(context);

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
            	context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            	context.drawImage(background, 0, 0);

            	mario.move(inputButtons);

            }
        };

        timer.start();
//		stage.setFullScreen(true);
        stage.show();
    	System.out.println("## ## ## after show");

    }

}
