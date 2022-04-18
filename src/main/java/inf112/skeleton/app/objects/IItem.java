package inf112.skeleton.app.objects;

import inf112.skeleton.app.objects.attributes.CollisionBox;
import inf112.skeleton.app.objects.attributes.Position;

public interface IItem {
	
	public void draw();
    public Position getPosition();
    public CollisionBox getCollisionBox();


}
