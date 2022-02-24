package inf112.skeleton.app;

public interface IWorldRemoveable<TWorldObject> {
	
	void Remove(GridPosition position);
	
	void Remove(TWorldObject object);

}
