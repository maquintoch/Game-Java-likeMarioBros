package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Enemy;
import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.ICollidable;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.application.Platform;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({BeforeAllTestsExtension.class})
public class EnemyTest {

    @Test
    public void enemyMovestest(){
        Position enemyPos = new Position(0,0);
        Enemy enemy = new Enemy(enemyPos);
        var collidables = new ArrayList<IGameObject>();
        collidables.add(new Tile(new Position(0,-16)));
        collidables.add(new Tile(new Position(16,-16)));
        collidables.add(new Tile(new Position(32,-16)));

        enemy.update(collidables);

        assertTrue(enemy.getPosition().getX() != 0);

    }
/**
    @Test
    public void EnemyDisappearWhenPlayerJumpOnIt(){
        InputHandler inputHandler = new InputHandler();
        var gameObjects = new ArrayList<IGameObject>();
        Enemy enemy = new Enemy(new Position(0,0));
        Player player = new Player(new Position(0,16), inputHandler);
        gameObjects.add(new Tile(new Position(0,-16)));
        gameObjects.add(enemy);

        assertTrue(gameObjects.contains(enemy));

        player.update(gameObjects);

        assertTrue(!gameObjects.contains(enemy));

        //Mockito.verify(gw, Mockito.times(1)).removeEntity(enemy);
    }
**/

    @Test
    public void EnemySwitchDirectionWhenCollideWall(){

        var gameObjects = new ArrayList<IGameObject>();

        Enemy enemy = new Enemy(new Position(0,0));
        gameObjects.add(new Tile(new Position(0, -16)));
        gameObjects.add(new Tile(new Position(16, 0)));

        var previousSpeed = enemy.getSpeed().velocityX;

        enemy.update(gameObjects);

        assertTrue(Math.signum(enemy.getSpeed().velocityX) != Math.signum(previousSpeed));


    }

    @Test
    public void EnemySwitchDirectionWhenCollidewithAnotherEnemy(){
        var gameObjects = new ArrayList<IGameObject>();
        Enemy enemy = new Enemy(new Position(0,0));
        Enemy enemy2 = new Enemy(new Position(16,0));
        gameObjects.add(new Tile(new Position(0, -16)));
        gameObjects.add(new Tile(new Position(32, 0)));
        gameObjects.add(enemy2);
        var previousSpeedEnemy = enemy.getSpeed().velocityX;

        enemy.update(gameObjects);

        assertTrue(Math.signum(enemy.getSpeed().velocityX) != Math.signum(previousSpeedEnemy));


    }

}
