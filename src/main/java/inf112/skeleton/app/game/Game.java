package inf112.skeleton.app.game;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;
import inf112.skeleton.app.net.packets.LoginPacket;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.Position;
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
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Game extends Application implements IGameObserver {

    private GameMenu gameMenu;
    private GameWorld gameWorld;
    private IInputHandler inputHandler;

    public int levelCount = 0;
    public HealthUI healthUI;
    public CoinUI coinUI;
    public Stage stage;
    private MediaPlayer mp;

    public static void launchGame(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.gameMenu = new GameMenu(stage);
        this.gameMenu.startScreen.show(stage);
        this.gameMenu.addGameObserver(this);
    }

    private void setupGame() {

    }

    public void startGame(){
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

            healthUI = new HealthUI(canvas.getGraphicsContext2D());
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
            gameWorld = new GameWorld(canvas, levelLoader, inputHandler);


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

                        if(gameWorld.getScore() == 3) {
                            levelCount++;
                            if(levelLoader.levelExists(levelCount)) {
                                gameWorld.Load(levelCount);
                            } else {
                                gameMenu.winningScreen.show(stage);
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

    @Override
    public void joinServer(String s) {
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

        healthUI = new HealthUI(canvas.getGraphicsContext2D());
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
        gameWorld = new GameWorld(canvas, levelLoader, inputHandler);

        GameClient client = null;
        try {
            client = new GameClient(gameWorld, InetAddress.getLocalHost().getHostAddress(), 1331);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        };

        client.start();
        var loginPacket = new LoginPacket("Lars andreas");
        client.sendData(loginPacket.getPacketData());

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

                    if(gameWorld.getScore() == 3) {
                        levelCount++;
                        if(levelLoader.levelExists(levelCount)) {
                            gameWorld.Load(levelCount);
                        } else {
                            gameMenu.winningScreen.show(stage);
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

    @Override
    public void hostServer() {
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

        healthUI = new HealthUI(canvas.getGraphicsContext2D());
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
        gameWorld = new GameWorld(canvas, levelLoader, inputHandler);
        var server = new GameServer(new GameWorld(canvas, levelLoader, inputHandler), 1331);
        server.start();

        GameClient client = null;
        try {
            client = new GameClient(gameWorld, InetAddress.getLocalHost().getHostAddress(), 1331);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        };

        client.start();
        var loginPacket = new LoginPacket("Lars andreas");
        client.sendData(loginPacket.getPacketData());

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

                    if(gameWorld.getScore() == 3) {
                        levelCount++;
                        if(levelLoader.levelExists(levelCount)) {
                            gameWorld.Load(levelCount);
                        } else {
                            gameMenu.winningScreen.show(stage);
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
        this.gameMenu.endScreen.show(stage);
    }
}


