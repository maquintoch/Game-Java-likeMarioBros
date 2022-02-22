package inf112.skeleton.app;

public class MoveHandler implements IMoveHandler {

    private final Entity entity;

    public MoveHandler(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void MoveTo(Position position) {

    }

    @Override
    public void Move(Speed speed) {
        //entity.position.x = collisionLogic.ClosestPositionX(speed);
        //entity.position.y = collisionLogic.ClosestPositionY(speed);
    }
}
