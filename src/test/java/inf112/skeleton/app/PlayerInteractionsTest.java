package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Coin;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.Trampoline;
import inf112.skeleton.app.objects.attributes.ICollidable;
import inf112.skeleton.app.objects.attributes.ItemType;
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
    private GameWorld gw;
    @BeforeAll
    public static void setup() {
        Platform.startup(()->{});
    }
    @BeforeEach
    public void setUp(){
        gw = mock(GameWorld.class);
        player = new Player(gw, 0,0);
    }

    @Test
    public void takeDamageWhenHitEnemy(){
        player.collide(ItemType.Enemy);
        Mockito.verify(gw, Mockito.times(1)).addHealth(-1);
    }

    @Test
    public void playerJumpsWhenHitTrampoline(){
        Assertions.assertEquals(player.getSpeed().velocityY,0);
        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Trampoline(gw, 0, -16));
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        player.collide(ItemType.Trampoline);
        player.update();
        Assertions.assertEquals(Math.round(player.getSpeed().velocityY),5);
    }

    @Test
    public void playerGetsScoreWhenHitCoin(){
        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Coin(gw, 0, 0));
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        player.itemCollision(collidables);
        Mockito.verify(gw, Mockito.times(1)).addScore(1);
    }
    @Test
    public void playerDiesWhenFallingOfMap(){
        player.getPosition().setY(-201);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        player.update();
        Mockito.verify(gw,Mockito.times(1)).setHealth(0);
    }
}
