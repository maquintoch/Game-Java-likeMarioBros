package inf112.skeleton.app.GameWorld;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.GridPosition;

public interface IWorldGettable<TWorldObject> {
	
	TWorldObject Get(GridPosition position);

}
