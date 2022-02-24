package inf112.skeleton.app;

public interface IWorldGettable<TWorldObject> {
	
	TWorldObject Get(GridPosition position);

}
