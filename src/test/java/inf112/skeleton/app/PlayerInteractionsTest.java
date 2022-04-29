package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.*;
import inf112.skeleton.app.objects.attributes.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
@ExtendWith({BeforeAllTestsExtension.class})
public class PlayerInteractionsTest {

    private Player player;
    private InputHandler inputHandler;


    @BeforeEach
    public void setUp(){
        inputHandler = new InputHandler();
        player = new Player(new Position(0,0), inputHandler);
    }

    @Test
    public void takeDamageWhenHitEnemy(){
        //arrange
        Enemy enemy = new Enemy(new Position(-16,0));
        GameWorld gw = mock(GameWorld.class);
        player.addGameWorldObserver(gw);

        //act
        player.collide(enemy);

        //assert
        Mockito.verify(gw,Mockito.times(1)).addHealth(-1);
    }

    @Test
    public void playerJumpsWhenHitTrampoline(){
        Assertions.assertEquals(player.getVelocity().velocityY,0);
        var gameObjects = new ArrayList<IGameObject>();
        Trampoline trampoline = new Trampoline(new Position(0, -16));
        gameObjects.add(trampoline);

        player.collide(trampoline);
        player.update(gameObjects);
        Assertions.assertTrue(player.getVelocity().velocityY > 5);
    }
    @Test
    public void playerGetsScoreWhenHitCoin(){
        //arrange
        GameWorld gw = mock(GameWorld.class);
        Coin coin = new Coin(new Position(0, -16));
        coin.addGameWorldObserver(gw);

        //act
        coin.collide(player);

        //assert
        Mockito.verify(gw, Mockito.times(1)).addScore(1);
    }

    @Test
    public void playerDiesWhenFallingOfMap(){
        //arrange
        GameWorld gw = mock(GameWorld.class);
        player.addGameWorldObserver(gw);
        var collidables = new ArrayList<IGameObject>();
        player.getPosition().setY(-201);

        //act
        player.update(collidables);

        //assert
        Mockito.verify(gw,Mockito.times(1)).setHealth(0);
    }
}
