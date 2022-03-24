package inf112.skeleton.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.CollisionBox;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollisionBoxTest {

    private CollisionBox _sutA;
    private CollisionBox _sutB;

    @CsvSource(value = {
            "0,0,1,1,0,0,1,1",
            "0,0,1,1,0,0,3,3",
            "0,0,1,1,0,0,0.5,3",
            "0,0,1,1,0,0,3,0.5",
    })
    @ParameterizedTest(name = "overlap")
    void overlap_shouldDetectOverlap_WhenOverlap(
            double Ax0, double Ay0, double Ax1, double Ay1,
            double Bx0, double By0, double Bx1, double By1
    ) {
        var sutAPositionA = new Position(Ax0, Ay0);
        var sutAPositionB = new Position(Ax1, Ay1);
        var sutBPositionA = new Position(Bx0, By0);
        var sutBPositionB = new Position(Bx1, By1);
        _sutA = new CollisionBox(sutAPositionA, sutAPositionB);
        _sutB = new CollisionBox(sutBPositionA, sutBPositionB);

        var result = _sutA.overlap(_sutB);

        assertEquals(result, true);
    }


    @CsvSource(value = {
            "0,0,1,1,2,2,3,3",
            "0,0,1,1,0,2,3,3",
            "0,0,1,1,0,1,3,3",
            "0,0,1,1,1,0,3,3",
            "1,0,3,3,0,0,1,1",
            "0,1,3,3,0,0,1,1",
    })
    @ParameterizedTest(name = "overlap")
    void overlap_shouldNotDetectOverlap_WhenNotOverlap(
            double Ax0, double Ay0, double Ax1, double Ay1,
            double Bx0, double By0, double Bx1, double By1
    ) {
        var sutAPositionA = new Position(Ax0, Ay0);
        var sutAPositionB = new Position(Ax1, Ay1);
        var sutBPositionA = new Position(Bx0, By0);
        var sutBPositionB = new Position(Bx1, By1);
        _sutA = new CollisionBox(sutAPositionA, sutAPositionB);
        _sutB = new CollisionBox(sutBPositionA, sutBPositionB);

        var result = _sutA.overlap(_sutB);

        assertEquals(result, false);
    }
}
