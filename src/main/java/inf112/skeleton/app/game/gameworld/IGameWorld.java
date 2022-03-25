package inf112.skeleton.app.game.gameworld;

import inf112.skeleton.app.objects.attributes.GridPosition;

public interface IGameWorld<TWorldObject> {
	
	TWorldObject get(GridPosition position);
	
	void set(TWorldObject object);
	void remove(GridPosition position);
	void remove(TWorldObject object);
}
