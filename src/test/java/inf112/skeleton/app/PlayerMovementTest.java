package inf112.skeleton.app;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlayerMovementTest {
    private static Game game;
    private static Canvas canvas;
    private static HealthUI healthUI;
    private static CoinUI coinUI;
    private static InputHandler inputHandler;
    private static GameWorld gameWorld;
    private static Player player;
    private static Stage stage;


    @BeforeAll
    public static void setUp() throws Exception {

    }


    @Test
    public void jumpTest(){
        Assertions.assertEquals(player.getPosition(),new Position(0,0));
    }
}
