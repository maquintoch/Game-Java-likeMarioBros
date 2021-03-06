package inf112.skeleton.app.game;

import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.game.gameworld.GameWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class Game extends Application implements IGameObserver {

    private GameMenu gameMenu;
    private GameWorld gameWorld;
    private InputHandler inputHandler;

    public int levelCount = 0;
    public Stage stage;
    private MediaPlayer mp;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.gameMenu = new GameMenu(stage);
        this.gameMenu.startScreen.show();
        this.gameMenu.addGameObserver(this);
    }

    public void startGame(boolean isMultiplayer){
            stage.setTitle("Mario");
            double width = 500;
            double height = 500;
            Group root = new Group();
            Scene scene = new Scene(root, width, height, Color.LIGHTSKYBLUE);
            stage.setScene(scene);
            
            Canvas canvas = new Canvas(width, height);
            canvas.widthProperty().bind(scene.widthProperty());
            canvas.heightProperty().bind(scene.heightProperty());
            root.getChildren().add(canvas);

            HealthUI healthUI = new HealthUI(canvas.getGraphicsContext2D());
            CoinUI coinUI = new CoinUI(canvas.getGraphicsContext2D());

            inputHandler = new InputHandler();

            scene.setOnKeyPressed(event -> {
            	KeyCode keyCode = event.getCode();
                inputHandler.setActive(keyCode);
            });

            scene.setOnKeyReleased(event -> {
            	KeyCode keyCode = event.getCode();
                inputHandler.setInactive(keyCode);
            });

            var levelLoader = new LevelLoader();
            gameWorld = new GameWorld(canvas, levelLoader, inputHandler, isMultiplayer);
            gameWorld.addScoreObserver(coinUI);
            gameWorld.addHealthObserver(healthUI);

        // add game music
            String soundGameTheme = "src/main/java/inf112/skeleton/app/assets/GameTheme.mp3";
            Media media = new Media(new File(soundGameTheme).toURI().toString());
            mp = new MediaPlayer(media);

            // cycling music in background
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.setVolume(0.05);
            mp.play();

        AnimationTimer timer = new AnimationTimer() {

                final int TICKS_PER_SECOND = 60;
                final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
                double next_game_tick = System.currentTimeMillis();

                @Override
                public void handle(long now) {
                    if(gameWorld.getHealth() <= 0){
                        endGame();
                        this.stop();
                    }
                    if (System.currentTimeMillis() > next_game_tick) {

                        if(gameWorld.getScore() == 10) {
                            levelCount++;
                            if(levelLoader.levelExists(levelCount)) {
                                gameWorld.Load(levelCount);
                            } else {
                                gameMenu.winningScreen.show();
                                levelCount = 0;
                                mp.stop();
                                this.stop();
                            }
                        }

                        gameWorld.update();
                        gameWorld.draw();

                        coinUI.draw();
                        healthUI.draw(0, 0);

                        next_game_tick += SKIP_TICKS;
                    }
                }
            };

            timer.start();
            stage.show();
    }
    private void endGame(){
        levelCount = 0;
        mp.stop();
        this.gameMenu.endScreen.show();
    }
}


