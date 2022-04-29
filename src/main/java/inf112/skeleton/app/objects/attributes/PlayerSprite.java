package inf112.skeleton.app.objects.attributes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerSprite {

    private final int spriteCount = 5;

    private Image[] imageRight = new Image[spriteCount]; // right images for different states
    private Image[] imageLeft = new Image[spriteCount]; // left images for different states


    private int moveIndex = 0;

    private boolean movingRight;

    private AnimationTimer timer;
    private boolean running;

    public PlayerSprite() {

        try {
            for (int k = 0; k < 5; k++) {
                // change the file path if needed
                FileInputStream inputMarioRight = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/player1/marioRight" + k + "Lvl" + "1" + ".png");
                FileInputStream inputMarioLeft = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/player1/marioLeft" + k + "Lvl" + "1" + ".png");
                FileInputStream inputblank = new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/blank.png");
                imageRight[k] = new Image(inputMarioRight);
                imageLeft[k] = new Image(inputMarioLeft);

            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Player image not found!");
            e.printStackTrace();
        }
        start();
    }

    public Image getImage() {
        if (movingRight) {
            return imageRight[moveIndex];
        } else {
            return imageLeft[moveIndex];
        }
    }

    public void moveRight() {
        this.movingRight = true;
    }

    public void moveLeft() {
        this.movingRight = false;
    }

    public void start() {

        timer = new AnimationTimer() {
            final int TICKS_PER_SECOND = 5;
            final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
            double next_game_tick = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() > next_game_tick) {

                    moveIndex = (moveIndex + 1) % (spriteCount);

                    next_game_tick += SKIP_TICKS;
                }
            }
        };

        timer.start();
        this.running = true;
    }

    public void stop() {
        timer.stop();
        this.running = false;
        this.moveIndex = 0;
    }

    public boolean isRunning() {
        return running;
    }
}
