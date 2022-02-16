package inf112.skeleton.app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;


public class BallDemo extends Application {
	private AnimationTimer timer;
	private Canvas canvas;
	private long nanosPerStep = 1000_000_000L / 60L;
	private long timeBudget = nanosPerStep;
	private long lastUpdateTime = 0L;
	private List<Player> players = new ArrayList<>();
	private Random random = new Random();
	private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();


	public static void startIt(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		double width = 500;
		double height = 500;
		Group root = new Group();
		Scene scene = new Scene(root, width, height, Color.LIGHTBLUE);
		stage.setScene(scene);

		scene.setOnKeyPressed(event -> {
			String codeString = event.getCode().toString();
			if (!currentlyActiveKeys.containsKey(codeString)) {
				currentlyActiveKeys.put(codeString, true);
			}
		});
		scene.setOnKeyReleased(event ->
				currentlyActiveKeys.remove(event.getCode().toString())
		);


		canvas = new Canvas(width, height);
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());
		setup();
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (removeActiveKey("LEFT")) {
					System.out.println("left");
					players.get(0).moveTo(players.get(0).getX()-5,players.get(0).getY());
					//players.get(0).accelerate(-0.5, 0);
				}

				if (removeActiveKey("RIGHT")) {
					System.out.println("right");
					players.get(0).accelerate(0.5, 0);
				}

				if (removeActiveKey("UP")) {
					System.out.println("up");
					players.get(0).accelerate(0, -0.5);
				}

				if (removeActiveKey("DOWN")) {
					System.out.println("down");
					players.get(0).accelerate(0, 0.5);
				}
				draw();
			}
		}.start();


		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// System.out.println("Elapsed: " + (now -
				// lastUpdateTime)/(double)millisPerStep);
				if (lastUpdateTime > 0) {
					timeBudget = Math.min(timeBudget + (now - lastUpdateTime), 10 * nanosPerStep);
				}
				lastUpdateTime = now;

				while (timeBudget >= nanosPerStep) {
					// System.out.println("Budget: " + timeBudget);
					timeBudget = timeBudget - nanosPerStep;

					step();
				}
				draw();
			}

		};
		root.getChildren().add(canvas);

		// canvas.setEffect(new BoxBlur());
		timer.start();
		//stage.setFullScreen(true);
		stage.show();

	}

	private boolean removeActiveKey(String codeString) {
		Boolean isActive = currentlyActiveKeys.get(codeString);

		if (isActive != null && isActive) {
			currentlyActiveKeys.put(codeString, false);
			return true;
		} else {
			return false;
		}
	}


	private void setup() {
		for (int i = 0; i < 13; i++) {
			newBall();
		}
	}

	public void addBall(Player player) {
		players.add(player);
	}

	public void removeBall(Player player) {
		players.remove(player);
	}

	public void addExplosion(Player player) {
		players.remove(player);
		for (int i = 0; i < 10; i++) {
			Player b = new Player(player.getColor(), player.getRadius() / 2, this);
			b.moveTo(player.getX(), player.getY());
			b.accelerate(player.getDeltaX(), player.getDeltaY());
			b.accelerate(random.nextDouble() * 2 - 1.0, random.nextDouble() * 2 - 1.0);
			players.add(b);

		}
	}


	private void newBall() {
		newBall(1, 1, 5);
	}

	private void newBall(double x, double y, double size) {
		Color color = Color.RED.deriveColor(64 * random.nextDouble() - 32.0, 1.0, 1.0, .7);
		Color white = color.deriveColor(0.0, .33, 3.0, 2.0);
		Paint paint = new RadialGradient(0.0, 0.0, 0.3, 0.3, .6, true, CycleMethod.NO_CYCLE, new Stop(0.0, white),
				new Stop(1.0, (Color) color));
		Player b = new Player(paint, size, this);
		b.moveTo(canvas.getHeight() / 2, canvas.getWidth() / 2);
		// b.moveTo(256 + random.nextInt(1400), random.nextInt(1280) - 200);
		// b.accelerate(i - 1, -3 * (i - 1));
		//b.accelerate((128/size) * random.nextDouble() - 1.5, -(512/size) * random.nextDouble() + 1);
		//players.add(b);
		if (players.size() < 1) {
			players.add(b);
		}
	}

	private int stepCount = 0;


	protected void step() {
				for (Player b : new ArrayList<>(players)) {
			if (b.getY() + b.getHeight() / 2 < canvas.getHeight()) {
				//b.accelerate(0, 0.098f);
			}
			b.step();
			double bounceX = 0.0, bounceY = 0.0;
			if (b.getX() + b.getWidth() / 2 <= 0.0) {
				players.remove(b);
			} else if (b.getX() - b.getWidth() / 2 >= canvas.getWidth()) {
				players.remove(b);
			} else if (b.getX() - b.getWidth() / 2 <= 0.0) {
				 bounceX = 0 - (b.getX() - b.getWidth() / 2);
			} else if (b.getX() + b.getWidth() / 2 >= canvas.getWidth()) {
				 bounceX = canvas.getWidth() - (b.getX() + b.getWidth() / 2);
			}
			// if (b.getY() - b.getHeight() / 2 <= 0.0) {
			// bounceY = 0 - (b.getY() - b.getHeight() / 2);
			// } else
			if (b.getY() - b.getHeight() / 2 >= canvas.getHeight()) {
				players.remove(b);
			} else if (b.getY() + b.getHeight() / 2 >= canvas.getHeight()) {
				bounceY = canvas.getHeight() - (b.getY() + b.getHeight() / 2);
			}
			if (bounceY != 0.0) {
				if (random.nextInt(1) == 0) { //Math.abs(bounceY) < 0.1) {
					players.remove(b);
					if (b.getRadius() > 8.0) {
						double dy = b.getDeltaY();
						for (int i = 0; i < 4; i++) {
							newBall(b.getX(), b.getY(), b.getRadius() / 1.414);
							b = players.get(players.size()-1);
							b.accelerate(-b.getDeltaX(), -b.getDeltaY());
							b.accelerate(10 * random.nextDouble() - 5, -0.5*dy);
						}
					}
				}
				b.hit(bounceX, bounceY);
			}

		}
		if (stepCount % 8 == 0)
			newBall();
		stepCount++;
	}

	double rotation = 0;

	protected void draw() {
		GraphicsContext context = canvas.getGraphicsContext2D();
		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (int i = players.size() - 1; i >= 0; i--) {
			Player b = players.get(i);
			double w = b.getWidth();
			double h = b.getHeight();
			double xPos = b.getX() - w / 2.0;
			double yPos = b.getY() - h / 2.0;
			context.save();
			context.setFill(b.getColor());
			context.fillOval(xPos, yPos, w / 1, h / 1);
			context.restore();
		}
		rotation += 1;
	}
	public void keyPressed(javafx.scene.input.KeyCode key) {

	}

}
