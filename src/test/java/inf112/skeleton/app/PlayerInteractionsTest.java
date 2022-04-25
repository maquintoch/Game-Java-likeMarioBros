package inf112.skeleton.app;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.*;
import inf112.skeleton.app.objects.attributes.ICollidable;
import inf112.skeleton.app.objects.attributes.ItemType;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class PlayerInteractionsTest {

    private Player player;
    private IInputHandler inputHandler;
    @BeforeAll
    public static void setup() {
        Platform.startup(()->{});
    }
    @BeforeEach
    public void setUp(){
        inputHandler = new InputHandler();
        player = new Player(new Position(0,0), inputHandler);
    }
/**
    @Test
    public void takeDamageWhenHitEnemy(){
        player.collide(ItemType.Enemy);
        Mockito.verify(gw, Mockito.times(1)).addHealth(-1);
    }
**/
    @Test
    public void playerJumpsWhenHitTrampoline(){
        Assertions.assertEquals(player.getVelocity().velocityY,0);
        var gameObjects = new ArrayList<IGameObject>();
        Trampoline trampoline = new Trampoline(new Position(0, -16));
        gameObjects.add(trampoline);

        player.collide(trampoline);
        player.update(gameObjects);
        Assertions.assertEquals(Math.round(player.getVelocity().velocityY),5);
    }
/**
    @Test
    public void playerGetsScoreWhenHitCoin(){
        GameWorld gw = mock(GameWorld.class);
        var gameObjects = new ArrayList<IGameObject>();
        Coin coin = new Coin(new Position(0, -16));
        gameObjects.add(coin);
        gameObjects.add(player);
        coin.collide(player);
        player.update(gameObjects);

        Mockito.verify(gw, Mockito.times(1)).addScore(1);
    }

    @Test
    public void playerDiesWhenFallingOfMap(){
        player.getPosition().setY(-201);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        player.update();
        Mockito.verify(gw,Mockito.times(1)).setHealth(0);
    }**/
}
