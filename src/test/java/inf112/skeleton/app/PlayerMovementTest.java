package inf112.skeleton.app;
import de.saxsys.javafx.test.JfxRunner;
import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Enemy;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.ICollidable;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.MediaPlayer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerMovementTest {

    @BeforeAll
    public static void setup() {
        Platform.startup(()->{});
    }

    @Test
    public void testPlayerCanMove() {

        GameWorld gw = Mockito.mock(GameWorld.class);

        Player player = new Player(gw, 0,0);

        assertEquals(0, player.getPosition().getX());
        assertTrue(player.getSpeed().velocityX == 0);
        player.moveRight();
        assertTrue(player.getSpeed().velocityX > 0);
        player.moveLeft();
        assertTrue(player.getSpeed().velocityX < 0);
    }

    @Test
    public void testPlayerIsStanding() {
        GameWorld gw = Mockito.mock(GameWorld.class);

        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        Player player = new Player(gw, 0,0);
        assertTrue(!player.isStanding);
        player.update();
        assertTrue(player.isStanding);
    }

    @Test
    public void testPlayerGravity() {
        GameWorld gw = Mockito.mock(GameWorld.class);

        var collidables = new ArrayList<ICollidable>();
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        Player player = new Player(gw, 0,0);

        assertEquals(0, player.getPosition().getY());
        player.update();
        assertTrue(player.getPosition().getY() < 0);
    }

    @Test
    public void testPlayerWalkRight() {
        GameWorld gw = Mockito.mock(GameWorld.class);

        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 16, -16));

        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        Player player = new Player(gw, 0,0);
        assertTrue(player.getPosition().getX() == 0);
        player.moveRight();
        player.update();
        assertTrue(player.getPosition().getX() != 0);
    }

    @Test
    public void testPlayerNotWalkThroughBlockRight() {
        GameWorld gw = Mockito.mock(GameWorld.class);

        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 14, 0));

        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        Player player = new Player(gw, 0,0);
        assertTrue(player.getPosition().getX() == 0);
        player.moveRight();
        player.update();
        assertTrue(player.getPosition().getX() == 0);
    }
}