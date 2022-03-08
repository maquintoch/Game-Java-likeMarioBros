package inf112.skeleton.app.Input;

import inf112.skeleton.app.Entity.Entity;
import inf112.skeleton.app.Entity.EntityAttributes.Position;
import inf112.skeleton.app.Entity.EntityAttributes.Speed;

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
