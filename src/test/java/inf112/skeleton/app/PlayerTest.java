package inf112.skeleton.app;

import inf112.skeleton.app.DrawBehavior.IDrawBehavior;
import inf112.skeleton.app.GameSetup.Game;
import inf112.skeleton.app.GameWorld.Entity.EnemyEntity;
import inf112.skeleton.app.GameWorld.Entity.Entity;
import inf112.skeleton.app.GameWorld.Entity.PlayerEntity;
import inf112.skeleton.app.GameWorld.Tiles.Tile;
import inf112.skeleton.app.GameWorld.Tiles.TileCollections.CoinCollection;
import inf112.skeleton.app.Graphics.CoinUIService;
import inf112.skeleton.app.Input.InputHandler;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
        /**
         * Static method run before everything else
         */
        @BeforeAll
        static void setUpBeforeAll() {
            Stage stage = new Stage();
        }

        /**
         * Setup method called before each of the test methods
         */
        @BeforeEach
        void setUpBeforeEach() {
        }

        /**
         * Simple test case
         */
        @Test
        void playerTest() {

        }

}
