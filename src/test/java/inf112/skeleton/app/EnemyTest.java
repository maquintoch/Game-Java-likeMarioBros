package inf112.skeleton.app;

import inf112.skeleton.app.Input.InputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Enemy;
import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({BeforeAllTestsExtension.class})
public class EnemyTest {

    @Test
    public void enemyMovestest(){
        //arrange
        Position enemyPos = new Position(0,0);
        Enemy enemy = new Enemy(enemyPos);
        var collidables = new ArrayList<IGameObject>();
        collidables.add(new Tile(new Position(0,-16)));
        collidables.add(new Tile(new Position(16,-16)));
        collidables.add(new Tile(new Position(32,-16)));

        //act
        enemy.update(collidables);

        //assert
        assertTrue(enemy.getPosition().getX() != 0);

    }

    @Test
    public void EnemyDisappearWhenPlayerJumpOnIt(){
        //arrange
        GameWorld gw = mock(GameWorld.class);
        Enemy enemy = new Enemy(new Position(0,0));
        enemy.addGameWorldObserver(gw);
        InputHandler inputHandler = new InputHandler();
        Player player = new Player(new Position(0,32), inputHandler);

        //act
        enemy.collide(player);

        //assert
        Mockito.verify(gw, Mockito.times(1)).removeGameObject(enemy);
    }


    @Test
    public void EnemySwitchDirectionWhenCollideWall(){
        //arrange
        var gameObjects = new ArrayList<IGameObject>();
        Enemy enemy = new Enemy(new Position(0,0));
        gameObjects.add(new Tile(new Position(0, -16)));
        gameObjects.add(new Tile(new Position(16, 0)));
        var previousSpeed = enemy.getSpeed().velocityX;

        //act
        enemy.update(gameObjects);

        //assert
        assertTrue(Math.signum(enemy.getSpeed().velocityX) != Math.signum(previousSpeed));


    }

    @Test
    public void EnemySwitchDirectionWhenCollidewithAnotherEnemy(){
        //arrange
        var gameObjects = new ArrayList<IGameObject>();
        Enemy enemy = new Enemy(new Position(0,0));
        Enemy enemy2 = new Enemy(new Position(16,0));
        gameObjects.add(new Tile(new Position(0, -16)));
        gameObjects.add(new Tile(new Position(32, 0)));
        gameObjects.add(enemy2);
        var previousSpeedEnemy = enemy.getSpeed().velocityX;

        //act
        enemy.update(gameObjects);

        //assert
        assertTrue(Math.signum(enemy.getSpeed().velocityX) != Math.signum(previousSpeedEnemy));


    }

}
