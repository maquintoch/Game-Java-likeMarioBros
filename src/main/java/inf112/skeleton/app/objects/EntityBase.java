package inf112.skeleton.app.objects;

import inf112.skeleton.app.objects.attributes.*;

import java.util.ArrayList;

public abstract class EntityBase extends GameObjectBase {
    public EntityBase(Position position) {
        super(position);

        velocity = new Speed(0, 0);
        acceleration = new Speed(0, -0.2f);
    }
}
