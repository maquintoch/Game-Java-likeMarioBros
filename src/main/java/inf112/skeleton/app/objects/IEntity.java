package inf112.skeleton.app.objects;

import inf112.skeleton.app.objects.attributes.Speed;

public interface IEntity extends IItem {

    public void draw();
    public void update();
    public boolean overlap(IItem collidable);
    public Speed getSpeed();
}
