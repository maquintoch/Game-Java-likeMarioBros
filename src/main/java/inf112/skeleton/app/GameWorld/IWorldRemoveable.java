package inf112.skeleton.app.GameWorld;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.GridPosition;

public interface IWorldRemoveable<TWorldObject> {
	
	void Remove(GridPosition position);
	
	void Remove(TWorldObject object);

}
