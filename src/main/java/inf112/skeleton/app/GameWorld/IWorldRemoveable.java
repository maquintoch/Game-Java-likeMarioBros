package inf112.skeleton.app;

import inf112.skeleton.app.Entity.EntityAttributes.GridPosition;

public interface IWorldRemoveable<TWorldObject> {
	
	void Remove(GridPosition position);
	
	void Remove(TWorldObject object);

}
