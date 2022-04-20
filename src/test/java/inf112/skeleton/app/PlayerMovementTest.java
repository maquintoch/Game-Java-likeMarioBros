package inf112.skeleton.app;
import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.draw.HealthUI;
import inf112.skeleton.app.draw.IDrawBehavior;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.Enemy;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.Position;
import inf112.skeleton.app.objects.attributes.Rectangle;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerMovementTest {


    @Test
    public void testPlayer() {
        GameWorld mockedGameWorld = mock(GameWorld.class);
        Player player = new Player(mockedGameWorld, 0,0);

        assertEquals(0, player.getPosition().getX());

    }
}