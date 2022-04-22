package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Enemy;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.ICollidable;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class EnemyTest {

    @BeforeAll
    public static void setup() {
        Platform.startup(()->{});
    }

    @Test
    public void enemyMovestest(){
        GameWorld gw = Mockito.mock(GameWorld.class);
        Enemy enemy = new Enemy(gw, 0,0);
        var collidables = new ArrayList<ICollidable>();
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 16, -16));
        collidables.add(new Tile(gw, 32, -16));
        Mockito.when(gw.getCollidables()).thenReturn(collidables);


        enemy.update();

        assertTrue(enemy.getPosition().getX() != 0);

    }
    @Test
    public void EnemyDisappearWhenPlayerJumpOnIt(){
        GameWorld gw = Mockito.mock(GameWorld.class);
        var collidables = new ArrayList<ICollidable>();
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        Enemy enemy = new Enemy(gw, 0,0);
        Player player = new Player(gw, 0, 16);
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(enemy);

        player.update();

        Mockito.verify(gw, Mockito.times(1)).removeEntity(enemy);
    }

    @Test
    public void EnemySwitchDirectionWhenCollideWall(){
        GameWorld gw = Mockito.mock(GameWorld.class);
        var collidables = new ArrayList<ICollidable>();
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        Enemy enemy = new Enemy(gw, 0,0);
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 16, 0));

        var previousSpeed = enemy.getSpeed().velocityX;

        enemy.update();

        assertTrue(Math.signum(enemy.getSpeed().velocityX) != Math.signum(previousSpeed));


    }

    @Test
    public void EnemySwitchDirectionWhenCollidewithAnotherEnemy(){
        GameWorld gw = Mockito.mock(GameWorld.class);
        var collidables = new ArrayList<ICollidable>();
        Mockito.when(gw.getCollidables()).thenReturn(collidables);
        Mockito.when(gw.getInputHandler()).thenReturn(mock(InputHandler.class));
        Enemy enemy = new Enemy(gw, 0,0);
        Enemy enemy2 = new Enemy(gw, 16,0);
        collidables.add(new Tile(gw, 0, -16));
        collidables.add(new Tile(gw, 32, 0));
        collidables.add(enemy2);
        var previousSpeedEnemy = enemy.getSpeed().velocityX;

        enemy.update();

        assertTrue(Math.signum(enemy.getSpeed().velocityX) != Math.signum(previousSpeedEnemy));


    }

}
