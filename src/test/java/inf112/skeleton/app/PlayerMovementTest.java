package inf112.skeleton.app;
import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

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
        IInputHandler inputhandler = Mockito.mock(IInputHandler.class);
        Player player = new Player(new Position(0,0), inputhandler);

        assertEquals(0, player.getPosition().getX());
        assertTrue(player.getVelocity().velocityX == 0);
        player.moveRight();
        assertTrue(player.getVelocity().velocityX > 0);
        player.moveLeft();
        assertTrue(player.getVelocity().velocityX < 0);
    }

    @Test
    public void testPlayerIsStanding() {
        var collidables = new ArrayList<IGameObject>();
        collidables.add(new Tile(new Position(0, -16)));

        IInputHandler inputhandler = Mockito.mock(IInputHandler.class);
        Player player = new Player(new Position(0,0), inputhandler);
        assertTrue(!player.isStanding());
        player.update(collidables);
        assertTrue(player.isStanding());
    }

    @Test
    public void testPlayerGravity() {
        var collidables = new ArrayList<IGameObject>();

        IInputHandler inputhandler = Mockito.mock(IInputHandler.class);
        Player player = new Player(new Position(0,0), inputhandler);

        assertEquals(0, player.getPosition().getY());
        player.update(collidables);
        assertTrue(player.getPosition().getY() < 0);
    }

    @Test
    public void testPlayerWalkRight() {
        var collidables = new ArrayList<IGameObject>();
        collidables.add(new Tile(new Position(0, -16)));
        collidables.add(new Tile(new Position(16, -16)));

        IInputHandler inputhandler = Mockito.mock(IInputHandler.class);
        Player player = new Player(new Position(0,0), inputhandler);

        assertTrue(player.getPosition().getX() == 0);
        player.moveRight();
        player.update(collidables);
        assertTrue(player.getPosition().getX() != 0);
    }

    @Test
    public void testPlayerNotWalkThroughBlockRight() {
        var collidables = new ArrayList<IGameObject>();
        collidables.add(new Tile(new Position(0, -16)));
        collidables.add(new Tile(new Position(14, 0)));

        IInputHandler inputhandler = Mockito.mock(IInputHandler.class);
        Player player = new Player(new Position(0,0), inputhandler);

        assertTrue(player.getPosition().getX() == 0);
        player.moveRight();
        player.update(collidables);
        assertTrue(player.getPosition().getX() == 0);
    }
}