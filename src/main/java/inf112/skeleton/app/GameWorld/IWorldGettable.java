package inf112.skeleton.app;

import inf112.skeleton.app.Entity.EntityAttributes.GridPosition;

public interface IWorldGettable<TWorldObject> {
	
	TWorldObject Get(GridPosition position);

}
