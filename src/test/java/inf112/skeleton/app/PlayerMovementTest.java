package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;

import inf112.skeleton.app.game.gameworld.GameWorld;

import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;

import inf112.skeleton.app.objects.attributes.ICollidable;

import javafx.application.Platform;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerMovementTest {
    private Player player;
    private GameWorld gw;
    @BeforeAll
    public static void setup() {

      //  Platform.startup(()->{});
    }
    @BeforeEach
    public void setUp(){
        gw = Mockito.mock(GameWorld.class);
        player = new Player(gw, 0,0);
    }

    @Test
    public void testPlayerCanMove() {
        assertEquals(0, player.getPosition().getX());
        assertTrue(player.getSpeed().velocityX == 0);
        player.moveRight();
        assertTrue(player.getSpeed().velocityX > 0);
        player.moveLeft();
        assertTrue(player.getSpeed().velocityX < 0);
    }

    @Test
    public void testPlayerIsStanding() {
        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        assertTrue(!player.isStanding);
        player.update();
        assertTrue(player.isStanding);
    }

    @Test
    public void testPlayerGravity() {

        var collidables = new ArrayList<ICollidable>();
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        assertEquals(0, player.getPosition().getY());
        player.update();
        assertTrue(player.getPosition().getY() < 0);
    }

    @Test
    public void testPlayerWalkRight() {

        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 16, -16));

        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        assertTrue(player.getPosition().getX() == 0);
        player.moveRight();
        player.update();
        assertTrue(player.getPosition().getX() != 0);
    }

    @Test
    public void testPlayerNotWalkThroughBlockRight() {

        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 14, 0));

        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));

        assertTrue(player.getPosition().getX() == 0);
        player.moveRight();
        player.update();
        assertTrue(player.getPosition().getX() == 0);
    }
}