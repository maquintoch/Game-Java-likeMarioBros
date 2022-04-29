package inf112.skeleton.app;

import inf112.skeleton.app.objects.attributes.Score;
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

}
