package inf112.skeleton.app;

import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.game.Game;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthUITest {

    private Game game;
    private Canvas canvas;
    private HealthUI healthUI;
    private HealthUI healthUI2;

    @BeforeEach
    public void setUp(){
        game = new Game();
        canvas = new Canvas(500,500);
        healthUI = new HealthUI(canvas.getGraphicsContext2D());
        healthUI2 = new HealthUI(canvas.getGraphicsContext2D());
        game.choice = true;
    }

    @Test
    public void playerOneHealth(){
        Assertions.assertEquals(healthUI.currentHealth.getHealth(),3);
    }

    @Test
    public void playerTwoHealth(){
        game.choice = false;
        Assertions.assertEquals(healthUI2.currentHealth.getHealth(),3);
    }

    @Test
    public void addHealth(){
        healthUI.currentHealth.addHealth(3);
        Assertions.assertEquals(healthUI.currentHealth.getHealth(),6);
        healthUI2.currentHealth.addHealth(10);
        Assertions.assertEquals(healthUI2.currentHealth.getHealth(),13);
    }

    @Test
    public void setHealth(){
        Assertions.assertEquals(healthUI.currentHealth.getHealth(),3);
        healthUI.currentHealth.setHealth(0);
        Assertions.assertEquals(healthUI.currentHealth.getHealth(),0);
    }

    @Test
    public void isGameOver(){
        game.choice = true;
        game.healthUI = this.healthUI;
        game.healthUI2 = this.healthUI2;
        Assertions.assertEquals(game.isGameOver(),false);
        healthUI.currentHealth.setHealth(0);
        Assertions.assertEquals(game.isGameOver(),true);
        game.choice = false;
        Assertions.assertEquals(game.isGameOver(),false);
        healthUI2.currentHealth.setHealth(0);
        Assertions.assertEquals(game.isGameOver(),true);
    }
}
