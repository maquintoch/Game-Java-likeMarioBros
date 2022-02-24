package inf112.skeleton.app;

import java.util.Objects;

public class GridPosition {
    public int x;
    public int y;

    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int hashCode() {
		return Objects.hash(this.x, this.y);
		
    }
    
    
    @Override
    public boolean equals(Object obj){
    	GridPosition gridPosition = (GridPosition) obj;
    	
    	return(this.x == gridPosition.x && this.y == gridPosition.y);
    		
    
    	
    }
    
}
