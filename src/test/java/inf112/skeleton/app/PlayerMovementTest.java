package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;

import inf112.skeleton.app.game.gameworld.GameWorld;

import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;

import inf112.skeleton.app.objects.attributes.ICollidable;

import inf112.skeleton.app.objects.attributes.Position;
import javafx.application.Platform;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({BeforeAllTestsExtension.class})
public class PlayerMovementTest {

    private Player player;


    @BeforeEach
    public void setUp(){
        InputHandler inputHandler = new InputHandler();
        player = new Player(new Position(0,0), inputHandler);
    }

    @Test
    public void testPlayerCanMove() {
        assertEquals(0, player.getPosition().getX());
        assertTrue(player.getVelocity().velocityX == 0);
        player.moveRight();
        assertTrue(player.getVelocity().velocityX > 0);
        player.moveLeft();
        assertTrue(player.getVelocity().velocityX < 0);
    }

    @Test
    public void testPlayerIsStanding() {
        var gameObjects = new ArrayList<IGameObject>();
        gameObjects.add(new Tile(new Position(0, -16)));

        assertTrue(!player.isStanding());
        player.update(gameObjects);
        assertTrue(player.isStanding());
    }

    @Test
    public void testPlayerGravity() {

        var gameObjects = new ArrayList<IGameObject>();

        assertEquals(0, player.getPosition().getY());

        player.update(gameObjects);

        assertTrue(player.getPosition().getY() < 0);
    }

    @Test
    public void testPlayerWalkRight() {
        var gameObjects = new ArrayList<IGameObject>();
        gameObjects.add(new Tile(new Position(0, -16)));
        gameObjects.add(new Tile(new Position(16, -16)));

        assertTrue(player.getPosition().getX() == 0);

        player.moveRight();
        player.update(gameObjects);

        assertTrue(player.getPosition().getX() != 0);
    }

    @Test
    public void testPlayerNotWalkThroughBlockRight() {

        var gameObjects = new ArrayList<IGameObject>();
        gameObjects.add(new Tile(new Position(0, -16)));
        gameObjects.add(new Tile(new Position(14, 0)));


        assertTrue(player.getPosition().getX() == 0);

        player.moveRight();
        player.update(gameObjects);

        assertTrue(player.getPosition().getX() == 0);
    }
}