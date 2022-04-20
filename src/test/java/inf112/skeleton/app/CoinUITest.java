package inf112.skeleton.app;

import inf112.skeleton.app.draw.CoinUI;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.objects.attributes.Score;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinUITest {

    @Test
    void addScoreTest() {
        Score score = new Score(5);
        assertEquals(5, score.getScore());
        score.addScore(2);
        assertEquals(7, score.getScore());
        score.addScore(-2);
        assertEquals(5, score.getScore());
    }

    @Test
    void levelCountTest(){
        Canvas canvas = new Canvas(500,500);
        Game g = new Game();
        assertEquals(0,g.levelCount);
        g.coinUI = new CoinUI(canvas.getGraphicsContext2D());
        g.coinUI.currentscore.addScore(10);
        g.allCoinsCollected(g.coinUI);
        assertEquals(1, g.levelCount);

        g.coinUI.currentscore.addScore(10);
        g.allCoinsCollected(g.coinUI);
        assertEquals(2, g.levelCount);

        g.coinUI.currentscore.addScore(10);
        g.allCoinsCollected(g.coinUI);
        assertEquals(3, g.levelCount);

        g.finishedAllLevels(g.levelCount);
        assertEquals(0,g.levelCount);

    }
}
